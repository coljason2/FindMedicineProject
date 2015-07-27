package com.app.findmedicine.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.findmedicine.R;

import java.util.List;

/**
 * Created by JunChiChen on 2015/7/20.
 */
public class dataAdapter extends ArrayAdapter<data> {

    private int resource;

    private List<data> items;


    public dataAdapter(Context context, int resource, List<data> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.items = objects;
    }

    //set List object
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout itemView;
        final data item = getItem(position);
        if (convertView == null) {
            // create ListView compomant
            itemView = new RelativeLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource, itemView, true);
        } else {
            itemView = (RelativeLayout) convertView;
        }

        //  TextView txtNum = (TextView) itemView.findViewById(R.id.txtNum);
        TextView txtname = (TextView) itemView.findViewById(R.id.txtName);
        TextView txtprice = (TextView) itemView.findViewById(R.id.txtPrice);
        TextView txtcode = (TextView) itemView.findViewById(R.id.txtCode);
        TextView hidPrice = (TextView) itemView.findViewById(R.id.txtCodePrice);
        // txtNum.setText((position + 1) + "");
        txtname.setText(item.getName());
        txtprice.setText(item.getPrice());
        txtcode.setText(item.getCode());
        hidPrice.setText(item.getHidPrice());


        return itemView;
    }


}
