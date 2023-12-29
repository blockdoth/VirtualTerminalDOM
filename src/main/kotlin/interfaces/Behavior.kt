package interfaces

import abstract_classes.DOMNode

interface Behavior{
    val behavior: (DOMNode, Any) -> Any
    val node: DOMNode
    fun update()
}
