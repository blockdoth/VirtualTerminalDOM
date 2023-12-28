package AsciiImpl

import abstract_classes.DOMNode
import interfaces.DOMNodeFactory

class AsciiDOMObjectFactory : DOMNodeFactory {
    override fun createRect(nodeId: String, x: Int, y: Int, width: Int, height: Int, backgroundChar: Char): DOMNode {
        return AsciiRect(nodeId, x, y, width, height, backgroundChar)
    }

    override fun createText(nodeId: String, x: Int, y: Int, content: String): DOMNode {
        return AsciiText(nodeId, x, y, content)
    }


}
