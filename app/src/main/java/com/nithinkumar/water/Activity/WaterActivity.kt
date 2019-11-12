package com.nithinkumar.water.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.nithinkumar.water.R
import com.nithinkumar.water.WaterShared
import com.nithinkumar.water.fragment.HomeFragment
import com.nithinkumar.water.fragment.SplashScreenFragment

class WaterActivity : AppCompatActivity(), SplashScreenFragment.OnFragmentInteractionListener {

    private val fragmentManager: FragmentManager = supportFragmentManager
    private lateinit var mWaterShared: WaterShared

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water)
        mWaterShared = WaterShared(this)
        initialLoad()
    }

    override fun redirectToHomeScreen() {
        fragmentManager.popBackStack()
        fragmentManager.beginTransaction().apply {
            replace(R.id.content, HomeFragment.newInstance(), "2")
            commit()
        }
    }

    private fun initialLoad() {
        fragmentManager.beginTransaction().apply {
            add(R.id.content, SplashScreenFragment.newInstance(), "1")
            addToBackStack(null)
            commit()
        }
    }

}
