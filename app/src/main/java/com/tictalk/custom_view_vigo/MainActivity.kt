package com.tictalk.custom_view_vigo

import android.graphics.PointF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tictalk.widget.BubbleBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val demoData = arrayListOf(
        BubbleBean(PointF(0f,0f),PointF(0f,0f),PointF(0f,0f),PointF(300f,300f),50,100f),
        BubbleBean(PointF(0f,0f),PointF(0f,0f),PointF(0f,0f),PointF(450f,500f),50,100f)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bubble.bubbles.addAll(demoData)
    }
}
