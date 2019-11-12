package com.nithinkumar.water.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nithinkumar.water.R
import com.nithinkumar.water.WaterShared

class HomeFragment : Fragment() {
    private var mShared: WaterShared? = null
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val hmv = inflater.inflate(R.layout.fragment_home, container, false)
        mShared = WaterShared(context)
        return hmv
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}