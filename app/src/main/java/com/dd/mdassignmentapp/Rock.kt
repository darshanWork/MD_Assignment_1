package com.dd.mdassignmentapp

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import kotlin.random.Random

open class Rock (var width:Int, var height:Int, var x:Int, var y: Int, var image: Drawable) {

    open fun draw(canvas: Canvas)
    {
        image.setBounds(x, y, x+width, y+height)
        image.draw(canvas)
    }
}