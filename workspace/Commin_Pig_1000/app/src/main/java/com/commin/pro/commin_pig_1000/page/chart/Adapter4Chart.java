package com.commin.pro.commin_pig_1000.page.chart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.commin.pro.commin_pig_1000.R;
import com.commin.pro.commin_pig_1000.model.Model4Chart;

import java.util.ArrayList;

/**
 * Created by user on 2016-10-19.
 */
public class Adapter4Chart extends ArrayAdapter<Model4Chart> {

    private Page4Chart context;

    private int resource;

    private ArrayList<Model4Chart> items;

    public Adapter4Chart(Page4Chart context, int resource, ArrayList<Model4Chart> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
        this.items = items;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);
        }
        Model4Chart model = items.get(position);
        final TextView tv_item_value = (TextView) view.findViewById(R.id.tv_item_value);
        tv_item_value.setText(model.getDeposit_value());

        final TextView tv_item_date = (TextView) view.findViewById(R.id.tv_item_date);
        tv_item_date.setText(model.getDeposit_date());
        return view;
    }
}
