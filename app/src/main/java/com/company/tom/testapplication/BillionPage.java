package com.company.tom.testapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.Vector;

/**
 * Created by ddxv on 8/23/2014.
 */
public class BillionPage extends FragmentActivity{


    private PagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_layout);
        initialisePaging();
    }

    private void initialisePaging() {
        // TODO Auto-generated method stub
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this,BillionaireComparison.class.getName()));
        fragments.add(Fragment.instantiate(this,Fragment2.class.getName()));
        mPagerAdapter =new PageAdapter(this.getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(mPagerAdapter);

    }



}
