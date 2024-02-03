package main.kotlin.ui.elements

import main.kotlin.terminal.dom.DOMNode

class NodeFactory {
    fun createRect(nodeId: String, x: Int, y: Int, width: Int, height: Int, backgroundChar: Char, formatting: String): DOMNode {
        return Rect(nodeId, x, y, width, height, backgroundChar, formatting)
    }

    fun createText(nodeId: String, x: Int, y: Int, content: String, formatting: String): DOMNode {
        return AsciiText(nodeId, x, y, content, formatting)
    }

    fun createMultilineText(nodeId: String, x: Int, y: Int, content: String, formatting: String): DOMNode {
        return MultilineAsciiText(nodeId, x, y, content, formatting)
    }


}
