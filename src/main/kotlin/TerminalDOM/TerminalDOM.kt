package TerminalDOM

import CharFrameBuffer
import abstract_classes.DOMNode
import interfaces.*

class TerminalDOM (
    override var interpreter: Interpreter
) : DOM {
    private val pair: Pair<DOMNode, MutableList<Behavior>> = interpreter.interpret()
    override var root: DOMNode = pair.first
    private val behaviors: MutableList<Behavior> = pair.second

    override var width: Int = root.width
    override var height: Int = root.height

    override var frameBuffer: FrameBuffer = CharFrameBuffer(width, height)



    override fun update() {
        for (behavior in behaviors) {
            behavior.update()
        }
    }


    override fun flatten(): FrameBuffer {
        return root.flatten(frameBuffer)
    }

    override fun flattenIncrementally (): MutableList<FrameBuffer> {
        val incremetalBuffers: MutableList<FrameBuffer> = mutableListOf(frameBuffer)
        return root.flattenIncrementally(incremetalBuffers)
    }



    override fun resize(width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun findNode(id: String): DOMNode? {
        if (id == root.nodeId) {
            return root
        } else {
            return root.getNode(id)
        }
    }

    override fun printStructure() {
        println("Program Structure:")
        println(root.printStructure(0, false))
    }


}
