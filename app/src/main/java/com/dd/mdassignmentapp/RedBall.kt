package com.dd.mdassignmentapp

import android.graphics.Canvas
import android.graphics.drawable.Drawable

open class RedBall (var width:Int, var height:Int, var x:Int, var y:Int, var dx:Int, var dy:Int, var image:Drawable) {

    open fun move(canvas: Canvas)
    {
        x += dx
        y += dy
        if(x>(canvas.width-width) || x<0)
            dx = -dx
        if(y>(canvas.height-height) || y<0)
            dy = -dy
        image.setBounds(x, y,x+width,y+height)
        image.draw(canvas)
    }

    /**
     * open fun jump(canvas: Canvas)
    {
    y += dy
    /**
    while(//collision)
    {
    }
     *
    */
    for(i in 1..5)
    {
    y -= 1
    image.setBounds(x, y,x+width,y+height)
    image.draw(canvas)
    }
    }
     */
}