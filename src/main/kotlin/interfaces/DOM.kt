package interfaces

import abstract_classes.DOMNode

interface DOM {

    var width: Int
    var height: Int
    var frameBuffer: FrameBuffer

    var root : DOMNode
    var interpreter : Interpreter

    fun flatten(): FrameBuffer


    fun resize(width: Int, height: Int)

    fun findNode (id: String): DOMNode?
    fun printStructure()
    fun update()
    fun flattenIncrementally(): MutableList<FrameBuffer>
}