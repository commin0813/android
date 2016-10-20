package com.commin.pro.commin_pig_1000;

/**
 * Created by user on 2016-10-13.
 */
public class ApplicationProperty {
    //common code

    public static final int SAVE_TYPE_NORMAR = 1;//일반적으로 하나씩 가상 입금 할때 저장
    public static final int SAVE_TYPE_TOTAL = 2;//가상입금된 금액을 은행에 입금 한 금액

    public static final int DEPOSIT_STATUS_NORMAL = 0;//
    public static final int DEPOSIT_STATUS_COMPLETE = 1;//

    public static final int DB_VERSION = 1002;
    public static final String DB_NAME = "monkey_deposit_database.db";
}
