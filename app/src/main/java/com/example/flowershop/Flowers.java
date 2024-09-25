package com.example.flowershop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.flowershop.R;

public class Flowers extends AppCompatActivity {

    private RadioGroup rgProduct;
    private CheckBox checkRose, checkLily, checkTulip, checkMarigold;
    private TextView tvQuantity, tvPrice;
    private int quantity = 0;
    private double totalPrice = 0.0;
    private AlertDialog.Builder builder;
    private CardView cardview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowers);
         cardview=findViewById(R.id.cv_cardview);
        rgProduct = findViewById(R.id.rg_product);
        checkRose = findViewById(R.id.checkBox_rose);
        checkLily = findViewById(R.id.checkBox_lily);
        checkTulip = findViewById(R.id.checkBox_tulip);
        checkMarigold = findViewById(R.id.checkBox_marigold);
        tvQuantity = findViewById(R.id.tv_quantity);
        tvPrice = findViewById(R.id.tv_price);

        SeekBar seekBar = findViewById(R.id.seekBar);
        Switch brightSwitch = findViewById(R.id.btn_switch);
        TextView tvValue = findViewById(R.id.value);


        builder = new AlertDialog.Builder(this);

        Button btnIncrement = findViewById(R.id.btn_increment);
        Button btnDecrement = findViewById(R.id.btn_decrement);
        Button btnOrder = findViewById(R.id.btn_order);
        Button btnNotNow=findViewById(R.id.btn_not_now);
        Button btnRateNow=findViewById(R.id.btn_rate_now);


        btnDecrement.setOnClickListener(v -> {
            if (quantity > 0) {
                quantity--;
                updateQuantityAndPrice();
            }
        });

        btnIncrement.setOnClickListener(v -> {
            quantity++;
            updateQuantityAndPrice();
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvValue.setText("Brightness: " + progress + " / " + seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        brightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    seekBar.setProgress(40);
                    tvValue.setText("Brightness: " + 40 + " / " + seekBar.getMax());
                } else {
                    seekBar.setProgress(60);
                    tvValue.setText("Brightness: " + 60 + " / " + seekBar.getMax());
                }
            }
        });

        btnOrder.setOnClickListener(v -> {
            int selectedProductId = rgProduct.getCheckedRadioButtonId();
                RadioButton selectedProduct = findViewById(selectedProductId);

                if (selectedProductId == -1) {
                    Toast.makeText(getApplicationContext(), "Please select a product (Bouquet, Garland, or Bangles)!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String arr = getSelectedFlowers();
                if (arr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please select at least one flower!", Toast.LENGTH_SHORT).show();
                    return;
                }


                String radioValue = selectedProduct.getText().toString();


                if (quantity == 0) {
                    Toast.makeText(getApplicationContext(), "Please add quantity!", Toast.LENGTH_SHORT).show();
                } else {
                    builder.setTitle("Order Placed!")
                            .setMessage("Order Summary:\n" +
                                    "Product: " + radioValue + "\n" +
                                    "Flowers: " + arr + "\n" +
                                    "Quantity: " + quantity + "\n" +
                                    "Total Price: $" + totalPrice + "\n" +
                                    "Thank you!")
                            .setCancelable(false)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(), "Order Placed!", Toast.LENGTH_SHORT).show();
                                    resetOrder();
                                    cardview.setVisibility(v.VISIBLE);
                                }
                            }).show();
                }

        });



        btnNotNow.setOnClickListener(v->{
            cardview.setVisibility(v.GONE);
        });

    btnRateNow.setOnClickListener(v->{
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    });



    }

    private void updateQuantityAndPrice() {
        tvQuantity.setText(String.valueOf(quantity));
        countPrice();
    }

    private void countPrice() {
        double prodPrice = 0.0;
        int selectedProductId = rgProduct.getCheckedRadioButtonId();
        if (selectedProductId == R.id.radio_bouquet) {
            prodPrice = 50.0;
        } else if (selectedProductId == R.id.radio_Garland) {
            prodPrice = 15.0;
        } else if (selectedProductId == R.id.radio_Bangles) {
            prodPrice = 5.0;
        }

        totalPrice = prodPrice * quantity;


        tvPrice.setText("Total Price: $" + totalPrice);
    }

    private String getSelectedFlowers() {
        StringBuilder flowers = new StringBuilder();
        if (checkRose.isChecked()) {
            flowers.append("Rose ");
        }
        if (checkLily.isChecked()) {
            flowers.append("Lily ");
        }
        if (checkTulip.isChecked()) {
            flowers.append("Tulip ");
        }
        if (checkMarigold.isChecked()) {
            flowers.append("Marigold ");
        }
        return flowers.toString().trim();
    }

    private void resetOrder() {
        quantity = 0;
        totalPrice = 0.0;
        tvQuantity.setText("0");
        tvPrice.setText("Total Price: $0");
        checkRose.setChecked(false);
        checkLily.setChecked(false);
        checkTulip.setChecked(false);
        checkMarigold.setChecked(false);
        rgProduct.clearCheck();
    }




}
