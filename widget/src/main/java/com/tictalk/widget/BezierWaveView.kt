package com.tictalk.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator

class BezierWaveView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var phi = 0.0
    private var psi = 0.0

    private var waveColor = Color.BLUE

    private var waveSpeed = 12f
    private var startPeriod = 0f

    private var valueAnimator: ValueAnimator? = null

    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BezierWaveView)
        startPeriod = typedArray.getFloat(R.styleable.BezierWaveView_startPeriod, 0f)
        waveColor = typedArray.getColor(R.styleable.BezierWaveView_bgColor, Color.BLUE)
        typedArray.recycle()


        paint.style = Paint.Style.FILL
        paint.color = waveColor
        paint.strokeWidth = 5f
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        psi = 4 * Math.PI / width
        initAnimation()
    }

    override fun onDraw(canvas: Canvas?) {
        drawSin()
        canvas?.drawPath(path, paint)
    }

    private fun drawSin() {
        phi -= waveSpeed / 100f
        path.reset()
        path.moveTo(0f, height.toFloat())
        var y: Double
        for (x in 0..width step 20) {
            y = Math.sin(x * psi + phi + Math.PI * startPeriod) * 100 + 300
            path.lineTo(x.toFloat(), height - y.toFloat())
        }
        path.lineTo(width.toFloat(), height.toFloat())
        path.lineTo(0f, height.toFloat())
        path.close()
    }

    private fun initAnimation() {
        valueAnimator = ValueAnimator.ofInt(0)
        valueAnimator?.duration = 1000
        valueAnimator?.interpolator = LinearInterpolator()
        valueAnimator?.repeatCount = ValueAnimator.INFINITE
        valueAnimator?.addUpdateListener {
            invalidate()
        }

        valueAnimator?.start()
    }
}