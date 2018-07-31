package com.example.vst.apptest.ServiceRetrofit;
/*
 * cung cấp đường dẫn ra
 *
 * */

public class APIUtils {

    // noi chua file duong dan toi folder goc
    public static final String BASEURL = "";

    /* goi phuong thuc tu interface de tra len cho service
       giup gui va nhan du lieu ve de chua trong interface DataClient

    */
    public static DataClient getData() {

        return RetrofitClient.getClient(BASEURL).create(DataClient.class);

    }


}
