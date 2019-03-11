package com.tictalk.layout

import android.content.Context
import android.util.AttributeSet
import android.view.View

class OneHundredView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(100,100)
    }
}