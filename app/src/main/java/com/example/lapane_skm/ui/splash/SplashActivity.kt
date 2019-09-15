package com.example.lapane_skm.ui.splash

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.lapane_skm.R
import com.example.lapane_skm.ui.main.BaseActivity
import com.example.lapane_skm.ui.main.MainActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                intentTo(MainActivity::class.java)
            }
            finish()
        }, 5000)
    }
}
