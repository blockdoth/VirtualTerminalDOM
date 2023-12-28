package abstract_classes

import interfaces.FrameBuffer

abstract class DOMNode(
    val nodeId: String,
    var xPos: Int,
    var yPos: Int,
    ) {
    abstract var width: Int
    abstract var height: Int
    abstract var localFramebuffer: FrameBuffer

    var parent: DOMNode? = null
    var hasUpdate: Boolean = true
    var children: MutableList<DOMNode> = mutableListOf()
    var DOMNodeMap : MutableMap<String, DOMNode> = mutableMapOf()


    fun hasUpdate(): Boolean {
        if (hasUpdate) {
            return true
        } else {
            for (child in children) {
                if (child.hasUpdate()) {
                    return true
                }
            }
        }
        return false
    }

    fun resize(width: Int, height: Int){
        this.height = height
        this.width = width

        localFramebuffer.resize(width, height)
        for (child in children) {
            child.resize(width, height)
        }
        hasUpdate = true
        flatten(localFramebuffer)
    }


    fun addChild(node: DOMNode){
        node.parent = this
        children.add(node)
    }
    fun getNode(id: String): DOMNode? {
        if (nodeId == id) {
            return this
        } else if (DOMNodeMap.containsKey(id)) {
            return DOMNodeMap.get(id)!!
        }else if (!children.isEmpty()) {
            for (child in children){
                val node = child.getNode(id)
                if (node != null) {
                    return node
                }
            }
        }
        return null
    }

    fun move(xOffset: Int, yOffset: Int){
        if (xOffset != 0) {
            xPos = (xPos + xOffset) % parent!!.width
            hasUpdate = true
        }
        if (yOffset != 0) {
            yPos = (yPos + yOffset) % parent!!.height
            hasUpdate = true
        }

    }

    fun setPos(x: Int, y: Int){
        if (xPos != x || yPos != y) {
            xPos = x
            yPos = y
            hasUpdate = true
        }
    }
    fun flatten(outputBuffer: FrameBuffer): FrameBuffer {
        if (hasUpdate()) {
            draw(outputBuffer)
            for (child in children) {
                child.flatten(outputBuffer)
            }
        }
        return outputBuffer;
    }

    fun getLocalOrigin(): Pair<Int, Int> {
        if (parent == null) {
            return Pair(xPos, yPos)
        }
        val (parentX,parentY) = parent!!.getLocalOrigin()

        var localX = parentX + xPos
        if (localX < 0) {
            localX += width
        }
        if (localX - parentX >= parent!!.width) {
            localX %= parent!!.width
        }

        var localY = parentY + yPos
        if (localY < 0) {
            localY += height
        }
        if (localY - parentY >= parent!!.height) {
            localY %= parent!!.height
        }

        return Pair(localX, localY)
    }

    abstract fun draw(frameBuffer: FrameBuffer)
    fun printStructure(depth:Int, leave: Boolean): String{
        //TODO("Make this a separate program")
        var structure: String = nodeId + "\n"
        for (child in children) {
            var folderStructure = ""
            if (leave) {
                folderStructure += "   ".repeat(depth)
            }else {
                folderStructure += " │ ".repeat(depth)
            }

            if (child == children.last()) {
                folderStructure += " └ "
                structure += folderStructure + child.printStructure(depth + 1, true)
            } else{
                folderStructure += " ├ "
                structure += folderStructure + child.printStructure(depth + 1, false)
            }
        }
        return structure
    }

}