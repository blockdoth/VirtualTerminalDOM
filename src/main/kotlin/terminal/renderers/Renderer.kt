package main.kotlin.terminal.renderers

import main.kotlin.terminal.window.FrameBuffer


interface Renderer {

    fun render(frameBuffer: FrameBuffer)
}
