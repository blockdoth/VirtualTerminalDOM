package interfaces

import Glyph

interface FrameBuffer {
    var width: Int
    var height: Int

    fun insertSingeGlyph(xPos: Int, yPos: Int, content: Glyph)
    fun resize(width: Int, height: Int)
    fun getBuffer(): CharArray
    fun getGlyph(xPos: Int, yPos: Int): Glyph
    override fun equals(other: Any?): Boolean

    fun copy(): FrameBuffer


    fun flatCoords(xPos: Int, yPos: Int): Int
}