package com.example.sanjana.salmon_v1demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class Unprompted extends Activity {
    private static final String TAG = "BroadcastTest";
    private Intent intent;
    ArrayList<Integer> callLog = new ArrayList<>();
    ArrayList<String> smsLog = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unprompted);
        Log.d("started", "Unprompted");
        intent = new Intent(this, CallData.class);
        startService(intent);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        startService(intent);
        registerReceiver(broadcastReceiver, new IntentFilter(CallData.BROADCAST_ACTION));
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
        stopService(intent);
    }

    private void updateUI(Intent intent) {
        callLog = intent.getIntegerArrayListExtra("callLog");
        smsLog = intent.getStringArrayListExtra("smsLog");

        TextView txt = (TextView) findViewById(R.id.textView2);
        txt.setText("Calls: " + callLog.toString() + "\n \n" + "Texts: " + smsLog.toString() + "\n \n" + "Choice: " + TestNotification.getChoice());
    }
}