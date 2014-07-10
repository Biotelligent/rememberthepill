package com.wearable.remember;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class PickerDialog extends DialogFragment implements
		TimePickerDialog.OnTimeSetListener {

	InterfaceSetDate setDateCallback;

	public interface InterfaceSetDate {
		public void selectedDate(String date, int hour, int minute);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		setDateCallback = (InterfaceSetDate) getFragmentManager().findFragmentByTag("NewReminderDialog");
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		return new TimePickerDialog(getActivity(), this, hour, minute,
				DateFormat.is24HourFormat(getActivity()));
	}

	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		setDateCallback.selectedDate(String.valueOf(hourOfDay) + ":" + String.valueOf(minute), hourOfDay, minute);
	}
}