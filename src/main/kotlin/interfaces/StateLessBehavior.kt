package interfaces

import abstract_classes.DOMNode

class StateLessBehavior(
    val node: DOMNode,
    val behavior: (node: DOMNode) -> Unit

) : Behavior {
    override fun update() {
        behavior(node)
    }
}