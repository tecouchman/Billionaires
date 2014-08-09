package com.company.tom.testapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.company.tom.testapplication.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BillionaireComparison extends Activity {


    TextView billionaireNameTextView;
    TextView billionaireWorthTextView;
    TextView billionaireAgeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billionaire_comparison);

        billionaireAgeTextView = (TextView) findViewById(R.id.billionaire_age_textview);
        billionaireWorthTextView = (TextView) findViewById(R.id.billionaire_worth_textview);
        billionaireNameTextView = (TextView) findViewById(R.id.billionaire_name_textview);

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

            XmlPullParser xpp=getResources().getXml(R.xml.billionairesinfo);

            while (xpp.getEventType()!=XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType()==XmlPullParser.START_TAG) {

                    Billionaire billionaire = new Billionaire();

                    while (xpp.next() != XmlPullParser.END_TAG) {
                        if (xpp.getEventType() != XmlPullParser.START_TAG) {
                            xpp.getAttributeValue(null, "id");
                            continue;
                        }
                        String name = xpp.getName();

                        if (name.equals("b_name")) {
                            billionaire.name = readText(xpp);
                        } else if (name.equals("b_worth")) {
                            billionaire.worth = readText(xpp);
                        } else if (name.equals("b_age")) {
                            billionaire.age = readText(xpp);
                        } else if (name.equals("b_rank")) {
                            billionaire.rank = readText(xpp);
                        } else if (name.equals("b_source")) {
                            billionaire.source = readText(xpp);
                        } else if (name.equals("b_country")) {
                            billionaire.country = readText(xpp);
                        } else if (name.equals("b_change")) {
                            billionaire.change = readText(xpp);
                        }
                    }

                    items.add(billionaire);

                }

                xpp.next();
            }

        }
        catch (Exception e) {
        }




        Random rand = new Random();
        int randomNum = rand.nextInt(items.size());

        Billionaire randomBillionaire = items.get(randomNum);

        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(items.get(randomNum).name)
                .setTitle("Billionaire");
        AlertDialog dialog = builder.create();
        dialog.show();*/

        billionaireNameTextView.setText(randomBillionaire.name);
        billionaireWorthTextView.setText(randomBillionaire.worth);
        billionaireAgeTextView.setText(randomBillionaire.age);
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
