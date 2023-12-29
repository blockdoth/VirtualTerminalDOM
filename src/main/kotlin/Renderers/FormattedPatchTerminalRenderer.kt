package Renderers

import interfaces.FrameBuffer

class FormattedPatchTerminalRenderer : FormattedNaiveTerminalRenderer() {

    var localFrameBuffer : FrameBuffer? = null

    class Patch {
        var x: Int = 0
        var y: Int = 0
        var content: Char = ' '
        var formatting: String = ""
    }

    override fun render(frameBuffer: FrameBuffer) {
        if (localFrameBuffer == null){
            localFrameBuffer = frameBuffer
            super.render(frameBuffer)
            return
        }
        val patchList: List<Patch> = generatePatchList(localFrameBuffer!!, frameBuffer)

        val sb = StringBuilder()
        for (patch in patchList) {
            sb.append(patch.formatting)
            sb.append("\u001B[${patch.y + 1};${patch.x}H${patch.content}")
            sb.append("\u001B[0m")
        }
        print(sb.toString())
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
                    patch.formatting = newGlyph.formatting
                    patchList.add(patch)
                }
            }
        }
        //println(patchList)
        return patchList
    }
}