package com.example.project.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.project.R;
import com.example.project.ui.contracts.IAddFeedSourceDialogListener;

public class AddFeedSourceDialog extends DialogFragment {

    private IAddFeedSourceDialogListener mListener;

    private View mView;
    private EditText mEditText;

    static public AddFeedSourceDialog newInstance(IAddFeedSourceDialogListener listener) {
        AddFeedSourceDialog fragment = new AddFeedSourceDialog();

        // Ugly but hey! It works and it's 3:30am now
        fragment.mListener = listener;

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        mView = inflater.inflate(R.layout.dialog_add_feed, null);
        mEditText = mView.findViewById(R.id.dialog_add_feed_source_edittext);

        return new AlertDialog.Builder(getActivity())
            //.setIcon(R.drawable.ic_add) // <-- ugly as hell with this icon
            .setTitle(R.string.dialog_addfeedsource_title)
            .setView(mView)
            .setPositiveButton(R.string.dialog_addfeedsource_positifbutton, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    mListener.onPositiveClick(mEditText.getText().toString());
                }
            })
            .setNegativeButton(R.string.dialog_addfeedsource_negatifbutton, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    mListener.onNegativeClick();
                }
            })
            .setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    mListener.onCancelClick();
                }
            })
            .create();
    }
}
