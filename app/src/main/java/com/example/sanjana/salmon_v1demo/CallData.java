package com.example.sanjana.salmon_v1demo;

/**
 * Created by Sanjana on 3/11/15.
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.IBinder;
import android.provider.CallLog;
import android.provider.Telephony;
import android.util.Log;

public class CallData extends Service {
    int mStartMode;
    private static final String TAG = "BroadcastService";
    public static final String BROADCAST_ACTION = "com.example.sanjana.salmon_v1demo";
    private final Handler handler = new Handler();
    Intent intent;
    ArrayList<Integer> callLog = new ArrayList<>();
    ArrayList<Long> smsLong = new ArrayList<>();
    ArrayList<String> smsLog = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        intent = new Intent(BROADCAST_ACTION);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("started", "CallData");
        handler.removeCallbacks(sendUpdatesToUI);
        handler.postDelayed(sendUpdatesToUI, 1000); // 1 second
        return mStartMode;
    }

    public void getSMSDetails() {
        /*Query Call Log Content Provider*/
        Cursor smsLogCursor = getContentResolver().query(	android.provider.Telephony.Sms.CONTENT_URI, null, null, null, 	android.provider.Telephony.Sms.DEFAULT_SORT_ORDER);

	    /*Check if cursor is not null*/
        if (smsLogCursor != null) {

	    /*Loop through the cursor*/
            while (smsLogCursor.moveToNext()) {

		/*Get Time information*/
                long dateMillis = smsLogCursor.getLong(smsLogCursor.getColumnIndex(Telephony.Sms.DATE));
                smsLong.add(dateMillis);
            }

        }
        ArrayList<Calendar> calendars = formatSMSLog(smsLong);
        for (Calendar c: calendars) {
            StringBuilder s = new StringBuilder();
            int correctMonth = c.get(Calendar.MONTH) +1;
            s.append(correctMonth + "/" +
                    c.get(Calendar.DAY_OF_MONTH) + "/" +
                    c.get(Calendar.YEAR) + " " +
                    c.get(Calendar.HOUR) + ":" +
                    c.get(Calendar.MINUTE));
            smsLog.add(s.toString());
        }
    }

    private ArrayList<Calendar> formatSMSLog(ArrayList<Long> raw) {
        ArrayList<Calendar> formatted = new ArrayList<>();
        for(long i: raw) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(i);
            formatted.add(c);
        }
        return formatted;
    }

    public void readCallLog() {
        /*Query Call Log Content Provider*/
        Cursor callLogCursor = getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI, null, null, null, android.provider.CallLog.Calls.DEFAULT_SORT_ORDER);

	    /*Check if cursor is not null*/
        if (callLogCursor != null) {

	    /*Loop through the cursor*/
            while (callLogCursor.moveToNext()) {

		/*Get Time information*/
                long durationMillis = callLogCursor.getLong(callLogCursor.getColumnIndex(CallLog.Calls.DURATION));
                ;
                callLog.add((int) durationMillis);
            }

        }

    }

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            DisplayLoggingInfo();
            stopSelf();
        }
    };

    private void DisplayLoggingInfo() {
        readCallLog();
        getSMSDetails();

        intent.putIntegerArrayListExtra("callLog", callLog);
        intent.putStringArrayListExtra("smsLog", smsLog);
        sendBroadcast(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(sendUpdatesToUI);
        super.onDestroy();
    }
}