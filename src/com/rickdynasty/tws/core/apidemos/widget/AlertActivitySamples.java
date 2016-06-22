package com.rickdynasty.tws.core.apidemos.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.rickdynasty.tws.core.app.AlertActivity;
import com.rickdynasty.tws.core.app.AlertDialog;
import com.rickdynasty.tws.core.widget.Toast;

public class AlertActivitySamples extends AlertActivity implements DialogInterface.OnClickListener{

    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int[] buttonColors = {AlertDialog.BOTTOM_BUTTON_COLOR_BLUE, AlertDialog.BOTTOM_BUTTON_COLOR_BLACK};
        mContext = this;
        setBottomDialog(true);
        mAlertParams.mTitle = "title";
        mAlertParams.mMessage = "msg content";
        mAlertParams.mBottomButtonItems = new String[]{"ok","cancel"};
        mAlertParams.mBottomButtonOnClickListener = this;
        mAlertParams.mBottomButtonColorItems = buttonColors;
        setupAlert();
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case 0:
                Toast.makeText(mContext, "ok", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(mContext, "cancel", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
    

}
