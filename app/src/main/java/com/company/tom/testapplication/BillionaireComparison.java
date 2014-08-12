package com.company.tom.testapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.tom.testapplication.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.Console;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class BillionaireComparison extends Activity  {




    TextView billionaireNameTextView;
    TextView billionaireWorthTextView;
    TextView billionaireAgeTextView;
    TextView userSalaryTextView;
    ImageView billionaireImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billionaire_comparison);

        billionaireAgeTextView = (TextView) findViewById(R.id.billionaire_age_textview);
        billionaireWorthTextView = (TextView) findViewById(R.id.billionaire_worth_textview);
        billionaireNameTextView = (TextView) findViewById(R.id.billionaire_name_textview);
        billionaireImageView = (ImageView) findViewById(R.id.billionaire_imageview);
        userSalaryTextView = (TextView) findViewById(R.id.user_salary_textview);
        showRandomBillionaire();
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void showRandomBillionaire() {

        ArrayList<Billionaire> items = new ArrayList<Billionaire>();

        try {

            XmlPullParser xpp=getResources().getXml(R.xml.billionairesfix);



            while (xpp.getEventType()!=XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType()==XmlPullParser.START_TAG) {

                    Billionaire billionaire = new Billionaire();


                    String name = xpp.getName();

                       String personID = "";
                    if (name.equals("person")) {
                        personID = xpp.getAttributeValue(0);
                    }

                    while (xpp.next() != XmlPullParser.END_TAG) {
                        if (xpp.getEventType() != XmlPullParser.START_TAG) {

                            continue;
                        }
                        billionaire.ID = personID;

                        name = xpp.getName();

                        if (name.equals("b_name")) {
                            billionaire.name = readText(xpp);
                        } else if (name.equals("b_wealth")) {
                            billionaire.worth = readText(xpp);
                        } else if (name.equals("b_age")) {
                            billionaire.age = readText(xpp);
                        } else if (name.equals("b_rank")) {
                            billionaire.rank = readText(xpp);
                        } else if (name.equals("b_source")) {
                            billionaire.source = readText(xpp);
                        } else if (name.equals("b_country")) {
                            billionaire.country = readText(xpp);
                        }
                    }

                    items.add(billionaire);

                }

                xpp.next();

            }

        }
        catch (Exception e) {
            // If an exception is thrown while the xml is being parsed then the billionaire data
            // can't be displayed so display an error message and return to previous screen.
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please try again.")
                    .setTitle("Something went wrong")
                    .setPositiveButton("OK", null)
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }


        Random rand = new Random();
        int randomNum = rand.nextInt(items.size());
        Billionaire randomBillionaire = items.get(randomNum);

        billionaireAgeTextView.setText(randomBillionaire.ID + "Years Old");
        billionaireWorthTextView.setText("$" + randomBillionaire.worth + " Billion");
        billionaireNameTextView.setText(randomBillionaire.name);

        // Determine the resource name of the relevant image and get the resource ID, then set the
        // the image to the image view using the resource ID.
        Context context = billionaireImageView.getContext();
        int id = context.getResources().getIdentifier("big" + randomBillionaire.ID, "drawable", context.getPackageName());
        billionaireImageView.setImageResource(id);

        // Retrieve the salary from th previous activity
        double userSalary = getIntent().getExtras().getDouble("salary");
        // Calculate the billionaire's worth as an actual number, not just number of billions.
        double networth = Double.parseDouble(randomBillionaire.worth) * 1000000000;

        // calculate how many years based, includes compound interest
        int year = 1;
        double currentWorth = userSalary;
        while (networth > currentWorth) {
             year++;
             double interest = currentWorth*.1;
             currentWorth = userSalary + currentWorth + interest;
        }
        // Return years
        userSalaryTextView.setText("With your current salary, with 100% savings and 10% interest it would take you " + year + " years to earn this much.");

    }

    public static int getResId(String variableName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
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
