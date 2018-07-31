package com.example.vst.apptest;

/**
 * Created by vst on 25/07/2018.
 */

public interface InterfaceTitle {

   public void setTitle(String title);
   public void setOnBackListener(OnBackListener onBackListener);

   interface OnBackListener {
      void doBack();
   }

}
