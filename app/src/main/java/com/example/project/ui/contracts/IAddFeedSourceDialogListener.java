package com.example.project.ui.contracts;

public interface IAddFeedSourceDialogListener {

    void onNegativeClick();
    void onPositiveClick(String newFeed);
    void onCancelClick();

}
