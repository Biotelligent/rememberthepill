package com.wearable.remember;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class SetReminderDialog extends DialogFragment implements PickerDialog.InterfaceSetDate {
	
	ConfirmDialogCompliant caller;
	
	private int hour;
	
	private int min;
	
	public interface ConfirmDialogCompliant {
        public void setReminder(String name, String days, int hour, int min);
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_dialog, container,
				false);

		Button timePickerButton = (Button)v.findViewById(R.id.btnTimePicker);
		timePickerButton.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	DialogFragment timePicker = new PickerDialog();
	        	timePicker.show(getFragmentManager(), "timePicker");
	        }
	    });
	    
	    Button saveReminderButton = (Button)v.findViewById(R.id.btnSaveReminder);
	    saveReminderButton.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	caller.setReminder("test", "some-time", hour, min);
	        }
	    });
	    
		return v;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			caller = (ConfirmDialogCompliant) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
	}
	
	@Override
	public void selectedDate(String date, int hour, int min) {
		EditText tv = (EditText)getView().findViewById(R.id.etDATE);
		tv.setText(date);	
		this.hour = hour;
		this.min = min;
	}


}