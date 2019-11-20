package com.example.foode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    TextView samosa,shakes,sandwich,drinks,patties,tea;
    Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        samosa=(TextView)findViewById(R.id.samosa);
        shakes=(TextView)findViewById(R.id.shakes);
        sandwich=(TextView)findViewById(R.id.sandwich);
        drinks=(TextView)findViewById(R.id.drinks);
        patties=(TextView)findViewById(R.id.patties);
        tea=(TextView)findViewById(R.id.tea);
        checkout=(Button)findViewById(R.id.checkout);

        samosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Samosa.class);
                startActivity(intent);
            }
        });

        shakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Shakes.class);
                startActivity(intent);
            }
        });

        sandwich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Sandwich.class);
                startActivity(intent);
            }
        });

        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Drinks.class);
                startActivity(intent);
            }
        });

        patties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Patties.class);
                startActivity(intent);
            }
        });

        tea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Tea.class);
                startActivity(intent);
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
