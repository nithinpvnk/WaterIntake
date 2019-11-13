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

class HomeFragment : Fragment() {
    private var mShared: WaterShared? = null
    private var fragmentListener: OnHomeScreenFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val homeFragmentView = inflater.inflate(R.layout.home_screen, container, false)
        mShared = WaterShared(context)

        return homeFragmentView
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnHomeScreenFragmentInteractionListener) {
//            fragmentListener = context
//        } else {
//            throw RuntimeException("$context must implement OnWaterScreenFragmentInteractionListener")
//        }
//    }

    override fun onDetach() {
        super.onDetach()
        fragmentListener = null
    }


    interface OnHomeScreenFragmentInteractionListener {
        fun onDefaultLoad(pageContainer: FrameLayout)
        fun onHomeClicked(pageContainer: FrameLayout)
        fun onHistoryClicked(pageContainer: FrameLayout)
        fun onSettingsClicked(pageContainer: FrameLayout)
        fun onFABClicked(pageContainer: FrameLayout)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}

