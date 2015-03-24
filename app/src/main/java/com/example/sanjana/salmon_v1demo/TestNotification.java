package com.example.sanjana.salmon_v1demo;

import android.app.FragmentManager;
import android.content.Intent;
import android.app.*;
import android.os.*;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.*;

public class TestNotification extends Activity {
    /** Stores the selected item's position */
    int position = 0;

    public static String choice1 = "";
    public static String choice2 = "";
    public static String choice3 = "";
    static String[] key = {
            "Not at all",
            "Somewhat Positive/Negative",
            "Very Positive/Negative"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        autoHandleDialogs();
    }

    public void autoHandleDialogs() {
        dialogFirst();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialogSecond();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogThird();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(getApplicationContext(), Unprompted.class));
                            }
                        }, 10000);
                    }
                }, 10000);
            }
        }, 10000);
    }

    public void dialogFirst() {
        /** Getting the fragment manager */
        FragmentManager manager = getFragmentManager();

        /** Instantiating the DialogFragment class */
        AfterCallPrompt_1 sb = new AfterCallPrompt_1();

        /** Creating the dialog fragment object, which will in turn open the alert dialog window */
        sb.show(manager, "alert_dialog_radio_button");
    }

    public void dialogSecond() {
        /** Getting the fragment manager */
        FragmentManager manager = getFragmentManager();

        /** Instantiating the DialogFragment class */
        SeekBarDialogFragment1 sb = new SeekBarDialogFragment1();

        /** Creating the dialog fragment object, which will in turn open the alert dialog window */
        sb.show(manager, "alert_dialog_seek_bar");

    }

    public void dialogThird() {
        /** Getting the fragment manager */
        FragmentManager manager = getFragmentManager();

        /** Instantiating the DialogFragment class */
        SeekBarDialogFragment2 sb = new SeekBarDialogFragment2();

        /** Creating the dialog fragment object, which will in turn open the alert dialog window */
        sb.show(manager, "alert_dialog_seek_bar2");

    }

    public static String getChoice() {

        return choice1 + ", " + choice2 + ", " + choice3;

    }

    public static void setChoice1(String choice1) {
        TestNotification.choice1 = choice1;
    }

    public static void setChoice2(int choice2) {

        TestNotification.choice2 = key[choice2];
    }

    public static void setChoice3(int choice3) {

        TestNotification.choice3 = key[choice3];
    }

    public static String getChoice1() {
        return choice1;
    }

    public static String getChoice2() {
        return choice2;
    }

    public static String getChoice3() {
        return choice3;
    }
}
