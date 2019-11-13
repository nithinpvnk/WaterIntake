package com.nithinkumar.water.activity

import android.app.ProgressDialog.show
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.nithinkumar.water.R
import com.nithinkumar.water.WaterShared
import com.nithinkumar.water.fragment.*

class WaterActivity : AppCompatActivity(),
        SplashScreenFragment.OnSplashScreenFragmentInteractionListener,
        WaterFragment.OnWaterScreenFragmentInteractionListener,
        HomeFragment.OnHomeScreenFragmentInteractionListener {

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
            replace(R.id.content, WaterFragment.newInstance(), "2")
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

    override fun onDefaultLoad() {
        fragmentManager.beginTransaction().apply {
            add(R.id.pageContainer, HomeFragment.newInstance(), "3")
            commit()
        }
    }

    override fun onHomeClicked() {
        fragmentManager.beginTransaction().apply {
            replace(R.id.pageContainer, HomeFragment.newInstance(), "3")
            commit()
        }

    }

    override fun onHistoryClicked() {
        fragmentManager.beginTransaction().apply {
            replace(R.id.pageContainer, HistoryFragment.newInstance(), "4")
            commit()
        }

    }

    override fun onSettingsClicked() {
        fragmentManager.beginTransaction().apply {
            replace(R.id.pageContainer, SettingsFragment.newInstance(), "5")
            commit()
        }
    }

    override fun onFABClicked() {
        WaterIntakeFragment().let { addWater ->
            addWater.setTargetFragment(fragmentManager.findFragmentByTag("2"), 280)
            fragmentManager.let { addWater.show(it, "Quantity Consumed") }
        }
    }
}
