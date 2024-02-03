package main.kotlin.ui.interpreters

import main.kotlin.terminal.dom.DOMNode
import main.kotlin.ui.elements.NodeFactory
import main.kotlin.ui.behaviors.Behavior
import main.kotlin.ui.behaviors.StateFullBehavior
import main.kotlin.ui.behaviors.StateLessBehavior

class CodeInterpreter(
    override var factory: NodeFactory
    ) : Interpreter {


    var width = 100
    var height = 30

    override fun interpret(): Pair<DOMNode, MutableList<Behavior>> {
        var behaviors: MutableList<Behavior> = mutableListOf()

        val infoString = " width: $width, height: $height "
        val textStyle = "\u001B[48;5;8m"

        val root = factory.createRect( "Background", 0,0, width, height, ' ',"\u001B[48;5;8m")

        val longTitleBackground = factory.createRect( "longTitleBackground", 0,0, width, 3, ' ',"\u001B[48;5;7m")
        val title = factory.createText("Long title", 1, 1, " Long title ",textStyle) //TODO("Make centered")
        longTitleBackground.addChild(title)


        val bottomInfoBackground = factory.createRect( "bottomInfoBackground", 0,height-3, width, 3, ' ',"\u001B[48;5;7m")
        val bottomInfoTitle = factory.createText("Program Info Title", 1, 1, "Program info: ",textStyle)
        val bottomInfo = factory.createText("Program info", 16, 1, infoString,textStyle + "\u001B[1m")

        bottomInfoBackground.addChild(bottomInfoTitle)
        bottomInfoBackground.addChild(bottomInfo)

        root.addChild(longTitleBackground)
        root.addChild(bottomInfoBackground)


        val structureRect = factory.createRect( "structureRect", 5,5, 0, 0, ' ',"\u001B[48;5;7m")
        val structure = factory.createMultilineText("structure", 0, 0, "","\u001B[31m")
        structureRect.addChild(structure)
        root.addChild(structureRect)

        val coordsRect = factory.createRect( "coordsRect", 50,0, 20, 5, ' ',"\u001B[48;5;7m")
        val coordsTopLeft = factory.createText("coordsTopLeft", 0, 0, "","\u001B[31m")
        val coordsBottomRight = factory.createText("coordsBottomRight", 0, 0, "","\u001B[31m")

        coordsRect.addChild(coordsTopLeft)
        coordsRect.addChild(coordsBottomRight)
        root.addChild(coordsRect)



        val moveLogic: (node: DOMNode, state: Any) -> Any = { node: DOMNode, state: Any ->
            var (moveX,moveY) = state as Pair<Float,Float>
            if (node.yPos + node.height >= height && moveY > 0) {
                moveY *= -1
            }else if (node.yPos <= 0 && moveY < 0) {
                moveY *= -1
            }

            if (node.xPos + node.width >= width && moveX > 0) {
                moveX *= -1
            } else if (node.xPos <= 0 && moveX < 0) {
                moveX *= -1
            }

            //moveY += 0.2f
            node.move(moveX.toInt(),moveY.toInt())
            Pair(moveX,moveY)
        }


        val printStruct: (node: DOMNode) -> Unit = { node: DOMNode ->
            var currentNode = node
            while (currentNode.parent != null) {
                currentNode = currentNode.parent!!
            }

            val struct = currentNode.toString(0, false).dropLast(1)
            val width = struct.lines().maxOf { it.length }
            val height = struct.lines().size

            node.height = height
            node.width = width
            node.content = struct

            node.parent!!.width = width
            node.parent!!.height = height

        }



        val coordsBehavior: (node: DOMNode) -> Unit = { node: DOMNode ->
            val topLeft = node.children[0]
            val bottomRight = node.children[1]

            topLeft.content = "(${node.xPos},${node.yPos})"
            topLeft.height = 1
            topLeft.width = topLeft.content.length

            bottomRight.content = "(${node.xPos + node.width},${node.yPos + node.height})"
            bottomRight.height = 1
            bottomRight.width = bottomRight.content.length
            bottomRight.xPos = node.width - bottomRight.width
            bottomRight.yPos = node.height - 1
        }

        val moveBehaviorFactory= { node: DOMNode, xDir:Float, yDir:Float ->
            StateFullBehavior(
                node,
                moveLogic,
                Pair(xDir,yDir)
            )
        }
        behaviors.add(moveBehaviorFactory(coordsRect,1f,1f))
        behaviors.add(moveBehaviorFactory(title,1f,0f))

        val printStructBehavior = StateLessBehavior(
            structure,
            printStruct
        )
        val coordsRectBehavior = StateLessBehavior(
            coordsRect,
            coordsBehavior
        )


        behaviors.add(printStructBehavior)
        //behaviors.add(moveBehavior(coordsRect,1f,1f))
        behaviors.add(coordsRectBehavior)




        return Pair(root,behaviors)
    }

}
