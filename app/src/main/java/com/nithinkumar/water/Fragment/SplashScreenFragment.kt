package com.nithinkumar.water.fragment

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nithinkumar.water.R
import kotlinx.android.synthetic.main.fragment_splash_screen.view.*

class SplashScreenFragment : Fragment() {

    private var fragmentListener: OnSplashScreenFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)

//        WaterIntakeNotificationChannel().createNotificationChannel(this,
//                NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
//                getString(R.string.app_name), "Water Local Notification channel.")

        view.splash_logo_fragment.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                Toast.makeText(context, "ANIMATION REPEAT", Toast.LENGTH_LONG).show()
            }

            override fun onAnimationEnd(animation: Animator?) {
                Toast.makeText(context, "ANIMATION DONE", Toast.LENGTH_LONG).show()
                fragmentListener?.redirectToHomeScreen()
//              WaterIntakeNotificationChannel().createNotification(this@SplashScreen)
            }

            override fun onAnimationCancel(animation: Animator?) {
                Toast.makeText(context, "ANIMATION CANCEL", Toast.LENGTH_LONG).show()
            }

            override fun onAnimationStart(animation: Animator?) {
                Toast.makeText(context, "ANIMATION START", Toast.LENGTH_LONG).show()
            }
        })

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSplashScreenFragmentInteractionListener) {
            fragmentListener = context
        } else {
            throw RuntimeException("$context must implement OnSplashScreenFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentListener = null
    }

    interface OnSplashScreenFragmentInteractionListener {
        fun redirectToHomeScreen()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashScreenFragment()
    }
}
