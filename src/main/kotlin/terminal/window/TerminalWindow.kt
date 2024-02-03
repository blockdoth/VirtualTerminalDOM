package main.kotlin.terminal.window

import Renderers.NaiveRenderer
import main.kotlin.terminal.dom.DOM
import main.kotlin.terminal.renderers.Renderer


class TerminalWindow(
    var width: Int,
    var height: Int
    ){

    var renderer: Renderer = NaiveRenderer()
        set(value) {
            field = value
        }


    fun init() {
        //print("\u001b[8;${w};${h}t")
        print("\u001b[s\u001b[999;999H\u001b[6n\u001b[u")
        try {
            val processBuilder = ProcessBuilder("cmd", "/c", "chcp 65001")
            processBuilder.redirectErrorStream(true)
            val process = processBuilder.start()

            val inputStream = process.inputStream
            inputStream.reader().useLines { lines ->
                lines.forEach { line ->
                    println(line)
                }
            }

            val exitCode = process.waitFor()
            println("Command exited with code $exitCode")

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun resize(width: Int, height: Int) {
//        try {
//            val processCols = Runtime.getRuntime().exec("cmd.exe /c stty size")
//
//            val readerCols = BufferedReader(InputStreamReader(processCols.inputStream))
//
//            while (readerCols.ready()) {
//                println(readerCols.readLine())
//            }
//
//
//            val dims = readerCols.readLine().split(" ")
//            val lines = dims[0].toInt()
//            val cols = dims[1].toInt()
////            readerCols.readLine()
////            readerCols.readLine()
////            readerCols.readLine()
////            val lines = readerCols.readLine().replace(Regex("\\D"), "").toInt()
////            val cols = readerCols.readLine().replace(Regex("\\D"), "").toInt()
//
//
//            println("Terminal Size: $cols x $lines")
//            readerCols.close()
//
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
    }
    fun draw(dom: DOM) {
        renderer.render(dom.flatten())
    }

    fun drawIncrementally(dom: DOM, delay: Int) {
        val frames = dom.flattenIncrementally()
        for (i in 1 until frames.size) {
            val frame = frames[i]
            renderer.render(frame)
            Thread.sleep(delay.toLong())
        }
    }

}
