package TerminalDOM

import CharFrameBuffer
import abstract_classes.DOMNode
import interfaces.DOM
import interfaces.FrameBuffer

class TerminalDOM (
    override var width: Int,
    override var height: Int,
    override var rootDOMNode: DOMNode
) : DOM {

    override var frameBuffer: FrameBuffer = CharFrameBuffer(width, height)


    override fun flatten(): FrameBuffer {
        return rootDOMNode.flatten(frameBuffer)
    }

    override fun resize(width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun setDOM(dom: DOMNode) {
        rootDOMNode = dom
    }

    override fun findNode(id: String): DOMNode? {
        if (id == rootDOMNode.nodeId) {
            return rootDOMNode
        } else {
            return rootDOMNode.getNode(id)
        }
    }

    override fun printStructure() {
        println("Program Structure:")
        println(rootDOMNode.printStructure(0, false))
    }

}
