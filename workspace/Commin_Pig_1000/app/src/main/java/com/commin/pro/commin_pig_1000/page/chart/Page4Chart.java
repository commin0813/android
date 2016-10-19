package com.commin.pro.commin_pig_1000.page.chart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.commin.pro.commin_pig_1000.ApplicationProperty;
import com.commin.pro.commin_pig_1000.R;
import com.commin.pro.commin_pig_1000.dao.DataBaseHelper;
import com.commin.pro.commin_pig_1000.model.Model4Chart;
import com.commin.pro.commin_pig_1000.page.calendar.Adapter4Calendar;
import com.commin.pro.commin_pig_1000.util.UtilDate;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Page4Chart extends AppCompatActivity {

    private Button btn_tv_imsi_deposit, btn_tv_confirm_deposit, btn_add_deposit,btn_menu;
    private EditText ed_deposit;
    private ListView lst_deposit;

    private Adapter4Chart adapter4Chart;

    private ArrayList<Model4Chart> items;
    private DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_layout);
        createGUI();

    }

    private void createGUI() {
        dataBaseHelper = new DataBaseHelper(this, ApplicationProperty.DB_NAME, null, ApplicationProperty.DB_VERSION);

        btn_tv_imsi_deposit = (Button) findViewById(R.id.btn_tv_imsi_deposit);
        btn_tv_confirm_deposit = (Button) findViewById(R.id.btn_tv_confirm_deposit);

        btn_menu = (Button)findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseHelper.deleteAll();
                queryList();
            }
        });
        btn_add_deposit = (Button) findViewById(R.id.btn_add_deposit);
        btn_add_deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = ed_deposit.getText().toString();
                if (value.equals("") || value.equals(" ")) {
                    return;
                }
                addValue(value);
            }
        });

        ed_deposit = (EditText) findViewById(R.id.ed_deposit);

        items = new ArrayList<Model4Chart>();

        lst_deposit = (ListView) findViewById(R.id.lst_deposit);


        adapter4Chart = new Adapter4Chart(this, R.layout.item_chart, items);
        lst_deposit.setAdapter(adapter4Chart);

        queryList();

    }

    private void queryList() {

        items.clear();
        ArrayList<Model4Chart> item = dataBaseHelper.getResult();
        if(item.size()==0){
            adapter4Chart.notifyDataSetChanged();
            return;
        }
        for(Model4Chart model4Chart : item){
            items.add(model4Chart);
        }

        adapter4Chart.notifyDataSetChanged();

    }

    private void addValue(String value) {
        dataBaseHelper.insert(value, UtilDate.format(new Date()), 1);
        queryList();
    }
}
