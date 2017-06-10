package com.app.findmedicine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.EditText;

import com.app.findmedicine.comman.GenericActionActivity;

import java.lang.reflect.Field;


public class MainActivity extends GenericActionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewConfiguration mconfig = ViewConfiguration.get(this);
        try {
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(mconfig, false);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        switch (item.getItemId()) {
//            case R.id.item_about:
//                new AlertDialog
//                        .Builder(this)
//                        .setTitle(R.string.DialogAbout)
//                        .setPositiveButton(R.string.DialogYes, null)
//                        .setMessage(R.string.DialogAboutMsg)
//                        .setCancelable(false)
//                        .show();
//                break;
//            case R.id.item_version:
//                new AlertDialog
//                        .Builder(this)
//                        .setTitle(R.string.DialogVers)
//                        .setPositiveButton(R.string.DialogYes, null)
//                        .setMessage(R.string.DialogAboutVers)
//                        .setCancelable(false)
//                        .show();
//                break;
//            case R.id.item_exit:
//                finish();
//                break;
//            default:
//                return true;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }

    public void sendSearch(View view) {
        isConnectNetwork = connectCheck.isConnectingToInternet();
        if (isConnectNetwork) {
            Intent display = new Intent(MainActivity.this, DisplayLayoutActivity.class);
            Intent Hidisplay = new Intent(MainActivity.this, HiDisplayLayoutActivity.class);
            EditText txtName = (EditText) findViewById(R.id.editText);
            EditText txtCode = (EditText) findViewById(R.id.txtCode);
            EditText hitxtName = (EditText) findViewById(R.id.HieditText);
            EditText HitxtCode = (EditText) findViewById(R.id.HitxtCode);

            Log.d("String txtName=", txtName.getText().toString());
            Log.d("String txtCode=", txtCode.getText().toString());
            Log.d("String hitxtName=", hitxtName.getText().toString());
            Log.d("String HitxtCode=", HitxtCode.getText().toString());

            if (txtName.getText().toString().trim().length() > 0 &&
                    txtCode.getText().toString().trim().length() == 0 &&
                    hitxtName.getText().toString().trim().length() == 0 &&
                    HitxtCode.getText().toString().trim().length() == 0) {
                action_type = 1;
            } else if (txtName.getText().toString().trim().length() == 0 &&
                    txtCode.getText().toString().trim().length() > 0 &&
                    hitxtName.getText().toString().trim().length() == 0 &&
                    HitxtCode.getText().toString().trim().length() == 0) {
                action_type = 2;
            } else if (txtName.getText().toString().trim().length() == 0 &&
                    txtCode.getText().toString().trim().length() == 0 &&
                    hitxtName.getText().toString().trim().length() > 0 &&
                    HitxtCode.getText().toString().trim().length() == 0) {
                action_type = 3;
            } else if (txtName.getText().toString().trim().length() == 0 &&
                    txtCode.getText().toString().trim().length() == 0 &&
                    hitxtName.getText().toString().trim().length() == 0 &&
                    HitxtCode.getText().toString().trim().length() > 0) {
                action_type = 4;
            } else if (txtName.getText().toString().trim().length() > 0 ||
                    txtCode.getText().toString().trim().length() > 0 ||
                    hitxtName.getText().toString().trim().length() > 0 ||
                    HitxtCode.getText().toString().trim().length() > 0) {
                action_type = 5;
            } else {
                action_type = 6;
            }
            Log.d("action_type =", action_type + "");

            if (action_type == 5) {
                new AlertDialog.Builder(MainActivity.this).
                        setTitle(R.string.DialogTitle).
                        setMessage(R.string.DialogWarn).
                        setPositiveButton("OK", null).
                        show();
            } else if (action_type == 6) {
                new AlertDialog.Builder(MainActivity.this).
                        setTitle(R.string.DialogTitle).
                        setMessage(R.string.DialogNoString).
                        setPositiveButton("OK", null).
                        show();
            } else if (action_type == 1) {
                display.putExtra("name", txtName.getText().toString());
                display.putExtra("code", "");
                startActivity(display);
            } else if (action_type == 2) {
                display.putExtra("name", "");
                display.putExtra("code", txtCode.getText().toString());
                startActivity(display);
            } else if (action_type == 3) {
                Hidisplay.putExtra("name", hitxtName.getText().toString());
                Hidisplay.putExtra("code", "");
                startActivity(Hidisplay);
            } else if (action_type == 4) {
                Hidisplay.putExtra("name", "");
                Hidisplay.putExtra("code", HitxtCode.getText().toString());
                startActivity(Hidisplay);
            }


        } else {
            showNetWorkErrorMsg();
        }
    }

}
