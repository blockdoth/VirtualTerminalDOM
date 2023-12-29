import AsciiImpl.AsciiDOMNodeFactory
import AsciiImpl.FormattedAsciiDOMNodeFactory
import TerminalDOM.TerminalDOM
import TerminalDOM.TerminalWindow
import interfaces.DOM
import interfaces.DOMNodeFactory
import interfaces.Interpreter
import interfaces.Window

class App {

    lateinit var dom: DOM
    lateinit var window: Window

    fun run() {
        val factory: DOMNodeFactory = FormattedAsciiDOMNodeFactory()
        val interpreter: Interpreter = CodeInterpreter(factory)
        dom = TerminalDOM(interpreter)
        window = TerminalWindow(dom.width, dom.height)

        dom.printStructure()


        while (true) {
            dom.update()
            window.draw(dom.flatten())
            Thread.sleep(10)
        }
    }



}
