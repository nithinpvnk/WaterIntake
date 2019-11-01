package com.nithinkumar.water.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.nithinkumar.water.OnboardingAdapter
import com.nithinkumar.water.R

class OnboardingFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstance: Bundle?): View? =
            inflater.inflate(R.layout.fragment_onboarding, container, false).also {
                it.findViewById<ViewPager2>(R.id.onboardingViewPager)?.apply {
                    adapter = OnboardingAdapter()
                }
            }

    companion object {
        fun newInstance() = OnboardingFragment()
    }
}
