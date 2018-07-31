package com.example.vst.apptest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vst on 25/07/2018.
 */

public class Fragment_Dang_Ky extends Fragment implements InterfaceTitle {

    private EditText mEmail;
    private EditText mMatKhau;
    private EditText mNhapLaiMatKhau;
    private Button mDangNhap;
    private CheckBox mCheckBox;
    private Bundle mBundle;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment_Dang_Ky_Thanh_Cong mFragment_dang_ky_thanh_cong;
    private SharedPreferences mSharedPreferences;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonDangNhap;
    private RadioButton mRadioButtonDangKy;

    private static final String DATA_EMAIL = "email";
    private static final String DATA_MAT_KHAU_EMAIL = "matkhau_email";
    private static final String EDIT_EMAIL = "Editter_Email";
    private static final String EDIT_MAT_KHAU = "Editter_PassWord";
    private static final String EDIT_SAVE_MATKHAU = "Editter_Save_PassWord";
    private static final String EDIT_BOOLEAN = "Checked";
    private static final String EDIT_BOOLEAN_LOGIN = "isLogin";


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSharedPreferences = getActivity().getSharedPreferences("dataDangKy", Context.MODE_PRIVATE);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dang_ky, container, false);

        mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        mRadioButtonDangNhap = (RadioButton) view.findViewById(R.id.radio_dangnhap);
        mRadioButtonDangKy = (RadioButton) view.findViewById(R.id.radio_dangky);
        mEmail = (EditText) view.findViewById(R.id.email);
        mMatKhau = (EditText) view.findViewById(R.id.matkhau_email);
        mCheckBox = (CheckBox) view.findViewById(R.id.check_box);
        mNhapLaiMatKhau = (EditText) view.findViewById(R.id.nhap_lai_mk_email);
        mDangNhap = (Button) view.findViewById(R.id.dangnhap);

        mDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String soSanhEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String sosanhMk = "^" + "(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*[a-zA-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$)" + ".{6,}" + "$";
                String sosanhLaiMk = "^" + "(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*[a-zA-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$)" + ".{6,}" + "$";

                String inPutEmail = mEmail.getText().toString().trim();
                String inPutPassWord = mMatKhau.getText().toString().trim();
                String inPutCheckPassWord = mNhapLaiMatKhau.getText().toString().trim();

                Matcher matcherEmail = Pattern.compile(soSanhEmail).matcher(inPutEmail);
                Matcher matcherPassWord = Pattern.compile(sosanhMk).matcher(inPutPassWord);
                Matcher matcherCheckPassWord = Pattern.compile(sosanhLaiMk).matcher(inPutCheckPassWord);

                if (matcherEmail.matches() && matcherPassWord.matches() && matcherCheckPassWord.matches()) {

                    if (inPutPassWord.equals(inPutCheckPassWord)) {

                        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                        if (mCheckBox.isChecked()) {

                            mEditor.putString(EDIT_EMAIL, inPutEmail);
                            mEditor.putString(EDIT_MAT_KHAU, inPutPassWord);
                            mEditor.putString(EDIT_SAVE_MATKHAU, inPutCheckPassWord);
                            mEditor.putBoolean(EDIT_BOOLEAN, true);
                            mEditor.putBoolean(EDIT_BOOLEAN_LOGIN, true);
                            mEditor.commit();

                        } else {
                            SharedPreferences.Editor mEditorElse = mSharedPreferences.edit();
                            mEditorElse.remove(EDIT_EMAIL);
                            mEditorElse.remove(EDIT_MAT_KHAU);
                            mEditorElse.remove(EDIT_SAVE_MATKHAU);
                            mEditorElse.remove(EDIT_BOOLEAN);
                            mEditorElse.commit();

                        }

                        mBundle = new Bundle();
                        mBundle.putString(DATA_EMAIL, inPutEmail);
                        mBundle.putString(DATA_MAT_KHAU_EMAIL, inPutPassWord);
                        Log.d("LOG", inPutEmail);
                        Log.d("LOG", inPutPassWord);
                        mFragmentManager = getFragmentManager();
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragment_dang_ky_thanh_cong = new Fragment_Dang_Ky_Thanh_Cong();
                        mFragment_dang_ky_thanh_cong.setArguments(mBundle);
                        mFragmentTransaction.replace(R.id.frame_main, mFragment_dang_ky_thanh_cong);
                        mFragmentTransaction.addToBackStack(null);
                        mFragmentTransaction.commit();

                    }

                } else {

                    Toast.makeText(getActivity(), "Email không hợp lệ ", Toast.LENGTH_SHORT).show();
                }
                if (mMatKhau.getText().toString().equals("") && mNhapLaiMatKhau.getText().toString().equals("") && mEmail.getText().toString().equals("")) {
                    mMatKhau.setError("Bạn chưa nhập mật khẩu ");
                    mNhapLaiMatKhau.setError("Bạn chưa nhập lại mật khẩu");
                    mEmail.setError("Bạn chưa nhập email");
                }

            }
        });


        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){

                    case R.id.radio_dangky:
                        if(mRadioButtonDangKy.isChecked()){
                            mNhapLaiMatKhau.setVisibility(View.VISIBLE);

                        }else {
                            mNhapLaiMatKhau.setVisibility(View.INVISIBLE);

                        }

                        break;
                    case R.id.radio_dangnhap:
                        if(mRadioButtonDangNhap.isChecked()){
                            mNhapLaiMatKhau.setVisibility(View.INVISIBLE);

                        }else {
                            mNhapLaiMatKhau.setVisibility(View.VISIBLE);

                        }
                        break;

                }
            }
        });


        return view;
    }


    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setOnBackListener(OnBackListener onBackListener) {

    }
}
