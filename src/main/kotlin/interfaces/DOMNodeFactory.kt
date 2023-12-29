package interfaces

import abstract_classes.DOMNode

interface DOMNodeFactory {

    fun createRect(nodeId: String, x: Int, y: Int, width:Int, height:Int, backgroundChar: Char, formatting: String): DOMNode
    fun createText(nodeId: String, x: Int, y: Int, content: String, formatting: String): DOMNode
    fun createMultilineText(nodeId: String, x: Int, y: Int, content: String, formatting: String): DOMNode
}
