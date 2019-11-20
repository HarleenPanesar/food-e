package com.example.foode;

import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.util.Log;
import android.os.Bundle;
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

public class Shakes extends AppCompatActivity {

    EditText e5,e6,e7,e8;
    CheckBox c5,c6,c7,c8;
    TextView t5,t6,t7,t8;
    Button b1;
    String num5="0",num6="0",num7="0",num8="0";
    String url="http://192.168.43.115/foodie/cart.php";
    ArrayList<String> product=new ArrayList<>();
    ArrayList<String> singlePrice=new ArrayList<>();
    ArrayList<String> quantity=new ArrayList<>();
    ArrayList<String> total=new ArrayList<>();
    SharedPreferences sp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shakes);

        e5=(EditText)findViewById(R.id.e5);
        e6=(EditText)findViewById(R.id.e6);
        e7=(EditText)findViewById(R.id.e7);
        e8=(EditText)findViewById(R.id.e8);
        c5=(CheckBox)findViewById(R.id.c5);
        c6=(CheckBox)findViewById(R.id.c6);
        c7=(CheckBox)findViewById(R.id.c7);
        c8=(CheckBox)findViewById(R.id.c8);
        t5=(TextView)findViewById(R.id.t5);
        t6=(TextView)findViewById(R.id.t6);
        t7=(TextView)findViewById(R.id.t7);
        t8=(TextView)findViewById(R.id.t8);
        b1=(Button)findViewById(R.id.b1);
        sp2=getSharedPreferences("userDetails",MODE_PRIVATE);

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c5).isChecked();
                if (checked){
                    e5.setVisibility(View.VISIBLE);
                    c5.setChecked(true);
                }
                else {
                    e5.setVisibility(View.INVISIBLE);
                    c5.setChecked(false);
                }
            }
        });

        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c6).isChecked();
                if (checked){
                    e6.setVisibility(View.VISIBLE);
                    c6.setChecked(true);
                }
                else {
                    e6.setVisibility(View.INVISIBLE);
                    c6.setChecked(false);
                }
            }
        });

        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c7).isChecked();
                if (checked){
                    e7.setVisibility(View.VISIBLE);
                    c7.setChecked(true);
                }
                else {
                    e7.setVisibility(View.INVISIBLE);
                    c7.setChecked(false);
                }
            }
        });

        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c8).isChecked();
                if (checked){
                    e8.setVisibility(View.VISIBLE);
                    c8.setChecked(true);
                }
                else {
                    e8.setVisibility(View.INVISIBLE);
                    c8.setChecked(false);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e5.getVisibility()==View.VISIBLE)
                {
                    num5=e5.getText().toString();
                    product.add("Vanilla Shake");
                    singlePrice.add("40");
                    quantity.add(""+num5);
                    int totalPrice=Integer.parseInt(num5)*40;
                    total.add(""+totalPrice);

                }

                if (e6.getVisibility()==View.VISIBLE)
                {
                    num6=e6.getText().toString();
                    product.add("Blackcurrent Shake");
                    singlePrice.add("40");
                    quantity.add(""+num6);
                    int totalPrice=Integer.parseInt(num6)*40;
                    total.add(""+totalPrice);

                }

                if (e7.getVisibility()==View.VISIBLE)
                {
                    num7=e7.getText().toString();
                    product.add("Chocolate Shake");
                    singlePrice.add("50");
                    quantity.add(""+num7);
                    int totalPrice=Integer.parseInt(num7)*50;
                    total.add(""+totalPrice);

                }

                if (e8.getVisibility()==View.VISIBLE)
                {
                    num8=e8.getText().toString();
                    product.add("Cold Coffee");
                    singlePrice.add("60");
                    quantity.add(""+num8);
                    int totalPrice=Integer.parseInt(num8)*60;
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
                                Toast.makeText(Shakes.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Shakes.this, "Some error occured", Toast.LENGTH_SHORT).show();
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
                        params.put("email",sp2.getString("email",""));
                        params.put("product",productData);
                        params.put("quantity",quantityData);
                        params.put("single",singleData);
                        params.put("total",totalData);

                        return params;
                    }
                };

                RequestQueue queue=Volley.newRequestQueue(Shakes.this);
                queue.add(request);

            }
        });
    }
}
