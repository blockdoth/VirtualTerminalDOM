package FormattedAsciiImpl

import CharFrameBuffer
import Glyph
import abstract_classes.DOMNode
import interfaces.FrameBuffer

class FormattedMultilineAsciiText(
    nodeId: String,
    xPos: Int,
    yPos: Int,
    override var content: String,
    override var formatting: String,
) : DOMNode(nodeId, xPos,yPos) {

    override var width: Int = content.length
    override var height: Int = content.split("\n").size

    override fun draw(frameBuffer: FrameBuffer) {
        val (x,y) = getLocalOrigin()
        val lines = content.split("\n").toMutableList()
        for (i in 0 until height) {
            lines[i] = lines[i].padEnd(width, ' ')
            for (j in 0 until width) {
                if (x + j >= x + parent!!.width) {
                    break
                }
                frameBuffer.insertSingeGlyph(x + j, y + i, Glyph(lines[i][j], formatting))
            }
        }
    }
}