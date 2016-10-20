package com.commin.pro.commin_pig_1000.page.chart;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.commin.pro.commin_pig_1000.ApplicationProperty;
import com.commin.pro.commin_pig_1000.R;
import com.commin.pro.commin_pig_1000.dao.DataBaseHelper;
import com.commin.pro.commin_pig_1000.model.Model4Chart;
import com.commin.pro.commin_pig_1000.util.UtilCalculate;
import com.commin.pro.commin_pig_1000.util.UtilDate;
import com.commin.pro.commin_pig_1000.util.UtilDialog;

import java.util.ArrayList;
import java.util.Date;

public class Page4Chart extends AppCompatActivity {

    private Button btn_tv_imsi_deposit, btn_tv_confirm_deposit, btn_add_deposit, btn_menu;
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
        btn_tv_imsi_deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMoney();
            }
        });
        btn_tv_confirm_deposit = (Button) findViewById(R.id.btn_tv_confirm_deposit);

        btn_menu = (Button) findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
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
                addValue(value, ApplicationProperty.SAVE_TYPE_NORMAR);
                ed_deposit.setText("");
            }
        });

        ed_deposit = (EditText) findViewById(R.id.ed_deposit);

        items = new ArrayList<Model4Chart>();

        lst_deposit = (ListView) findViewById(R.id.lst_deposit);
        lst_deposit.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                Model4Chart model = items.get(position);

                int type = model.getType();
                if (type == ApplicationProperty.SAVE_TYPE_TOTAL) {
                    UtilDialog.showToast(Page4Chart.this, "Coudn't delete");
                    return false;
                }

                final int id = model.getId();
                int status = model.getStatus();

                if (status == ApplicationProperty.DEPOSIT_STATUS_COMPLETE) {
                    UtilDialog.showToast(Page4Chart.this, "Aleady Clompleted..");
                    return false;
                }

                UtilDialog.openConfirm(Page4Chart.this, "DELETE?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete(id);
                        UtilDialog.showToast(Page4Chart.this, "Delete Clomplete");
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                return true;
            }
        });

        adapter4Chart = new Adapter4Chart(this, R.layout.item_chart, items);
        lst_deposit.setAdapter(adapter4Chart);

        queryList();

    }

    private void delete(int id) {

        dataBaseHelper.deleteByKeyValue(id);
        queryList();
    }

    private void menu() {
        dataBaseHelper.deleteAll();
        queryList();
    }

    private void saveMoney() {
        String str = btn_tv_imsi_deposit.getText().toString();

        if (str.equals("0") || Integer.parseInt(str) == 0) {
            return;
        }

        dataBaseHelper.insert_result(str, UtilDate.format(new Date()));
        addValue(str, ApplicationProperty.SAVE_TYPE_TOTAL);

    }


    private void queryList() {

        items.clear();
        ArrayList<Model4Chart> item = dataBaseHelper.getResult_normal();
        ArrayList<Model4Chart> item_total = dataBaseHelper.getResult_total();
        if (item.size() == 0) {
            btn_tv_imsi_deposit.setText("0");
            btn_tv_confirm_deposit.setText("0");
            adapter4Chart.notifyDataSetChanged();
            return;
        }

        String[] elements = new String[item.size()];
        int i = 0;
        for (Model4Chart model4Chart : item) {
            if (model4Chart.getType() != ApplicationProperty.SAVE_TYPE_TOTAL) {
                elements[i] = model4Chart.getDeposit_value();

            } else {
                elements[i] = "0";
                model4Chart.setDeposit_date("입금완료");
            }
            i++;

            items.add(model4Chart);
        }

        String value_result = UtilCalculate.StringAddition(elements);

        int sss = 0;
        for (Model4Chart model4Chart : item_total) {
            String value = model4Chart.getTotal_deposit_value();
            sss += Integer.parseInt(value);
        }

        String value_total = String.valueOf(sss);
        String result = UtilCalculate.StringSubtract(value_result, value_total);
        btn_tv_imsi_deposit.setText(result);
        btn_tv_confirm_deposit.setText(value_total);


        adapter4Chart.notifyDataSetChanged();

    }

    private void addValue(String value, int type) {

        if (value.length() >= 9) {
            //Dialog!!
            return;
        }
        if (type == ApplicationProperty.SAVE_TYPE_TOTAL) {
            dataBaseHelper.updateSTATUS(ApplicationProperty.DEPOSIT_STATUS_COMPLETE);
            dataBaseHelper.insert(value, UtilDate.format(new Date()), type);
        } else {
            dataBaseHelper.insert(value, UtilDate.format(new Date()), type);
        }
        queryList();
    }

}
