package com.company.tom.testapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
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


public class BillionaireComparison extends FragmentActivity {




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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Added to fix nullpointerexception, does not update anything
        LayoutInflater inflater =(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_billionaire_comparison, null);


        billionaireAgeTextView = (TextView) v.findViewById(R.id.billionaire_age_textview);
        billionaireWorthTextView = (TextView)v.findViewById(R.id.billionaire_worth_textview);
        billionaireNameTextView = (TextView) v.findViewById(R.id.billionaire_name_textview);
        billionaireImageView = (ImageView)v.findViewById(R.id.billionaire_imageview);
        foodImageView = (ImageView) v.findViewById(R.id.food_imageview);
        userSalaryTextView = (TextView) v.findViewById(R.id.user_salary_textview);
        billionaireDescTextView = (TextView) v.findViewById(R.id.b_desc);
        showRandomBillionaire();


/*


        billionaireAgeTextView = (TextView) findViewById(R.id.billionaire_age_textview);
        billionaireWorthTextView = (TextView)findViewById(R.id.billionaire_worth_textview);
        billionaireNameTextView = (TextView) findViewById(R.id.billionaire_name_textview);
        billionaireImageView = (ImageView)findViewById(R.id.billionaire_imageview);
        foodImageView = (ImageView) findViewById(R.id.food_imageview);
        userSalaryTextView = (TextView) findViewById(R.id.user_salary_textview);
        billionaireDescTextView = (TextView) findViewById(R.id.b_desc);
        showRandomBillionaire();
*/


      //  foodNameTextView = (TextView) findViewById(R.id.food_name_textview);
        foodCostTextView = (TextView) v.findViewById(R.id.food_cost_textview);
        // foodSourceTextView = (TextView) findViewById(R.id.food_source_textview);
        showRandomFood();

        //viewpager layout
        //setContentView(R.layout.viewpager_layout);
       // initialisePaging();

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

            XmlPullParser xpp=getResources().getXml(R.xml.bnolink);

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

                   /*     else if (name.equals("b_link")) {
                            billionaire.link = readText(xpp);
                        }*/

                        else if (name.equals("b_desc")) {
                            billionaire.desc = readText(xpp);
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

       billionaireAgeTextView.setText("Age " + randomBillionaire.age);
       billionaireWorthTextView.setText("$" + randomBillionaire.worth + " Billion");
       billionaireNameTextView.setText(randomBillionaire.name);
       billionaireDescTextView.setText(randomBillionaire.desc);



        // Determine the resource name of the relevant image and get the resource ID, then set the
        // the image to the image view using the resource ID.
        Context context = billionaireImageView.getContext();
        int id = context.getResources().getIdentifier("pic" + randomBillionaire.ID, "drawable", context.getPackageName());
        billionaireImageView.setImageResource(id);



        // Retrieve the salary from th previous activity
        //commenting out as it is having trouble nullpointer exception
        //        double userSalary = getIntent().getExtras().getDouble("salary");


        double userSalary = 1000;


        // Calculate the billionaire's worth as an actual number, not just number of billions.
        double networth = Double.parseDouble(randomBillionaire.worth) * 1000000000;

        Billionaire.shareworth = networth;
        //randomBillionaire.currentbworth = networth;


        // calculate how many years based, includes compound interest
        int year = 1;
        double currentWorth = userSalary;
        while (networth > currentWorth) {
             year++;
             double interest = currentWorth*.1;
             currentWorth = userSalary + currentWorth + interest;
        }
        // Return years
        userSalaryTextView.setText("With your current salary, with 100% savings and 10% interest it would take you " + year + " to save this much $$$.");

    }





    private void showRandomFood() {

        ArrayList<Food> foodItems = new ArrayList<Food>();

        try {

            XmlPullParser xpp=getResources().getXml(R.xml.food);

            while (xpp.getEventType()!=XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType()==XmlPullParser.START_TAG) {

                    Food food = new Food();


                    String fname = xpp.getName();

                    String foodID = "";
                    if (fname.equals("food")) {
                        foodID = xpp.getAttributeValue(0);
                    }



                    while (xpp.next() != XmlPullParser.END_TAG) {
                        if (xpp.getEventType() != XmlPullParser.START_TAG) {

                            continue;
                        }
                        food.ID = foodID;

                        fname = xpp.getName();

                        if (fname.equals("f_name")) {
                            food.name = readText(xpp);
                        } else if (fname.equals("f_cost")) {
                            food.cost = Double.parseDouble(readText(xpp));
                        } else if (fname.equals("f_from")) {
                            food.source = readText(xpp);
                        }
                    }

                    foodItems.add(food);

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


        Random frand = new Random();
        int randomNum = frand.nextInt(foodItems.size());
        Food frandomFood = foodItems.get(randomNum);

        // Figuring out how many of a food the billionaire could buy.
        double howmany = frandomFood.cost;
       // double worthbills = Double.parseDouble(Billionaire.shareworth) * 1000000000;

        long numberfood = Math.round( Billionaire.shareworth / howmany);

        foodCostTextView.setText(numberfood + " " + frandomFood.name) ;


        // Determine the resource name of the relevant image and get the resource ID, then set the
        // the image to the image view using the resource ID.
        Context context = foodImageView.getContext();
        int id = context.getResources().getIdentifier("stf" + frandomFood.ID, "drawable", context.getPackageName());
        foodImageView.setImageResource(id);



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
