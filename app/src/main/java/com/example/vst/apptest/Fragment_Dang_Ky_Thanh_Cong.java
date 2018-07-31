package com.example.vst.apptest;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by vst on 25/07/2018.
 */

public class Fragment_Dang_Ky_Thanh_Cong extends Fragment implements InterfaceTitle {

    private TextView mEmail;
    private TextView mMatKhauEmail;
    private SharedPreferences mSharedPreferences;
    private Bundle mBundle;

    private static final String NAME_SHAREDPREFERRENCES = "dataDangKy";
    private static final String EDIT_EMAIL = "Editter_Email";
    private static final String EDIT_MAT_KHAU = "Editter_PassWord";
    private static final String DATA_EMAIL = "email";
    private static final String DATA_MAT_KHAU_EMAIL = "matkhau_email";




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        mSharedPreferences = getActivity().getSharedPreferences(NAME_SHAREDPREFERRENCES, Context.MODE_PRIVATE);
        String dataEmail = mSharedPreferences.getString(EDIT_EMAIL, "");
        String dataMatKhau = mSharedPreferences.getString(EDIT_MAT_KHAU, "");

        View view = inflater.inflate(R.layout.fragment_dang_ky_thanh_cong, container, false);
        mEmail = (TextView) view.findViewById(R.id.tv_email);
        mMatKhauEmail = (TextView) view.findViewById(R.id.tv_mk_email);

        mBundle = getArguments();

        if (mSharedPreferences != null) {
            mEmail.setText(dataEmail);
            mMatKhauEmail.setText(dataMatKhau);
        } else {

            mEmail.setText(mBundle.getString(DATA_EMAIL));
            mMatKhauEmail.setText(mBundle.getString(DATA_MAT_KHAU_EMAIL));
        }

        return view;
    }


    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setOnBackListener(OnBackListener onBackListener) {

    }
}
