package com.example.brittanywoods.homework2;

/**
 * This is the dialog class for the dialog box that is displayed by the floating button in main.
 *
 * This class simply displays the dialog box.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomDialog extends Dialog {

    public CustomDialog(Context context){
        super(context);
    }

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.dialog);
    }
}
