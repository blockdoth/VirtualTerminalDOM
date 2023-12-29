package interfaces

interface Window {

    var width: Int
    var height: Int
    var renderer: Renderer
    fun resize(width: Int, height: Int)
    fun draw(dom: DOM)
    fun drawIncrementally(dom: DOM, delay: Int)
}
