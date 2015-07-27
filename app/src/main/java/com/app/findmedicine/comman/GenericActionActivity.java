package com.app.findmedicine.comman;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.app.findmedicine.R;

/**
 * Created by JunChiChen on 2015/7/27.
 */
public class GenericActionActivity extends ActionBarActivity {
    public static boolean isConnectNetwork = false;
    public static ConnectionDetector connectCheck;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        connectCheck = new ConnectionDetector(getApplicationContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.item_about:
                new AlertDialog
                        .Builder(this)
                        .setTitle(R.string.DialogAbout)
                        .setPositiveButton(R.string.DialogYes, null)
                        .setMessage(R.string.DialogAboutMsg)
                        .setCancelable(false)
                        .show();
                break;
            case R.id.item_version:
                new AlertDialog
                        .Builder(this)
                        .setTitle(R.string.DialogVers)
                        .setPositiveButton(R.string.DialogYes, null)
                        .setMessage(R.string.DialogAboutVers)
                        .setCancelable(false)
                        .show();
                break;
            case R.id.item_exit:
                finish();
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            ConfirmExit();
            {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //
    public void ConfirmExit() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle(R.string.DialogTitle);
        ad.setMessage(R.string.DialogExitMessage);
        ad.setPositiveButton(R.string.DialogYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                //exit app all
                ActivityCompat.finishAffinity(GenericActionActivity.this);
            }
        });
        ad.setNegativeButton(R.string.DialogNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                //do nothing
            }
        });
        ad.show();
    }

    public void showNetWorkErrorMsg() {
        new AlertDialog.Builder(this).
                setTitle(R.string.DialogTitle).
                setMessage(R.string.DialogNetMessage).
                setCancelable(false).
                setPositiveButton("OK", null).
                show();
    }
}
