package com.example.sanjana.salmon_v1demo;

/**
 * Created by Sanjana on 3/9/15.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PhoneReceiver extends BroadcastReceiver {

    private static final String TAG = "PSBR";

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.d("started", "PhoneReceiver");
        TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE); //TelephonyManager object
        PhoneStateListener customPhoneListener = new PhoneStateListener(){
            private static final String TAG = "CPSL";
            private int prev_state;
            Intent intent;

            @Override
            public void onCallStateChanged(int state, String incomingNumber){

                switch(state){
                    case TelephonyManager.CALL_STATE_RINGING:
                        prev_state=state;
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        prev_state=state;
                        break;
                    case TelephonyManager.CALL_STATE_IDLE:
                        if((prev_state==TelephonyManager.CALL_STATE_OFFHOOK)){
                            prev_state=state;
                            //Answered Call which is ended
                            intent = new Intent(context, NotificationManager.class);
                        }
                        if((prev_state==TelephonyManager.CALL_STATE_RINGING)){
                            prev_state=state;
                            //Rejected or Missed call
                        }
                        break;

                }
            }
        };
        telephony.listen(customPhoneListener, PhoneStateListener.LISTEN_CALL_STATE); //Register our listener with TelephonyManager


    }

}
