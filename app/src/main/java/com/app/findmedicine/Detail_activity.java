package com.app.findmedicine;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.app.findmedicine.comman.GenericActionActivity;
import com.app.findmedicine.entity.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Text;

import java.io.IOException;


public class Detail_activity extends GenericActionActivity {
    private final String url = "http://www.nhi.gov.tw/Query/query1_list.aspx?Q1ID=";
    private final String mainurl = "http://www.fda.gov.tw/MLMS/H0001D.aspx?Type=Lic&LicId=";
    private ProgressDialog mProgressDialog;
    private static String HidCode = "";
    private static String HidPrice = "";
    private static String LicId = "";
    private static Document doc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_layout);
        //get Object
        Intent intent = getIntent();
        data med = (data) intent.getSerializableExtra("med");
        HidCode = med.getCode();
        HidPrice = med.getHidPrice();
        new findDetail().execute();
    }

    private class findDetail extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(Detail_activity.this);
            mProgressDialog.setTitle(R.string.DialogLoading);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                loadMsg();
            } catch (Exception ex) {
                ex.printStackTrace();
                loadMsg();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mProgressDialog.dismiss();
            //get textview
            TextView upDate = (TextView) findViewById(R.id.txtIsChange);
            TextView FromTo = (TextView) findViewById(R.id.txtFromTo);
            TextView Company = (TextView) findViewById(R.id.txtCompany);
            TextView eName = (TextView) findViewById(R.id.txtEName);
            TextView Name = (TextView) findViewById(R.id.txtMedName);
            TextView Code = (TextView) findViewById(R.id.txtMedCode);
            TextView Hprice = (TextView) findViewById(R.id.txtHprice);
            TextView IndiCat = (TextView) findViewById(R.id.txtIndiCat);
            //set textview
            upDate.setText(doc.getElementById("lblGiDate").text());
            FromTo.setText(doc.getElementById("lblEfDate").text());
            Company.setText(doc.getElementById("lblApp").text());
            eName.setText(doc.getElementById("lblEnName").text());
            Name.setText(doc.getElementById("lblChName").text());
            Code.setText(HidCode);
            Hprice.setText(HidPrice);
            IndiCat.setText(doc.getElementById("lblIndiCat").text());


        }

        protected void loadMsg() {
            try {
                doc = Jsoup.connect(url + HidCode).get();
                LicId = doc.getElementById("gvQuery1Data_ctl02_lblQ1ID").getElementsByTag("a").toString().substring(67, 75);
                doc = Jsoup.connect(mainurl + LicId).get();
                // Log.e("Doc", doc.toString());
            } catch (IOException e) {
                loadMsg();
                e.printStackTrace();
            }
        }
    }
}
