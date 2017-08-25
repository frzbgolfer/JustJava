package com.example.frzbgolfer.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int numCoffees = 0;
    int pricePerCup = 5;

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
        int total = calculatePrice(numCoffees, pricePerCup);
        CheckBox whipCreamCheckBox = (CheckBox) findViewById(R.id.whipcream_checkbox);
        boolean hasWhipCream = whipCreamCheckBox.isChecked();
        CheckBox chocCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocCheckBox.isChecked();
        String priceMessage = createOrderSummary(namePerson, numCoffees, hasWhipCream, hasChocolate, total);
        displayMessage(priceMessage);
    }

    /**
     * This method is called to calculate the Price
     * @param quantity is number of the item purchased
     * @param cost is the cost per unit
     */
    private int calculatePrice(int quantity, int cost){
        int price = quantity * cost;
        return price;
    }

    /**
     * This method creates and returns a summary message
     * @param quantity is number of items ordered.
     * @param total is order total due
     * @param hasWhipCream indicates if a whipped cream topping is added
     * @param hasChoc indicates if chocolate topping is added
     */
    private String createOrderSummary(String name, int quantity, boolean hasWhipCream, boolean hasChoc, int total){
        String summary =    "Name: " + name +  "\n" +
                            "Add whipped cream? " + hasWhipCream + "\n" +
                            "Add chocolate? " + hasChoc + "\n" +
                            "Quantity: " + quantity + "\n" +
                            "Total: " + total + "\n" +
                            "Thank you!";
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
        numCoffees++;
        display(numCoffees);
    }

    // This method decreases the quantity
    public void decrementQuantity(View view){
        if(numCoffees > 0){
            numCoffees--;
        }
        display(numCoffees);
    }

    /**
     * This method displays the given text on the screen
     */
    private void displayMessage(String message){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
