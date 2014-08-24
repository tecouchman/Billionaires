package com.company.tom.testapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.tom.testapplication.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;



import java.io.Console;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Vector;


public class BillionaireComparison extends FragmentActivity implements ViewPager.OnPageChangeListener {




    TextView billionaireNameTextView;
    TextView billionaireWorthTextView;
    TextView billionaireAgeTextView;
    TextView userSalaryTextView;
    TextView billionaireDescTextView;
    ImageView billionaireImageView;
    ImageView foodImageView;

    TextView foodNameTextView;
    TextView foodSourceTextView;
    TextView foodCostTextView;

    private PagerAdapter mPagerAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Added to fix nullpointerexception, does not update anything
        LayoutInflater inflater =(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_billionaire_comparison, null);
        //test = (TextView)v.findViewById(R.id.txtPagerDate);



        //viewpager layout
        setContentView(R.layout.viewpager_layout);
        initialisePaging();

    }




    List<android.support.v4.app.Fragment> fragments;
    ViewPager pager;
    private void initialisePaging() {
        // TODO Auto-generated method stub
        fragments = new Vector<android.support.v4.app.Fragment>();
        fragments.add(android.support.v4.app.Fragment.instantiate(this, Fragment1.class.getName()));
        fragments.add(android.support.v4.app.Fragment.instantiate(this, Fragment1.class.getName()));
        fragments.add(android.support.v4.app.Fragment.instantiate(this, Fragment1.class.getName()));
        mPagerAdapter =new PageAdapter(this.getSupportFragmentManager(), fragments);

        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setPageTransformer(false, new ZoomOutPageTransformer());
        pager.setAdapter(mPagerAdapter);
        pager.setCurrentItem(1);
        pager.setOnPageChangeListener(this);
    }









//WHAT IS THIS BELOW? Adding and removing it seems to have no effect?
 /*  public static int getResId(String variableName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }*/



    //int focusedPage;
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        //focusedPage = position;

        //Log.d("","position: " + Integer.toString(position));
        fragments.add(android.support.v4.app.Fragment.instantiate(this, Fragment1.class.getName()));
        mPagerAdapter.notifyDataSetChanged();

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //pager.setCurrentItem(0,false);

    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.billionaire_comparison, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }





}
