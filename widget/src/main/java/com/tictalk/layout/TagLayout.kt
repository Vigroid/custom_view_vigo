package com.tictalk.layout

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup

class TagLayout(context: Context, attributeSet: AttributeSet) : ViewGroup(context, attributeSet) {
    val childBounds = mutableListOf<Rect>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthUsed = 0
        var heightUsed = 0
        var lineMaxHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChildWithMargins(child, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed)

            var childBound:Rect

            if (childBounds.size <= i) {
                childBound = Rect()
                childBounds.add(childBound)
            } else {
                childBound = childBounds[i]
            }

            childBound.set(widthUsed, heightUsed, widthUsed + child.measuredWidth, heightUsed + child.measuredHeight)
            widthUsed += child.measuredWidth
            lineMaxHeight = Math.max(lineMaxHeight, child.measuredHeight)
        }

        setMeasuredDimension(widthUsed, lineMaxHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childBound = childBounds[i]
            child?.layout(childBound.left, childBound.top, childBound.right, childBound.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}