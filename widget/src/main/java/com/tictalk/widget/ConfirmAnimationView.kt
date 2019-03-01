package com.tictalk.widget

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

class ConfirmAnimationView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val length = 400f
    private val presetHeight = 100f

    private var shouldDrawOk = false

    //图形位置
    private var left = 0f
    private var top = 0f
    private var right = 0f
    private var bottom = 0f
    private var rx = 0f
    private var ry = 0f
    private var transY = 0f
        set(value) {
            field = value
            invalidate()
        }

    private var initLeft = 0f
    private var initRight = 0f
    private var initRx = 0f
    private var initRy = 0f

    private lateinit var squareToCircleAnim: ValueAnimator
    private lateinit var moveUpAnim: ValueAnimator
    private lateinit var drawOKAnim: ValueAnimator

    private val animatorSet = AnimatorSet()

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val okPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val okPath = Path()
    private val okPathMeasure = PathMeasure()
    private var okPathEffect: PathEffect? = null

    init {
        paint.style = Paint.Style.FILL
        paint.color = Color.RED
        initOkPaint()
    }

    private fun initOkPaint() {
        okPaint.style = Paint.Style.STROKE
        okPaint.color = Color.WHITE
        okPaint.strokeWidth = 10f
    }

    private fun initSquareToCircleAnim() {
        squareToCircleAnim = ValueAnimator.ofFloat(0f, 1f)
        squareToCircleAnim.interpolator = AccelerateDecelerateInterpolator()
        squareToCircleAnim.duration = 500
        squareToCircleAnim.addUpdateListener {
            val diff = length - presetHeight
            val angleDiff = presetHeight / 2 - initRx

            left = initLeft + (diff / 2) * it.animatedFraction
            right = initRight - (diff / 2) * it.animatedFraction
            rx = initRx + (angleDiff) * it.animatedFraction
            ry = initRy + (angleDiff) * it.animatedFraction
            invalidate()
        }
    }

    private fun initMoveUpCircleAnim() {
        moveUpAnim = ObjectAnimator.ofFloat(this, "transY", 0f, 300f)
        moveUpAnim.interpolator = AccelerateDecelerateInterpolator()
        moveUpAnim.duration = 500
    }

    private fun initCheckDrawingAnim() {
        drawOKAnim = ValueAnimator.ofFloat(1f, 0f)
        drawOKAnim.duration = 1000
        drawOKAnim.addUpdateListener {
            shouldDrawOk = true
            val t = it.animatedValue as Float
            okPathEffect =
                DashPathEffect(floatArrayOf(okPathMeasure.length, okPathMeasure.length),
                    t * okPathMeasure.length)
            okPaint.pathEffect = okPathEffect
            invalidate()
        }
    }

    private fun initOkPath() {
        okPath.moveTo(width / 2f - presetHeight / 4, top - 300f + presetHeight / 2)
        okPath.lineTo(width / 2f - presetHeight / 4 + presetHeight / 4, top - 300f + 3 * presetHeight / 4)
        okPath.lineTo(width / 2f - presetHeight / 4 + presetHeight / 2, top - 300f + presetHeight / 4)
        okPathMeasure.setPath(okPath, false)
    }

    private fun initAnimations() {
        initSquareToCircleAnim()
        initMoveUpCircleAnim()
        initCheckDrawingAnim()
        animatorSet
            .play(moveUpAnim)
            .before(drawOKAnim)
            .after(squareToCircleAnim)
        animatorSet.startDelay = 300
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        left = (width - length) / 2
        right = (width + length) / 2
        bottom = (height + presetHeight) / 2
        top = (height - presetHeight) / 2
        initLeft = left
        initRight = right

        initOkPath()
        initAnimations()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRoundRect(left, top - transY, right, bottom - transY, rx, ry, paint)
        if (shouldDrawOk) {
            canvas?.drawPath(okPath, okPaint)
        }
    }

    fun startAnim() {
        animatorSet.start()
    }
}