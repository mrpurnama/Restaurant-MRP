package com.restaurant.mrp_1147050103.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtils {
    public static ProgressDialog progressDialog;
    public static void openDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading . . . ");
        progressDialog.show();
    }
    public static void closeDialog() {
        progressDialog.dismiss();
    }
}

