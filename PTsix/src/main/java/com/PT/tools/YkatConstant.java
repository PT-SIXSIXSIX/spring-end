package com.PT.tools;

public class YkatConstant {

    public static final String telePhoneRegex = "^1[3|4|5|7|8][0-9]\\d{4,8}$";
    public static final String phoneRegex =  "^(\\d{3,4}-)?\\d{6,8}$";
    /**
     * 保证金大于这个的时候 表示充足
     *
     */
    public static final Integer ENOUGH_DEPOSIT= 2000;

    public static final Integer ENOUGH_DEPOSIT_STATE = 1;

    public static final Integer SHORT_OF_DEPOSIT_STATE = 0;

    public static final Integer DELETE_DEPOSIT_STATE = 2;

    public static final Integer ORDER_STATE_IDLE = 0;

    public static final Integer ORDER_STATE_ACCEPT = 1;

    public static final Integer ORDER_STATE_REFUSE = -1;

    public static final Integer ORDER_STATE_DELETE = 2;

    public static final Integer SETTEL_RECORD_STATE_IDLE = 1;

    public static final Integer SETTEL_RECORD_STATE_ACCEPT = 2;

    public static final Integer SETTEL_RECORD_STATE_DELETE = 3;

    public static final Integer SETTEL_RECORD_STATE_GURANTEE = 0;

    public static final Integer DRIVER_STATUS_PROCESSED = 0;

    public static final Integer DRIVER_STATUS_UNPROCESSED = 1;

    public static final Integer USER_ROLE_STAFF=1;

    public static final Integer USER_ROLE_MANAGER = 0;


}

