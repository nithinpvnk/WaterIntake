package com.nithinkumar.water.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.listener.OnStateChangeListener;

import com.nithinkumar.water.Paths;
import com.nithinkumar.water.R;
import com.nithinkumar.water.WaterShared;

public class HomeFragment extends Fragment implements AddWaterDialogFragment.OnFragmentInteractionListener, OnStateChangeListener {
    private static final String ARG_LAYOUT_NUMBER = "Layout Number";
    WaterShared mShared;
    FloatingActionButton waterAdd;
    TextView targetValue;
    TextView currentValue;
    FillableLoader mFillableLoader;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(int layout) {
        HomeFragment fragment = new HomeFragment();
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
        View hmv = inflater.inflate(R.layout.fragment_home, container, false);
        mShared = new WaterShared(getContext());
        waterAdd = (FloatingActionButton) hmv.findViewById(R.id.add_water);
        targetValue = (TextView) hmv.findViewById(R.id.target_water_value);
        currentValue = (TextView) hmv.findViewById(R.id.current_water_value);
        mFillableLoader = (FillableLoader) hmv.findViewById(R.id.homeLoader);

        int tv = (mShared.getWeight_userValue() * 40);

        targetValue.setText(String.valueOf(tv));

        currentValue.setText(String.valueOf(mShared.getWaterCurrentValue_daily()));


//        int viewSize = getResources().getDimensionPixelSize(R.dimen.fourthSampleViewSize);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(viewSize, viewSize);
//        params.gravity = Gravity.CENTER;
//        FillableLoaderBuilder loaderBuilder = new FillableLoaderBuilder();
//        mFillableLoader = loaderBuilder.parentView((FrameLayout) hmv)
//                .svgPath(Paths.RONALDO)
//                .layoutParams(params)
//                .originalDimensions(1000, 1000)
//                .strokeColor(Color.parseColor("#1c9ade"))
//                .fillColor(Color.parseColor("#1c9ade"))
//                .strokeDrawingDuration(2000)
//                .clippingTransform(new WavesClippingTransform())
//                .fillDuration(10000)
//                .build();

        mFillableLoader.setSvgPath(Paths.RONALDO);
        mFillableLoader.setOnStateChangeListener(this);

        waterAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddWaterDialogFragment awdf = new AddWaterDialogFragment();
                awdf.setTargetFragment(getFragmentManager().findFragmentByTag("1"), 2);
                awdf.show(getFragmentManager(), "AddWater");
            }
        });
        return hmv;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFillableLoader.setSvgPath(Paths.RONALDO);
        mFillableLoader.setOnStateChangeListener(this);

    }

    @Override
    public void onWaterTakenFragmentInteraction(int wac) {
        Toast.makeText(getContext(), "Another Glass Drank!", Toast.LENGTH_LONG).show();
        mShared.setWaterAdded_current(wac);
        int val = mShared.getWaterAdded_current() + mShared.getWaterCurrentValue_daily();
        mShared.setWaterCurrentValue_daily(val);
        currentValue.setText(String.valueOf(val));

    }

    @Override
    public void onStateChange(int state) {
        try {
            Snackbar.make(waterAdd, "State changed callback: " + state, Snackbar.LENGTH_SHORT).show();
        } catch (NullPointerException e) {
            Log.d("showStateHint", e.toString());
        }
    }
}
