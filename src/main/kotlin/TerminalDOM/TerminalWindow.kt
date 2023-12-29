package TerminalDOM

import Renderers.FormattedNaiveTerminalRenderer
import Renderers.FormattedPatchTerminalRenderer
import interfaces.DOM
import interfaces.Renderer
import interfaces.Window
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class TerminalWindow(
    override var width: Int,
    override var height: Int
    ) : Window {

    override var renderer: Renderer = FormattedNaiveTerminalRenderer()
        set(value) {
            field = value
        }


    override fun resize(width: Int, height: Int) {
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

    override fun draw(dom: DOM) {
        renderer.render(dom.flatten())
    }
    override fun drawIncrementally(dom: DOM, delay: Int) {
        val frames = dom.flattenIncrementally()
        for (i in 1 until frames.size) {
            val frame = frames[i]
            renderer.render(frame)
            Thread.sleep(delay.toLong())
        }
    }

}
