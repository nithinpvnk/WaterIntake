package com.nithinkumar.water.Fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nithinkumar.water.R;
import com.nithinkumar.water.WaterShared;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


/**
 * Created by nithinkumar on 7/21/17.
 */

public class AddWaterDialogFragment extends DialogFragment implements AdapterView.OnItemClickListener {

    AutoCompleteTextView addWaterValue;

    WaterShared mWaterVals;

    private OnFragmentInteractionListener mListener;

    int waterAdded_current;

    public AddWaterDialogFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) getTargetFragment();
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mWaterVals = new WaterShared(getContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View awv = inflater.inflate(R.layout.fragment_water_add_dialog, null);

        mListener = (OnFragmentInteractionListener) getTargetFragment();
        addWaterValue = (AutoCompleteTextView) awv.findViewById(R.id.added_water_value);

        String[] dataList = getResources().getStringArray(R.array.waterValues_array);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, dataList);
        addWaterValue.setAdapter(arrayAdapter);

        addWaterValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWaterValue.showDropDown();
            }
        });
        addWaterValue.setOnItemClickListener(this);

        builder.setView(awv)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        mListener.onWaterTakenFragmentInteraction(waterAdded_current);

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddWaterDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
        String tt = (parent.getItemAtPosition(position).toString());
        String[] t = tt.split(" ");
        waterAdded_current = Integer.valueOf(t[0]);

        addWaterValue.setText(String.valueOf(waterAdded_current));
    }

    public interface OnFragmentInteractionListener {
        void onWaterTakenFragmentInteraction(int waterAdded);
    }
}
