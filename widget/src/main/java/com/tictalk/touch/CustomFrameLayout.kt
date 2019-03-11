package com.tictalk.touch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.blankj.utilcode.util.LogUtils

class CustomFrameLayout(context: Context, attributeSet: AttributeSet?):FrameLayout(context, attributeSet) {
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i("layout dispatchTouchEvent ${ev.toString()}")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i("layout onInterceptTouchEvent ${ev.toString()}")
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.i("layout onTouchEvent ${event.toString()}")
        return super.onTouchEvent(event)
    }
}