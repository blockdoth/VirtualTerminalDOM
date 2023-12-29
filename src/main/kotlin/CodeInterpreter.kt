import abstract_classes.DOMNode
import interfaces.Behavior
import interfaces.DOMNodeFactory
import interfaces.Interpreter
import interfaces.StateFullBehavior

class CodeInterpreter(
    override var factory: DOMNodeFactory
    ) : Interpreter {


    var width = 200
    var height = 30

    override fun interpret(): Pair<DOMNode, MutableList<Behavior>> {
        var behaviors: MutableList<Behavior> = mutableListOf()

        val infoString = " width: $width, height: $height "
        val textStyle = "\u001B[48;5;8m"

        val root = factory.createRect( "Background", 0,0, width, height, ' ',"\u001B[48;5;8m")

        val longTitleBackground = factory.createRect( "longTitleBackground", 0,0, width, 3, ' ',"\u001B[48;5;7m")
        val longTitle = factory.createText("Long title", 1, 1, " Long title ",textStyle) //TODO("Make centered")
        longTitleBackground.addChild(longTitle)


        val bottomInfoBackground = factory.createRect( "bottomInfoBackground", 0,height-3, width, 3, ' ',"\u001B[48;5;7m")
        val bottomInfoTitle = factory.createText("Program Info Title", 1, 1, "Program info: ",textStyle)
        val bottomInfo = factory.createText("Program info", 16, 1, infoString,textStyle)

        bottomInfoBackground.addChild(bottomInfoTitle)
        bottomInfoBackground.addChild(bottomInfo)

        root.addChild(longTitleBackground)
        root.addChild(bottomInfoBackground)



        val nodeBehavior: (node: DOMNode, state: Any) -> Any = { node: DOMNode, state: Any ->
            var moveDir: Int = state as Int
            if (node.xPos + node.width >= width || node.xPos < 16) {
                moveDir *= -1
            }
            node.move(moveDir,0)
            moveDir
        }


        val behavior = StateFullBehavior(
            bottomInfo,
            nodeBehavior,
            1
        )

        behaviors.add(behavior)



        return Pair(root,behaviors)
    }

}
