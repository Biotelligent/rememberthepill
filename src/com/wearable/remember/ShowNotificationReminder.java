package com.wearable.remember;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;

public class ShowNotificationReminder extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		int notificationId = 001;
		// Build intent for notification content
		Intent viewIntent = new Intent(context, MainActivity.class);
		
		PendingIntent viewPendingIntent = PendingIntent.getActivity(context, 0, viewIntent, 0);

		NotificationCompat.Builder notificationBuilder =
		        new NotificationCompat.Builder(context)
		        .setSmallIcon(R.drawable.ic_pill)
		        .setContentTitle("time to pill")
		        //.setContentText(eventLocation)
		        .setContentIntent(viewPendingIntent);

		// Get an instance of the NotificationManager service
		NotificationManagerCompat notificationManager =
		        NotificationManagerCompat.from(context);

		//Build the notification and issues it with notification manager.
		notificationManager.notify(notificationId, notificationBuilder.build());
		
	}	
}
