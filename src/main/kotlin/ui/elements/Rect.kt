package main.kotlin.ui.elements

import main.kotlin.terminal.dom.DOMNode
import main.kotlin.terminal.window.FrameBuffer
import main.kotlin.terminal.window.Glyph

class Rect(
    id: String,
    xPos: Int,
    yPos: Int,
    override var width: Int,
    override var height: Int,
    val backgroundChar: Char,
    override var formatting: String,
    ) : DOMNode(id, xPos,yPos) {

    override fun draw(frameBuffer: FrameBuffer) {
        val (x,y) = getLocalOrigin()
        for (localY in 0 until height) {
            for (localX in 0 until width) {
                frameBuffer.insertSingeGlyph(x + localX, y + localY, Glyph(backgroundChar, formatting))
            }
        }
    }


}
