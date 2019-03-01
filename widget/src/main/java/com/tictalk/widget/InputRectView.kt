package com.tictalk.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.InputFilter
import android.util.AttributeSet
import android.widget.EditText

class InputRectView(context: Context, attributeSet: AttributeSet) : EditText(context, attributeSet) {

    private var textLength = 0
    private val maxTextLength = 6
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val framePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.style = Paint.Style.FILL
        paint.color = Color.BLACK
        framePaint.style = Paint.Style.STROKE
        framePaint.strokeWidth = 3f
        filters = arrayOf(InputFilter.LengthFilter(maxTextLength))
        setBackgroundColor(Color.TRANSPARENT)
        isCursorVisible = false
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        textLength = Math.min(text?.length ?: 0, maxTextLength)
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), 10f,10f, framePaint)
        canvas?.let { drawPswCircles(it) }
    }

    private fun drawPswCircles(canvas: Canvas) {
        val drawingHeight = height / 2f
        val drawingWidth = width / (maxTextLength+1).toFloat()

        for (i in 1..textLength) {
            canvas.drawCircle(drawingWidth*i, drawingHeight, 20f, paint)
            canvas.drawLine(drawingWidth*i -20f, height -10f, drawingWidth*i+ 20f, height -10f, framePaint)
        }
    }
}