package com.company.tom.testapplication;

/**
 * Created by ddxv on 8/23/2014.
 */


import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;


public class PageAdapter extends PagerAdapter {


        public int getCount() {
            return 5;
        }




    public Object instantiateItem(View collection, int position) {
        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int resId = 0;
        switch (position) {
            case 0:
                resId = R.layout.activity_billionaire_comparison;
                break;
            case 1:
                resId = R.layout.activity_billionaire_comparison;
                break;
            case 2:
                resId = R.layout.activity_billionaire_comparison;
                break;
            case 3:
                resId = R.layout.activity_billionaire_comparison;
                break;
            case 4:
                resId = R.layout.activity_billionaire_comparison;
                break;
        }

        View view = inflater.inflate(resId, null);

        ((ViewPager) collection).addView(view, 0);
        return view;
    }



        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == ((View) arg1);
        }
/*
        @Override
        public Parcelable saveState() {
            return null;
        }*/
    }
