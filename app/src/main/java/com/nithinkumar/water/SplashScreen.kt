package com.nithinkumar.water

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import com.nithinkumar.water.Activity.WaterActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        WaterIntakeNotificationChannel().createNotificationChannel(this,
                NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
                getString(R.string.app_name), "Water Local Notification channel.")

        splash_logo.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                Toast.makeText(this@SplashScreen, "ANIMATION REPEAT", Toast.LENGTH_LONG).show()
            }

            override fun onAnimationEnd(animation: Animator?) {
                Toast.makeText(this@SplashScreen, "ANIMATION DONE", Toast.LENGTH_LONG).show()
                val intent = Intent(this@SplashScreen, WaterActivity::class.java)
                startActivity(intent)
                WaterIntakeNotificationChannel().createNotification(this@SplashScreen)
            }

            override fun onAnimationCancel(animation: Animator?) {
                Toast.makeText(this@SplashScreen, "ANIMATION CANCEL", Toast.LENGTH_LONG).show()
            }

            override fun onAnimationStart(animation: Animator?) {
                Toast.makeText(this@SplashScreen, "ANIMATION START", Toast.LENGTH_LONG).show()
            }

        })

    }
}
