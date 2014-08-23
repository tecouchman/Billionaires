package com.company.tom.testapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class MyActivity extends Activity implements View.OnClickListener {


    //TextView welcomeTextView;
    Button mainButton;
    EditText salaryEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //welcomeTextView = (TextView) findViewById(R.id.welcome_textview);
        //welcomeTextView.setText("Set in Java!");

        // find the salary textbox
        salaryEditText = (EditText) findViewById(R.id.salary_edittext);

        // find button and set up click listener
        mainButton = (Button) findViewById(R.id.main_button);
        mainButton.setOnClickListener(this);
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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

    @Override
    public void onClick(View view) {

        // if no salary entered, display an alert dialog
        if (salaryEditText.getText().toString().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("")
                    .setTitle("Please enter a salary")
                    .setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();

        }
       else {
           Intent billionaireComparison = new Intent(this, BillionaireComparison.class);
            Double salary = Double.parseDouble(salaryEditText.getText().toString());
            billionaireComparison.putExtra("salary", salary);
            startActivity(billionaireComparison);

        }
    }



}
