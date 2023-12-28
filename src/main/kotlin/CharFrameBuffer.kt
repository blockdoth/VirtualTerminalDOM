import interfaces.FrameBuffer

class CharFrameBuffer(
    override var width: Int,
    override var height: Int
    ) : FrameBuffer {

    private var frameBuffer: CharArray = CharArray(width * height)

    override fun insertSingeGlyph(xPos: Int, yPos: Int, content: Glyph) {
        val index = yPos * width + xPos
        frameBuffer[index] = content.content
    }



    override fun resize(width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun getBuffer(): CharArray {
        return frameBuffer
    }

    override fun getGlyph(xPos: Int, yPos: Int): Glyph {
        val index = yPos * width + xPos
        return Glyph(frameBuffer[index])
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CharFrameBuffer

        if (width != other.width) return false
        if (height != other.height) return false
        if (!frameBuffer.contentEquals(other.frameBuffer)) return false

        return true
    }

    override fun copy(): CharFrameBuffer {
        val newFrameBuffer = CharFrameBuffer(width, height)
        newFrameBuffer.frameBuffer = frameBuffer.copyOf()
        return newFrameBuffer
    }


}