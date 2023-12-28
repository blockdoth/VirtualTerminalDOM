package interfaces

import interfaces.FrameBuffer

interface Renderer {

    fun render(frameBuffer: FrameBuffer)
}
