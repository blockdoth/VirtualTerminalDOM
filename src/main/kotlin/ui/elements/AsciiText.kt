package main.kotlin.ui.elements

import main.kotlin.terminal.dom.DOMNode
import main.kotlin.terminal.window.Glyph
import main.kotlin.terminal.window.FrameBuffer

class AsciiText(
    nodeId: String,
    xPos: Int,
    yPos: Int,
    override var content: String,
    override var formatting: String,
) : DOMNode(nodeId, xPos,yPos) {


    override var width: Int = content.length
    override var height: Int = 1

    override fun draw(frameBuffer: FrameBuffer) {
        val (x,y) = getLocalOrigin()
        for (i in 0 until width) {
            if (x + i >= x + parent!!.width - 1) {
                break
            }
            frameBuffer.insertSingeGlyph(x + i, y, Glyph(content[i], formatting))
        }
    }

}

