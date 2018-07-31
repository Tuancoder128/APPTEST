package com.example.vst.apptest;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.vst.apptest.Fragment_Item_Drawer.Fragment_Item_AsyncTask;
import com.example.vst.apptest.Fragment_Item_Drawer.Fragment_Item_DangKy;
import com.example.vst.apptest.Fragment_Item_Drawer.Fragment_Item_DanhSachSanPham;
import com.example.vst.apptest.Fragment_Item_Drawer.Fragment_Item_Handler;
import com.example.vst.apptest.Fragment_Item_Drawer.Fragment_Item_ServiceBroadcastReceiver;
import com.example.vst.apptest.Fragment_Item_Drawer.Fragment_Item_TaoSanPham;
import com.example.vst.apptest.Fragment_Item_Drawer.Fragment_Item_Thread;
import com.example.vst.apptest.Fragment_Item_Drawer.Fragment_Item_ViewPager_TabLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Bundle mBundle;
    private SharedPreferences mSharedPreferences;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBar mActionBar;
    private NavigationView mNavigationView;


    private static final String NAME_SHAREDPREFERRENCES = "dataDangKy";
    private static final String EDIT_EMAIL = "Editter_Email";
    private static final String EDIT_MAT_KHAU = "Editter_PassWord";
    private static final String CHECK_DATA_NULL = "SharedPreferences null";
    private static final String CHECK_DATA_NO_NULL = "SharedPreferences != null";
    private static final String BUNDLE_EMAIL = "BundleEmail";
    private static final String BUNDLE_MKEMAIL = "SharedPreferences != null";

    private static final String FRAGMENT_ITEM_ASYNCTASK = "AsynTask";
    private static final String FRAGMENT_ITEM_DANGKY = "Dang Ky";
    private static final String FRAGMENT_ITEM_DS_SANPHAM = "Danh Sach San Pham";
    private static final String FRAGMENT_ITEM_HANDLER = "Handler";
    private static final String FRAGMENT_ITEM_SERVICE_BROADCAST = "Service BroadCast";
    private static final String FRAGMENT_ITEM_TAOSANPHAM = "Tao San Pham";
    private static final String FRAGMENT_ITEM_THREAD = " Thead";
    private static final String FRAGMENT_ITEM_VIEWPAGER_TABLAYOUT = "ViewPager TabLayout";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, R.string.open, R.string.close);
        drawerToggle.syncState();

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });


        mSharedPreferences = getSharedPreferences(NAME_SHAREDPREFERRENCES, Context.MODE_PRIVATE);
        String dataEmail = mSharedPreferences.getString(EDIT_EMAIL, "");
        String dataMatKhau = mSharedPreferences.getString(EDIT_MAT_KHAU, "");

        if (dataEmail != null && dataMatKhau != null) {
            Toast.makeText(this, CHECK_DATA_NO_NULL, Toast.LENGTH_SHORT).show();
            mBundle = new Bundle();
            mBundle.putString(BUNDLE_EMAIL, dataEmail);
            mBundle.putString(BUNDLE_MKEMAIL, dataMatKhau);
            mFragmentManager = getFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            Fragment_Dang_Ky_Thanh_Cong fragment_dang_ky_thanh_cong = new Fragment_Dang_Ky_Thanh_Cong();
            fragment_dang_ky_thanh_cong.setArguments(mBundle);
            mFragmentTransaction.replace(R.id.frame_main, fragment_dang_ky_thanh_cong);
            mFragmentTransaction.commit();
        } else {

            Toast.makeText(this, CHECK_DATA_NULL, Toast.LENGTH_SHORT).show();
            mFragmentManager = getFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            Fragment_Dang_Ky mFragment_dang_ky = new Fragment_Dang_Ky();
            mFragmentTransaction.replace(R.id.frame_main, mFragment_dang_ky);
            mFragmentTransaction.commit();
        }


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id) {
                    case R.id.dangky:

                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.frame_main, new Fragment_Dang_Ky());
                        mFragmentTransaction.commit();
                        getSupportActionBar().setTitle(FRAGMENT_ITEM_DANGKY);
                        mDrawerLayout.closeDrawer(GravityCompat.START);

                        break;
                    case R.id.async_task:

                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.frame_main, new Fragment_Item_AsyncTask());
                        mFragmentTransaction.commit();
                        getSupportActionBar().setTitle(FRAGMENT_ITEM_ASYNCTASK);
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.viewPager_tabLayout:

                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.frame_main, new Fragment_Item_ViewPager_TabLayout());
                        mFragmentTransaction.commit();
                        getSupportActionBar().setTitle(FRAGMENT_ITEM_VIEWPAGER_TABLAYOUT);
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.thread:

                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.frame_main, new Fragment_Item_Thread());
                        mFragmentTransaction.commit();
                        getSupportActionBar().setTitle(FRAGMENT_ITEM_THREAD);
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.taosanpham:

                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.frame_main, new Fragment_Item_TaoSanPham());
                        mFragmentTransaction.commit();
                        getSupportActionBar().setTitle(FRAGMENT_ITEM_TAOSANPHAM);
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.service_broadcast_receiver:

                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.frame_main, new Fragment_Item_ServiceBroadcastReceiver());
                        mFragmentTransaction.commit();
                        getSupportActionBar().setTitle(FRAGMENT_ITEM_SERVICE_BROADCAST);
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.handler:

                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.frame_main, new Fragment_Item_Handler());
                        mFragmentTransaction.commit();
                        getSupportActionBar().setTitle(FRAGMENT_ITEM_HANDLER);
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.danhsach_sanpham:

                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.frame_main, new Fragment_Item_DanhSachSanPham());
                        mFragmentTransaction.commit();
                        getSupportActionBar().setTitle(FRAGMENT_ITEM_DS_SANPHAM);
                        mDrawerLayout.closeDrawer(GravityCompat.START);

                        break;
                }

                return true;
            }
        });

    }

}