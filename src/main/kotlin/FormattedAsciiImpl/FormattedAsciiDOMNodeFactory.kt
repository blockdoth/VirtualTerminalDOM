package AsciiImpl

import FormattedAsciiImpl.FormattedMultilineAsciiText
import abstract_classes.DOMNode
import interfaces.DOMNodeFactory

class FormattedAsciiDOMNodeFactory : DOMNodeFactory {
    override fun createRect(nodeId: String, x: Int, y: Int, width: Int, height: Int, backgroundChar: Char, formatting: String): DOMNode {
        return FormattedRect(nodeId, x, y, width, height, backgroundChar, formatting)
    }

    override fun createText(nodeId: String, x: Int, y: Int, content: String, formatting: String): DOMNode {
        return FormattedAsciiText(nodeId, x, y, content, formatting)
    }

    override fun createMultilineText(nodeId: String, x: Int, y: Int, content: String, formatting: String): DOMNode {
        return FormattedMultilineAsciiText(nodeId, x, y, content, formatting)
    }


}
