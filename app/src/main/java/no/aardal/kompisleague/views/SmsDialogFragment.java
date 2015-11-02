package no.aardal.kompisleague.views;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Created by chrisaardal on 16/10/15.
 */
public class SmsDialogFragment extends AppCompatDialogFragment {

    private SmsDialogFragment self = this;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to message him?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "SEND SMS", Toast.LENGTH_SHORT).show();
                        sendSms(self.getArguments());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }


    private void sendSms(Bundle bundle) {
        String phoneNr = bundle.getString("phoneNr");

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNr, null, "Yo! Joine games eller? Sendt via KompisLeague", null, null);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Failed to send message", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
