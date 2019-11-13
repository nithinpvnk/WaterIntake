package com.nithinkumar.water.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.nithinkumar.water.R
import com.nithinkumar.water.WaterShared
import kotlinx.android.synthetic.main.fragment_home.view.*

class WaterFragment : Fragment() {
    private var mShared: WaterShared? = null
    private var fragmentListener: OnWaterScreenFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false)
        mShared = WaterShared(context)
        fragmentListener?.onDefaultLoad()

        homeFragmentView.navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    fragmentListener?.onHomeClicked()
                    true
                }
                R.id.navigation_dashboard -> {
                    fragmentListener?.onHistoryClicked()
                    true
                }
                R.id.navigation_notifications -> {
                    fragmentListener?.onSettingsClicked()
                    true
                }
                else -> false
            }
        }
        return homeFragmentView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnWaterScreenFragmentInteractionListener) {
            fragmentListener = context
        } else {
            throw RuntimeException("$context must implement OnWaterScreenFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentListener = null
    }


    interface OnWaterScreenFragmentInteractionListener {
        fun onDefaultLoad()
        fun onHomeClicked()
        fun onHistoryClicked()
        fun onSettingsClicked()
        fun onFABClicked(pageContainer: FrameLayout)
    }

    companion object {
        fun newInstance() = WaterFragment()
    }

}

