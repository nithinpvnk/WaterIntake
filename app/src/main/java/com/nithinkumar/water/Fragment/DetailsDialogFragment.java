package com.nithinkumar.water.Fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nithinkumar.water.R;
import com.nithinkumar.water.WaterShared;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by nithinkumar on 7/21/17.
 */

public class DetailsDialogFragment extends DialogFragment {

    EditText userName;
    EditText userHeight;
    EditText userWeight;
    EditText startTime;
    EditText endTime;

    WaterShared mWaterVals;

    private OnFragmentInteractionListener mListener;


    Calendar calendar;
    private SimpleDateFormat timeFormat;
    private static final String TIME_PATTERN = "HH:mm";

    public DetailsDialogFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
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

        calendar = Calendar.getInstance();
        timeFormat = new SimpleDateFormat(TIME_PATTERN, Locale.getDefault());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dv = inflater.inflate(R.layout.fragment_dialog_box, null);

        mListener = (OnFragmentInteractionListener) getTargetFragment();

        userName = (EditText) dv.findViewById(R.id.user_name_value);
        userHeight = (EditText) dv.findViewById(R.id.height_user_value);
        userWeight = (EditText) dv.findViewById(R.id.weight_user_value);
        startTime = (EditText) dv.findViewById(R.id.start_time_value);
        endTime = (EditText) dv.findViewById(R.id.end_time_value);

        startTime.setText(timeFormat.format(calendar.getTime()));
        endTime.setText(timeFormat.format(calendar.getTime()));

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        startTime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.show();

            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        endTime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.show();

            }
        });

        builder.setView(dv)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        valuesSetting();
                        if (mWaterVals.getFirstRun_app().equals("Done")) {
                            mListener.onFragmentInteraction(getTargetFragment());
                        } else {
                            mWaterVals.setFirstRun_app("Done");
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DetailsDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    public void valuesSetting() {

        if (userName.getText().toString().equals(null)) {
            Toast.makeText(getContext(), "Please Enter UserName", Toast.LENGTH_LONG).show();
        } else {
            mWaterVals.setName_userValue(userName.getText().toString());
        }

        if (userHeight.getText().toString().equals(null)) {
            Toast.makeText(getContext(), "Please Enter UserHeight", Toast.LENGTH_LONG).show();
        } else {
            mWaterVals.setHeight_userValue(Integer.valueOf(userHeight.getText().toString()));
        }

        if (userWeight.getText().toString().equals(null)) {
            Toast.makeText(getContext(), "Please Enter UserWeight", Toast.LENGTH_LONG).show();
        } else {
            mWaterVals.setWeight_userValue(Integer.valueOf(userWeight.getText().toString()));
        }


        mWaterVals.setStartTime_userValue((startTime.getText().toString()));
        mWaterVals.setEndTime_userValue((endTime.getText().toString()));
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Fragment fragment);
    }
}
