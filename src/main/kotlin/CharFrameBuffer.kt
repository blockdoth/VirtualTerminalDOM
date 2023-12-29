import interfaces.FrameBuffer

class CharFrameBuffer(
    override var width: Int,
    override var height: Int
    ) : FrameBuffer {

    private var frameBuffer: CharArray = CharArray(width * height)
    private var formattingMap: MutableMap<Int, String> = mutableMapOf()

    override fun insertSingeGlyph(xPos: Int, yPos: Int, content: Glyph) {
        val index = flatCoords(xPos, yPos)
        frameBuffer[index] = content.content
        formattingMap[index] = content.formatting
    }



    override fun resize(width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun getBuffer(): CharArray {
        return frameBuffer
    }

    override fun getGlyph(xPos: Int, yPos: Int): Glyph {
        val index = flatCoords(xPos, yPos)
        return Glyph(frameBuffer[index], formattingMap[index]!!)
    }



    override fun copy(): CharFrameBuffer {
        val newFrameBuffer = CharFrameBuffer(width, height)
        newFrameBuffer.frameBuffer = frameBuffer.copyOf()
        newFrameBuffer.formattingMap = formattingMap.toMutableMap()
        return newFrameBuffer
    }

    override fun flatCoords(xPos: Int, yPos: Int): Int {
        return yPos * width + xPos
    }

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        result = 31 * result + frameBuffer.contentHashCode()
        result = 31 * result + formattingMap.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CharFrameBuffer

        if (width != other.width) return false
        if (height != other.height) return false
        if (!frameBuffer.contentEquals(other.frameBuffer)) return false
        if (formattingMap != other.formattingMap) return false

        return true
    }

}