import main.kotlin.ui.elements.NodeFactory
import Renderers.PatchRenderer
import main.kotlin.terminal.dom.DOM
import main.kotlin.terminal.window.TerminalWindow
import main.kotlin.ui.interpreters.CodeInterpreter
import main.kotlin.ui.interpreters.Interpreter

class App {
    fun run() {
        val factory: NodeFactory = NodeFactory()
        val interpreter: Interpreter = CodeInterpreter(factory)
        val dom = DOM(interpreter)
        val window = TerminalWindow(dom.width, dom.height)
        window.init()

        return;
        print(dom.toString())

        window.renderer = PatchRenderer()
        while (true) {
            dom.update()
//            window.drawIncrementally(dom,500)
            window.draw(dom)

            Thread.sleep(50)
        }
    }

}
