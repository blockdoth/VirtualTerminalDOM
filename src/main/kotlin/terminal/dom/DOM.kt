package main.kotlin.terminal.dom

import main.kotlin.terminal.window.FrameBuffer
import main.kotlin.ui.behaviors.Behavior
import main.kotlin.ui.interpreters.Interpreter

class DOM (
    var interpreter: Interpreter
) {
    private val pair: Pair<DOMNode, MutableList<Behavior>> = interpreter.interpret()
    var root: DOMNode = pair.first
    private val behaviors: MutableList<Behavior> = pair.second

    var width: Int = root.width
    var height: Int = root.height

    var frameBuffer: FrameBuffer = FrameBuffer(width, height)



    fun update() {
        for (behavior in behaviors) {
            behavior.update()
        }
    }


    fun flatten(): FrameBuffer {
        return root.flatten(frameBuffer)
    }

    fun flattenIncrementally (): MutableList<FrameBuffer> {
        val incremetalBuffers: MutableList<FrameBuffer> = mutableListOf(frameBuffer)
        return root.flattenIncrementally(incremetalBuffers)
    }



    fun resize(width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    fun findNode(id: String): DOMNode? {
        if (id == root.nodeId) {
            return root
        } else {
            return root.getNode(id)
        }
    }

    override fun toString(): String {
        return "Program Structure:\n" + root.toString(0, false)
    }


}
