package com.dd.mdassignmentapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class EarthSurfaceView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs), Runnable {

    var paint = Paint()
    var isRunning = true
    lateinit var myThread: Thread
    lateinit var myHolder: SurfaceHolder
    var rocks = ArrayList<Rock>()
    var redBall: RedBall

    init {
        paint.color = Color.TRANSPARENT
        //initialise game
        val rock = context!!.resources.getDrawable(R.drawable.earth_rock,null)
        for(i in 1..5)
        {
            val x = (0..900).random()
            val y = (0..1500).random()
            rocks.add(Rock(250,100, x, y, rock))
        }
        redBall = RedBall(50, 50, (0..900).random(), 1500, 0, 5,
            context!!.resources.getDrawable(R.drawable.red_ball,null))
        //thread and holder
        myThread = Thread(this)
        myThread.start()
        myHolder = holder
    }


    override fun run() {
        while(isRunning) {
            if(!myHolder.surface.isValid)
                continue
            val canvas: Canvas = myHolder.lockCanvas()
            canvas.drawRect(0f,0f,canvas.width.toFloat(),canvas.height.toFloat(), paint)
            game(canvas)
            myHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun game(canvas: Canvas) {
        for(rock in rocks)
            rock.draw(canvas)
        redBall.move(canvas)
    }
}