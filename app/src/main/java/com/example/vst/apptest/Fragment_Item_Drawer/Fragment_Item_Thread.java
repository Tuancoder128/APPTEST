package com.example.vst.apptest.Fragment_Item_Drawer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vst.apptest.R;

/**
 * Created by vst on 28/07/2018.
 */

public class Fragment_Item_Thread extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_thread,container,false);
        return view;
    }
}
