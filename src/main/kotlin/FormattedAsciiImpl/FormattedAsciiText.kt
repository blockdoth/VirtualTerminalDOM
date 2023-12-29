package AsciiImpl

import CharFrameBuffer
import abstract_classes.DOMNode
import interfaces.FrameBuffer
import Glyph

class FormattedAsciiText(
    nodeId: String,
    xPos: Int,
    yPos: Int,
    var content: String,
    override var formatting: String,
) : DOMNode(nodeId, xPos,yPos) {


    override var width: Int = content.length
    override var height: Int = 1
    override var localFramebuffer: FrameBuffer = CharFrameBuffer(width, height)

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

