package main.kotlin.ui.behaviors

import main.kotlin.terminal.dom.DOMNode

class StateLessBehavior(
    val node: DOMNode,
    val behavior: (node: DOMNode) -> Unit

) : Behavior {
    override fun update() {
        behavior(node)
    }
}