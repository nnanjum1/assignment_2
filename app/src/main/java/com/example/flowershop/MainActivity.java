package com.example.flowershop;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flowershop.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RatingBar ratBar=findViewById(R.id.ratingBar);
        TextView ratingText=findViewById(R.id.rating);

        ratBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            ratingText.setText("Rating: " + rating);
        });








    }
}