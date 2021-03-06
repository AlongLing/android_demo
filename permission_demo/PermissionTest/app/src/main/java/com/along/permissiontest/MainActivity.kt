package com.along.permissiontest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnOne -> {
                startActivity(Intent(this, JavaActivity::class.java))
            }
            R.id.btnTwo -> {
                startActivity(Intent(this, KotlinActivity::class.java))
            }
            else -> {
                // do nothing.
            }
        }
    }

    private fun initListener() {
        btnOne.setOnClickListener(this)
        btnTwo.setOnClickListener(this)
    }
}