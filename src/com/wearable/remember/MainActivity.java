package com.wearable.remember;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Alejandro Magnani
 * 
 */
public class MainActivity extends Activity implements
		SetReminderDialog.ConfirmDialogCompliant {

	public static ArrayList<String> reminders = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// TODO: get all the Reminders
	
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new RemindersListFragment()).commit();
			getFragmentManager().beginTransaction()
					.add(R.id.container, new ButtonReminderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void setReminder(View btn) {
		newReminderDialog();
	}

	void newReminderDialog() {

		android.app.FragmentTransaction ft = getFragmentManager()
				.beginTransaction();
		Fragment prev = getFragmentManager().findFragmentByTag(
				"NewReminderDialog");
		if (prev != null) {
			ft.remove(prev);
		}
		ft.addToBackStack(null);

		SetReminderDialog newReminder = new SetReminderDialog();
		newReminder.show(ft, "NewReminderDialog");
	}

	public static class ButtonReminderFragment extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.button_framgment,
					container, false);
			return rootView;
		}
	}

	@Override
	public void setReminder(String name, String time, int hour, int min) {
		DialogFragment setReminderDialog = (SetReminderDialog) getFragmentManager()
				.findFragmentByTag("NewReminderDialog");

		setReminderDialog.dismiss();
		schedulePillReminder(hour, min);

		reminders.add(name);
		RemindersListFragment.adapter.notifyDataSetChanged();
	}

	private void schedulePillReminder(int hour, int min) {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTimeInMillis(System.currentTimeMillis());
				
		AlarmManager alarmManager = (AlarmManager) getApplicationContext()
				.getSystemService(Context.ALARM_SERVICE);
		
		int id = (int) System.currentTimeMillis();
		
		Intent intent = new Intent(this, ShowNotificationReminder.class);
		
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				getApplicationContext(), id, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		
		alarmManager.setExact(AlarmManager.RTC_WAKEUP,
				calendar.getTimeInMillis() + 5000, pendingIntent);
				
		/*
		 * Calendar calendar = Calendar.getInstance();
		 * calendar.setTimeInMillis(System.currentTimeMillis());
		 * calendar.set(Calendar.HOUR_OF_DAY, hour);
		 * calendar.set(Calendar.MINUTE, min);
		 * 
		 * AlarmManager alarmManager = (AlarmManager)
		 * getSystemService(Context.ALARM_SERVICE);
		 * alarmManager.setExact(AlarmManager.RTC_WAKEUP,
		 * calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,
		 * viewPendingIntent);
		 */
	}

}