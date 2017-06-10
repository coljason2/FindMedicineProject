package com.app.findmedicine.entity;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by JunChiChen on 2017/6/9.
 */

public class getHiQuery {
    private Calendar cal = Calendar.getInstance();
    private final String urlcode = "http://www.nhi.gov.tw/Query/query1_list.aspx?Q1ID=";
    private final String urlname = "http://www.nhi.gov.tw/Query/query1_list.aspx?NameChinese=";
    private final String time = "&StartYYY=" + (cal.get(Calendar.YEAR)-1911);
    private List<data> objects = new ArrayList<data>();
    int index = 0;
    public List<data> getHiMedicine(String name) throws NullPointerException {
        try {
            Document doc = Jsoup.connect(urlname + name + time).get();
           // Log.d("getHiMedicine=",doc.toString());
            for(Element n : doc.getElementsByClass("GridView")) {
                data m = new data();
                Log.d("Element = ", n.toString());
                m.setName(n.getElementById("gvQuery1Data_lblNameChinese_" + index).text());
                m.setHidPrice("健保價："+n.getElementById("gvQuery1Data_lblPrice_" + index).text());
                m.setStartyear("健保碼："+n.getElementById("gvQuery1Data_lblstartEndDate_" + index).text());
                m.setCode("期間"+n.getElementById("gvQuery1Data_hlID_" + index).text());
                objects.add(m);
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return objects;
    }

    public List<data> getHiCode(String code) throws NullPointerException {
        try {
            Document doc = Jsoup.connect(urlcode + code + time).get();
           // Log.d("getHiCode=",doc.toString());
            for(Element n : doc.getElementsByClass("GridView")) {
                data m = new data();
                Log.d("Element = ", n.toString());
                m.setName(n.getElementById("gvQuery1Data_lblNameChinese_" + index).text());
                m.setHidPrice("健保價："+n.getElementById("gvQuery1Data_lblPrice_" + index).text());
                m.setStartyear("健保碼："+n.getElementById("gvQuery1Data_lblstartEndDate_" + index).text());
                m.setCode("期間"+n.getElementById("gvQuery1Data_hlID_" + index).text());
                objects.add(m);
                index++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return objects;
    }
}