package com.tictalk.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View

class BezierWaveView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    val path = Path()
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var phi = 0.0

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        drawSin()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawPath(path, paint)
    }

    private fun drawSin() {
        val psi = 2 * Math.PI / width
        path.moveTo(0f, height.toFloat())
        var y: Double
        for (x in 0..width step 20) {
            y = Math.sin(x * psi + phi)* 100 + 110
            path.lineTo(x.toFloat(), height - y.toFloat())
        }
    }
}