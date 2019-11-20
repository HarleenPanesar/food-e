package com.example.foode;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Drinks extends AppCompatActivity {

    EditText e12,e13,e14,e15,e16,e17;
    CheckBox c12,c13,c14,c15,c16,c17;
    TextView t12,t13,t14,t15,t16,t17;
    Button b1;
    String num12="0",num13="0",num14="0",num15="0",num16="0",num17="0";
    String url="http://192.168.43.115/foodie/cart.php";
    ArrayList<String> product=new ArrayList<>();
    ArrayList<String> singlePrice=new ArrayList<>();
    ArrayList<String> quantity=new ArrayList<>();
    ArrayList<String> total=new ArrayList<>();
    SharedPreferences sp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);

        e12=(EditText)findViewById(R.id.e12);
        e13=(EditText)findViewById(R.id.e13);
        e14=(EditText)findViewById(R.id.e14);
        e15=(EditText)findViewById(R.id.e15);
        e16=(EditText)findViewById(R.id.e16);
        e17=(EditText)findViewById(R.id.e17);
        c12=(CheckBox)findViewById(R.id.c12);
        c13=(CheckBox)findViewById(R.id.c13);
        c14=(CheckBox)findViewById(R.id.c14);
        c15=(CheckBox)findViewById(R.id.c15);
        c16=(CheckBox)findViewById(R.id.c16);
        c17=(CheckBox)findViewById(R.id.c17);
        t12=(TextView)findViewById(R.id.t12);
        t13=(TextView)findViewById(R.id.t13);
        t14=(TextView)findViewById(R.id.t14);
        t15=(TextView)findViewById(R.id.t15);
        t16=(TextView)findViewById(R.id.t16);
        t17=(TextView)findViewById(R.id.t17);
        b1=(Button)findViewById(R.id.b1);
        sp4=getSharedPreferences("userDetails",MODE_PRIVATE);

        c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c12).isChecked();
                if (checked){
                    e12.setVisibility(View.VISIBLE);
                    c12.setChecked(true);
                }
                else {
                    e12.setVisibility(View.INVISIBLE);
                    c12.setChecked(false);
                }
            }
        });

        c13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c13).isChecked();
                if (checked){
                    e13.setVisibility(View.VISIBLE);
                    c13.setChecked(true);
                }
                else {
                    e13.setVisibility(View.INVISIBLE);
                    c13.setChecked(false);
                }
            }
        });

        c14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c14).isChecked();
                if (checked){
                    e14.setVisibility(View.VISIBLE);
                    c14.setChecked(true);
                }
                else {
                    e14.setVisibility(View.INVISIBLE);
                    c14.setChecked(false);
                }
            }
        });

        c15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c15).isChecked();
                if (checked){
                    e15.setVisibility(View.VISIBLE);
                    c15.setChecked(true);
                }
                else {
                    e15.setVisibility(View.INVISIBLE);
                    c15.setChecked(false);
                }
            }
        });

        c16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c16).isChecked();
                if (checked){
                    e16.setVisibility(View.VISIBLE);
                    c16.setChecked(true);
                }
                else {
                    e16.setVisibility(View.INVISIBLE);
                    c16.setChecked(false);
                }
            }
        });

        c17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c17).isChecked();
                if (checked){
                    e17.setVisibility(View.VISIBLE);
                    c17.setChecked(true);
                }
                else {
                    e17.setVisibility(View.INVISIBLE);
                    c17.setChecked(false);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e12.getVisibility()==View.VISIBLE)
                {
                    num12=e12.getText().toString();
                    product.add("Coke");
                    singlePrice.add("20");
                    quantity.add(""+num12);
                    int totalPrice=Integer.parseInt(num12)*20;
                    total.add(""+totalPrice);

                }

                if (e13.getVisibility()==View.VISIBLE)
                {
                    num13=e13.getText().toString();
                    product.add("Sprite");
                    singlePrice.add("20");
                    quantity.add(""+num13);
                    int totalPrice=Integer.parseInt(num13)*20;
                    total.add(""+totalPrice);

                }

                if (e14.getVisibility()==View.VISIBLE)
                {
                    num14=e14.getText().toString();
                    product.add("Maaza");
                    singlePrice.add("20");
                    quantity.add(""+num14);
                    int totalPrice=Integer.parseInt(num14)*20;
                    total.add(""+totalPrice);

                }

                if (e15.getVisibility()==View.VISIBLE)
                {
                    num15=e15.getText().toString();
                    product.add("Pepsi");
                    singlePrice.add("20");
                    quantity.add(""+num15);
                    int totalPrice=Integer.parseInt(num15)*20;
                    total.add(""+totalPrice);

                }

                if (e16.getVisibility()==View.VISIBLE)
                {
                    num16=e16.getText().toString();
                    product.add("Appy");
                    singlePrice.add("20");
                    quantity.add(""+num16);
                    int totalPrice=Integer.parseInt(num16)*20;
                    total.add(""+totalPrice);

                }

                if (e17.getVisibility()==View.VISIBLE)
                {
                    num17=e17.getText().toString();
                    product.add("Fanta");
                    singlePrice.add("20");
                    quantity.add(""+num17);
                    int totalPrice=Integer.parseInt(num17)*20;
                    total.add(""+totalPrice);

                }

                StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject o = new JSONObject(response.toString());

                            JSONArray user = o.getJSONArray("info");

                            JSONObject c = user.getJSONObject(0);

                            final String resultJ = c.getString("result");
                            final String product = c.getString("product");
                            Log.e("product", product);

                            if (resultJ.equals("success")) {
                                Toast.makeText(Drinks.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Drinks.this, "Some error occured", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        String productData=new Gson().toJson(product);
                        String singleData=new Gson().toJson(singlePrice);
                        String quantityData=new Gson().toJson(quantity);
                        String totalData= new Gson().toJson(total);
                        params.put("email",sp4.getString("email",""));
                        params.put("product",productData);
                        params.put("quantity",quantityData);
                        params.put("single",singleData);
                        params.put("total",totalData);

                        return params;
                    }
                };

                RequestQueue queue=Volley.newRequestQueue(Drinks.this);
                queue.add(request);

            }
        });
    }
}
