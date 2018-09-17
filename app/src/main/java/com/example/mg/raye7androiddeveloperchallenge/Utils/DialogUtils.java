package com.example.mg.raye7androiddeveloperchallenge.Utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.mg.raye7androiddeveloperchallenge.R;


public class DialogUtils {


    private final Context context;

    public DialogUtils(Context context) {
        this.context = context;
    }

    public ProgressDialog showLoadingDialog() {
        ProgressDialog progressDialog = new ProgressDialog(context, R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

}