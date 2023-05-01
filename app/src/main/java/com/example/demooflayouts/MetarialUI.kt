package com.example.demooflayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MetarialUI : AppCompatActivity() {

    lateinit var normalFAB : FloatingActionButton
    lateinit var extendFAB : ExtendedFloatingActionButton
    lateinit var bgColor : ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metarial_ui)

        normalFAB = findViewById(R.id.fbBtn)
        extendFAB = findViewById(R.id.extendFb)
        bgColor = findViewById(R.id.fabLayout)

        normalFAB.setOnClickListener {
            Snackbar.make(normalFAB,"Added Successfully",Snackbar.LENGTH_SHORT).show()
            Snackbar.make(normalFAB,"Added",Snackbar.LENGTH_SHORT).show()
        }

        extendFAB.setOnClickListener {
            Snackbar.make(extendFAB, "Extended Successfully", Snackbar.LENGTH_LONG)
                .setAction("ChangeColor") {
                    bgColor.setBackgroundColor(resources.getColor(R.color.colorSecondary))
                }.setBackgroundTint(resources.getColor(R.color.snackBarColor))
                .setActionTextColor(resources.getColor(R.color.teal_200))
                .show()
        }

    }
}