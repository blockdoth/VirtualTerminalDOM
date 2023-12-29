package Renderers

import interfaces.FrameBuffer
import interfaces.Renderer

open class NaiveTerminalRenderer: Renderer {

    override fun render(frameBuffer: FrameBuffer) {
        val sb = StringBuilder()
        sb.append("\n\u001B[2J\u001B[0;0H")

        for (y in 0 until frameBuffer.height) {
            for (x in 0 until frameBuffer.width) {
                sb.append(frameBuffer.getGlyph(x, y).content)
            }
            sb.append('\n')
        }
        print(sb.toString())
    }
}
