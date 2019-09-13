package com.nithinkumar.water.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nithinkumar.water.R;
import com.nithinkumar.water.WaterShared;


public class SettingsFragment extends Fragment implements DetailsDialogFragment.OnFragmentInteractionListener {

    private static final String ARG_LAYOUT_NUMBER = "Layout Number";

    ImageView editVals;
    TextView userName;
    TextView userHeight;
    TextView userWeight;
    TextView startTime;
    TextView endTime;

    CardView rateus;
    CardView shareWith;

    WaterShared mWaterShared;


    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance(int layout) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_NUMBER, layout);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View sv = inflater.inflate(R.layout.fragment_settings, container, false);
        //Shared Preference
        mWaterShared = new WaterShared(getContext());
        //Assigning the xml values
        editVals = (ImageView) sv.findViewById(R.id.edit_value);
        userName = (TextView) sv.findViewById(R.id.name_value);
        userHeight = (TextView) sv.findViewById(R.id.height_value);
        userWeight = (TextView) sv.findViewById(R.id.weight_value);
        startTime = (TextView) sv.findViewById(R.id.start_value);
        endTime = (TextView) sv.findViewById(R.id.end_value);

        rateus = (CardView) sv.findViewById(R.id.rate_us);
        shareWith = (CardView) sv.findViewById(R.id.share_with);

        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateUs();
            }
        });

        shareWith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareWithFriend();
            }
        });

        editVals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsDialogFragment ddf = new DetailsDialogFragment();
                ddf.setTargetFragment(getFragmentManager().findFragmentByTag("3"), 3);
                ddf.show(getFragmentManager(), "dialog");
            }
        });

        update();

        return sv;
    }


    public void update() {
        userName.setText(mWaterShared.getName_userValue());
        userHeight.setText(String.valueOf(mWaterShared.getHeight_userValue()));
        userWeight.setText(String.valueOf(mWaterShared.getWeight_userValue()));
        startTime.setText(mWaterShared.getStartTime_userValue());
        endTime.setText(mWaterShared.getEndTime_userValue());
    }

    public void rateUs() {
        Uri uri = Uri.parse("market://details?id=" + "com.nithinkumar.water");
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + "com.nithinkumar.water")));
        }
    }

    public void shareWithFriend() {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Keep Track of your Hydration");
            String name = mWaterShared.getName_userValue();
            String sAux = "\n" + name + "wants to help you know your Water input with the help of this application.\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=com.nithinkumar.water \n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    // TODO: Update clearAll Method with db access

    public void clearAll() {

    }

    @Override
    public void onFragmentInteraction(Fragment frag) {
        if(frag.equals(getFragmentManager().findFragmentByTag("3")))
        {
            update();
        }

    }

}
