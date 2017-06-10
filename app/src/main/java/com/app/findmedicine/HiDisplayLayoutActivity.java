package com.app.findmedicine;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.findmedicine.comman.GenericActionActivity;
import com.app.findmedicine.entity.data;
import com.app.findmedicine.entity.dataAdapter;
import com.app.findmedicine.entity.dataHiAdapter;
import com.app.findmedicine.entity.getHiQuery;

import java.util.ArrayList;
import java.util.List;


public class HiDisplayLayoutActivity extends GenericActionActivity {
    static List<data> objects = new ArrayList<data>();
    String MedName;
    String MedCode;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_hicode_layout);
        Intent intent = this.getIntent();

        MedName = intent.getStringExtra("name");
        MedCode = intent.getStringExtra("code");

        new FindHiMedicine().execute();
    }

    private class FindHiMedicine extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(HiDisplayLayoutActivity.this);
            mProgressDialog.setTitle("查詢中");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            if (MedName != null && MedName.trim().length() > 0)
                objects = new getHiQuery().getHiMedicine(MedName);
            else if (MedCode != null && MedCode.trim().length() > 0)
                objects = new getHiQuery().getHiCode(MedCode);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mProgressDialog.dismiss();
            if (objects.isEmpty()) {
                new AlertDialog.Builder(HiDisplayLayoutActivity.this)
                        .setTitle(R.string.DialogTitle)
                        .setMessage(R.string.DialogNullMessage)
                        .setPositiveButton(R.string.DialogYes, null)
                        .setCancelable(false)
                        .show();
            } else {
                ListView list = (ListView) findViewById(R.id.listView2);
                dataHiAdapter Hiadapter =
                       new dataHiAdapter(HiDisplayLayoutActivity.this, R.layout.layout_himed, objects);
              list.setAdapter(Hiadapter);

            }
        }

    }


}

