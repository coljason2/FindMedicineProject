package com.app.findmedicine;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.findmedicine.comman.GenericActionActivity;
import com.app.findmedicine.entity.data;
import com.app.findmedicine.entity.dataAdapter;
import com.app.findmedicine.entity.getQuery;

import java.util.ArrayList;
import java.util.List;


public class DisplayLayoutActivity extends GenericActionActivity {
    static List<data> objects = new ArrayList<data>();
    String MedName;
    String MedCode;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_layout);
        Intent intent = this.getIntent();

        MedName = intent.getStringExtra("name");
        MedCode = intent.getStringExtra("code");

        new FindMedicine().execute();
    }

    private class FindMedicine extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(DisplayLayoutActivity.this);
            mProgressDialog.setTitle("查詢中");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            if (MedName != null && MedName.trim().length() > 0)
                objects = new getQuery().getMedicine(MedName);
            else if (MedCode != null && MedCode.trim().length() > 0)
                objects = new getQuery().getCode(MedCode);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mProgressDialog.dismiss();
            if (objects.isEmpty()) {
                new AlertDialog.Builder(DisplayLayoutActivity.this)
                        .setTitle(R.string.DialogTitle)
                        .setMessage(R.string.DialogNullMessage)
                        .setPositiveButton(R.string.DialogYes, null)
                        .setCancelable(false)
                        .show();
            } else {
                ListView list = (ListView) findViewById(R.id.listView1);
                dataAdapter adapter =
                        new dataAdapter(DisplayLayoutActivity.this, R.layout.layout_med, objects);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        isConnectNetwork = connectCheck.isConnectingToInternet();
                        Intent detailintent = new Intent(DisplayLayoutActivity.this, Detail_activity.class);
                        data med = (data) parent.getAdapter().getItem(position);

                        if (isConnectNetwork) {
                            if (med.getCode().equals("無健保給付") ||
                                    med.getHidPrice().equals("") ||
                                    med.getCode().equals("生活用品")) {
                                new AlertDialog.Builder(DisplayLayoutActivity.this)
                                        .setTitle(R.string.DialogTitle)
                                        .setMessage(R.string.DialogNoHiCode)
                                        .setPositiveButton(R.string.DialogYes, null)
                                        .setCancelable(false)
                                        .show();
                            } else {
                                detailintent.putExtra("med", med);
                                startActivity(detailintent);
                            }
                        } else {
                            showNetWorkErrorMsg();
                        }
                    }
                });
                list.setAdapter(adapter);

            }
        }

    }


}

