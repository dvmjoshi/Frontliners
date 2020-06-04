package com.mad.frontliners

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    //Concept late-initialized properties
    private lateinit var heading: TextView
    private lateinit var subheading: TextView
    private lateinit var h1: LinearLayout
    private lateinit var h2: LinearLayout
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var frontlinerImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with(animationView) {
            setMinAndMaxFrame(30, 50)
        }

        heading = findViewById(R.id.headertitle)
        subheading = findViewById(R.id.subtitle)
        frontlinerImage = findViewById(R.id.Frontliner_image)
        h1 = findViewById(R.id.h1)
        h2 = findViewById(R.id.h2)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)

        val headinganim = AnimationUtils.loadAnimation(this, R.anim.frontanim)
        val imageanim = AnimationUtils.loadAnimation(this, R.anim.bottomamin)
        val h1anim = AnimationUtils.loadAnimation(this, R.anim.b1)
        val h2anim = AnimationUtils.loadAnimation(this, R.anim.b2)
        heading.startAnimation(headinganim)
        subheading.startAnimation(headinganim)
        frontlinerImage.startAnimation(imageanim)
        h1.startAnimation(h1anim)
        h2.startAnimation(h2anim)
        btn1.setOnClickListener {
            if (isConnected()) {
                val intent = Intent(this, Tracker::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    getApplicationContext(),
                    "No Internet Connection",
                    Toast.LENGTH_SHORT
                ).show();
            }

        }
        btn2.setOnClickListener {
            val intent = Intent(this, Optimistic::class.java)
            startActivity(intent)
        }


    }

    private fun isConnected(): Boolean {
        var connected = false
        try {
            val cm =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val nInfo = cm.activeNetworkInfo
            connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
            return connected
        } catch (e: Exception) {
            Log.e("Connectivity Exception", e.message)
        }
        return connected
    }

}
