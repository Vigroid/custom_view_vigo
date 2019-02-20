package com.tictalk.widget

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

class BubbleView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val colors = arrayOfNulls<Int>(2)
    private val positons = arrayOfNulls<Int>(2)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val bubbles = arrayListOf<BubbleBean>()

    private val animatorSet = AnimatorSet()
    private val inAnimator by lazy {
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = 800
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addUpdateListener {
            //            it.animatedValue
            //控制进场
        }
        return@lazy animator
    }

    init {
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        for (bubble in bubbles) {
            paint.alpha = bubble.alpha
            canvas?.drawCircle(bubble.movingPoint.x, bubble.movingPoint.y, bubble.radius, paint)
        }
    }
}