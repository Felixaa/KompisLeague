package no.aardal.kompisleague.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import no.aardal.kompisleague.R;

/**
 * Created by chrisaardal on 27/10/15.
 */
public class AddFriendDialog extends AppCompatDialogFragment {

    public EditText summonerName;
    public EditText phoneNumber;


    public interface NoticeDialogListener {
        public void onPositiveDialogClick(AppCompatDialogFragment dialog, String summoner, String phoneNumber);
        public void onNegativeDialogClick(AppCompatDialogFragment dialog);
    }

    NoticeDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_addfriend, null);

        summonerName = (EditText)view.findViewById(R.id.addSummonerEdit);
        phoneNumber = (EditText)view.findViewById(R.id.addPhoneNumberEdit);

        builder.setView(view)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onPositiveDialogClick(AddFriendDialog.this,
                                getSummonerNameFromEditText(),
                                getPhoneNumberFromEditText());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onNegativeDialogClick(AddFriendDialog.this);
                    }
                })
                .setTitle("Add a summoner!");


        return builder.create();
    }

    public String getSummonerNameFromEditText() {
        return summonerName.getText().toString();
    }

    public String getPhoneNumberFromEditText() {
        return phoneNumber.getText().toString();
    }

}
