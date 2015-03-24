package com.example.sanjana.salmon_v1demo;

/**
 * Created by Sanjana on 3/14/15.
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

public class SeekBarDialogFragment1 extends DialogFragment {
    public static SeekBarDialogFragment1 newInstance(String title) {
        SeekBarDialogFragment1 alertFragment = new SeekBarDialogFragment1();
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
                TestNotification.setChoice2(progress);
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
        t.setText("To what extent were your feelings positive/negative just before you received the call?");
        //builder.setCustomTitle(t);
        //builder.setTitle("To what extent were your feelings positive/negative just before you received the call?");
        builder.setCancelable(true);
        builder.setView(view);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(TestNotification.getChoice2().equals("")) {
                    TestNotification.setChoice2(1);
                }
                dialog.dismiss();
            }
        });
        return builder.create();
    }
}
