import AsciiImpl.AsciiDOMObjectFactory
import TerminalDOM.TerminalDOM
import TerminalDOM.TerminalWindow
import abstract_classes.DOMNode
import interfaces.DOM
import interfaces.DOMNodeFactory
import interfaces.Window

class App(
    widthString: String,
    heightString: String
    ) {

    val width: Int = widthString.toInt()
    val height: Int = heightString.toInt()

    lateinit var dom: DOM
    lateinit var window: Window

    fun run() {
        window = TerminalWindow(width , height)
        val factory: DOMNodeFactory = AsciiDOMObjectFactory()

        val root: DOMNode = factory.createRect( "Background", 0,0, width, height, ' ')
        dom = TerminalDOM(width, height, root)
        dom.setDOM(interpretFile(factory))

        dom.printStructure()
        var moveDir =  1
        while (true) {
            var node = dom.findNode("Program info")
            if (node != null) {
                if (node.xPos + node.width >= width || node.xPos < 16) {
                    moveDir *= -1
                }
                node.move(moveDir,0)
            }
            window.draw(dom.flatten())
            Thread.sleep(10)
        }
    }

    fun interpretFile(factory: DOMNodeFactory): DOMNode {
        val infoString = " width: $width, height: $height "
        val localRoot = factory.createRect( "Background", 0,0, width, height, '░')


        val longTitleBackground = factory.createRect( "longTitleBackground", 0,0, width, 3, '█')
        val longTitle = factory.createText("Long title", 1, 1, "================================ Long title ================================")
        longTitleBackground.addChild(longTitle)

        val bottomInfoBackground = factory.createRect( "bottomInfoBackground", 0,height-3, width, 3, '█')
        bottomInfoBackground.addChild(factory.createText("Program info Title", 1, 1, "Program info: "))
        bottomInfoBackground.addChild(factory.createText("Program info", 16, 1, infoString))

        localRoot.addChild(longTitleBackground)
        localRoot.addChild(bottomInfoBackground)


        return localRoot
    }
}
