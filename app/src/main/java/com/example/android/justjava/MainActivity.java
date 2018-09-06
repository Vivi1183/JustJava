package com.example.android.justjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private CheckBox hasWhippedCream;
    private CheckBox hasChocolate;
    private EditText nameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hasWhippedCream = (CheckBox) findViewById(R.id.has_whipped_cream);
        hasChocolate = (CheckBox) findViewById(R.id.has_chocolate);
        nameEditText = (EditText) findViewById(R.id.name_edit_text);

    }
    int quantity = 2;
    int price;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
              String priceMessage = createOrderSummary ();
//        displayMessage(priceMessage);

        String name = nameEditText.getText().toString();
        String subject = "JustJava " + getString(R.string.order_for) + name;
        String emailText = priceMessage;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private int calculatePrise () {
        int pricePerCup = 5;
        int pricePerWhippedCream = 1;
        int pricePerChocolate = 2;
        Boolean checkBoxWhippedCream = hasWhippedCream.isChecked();
        Boolean checkBoxChocolate = hasChocolate.isChecked();
        if (checkBoxWhippedCream == true){
            if (checkBoxChocolate == true)
                price = quantity * (pricePerCup + pricePerWhippedCream + pricePerChocolate);
            else
                price = quantity * (pricePerCup + pricePerWhippedCream);}

        else {
                if (checkBoxChocolate == true)
                    price = quantity * (pricePerCup + pricePerChocolate);
                else
                    price = quantity * pricePerCup;}

       return price;
    }
    private String createOrderSummary (){
        Boolean checkBoxWhippedCream = hasWhippedCream.isChecked();
        Boolean checkBoxChocolate = hasChocolate.isChecked();
        String name = nameEditText.getText().toString();
        String Message = getString(R.string.order_summary_name, name);
        Message += "\n" + getString(R.string.add_whipped_cream) + checkBoxWhippedCream;
        Message += "\n" + getString(R.string.add_chocolate) + checkBoxChocolate;
        Message += "\n" + getString(R.string.quantity) + ": " + quantity;
        Message += "\n" + getString(R.string.total) + calculatePrise ();
        Message += "\n" + getString(R.string.thank_you);
        return Message;
    }
    public void increment(View view) {
        Log.i("info", "calling increment method");
        if (quantity < 100) {
            quantity = quantity+1;
            displayQuantity (quantity);
        } else {
            Toast toastIncrement = Toast.makeText(getApplicationContext(), "You cannot have more than 100 coffee", Toast.LENGTH_SHORT);
            toastIncrement.show();
            return;
        }

    }
    public void decrement(View view) {
        Log.i("info", "calling decrement method");
        if (quantity > 1) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
        else {
            Toast toastDecrement = Toast.makeText(getApplicationContext(), "You cannot have less than 1 coffee", Toast.LENGTH_SHORT);
            toastDecrement.show();
            return;}
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
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
}