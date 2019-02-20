package com.tictalk.widget

import android.graphics.PointF

data class BubbleBean(
    /**
     * 起始坐标
     */
    var initPoint: PointF,
    /**
     * 悬浮坐标
     */
    var floatPoint: PointF,
    /**
     * 终点坐标
     */
    var endPoint: PointF,
    /**
     * 行进中坐标
     */
    var movingPoint: PointF,
    var alpha: Int,
    var radius: Float
)