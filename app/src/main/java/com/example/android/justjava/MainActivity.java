package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private CheckBox hasWhippedCream;
    private CheckBox hasChocolate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hasWhippedCream = (CheckBox) findViewById(R.id.has_whipped_cream);
        hasChocolate = (CheckBox) findViewById(R.id.has_chocolate);

    }
    int quantity = 0;
    int price;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
                String priceMessage = createOrderSummary ();
        displayMessage(priceMessage);
    }


    private int calculatePrise () {
        int pricePerCup = 5;
        price = quantity * pricePerCup;
        return price;
    }
    private String createOrderSummary (){
        Boolean checkBoxWhippedCream = hasWhippedCream.isChecked();
        Boolean checkBoxChocolate = hasChocolate.isChecked();
        String Message = "Name:" + "Viktoriia N." + "\nAdd whipped cream? " + checkBoxWhippedCream + "\nAdd chocolate? " + checkBoxChocolate + "\nQuantity:" + quantity + "\nTotal: " + calculatePrise () + " руб." + "\nThank you!";
        return Message;
    }
    public void increment(View view) {
        Log.i("info", "calling increment method");
        quantity = quantity+1;
        displayQuantity (quantity);
    }
    public void decrement(View view) {
        Log.i("info", "calling decrement method");
        if (quantity > 0) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
        else {quantity=0;}
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int displayQuantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + displayQuantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}