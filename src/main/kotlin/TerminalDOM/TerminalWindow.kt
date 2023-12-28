package TerminalDOM

import CharFrameBuffer
import NaiveTerminalRenderer
import PatchTerminalRenderer
import interfaces.FrameBuffer
import interfaces.Renderer
import interfaces.Window

class TerminalWindow(
    override var width: Int,
    override var height: Int
    ) : Window {

    override var renderer: Renderer = PatchTerminalRenderer()

    override var frameBuffer: FrameBuffer = CharFrameBuffer(width, height)
    override fun resize(width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun draw(frameBuffer: FrameBuffer) {
        renderer.render(frameBuffer)
    }


}
