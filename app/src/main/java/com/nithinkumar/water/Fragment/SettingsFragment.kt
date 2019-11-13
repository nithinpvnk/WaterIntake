package com.nithinkumar.water.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.nithinkumar.water.R
import com.nithinkumar.water.WaterShared
import kotlinx.android.synthetic.main.fragment_dialog_box.view.*
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : Fragment(), DetailsDialogFragment.OnFragmentInteractionListener {

    private var userName: TextView? = null
    private var userHeight: TextView? = null
    private var userWeight: TextView? = null
    private var startTime: TextView? = null
    private var endTime: TextView? = null

    private var mWaterShared: WaterShared? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val sv = inflater.inflate(R.layout.fragment_settings, container, false)
        //Shared Preference
        mWaterShared = WaterShared(context)
        //Assigning the xml values
        val editVals = sv.findViewById<ImageView>(R.id.edit_value)
        userName = sv.user_name_value
        userHeight = sv.height_value
        userWeight = sv.weight_value
        startTime = sv.start_value
        endTime = sv.end_value

        val rateus = sv.rate_us
        val shareWith = sv.share_with

        rateus.setOnClickListener { rateUs() }

        shareWith.setOnClickListener { shareWithFriend() }

        editVals.setOnClickListener {
            val ddf = DetailsDialogFragment()
            if (fragmentManager != null) {
                ddf.setTargetFragment(fragmentManager!!.findFragmentByTag("3"), 3)
                ddf.show(fragmentManager!!, "dialog")
            }
        }

        update()

        return sv
    }


    private fun update() {
        userName!!.text = mWaterShared!!.name_userValue
        userHeight!!.text = mWaterShared!!.height_userValue.toString()
        userWeight!!.text = mWaterShared!!.weight_userValue.toString()
        startTime!!.text = mWaterShared!!.startTime_userValue
        endTime!!.text = mWaterShared!!.endTime_userValue
    }

    private fun rateUs() {
        val uri = Uri.parse("market://details?id=" + "com.nithinkumar.water")
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + "com.nithinkumar.water")))
        }

    }

    private fun shareWithFriend() {
        try {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_SUBJECT, "Keep Track of your Hydration")
            val name = mWaterShared!!.name_userValue
            var sAux = "\n" + name + "wants to help you know your Water input with the help of this application.\n\n"
            sAux += "https://play.google.com/store/apps/details?id=com.nithinkumar.water \n\n"
            i.putExtra(Intent.EXTRA_TEXT, sAux)
            startActivity(Intent.createChooser(i, "choose one"))
        } catch (e: Exception) {
            //e.toString();
        }

    }

    // TODO: Update clearAll Method with db access

    fun clearAll() {

    }

    override fun onFragmentInteraction(frag: Fragment) {
        if (frag == fragmentManager?.findFragmentByTag("4")) {
            update()
        }
    }

    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
    }
}
