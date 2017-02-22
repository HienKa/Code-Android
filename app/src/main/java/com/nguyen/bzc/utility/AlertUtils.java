package com.nguyen.bzc.utility;

import android.app.AlertDialog;
import android.content.Context;

import com.nguyen.bzc.R;

/**
 * Created by Hien on 1/1/2016.
 */
public class AlertUtils {
    public static void showAlertDialog(Context context, String title, String message,
                                       Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        // Setting OK Button
        /*alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});*/

        // Showing Alert Message
        alertDialog.show();

    }

    /*public static void toast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }*/
}
