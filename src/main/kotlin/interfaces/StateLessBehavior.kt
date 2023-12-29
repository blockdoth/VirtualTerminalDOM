package interfaces

import abstract_classes.DOMNode

class StateFullBehavior(
    override val node: DOMNode,
    override val behavior: (node: DOMNode, state:Any) -> Any,
    private var state: Any

    ) : Behavior {
    override fun update() {
        state = behavior(node, state)
    }
}