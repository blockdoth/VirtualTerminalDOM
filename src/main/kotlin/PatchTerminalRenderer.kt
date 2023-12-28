import interfaces.FrameBuffer

class PatchTerminalRenderer : NaiveTerminalRenderer() {

    var localFrameBuffer : FrameBuffer? = null

    class Patch {
        var x: Int = 0
        var y: Int = 0
        var content: Char = ' '
    }

    override fun render(frameBuffer: FrameBuffer) {
        if (localFrameBuffer == null){
            localFrameBuffer = frameBuffer
            super.render(frameBuffer)
            return
        }
        val patchList: List<Patch> = generatePatchList(localFrameBuffer!!, frameBuffer)

        for (patch in patchList) {
            val sb = StringBuilder()
            //Ansi cursor coordinates are indexed 1
            sb.append("\u001B[${patch.y + 1};${patch.x}H${patch.content}")
            print(sb.toString())
        }
        localFrameBuffer = frameBuffer.copy()
    }

    private fun generatePatchList(oldBuffer: FrameBuffer, newBuffer: FrameBuffer): List<Patch> {
        if (oldBuffer == newBuffer) {
            return emptyList()
        }


        val patchList = mutableListOf<Patch>()

        for (y in 0 until oldBuffer.height) {
            for (x in 0 until oldBuffer.width) {
                val oldGlyph = oldBuffer.getGlyph(x, y)
                val newGlyph = newBuffer.getGlyph(x, y)
                if (oldGlyph.content != newGlyph.content) {
                    val patch = Patch()
                    patch.x = x
                    patch.y = y
                    patch.content = newGlyph.content
                    patchList.add(patch)
                }
            }
        }
        //println(patchList)
        return patchList
    }
}