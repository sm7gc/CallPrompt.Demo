package com.example.sanjana.salmon_v1demo;

/**
 * Created by Sanjana on 3/9/15.
 */
import com.example.sanjana.salmon_v1demo.TestNotification;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.*;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.widget.RadioGroup.*;
import android.widget.TextView;

import junit.framework.Test;

public class AfterCallPrompt_1 extends DialogFragment{

    static String[] code = new String[]{
            "Immediate Family (Spouse, Parents, Children, Siblings)" ,
            "Extended Family" ,
            "Colleague/Work Connection" ,
            "Romantic Partner/Date" ,
            "Close Friend" ,
            "Acquaintance" ,
            "Classmate" ,
            "Roommate" ,
            "Stranger" ,
            "Other" //(with optional input)
    };

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View view = factory.inflate(R.layout.dialog_fragment_radiobutton, null);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final TextView t = (TextView) view.findViewById(R.id.textView3);
        t.setText("Who were you talking to on the phone? \n Select the closest category, or enter your answer in the space provided for optional input.");

        final RadioButton radioButton = (RadioButton) view.findViewById(R.id.radioButton);
        if (radioButton.isChecked()) {
            TestNotification.setChoice1(code[0]);
            Log.d("Checked", code[0]);
        }
        RadioButton radioButton2 = (RadioButton) view.findViewById(R.id.radioButton2);
        if (radioButton2.isChecked()) {
            TestNotification.setChoice1(code[1]);
            Log.d("Checked", code[1]);
        }
        RadioButton radioButton3 = (RadioButton) view.findViewById(R.id.radioButton3);
        if (radioButton3.isChecked()) {
            TestNotification.setChoice1(code[2]);
            Log.d("Checked", code[2]);
        }
        RadioButton radioButton4 = (RadioButton) view.findViewById(R.id.radioButton4);
        if (radioButton4.isChecked()) {
            TestNotification.setChoice1(code[3]);
            Log.d("Checked", code[3]);
        }
        RadioButton radioButton5 = (RadioButton) view.findViewById(R.id.radioButton5);
        if (radioButton5.isChecked()) {
            TestNotification.setChoice1(code[4]);
            Log.d("Checked", code[4]);
        }
        RadioButton radioButton6 = (RadioButton) view.findViewById(R.id.radioButton6);
        if (radioButton6.isChecked()) {
            TestNotification.setChoice1(code[5]);
            Log.d("Checked", code[5]);
        }
        RadioButton radioButton7 = (RadioButton) view.findViewById(R.id.radioButton7);
        if (radioButton7.isChecked()) {
            TestNotification.setChoice1(code[6]);
            Log.d("Checked", code[6]);
        }
        RadioButton radioButton8 = (RadioButton) view.findViewById(R.id.radioButton8);
        if (radioButton8.isChecked()) {
            TestNotification.setChoice1(code[7]);
            Log.d("Checked", code[7]);
        }
        RadioButton radioButton9 = (RadioButton) view.findViewById(R.id.radioButton9);
        if (radioButton9.isChecked()) {
            TestNotification.setChoice1(code[8]);
            Log.d("Checked", code[8]);
        }
        RadioButton radioButton10 = (RadioButton) view.findViewById(R.id.radioButton10);
        if (radioButton10.isChecked()) {
            EditText et = (EditText) view.findViewById(R.id.editText);
            TestNotification.setChoice1(et.getText().toString());
            Log.d("Checked", et.getText().toString());
        }

        builder.setCancelable(true);
        builder.setView(view);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        return builder.create();
    }
}