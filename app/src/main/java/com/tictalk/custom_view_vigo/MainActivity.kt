package com.tictalk.custom_view_vigo

import android.graphics.PointF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tictalk.widget.BubbleBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    val demoData = arrayListOf(
//        BubbleBean(PointF(0f, 0f), PointF(600f, 100f), PointF(600f, 600f), PointF(0f, 0f), 30, 50f),
//        BubbleBean(PointF(0f, 0f), PointF(100f, 400f), PointF(100f, 800f), PointF(0f, 0f), 50, 100f)
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        confirm.setOnClickListener {
            confirm.startAnim()
        }
    }
}
