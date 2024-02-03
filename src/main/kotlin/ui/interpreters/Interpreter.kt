package main.kotlin.ui.interpreters

import main.kotlin.ui.behaviors.Behavior
import main.kotlin.terminal.dom.DOMNode
import main.kotlin.ui.elements.NodeFactory

interface Interpreter {
    var factory: NodeFactory
    fun interpret(): Pair<DOMNode, MutableList<Behavior>>


}