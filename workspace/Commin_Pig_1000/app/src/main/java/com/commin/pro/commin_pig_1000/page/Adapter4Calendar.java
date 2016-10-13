package com.commin.pro.commin_pig_1000.page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.commin.pro.commin_pig_1000.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by user on 2016-10-13.
 */
public class Adapter4Calendar extends BaseAdapter {
    private final List<String> list;

    private final LayoutInflater inflater;

    private Calendar mCal;
    private Context context;

    public Adapter4Calendar(Context context, List<String> list) {
        this.context = context;
        this.list = list;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override

    public String getItem(int position) {

        return list.get(position);

    }


    @Override

    public long getItemId(int position) {

        return position;

    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
            holder = new ViewHolder();
            holder.tvItemGridView = (TextView) convertView.findViewById(R.id.tv_item_gridview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvItemGridView.setText("" + getItem(position));
        //해당 날짜 텍스트 컬러,배경 변경
        mCal = Calendar.getInstance();
        //오늘 day 가져옴
        Integer today = mCal.get(Calendar.DAY_OF_MONTH);
        String sToday = String.valueOf(today);
        if (sToday.equals(getItem(position))) { //오늘 day 텍스트 컬러 변경
            holder.tvItemGridView.setTextColor(context.getResources().getColor(R.color.Color_BLACK));
        }
        return convertView;
    }


    private class ViewHolder {
        TextView tvItemGridView;
    }


}
