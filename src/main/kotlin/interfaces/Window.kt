package interfaces

interface Window {

    var width: Int
    var height: Int
    var renderer: Renderer
    var frameBuffer: FrameBuffer
    fun resize(width: Int, height: Int)

    fun draw(frameBuffer: FrameBuffer)
}
