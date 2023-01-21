package com.dd.mdassignmentapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlinx.coroutines.Runnable

class MoonSurfaceView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs), Runnable {

    var paint = Paint()
    var isRunning = true
    var myThread: Thread
    var myHolder: SurfaceHolder
    lateinit var canvas: Canvas
    var rocks = ArrayList<Rock>()
    var redBall: RedBall

    init
    {
        paint.color = R.drawable.moon_background
        //initialise game
        redBall = RedBall(50, 50, (0..900).random(), 1500,
            context!!.resources.getDrawable(R.drawable.red_ball,null))
        val rock = context!!.resources.getDrawable(R.drawable.moon_rock,null)
        for(i in 1..5) {
            val x = (0..900).random()
            val y = (0..1500).random()
            rocks.add(Rock(250, 100, x, y, rock))
        }
        //thread and holder
        myThread = Thread(this)
        myThread.start()
        myHolder = holder
    }
    override fun run() {
        while(isRunning) {
            if(!myHolder.surface.isValid)
                continue
            canvas = myHolder.lockCanvas()
            canvas.drawRect(0f,0f,canvas.width.toFloat(),canvas.height.toFloat(), paint)
            redBall.draw(canvas)
            game(canvas)
            myHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun game(canvas: Canvas) {
        for(rock in rocks)
        {
            rock.draw(canvas)
        }
        /*while(isRunning)
        {
            redBall.jump(canvas, 50)
        }*/
        redBall.jump(canvas, 50)
    }
}