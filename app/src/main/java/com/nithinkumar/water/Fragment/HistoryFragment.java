package com.nithinkumar.water.Fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nithinkumar.water.R;

public class HistoryFragment extends Fragment {
    private static final String ARG_LAYOUT_NUMBER = "Layout Number";

    public HistoryFragment() {
    }

    public static HistoryFragment newInstance(int layout) {
        HistoryFragment fragment = new HistoryFragment();
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
        View hv = inflater.inflate(R.layout.fragment_history, container, false);
        return hv;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
