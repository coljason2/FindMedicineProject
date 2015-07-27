package com.app.findmedicine.entity;

import android.app.AlertDialog;
import android.util.Log;

import com.app.findmedicine.R;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JunChiChen on 2015/7/21.
 */
public class getQuery {
    private final String getdrug = "http://www.chahwa.com.tw/order.php?act=query&&drug=";
    private final String getcode = "http://www.chahwa.com.tw/order.php?act=query&&hid=";
    private static final Pattern reUnicode = Pattern.compile("\\\\u([0-9a-zA-Z]{4})");
    private List<data> objects = new ArrayList<data>();
    private form form = new form();

    public List<data> getMedicine(String name) throws NullPointerException {
        String cookie = getCookies();
        String parseString = decode(
                Getcontext(name, cookie, getdrug).replace("\\//", "")).replace("\\", "")
                .replace("}", "").replace("{", "").replace("rn", "");

        Document resault = Jsoup.parse(parseString);

        for (Element n : resault.getElementsByClass("item_text")) {
            data m = new data();
            m.setPrice(n.getElementsByClass("sell_price").text());
            m.setName(n.getElementsByClass("name").text());
            m.setCode(n.getElementsByClass("code").text());
            m.setHidPrice(n.getElementsByClass("price").text());
            objects.add(m);
        }
        return objects;
    }

    public List<data> getCode(String code) throws NullPointerException {
        String cookie = getCookies();
        String parseString = decode(
                Getcontext(code, cookie, getcode).replace("\\//", "")).replace("\\", "")
                .replace("}", "").replace("{", "").replace("rn", "");

        Document resault = Jsoup.parse(parseString);

        for (Element n : resault.getElementsByClass("item_text")) {
            data m = new data();
            m.setPrice(n.getElementsByClass("sell_price").text());
            m.setName(n.getElementsByClass("name").text());
            m.setCode(n.getElementsByClass("code").text());
            m.setHidPrice(n.getElementsByClass("price").text());
            objects.add(m);
        }
        return objects;
    }

    public String Getcontext(String name, String cookiePara, String url) {
        String context = "";
        try {

            URL getUrl = new URL(url + URLEncoder.encode(name, "utf-8"));
            HttpURLConnection connection = (HttpURLConnection) getUrl
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setReadTimeout(3000);
            connection.addRequestProperty("Cookie", cookiePara);
            connection.connect();
            //log.info("Response code:" + connection.getResponseCode());
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                context = context + decode(lines);
            }
            reader.close();
            connection.disconnect();
        } catch (Exception e) {
            Getcontext(name, cookiePara, url);
            e.printStackTrace();
        }
        return context;
    }

    public String getCookies()  {
        Map<String, String> cookies = null;
        String cookiePara = "";
        try {
            Connection.Response res = Jsoup
                    .connect("https://www.chahwa.com.tw/user.php")
                    .data("username", form.getUsername(), "password",
                            form.getPassword(), "wsrc", form.getWsrc(), "act",
                            form.getAct(), "back_act", form.getBack_act())
                    .method(Connection.Method.POST).execute();
            Log.d("Connect",res.toString());
            cookies = res.cookies();
        } catch (Exception e) {
            e.printStackTrace();
            getCookies();
        }
        Log.d("Cookie", cookies.toString());
        for (String cookie : cookies.keySet()) {
            cookiePara = cookiePara + cookie.toString() + "="
                    + cookies.get(cookie) + ";";
        }

        return cookiePara;
    }

    //decode utf-8
    public String decode(String s) {

        Matcher m = reUnicode.matcher(s);
        StringBuffer sb = new StringBuffer(s.length());
        while (m.find()) {
            m.appendReplacement(sb,
                    Character.toString((char) Integer.parseInt(m.group(1), 16)));
        }
        m.appendTail(sb);
        return sb.toString();
    }
}

