package com.example.frzbgolfer.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        int total = calculatePrice(numCoffees, pricePerCup);
        String priceMessage = createOrderSummary(numCoffees, total);
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
     */
    private String createOrderSummary(int quantity, int total){
        String summary = "Name: Courtney Biel \nQuantity: " + quantity + "\nTotal: " + total + "\nThank you!";
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
