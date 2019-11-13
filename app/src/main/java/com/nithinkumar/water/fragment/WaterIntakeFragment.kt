package com.nithinkumar.water.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nithinkumar.water.ItemAdapter
import com.nithinkumar.water.R
import kotlinx.android.synthetic.main.fragment_water_intake.*
import kotlinx.android.synthetic.main.fragment_water_intake.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [WaterIntakeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WaterIntakeFragment : Fragment() {

    private val itemAdapter by lazy {
        ItemAdapter { position: Int, _: Item ->
            Toast.makeText(context, "Pos $position", Toast.LENGTH_LONG).show()
            item_list.smoothScrollToPosition(position)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_water_intake, container, false)

        view.item_list.initialize(itemAdapter)
        view.item_list.setViewsToChangeColor(listOf(R.id.list_item_background, R.id.list_item_text))
        itemAdapter.setItems(getLargeListOfItems())
        return view
    }

    private val possibleItems = listOf(
            Item("Airplanes", "first.json"),
            Item("Cars", "second.json"),
            Item("Food", "third.json"),
            Item("Gas", "fourth.json")
    )

    private fun getLargeListOfItems(): List<Item> {
        return possibleItems
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnSplashScreenFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnSplashScreenFragmentInteractionListener")
//        }
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment WaterIntakeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = WaterIntakeFragment()
    }
}

data class Item(
        val title: String,
        val icon: String
)
