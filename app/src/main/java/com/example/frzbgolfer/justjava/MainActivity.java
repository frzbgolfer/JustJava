package com.example.frzbgolfer.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int numCoffees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        String namePerson = nameEditText.getText().toString();

        CheckBox whipCreamCheckBox = (CheckBox) findViewById(R.id.whipcream_checkbox);
        boolean hasWhipCream = whipCreamCheckBox.isChecked();
        CheckBox chocCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocCheckBox.isChecked();

        int total = calculatePrice(numCoffees, hasWhipCream, hasChocolate);

        String priceMessage = createOrderSummary(namePerson, numCoffees, hasWhipCream, hasChocolate, total);
        displayMessage(priceMessage, namePerson);
    }

    /**
     * This method is called to calculate the Price
     * @param quantity is number of the item purchased
     * @param hasWhipCream indicates whether or not whipped cream is ordered with the coffee
     * @param hasChocolate indicates whether or not chocolate is ordered with the coffee
     */
    private int calculatePrice(int quantity, boolean hasWhipCream, boolean hasChocolate){
        int pricePerCup = 5;
        if(hasWhipCream){
            pricePerCup = pricePerCup + 1;
        }
        if(hasChocolate){
            pricePerCup = pricePerCup + 2;
        }

        return quantity * pricePerCup;
    }

    /**
     * This method creates and returns a summary message
     * @param quantity is number of items ordered.
     * @param total is order total due
     * @param hasWhipCream indicates if a whipped cream topping is added
     * @param hasChoc indicates if chocolate topping is added
     */
    private String createOrderSummary(String name, int quantity, boolean hasWhipCream, boolean hasChoc, int total){
        String summary =    getString(R.string.order_summary_name, name) +  "\n" +
                            getString(R.string.add_whipped_cream) + " " + hasWhipCream + "\n" +
                            getString(R.string.add_chocolate) + " " + hasChoc + "\n" +
                            getString(R.string.quantity)+ ": " + quantity + "\n" +
                            getString(R.string.total, total) + "\n" +
                            getString(R.string.thank_you);
        return summary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    // This method increases the quantity
    public void incrementQuantity(View view){
        if(numCoffees < 100){
            numCoffees++;
            display(numCoffees);
        }else{
            Toast.makeText(getApplicationContext(),"Limit of 100 coffees in an order", Toast.LENGTH_LONG).show();
        }

    }

    // This method decreases the quantity
    public void decrementQuantity(View view){
        if(numCoffees > 1){
            numCoffees--;
            display(numCoffees);
        }else{
            Toast.makeText(getApplicationContext(),"You must order at least 1 coffee", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method displays the given text on the screen
     */
    private void displayMessage(String message, String orderer){
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava Order for " + orderer);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
