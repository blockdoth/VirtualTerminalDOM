package Renderers

import Glyph
import interfaces.FrameBuffer
import interfaces.Renderer

open class FormattedNaiveTerminalRenderer: Renderer {

    override fun render(frameBuffer: FrameBuffer) {
        val sb = StringBuilder()
        sb.append("\n\u001B[2J\u001B[0;0H")
        //val formattingStack: Stack<String> = Stack()
        for (y in 0 until frameBuffer.height) {
            for (x in 0 until frameBuffer.width) {
                val glyph: Glyph = frameBuffer.getGlyph(x, y)
//                if (glyph.formatting != formattingStack.peek()) {
//                    //formattingStack.push(glyph.formatting)
//                }

                sb.append(glyph.formatting)
                sb.append(glyph.content)
                sb.append("\u001B[0m")


            }
            sb.append("\n")
        }
        sb.append("\u001B[0m")
        print(sb.toString())
    }
}
