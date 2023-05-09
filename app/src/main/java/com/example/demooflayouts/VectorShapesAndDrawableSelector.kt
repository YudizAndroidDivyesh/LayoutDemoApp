package com.example.demooflayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView

class VectorShapesAndDrawableSelector : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vector_shapes_and_drawable_selector)

        val topFlash = findViewById<ImageView>(R.id.down_UP)

        findViewById<Button>(R.id.btn_flashlight).setOnClickListener {
            Log.e("click","btn_flashlight Click")
            it.isSelected = !it.isSelected

                topFlash.isSelected = !topFlash.isSelected

        }


    }
}