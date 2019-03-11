package com.tictalk.measure

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView(context: Context, attributeSet: AttributeSet):View(context,attributeSet) {
    val radius = 80f
    val padding = 30f
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = (padding + radius) *2

        val width = resolveSize(size.toInt(), widthMeasureSpec)
        val height = resolveSize(size.toInt(), heightMeasureSpec)

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawColor(Color.RED)
            drawCircle( radius + padding, radius + padding, radius, paint)
        }
    }
}