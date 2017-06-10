package com.app.findmedicine.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.findmedicine.R;

import java.util.List;

/**
 * Created by JunChiChen on 2017/06/10.
 */
public class dataHiAdapter extends ArrayAdapter<data> {

    private int resource;

    private List<data> items;


    public dataHiAdapter(Context context, int resource, List<data> objects) {
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
        TextView txtname = (TextView) itemView.findViewById(R.id.txtHiName);
        TextView txtprice = (TextView) itemView.findViewById(R.id.txtHiPrice);
        TextView txtcode = (TextView) itemView.findViewById(R.id.txtHicode);
        TextView hidYear = (TextView) itemView.findViewById(R.id.txtEndYear);
        TextView hcompany = (TextView) itemView.findViewById(R.id.txtHCompany);
        // txtNum.setText((position + 1) + "");
        txtname.setText(item.getName());
        txtprice.setText(item.getHidPrice());
        txtcode.setText(item.getCode());
        hidYear.setText(item.getStartyear());
        hcompany.setText(item.getCompany());

        return itemView;
    }


}
