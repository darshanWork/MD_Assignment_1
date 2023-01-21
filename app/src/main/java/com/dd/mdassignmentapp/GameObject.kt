package com.dd.mdassignmentapp

import android.graphics.Canvas
import android.graphics.drawable.Drawable

open class GameObject(var width:Int, var height:Int, var x:Int, var y: Int, var image: Drawable) {
    open fun draw(canvas: Canvas)
    {
        image.setBounds(x, y, x+width, y+height)
        image.draw(canvas)
    }

    open fun isColliding(obj: GameObject): Boolean {
        var distance: Int = this.y - obj.y
        return (distance == 0)
    }
}