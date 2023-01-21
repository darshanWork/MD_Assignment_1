package com.dd.mdassignmentapp

import android.graphics.Canvas
import android.graphics.drawable.Drawable

open class RedBall(width: Int, height: Int, x: Int, y: Int, image: Drawable) :
    GameObject(width, height, x, y, image) {

    open fun move(canvas: Canvas, dx: Int)
    {
        if(x>(canvas.width-width) || x<0)
        {
            x += 0
        }
        else
        {
            x += dx
        }
    }

    open fun jump(canvas: Canvas, dy: Int)
    {
        if(y > (canvas.height-height) || y < 0)
        {
            y += 0
        }
        else
        {
            for(i in 1..dy)
            {
                y -= 1
            }
        }
    }

    open fun drop(canvas: Canvas)
    {
        while (y < 1680)
        {
            y += 1
        }
    }
}