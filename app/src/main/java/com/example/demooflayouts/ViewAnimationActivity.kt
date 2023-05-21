package com.example.demooflayouts

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Pair
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.ViewCompat

class ViewAnimationActivity : AppCompatActivity() {

   private lateinit var carIv : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_animation)

        carIv = findViewById(R.id.car_iv)

        findViewById<Button>(R.id.blink_btn).setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this,R.anim.blinkanim)
            carIv.startAnimation(animation)
        }

        findViewById<Button>(R.id.fade_btn).setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this,R.anim.fadeanim)
            carIv.startAnimation(animation)
        }
        findViewById<Button>(R.id.zoom_btn).setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this,R.anim.zoomanim)
            carIv.startAnimation(animation)
        }

        findViewById<Button>(R.id.rotate_btn).setOnClickListener {
            val animation = ObjectAnimator.ofFloat(carIv,"rotation",0f,360f)
            animation.duration = 4000
            animation.start()
            val animation1 = ObjectAnimator.ofFloat(carIv,"rotation",360f,0f)
            animation1.duration = 2000
            animation1.start()
        }
        findViewById<Button>(R.id.move_btn).setOnClickListener {
            val animation = ObjectAnimator.ofFloat(carIv,"TranslationX",0f,90f)
            animation.duration  = 2000
            animation.interpolator = LinearInterpolator()
            animation.start()
            val animation1 = ObjectAnimator.ofFloat(carIv,"TranslationX",90f,0f)
            animation1.duration  = 2000
            animation.interpolator = LinearInterpolator()
            animation1.start()
        }


        findViewById<Button>(R.id.bounced_btn).setOnClickListener{
            val scaleX = ObjectAnimator.ofFloat(carIv,"scaleX",0f,1f)
            val scaleY = ObjectAnimator.ofFloat(carIv,"scaleY",0f,1f)
            val pivotX = ObjectAnimator.ofFloat(carIv,"pivotX",1f)
            val pivotY = ObjectAnimator.ofFloat(carIv,"pivotY",1f)
            val animation = AnimatorSet()
            animation.interpolator = BounceInterpolator()
            animation.playTogether(scaleX,scaleY,pivotX,pivotY)
            animation.start()
        }

        findViewById<Button>(R.id.activity_btn).setOnClickListener {
            val intent = Intent(this,SaveFileActivity::class.java)
            val option = ActivityOptions.makeCustomAnimation(this,R.anim.slidedownanim,R.anim.slidedownanim)
            startActivity(intent,option.toBundle())

        }

        findViewById<Button>(R.id.single_btn).setOnClickListener {
            val intent = Intent(this,SaveFileActivity::class.java)
            val option = ActivityOptions.makeSceneTransitionAnimation(this,carIv,ViewCompat.getTransitionName(carIv))
            startActivity(intent,option.toBundle())
        }

        findViewById<Button>(R.id.pair_btn).setOnClickListener {
            val intent = Intent(this,SaveFileActivity::class.java)
            val option = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(carIv,"m"),Pair.create(carIv,"m"))
            startActivity(intent,option.toBundle())
        }

    }
}