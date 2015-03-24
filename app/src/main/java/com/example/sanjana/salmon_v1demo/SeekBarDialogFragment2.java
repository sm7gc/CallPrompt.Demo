package com.example.sanjana.salmon_v1demo;

/**
 * Created by Sanjana on 3/15/15.
 */
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.content.*;
import android.view.*;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.*;

public class SeekBarDialogFragment2 extends DialogFragment {
    public static SeekBarDialogFragment2 newInstance(String title) {
        SeekBarDialogFragment2 alertFragment = new SeekBarDialogFragment2();
        Bundle args = new Bundle();
        args.putString("title", title);
        alertFragment.setArguments(args);
        return alertFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View view = factory.inflate(R.layout.dialog_fragment_seekbar, null);

        final SeekBar seekbar = (SeekBar) view.findViewById(R.id.sbBar);
        seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                TestNotification.setChoice3(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final TextView t = (TextView) view.findViewById(R.id.textView);
        t.setText("To what extent did the phone call make you feel positive or negative?");
        //builder.setCustomTitle(t);
        //builder.setTitle("To what extent did the phone call make you feel positive or negative?");
        builder.setCancelable(true);
        builder.setView(view);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(TestNotification.getChoice3().equals("")) {
                    TestNotification.setChoice3(1);
                }
                dialog.dismiss();
            }
        });
        return builder.create();
    }
}
