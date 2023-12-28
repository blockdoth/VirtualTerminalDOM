package interfaces

import abstract_classes.DOMNode

interface DOM {

    var width: Int
    var height: Int
    var frameBuffer: FrameBuffer

    var rootDOMNode : DOMNode

    fun flatten(): FrameBuffer


    fun resize(width: Int, height: Int)
    fun setDOM(interpretFile: DOMNode)

    fun findNode (id: String): DOMNode?
    fun printStructure()
}