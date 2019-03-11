package com.tictalk.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.blankj.utilcode.util.LogUtils

class CustomView(context: Context, attributeSet: AttributeSet):View(context, attributeSet) {
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.i("view dispatchTouchEvent ${event.toString()}")
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.i("view onTouchEvent ${event.toString()}")
        return super.onTouchEvent(event)
    }
}