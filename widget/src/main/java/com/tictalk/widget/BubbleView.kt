package com.tictalk.widget

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.tictalk.core.BezierUtil

class BubbleView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val colors = arrayOfNulls<Int>(2)
    private val positons = arrayOfNulls<Int>(2)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val bubbles = arrayListOf<BubbleBean>()
    var switch = false

    private val animatorSet = AnimatorSet()
    private val inAnimator by lazy {
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = 800
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addUpdateListener {
            val t = it.animatedValue as Float
            //控制进场
            for (bubble in bubbles) {
                val realTimePointF =
                    BezierUtil.CalculateBezierPointForQuadratic(
                        t, bubble.initPoint, bubble.floatPoint, bubble.endPoint
                    )
                bubble.movingPoint = realTimePointF
            }
            invalidate()
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

    fun startAnimation() {
        if (!switch) {
            animatorSet.play(inAnimator)
            animatorSet.start()
        } else {
            animatorSet.reverse()
        }
        switch = !switch
    }
}