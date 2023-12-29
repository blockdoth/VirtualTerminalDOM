package AsciiImpl

import CharFrameBuffer
import abstract_classes.DOMNode
import interfaces.FrameBuffer
import Glyph

class FormattedRect(
    id: String,
    xPos: Int,
    yPos: Int,
    override var width: Int,
    override var height: Int,
    val backgroundChar: Char,
    override var formatting: String,
    ) : DOMNode(id, xPos,yPos) {

    override var localFramebuffer: FrameBuffer = CharFrameBuffer(width, height)
    override fun draw(frameBuffer: FrameBuffer) {
        val (x,y) = getLocalOrigin()
        for (localY in 0 until height) {
            for (localX in 0 until width) {
                frameBuffer.insertSingeGlyph(x + localX, y + localY, Glyph(backgroundChar, formatting))
            }
        }
    }


}
