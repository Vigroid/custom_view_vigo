package com.tictalk.widget

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.tictalk.core.BezierUtil

class BubbleView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val colors = arrayOfNulls<Int>(2)
    private val positons = arrayOfNulls<Int>(2)
    private val pathMeasures = arrayListOf<PathMeasure>()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val bubbles = arrayListOf<BubbleBean>()
    var isReverse = false

    private val animatorSet = AnimatorSet()
    private val inAnimator by lazy {
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = 800
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addUpdateListener {
            //控制进场
            for (bubble in bubbles) {
                val realTimePointF =
                    BezierUtil.CalculateBezierPointForQuadratic(
                        it.animatedValue as Float, bubble.initPoint, bubble.floatPoint, bubble.endPoint
                    )
                bubble.movingPoint = realTimePointF
            }
            invalidate()
        }
        return@lazy animator
    }

    private val jitterAnimator by lazy {
        val pos = FloatArray(2)
        val tan = FloatArray(2)

        for ((i, bubble) in bubbles.withIndex()) {
            val path = Path()
            if (i % 2 == 0) {
                path.addCircle(bubble.endPoint.x - 50f, bubble.endPoint.y, 50f, Path.Direction.CCW)
            } else {
                path.addCircle(bubble.endPoint.x - 50f, bubble.endPoint.y, 50f, Path.Direction.CW)
            }
            val pathMeasure = PathMeasure(path, true)
            pathMeasures.add(pathMeasure)
        }

        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = 3000
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addUpdateListener {
            val t = it.animatedFraction

            for ((i, bubble) in bubbles.withIndex()) {
                pathMeasures[i].getPosTan(pathMeasures[i].length * t, pos, tan)
                bubble.movingPoint = PointF(pos[0], pos[1])
                invalidate()
            }
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
        if (!isReverse) {
            animatorSet.play(jitterAnimator).after(inAnimator)
            animatorSet.start()
        } else {
            animatorSet.reverse()
        }
        isReverse = !isReverse
    }
}