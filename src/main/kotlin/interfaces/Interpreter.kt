package interfaces

import abstract_classes.DOMNode

interface Interpreter {
    var factory: DOMNodeFactory
    fun interpret(): Pair<DOMNode, MutableList<Behavior>>


}