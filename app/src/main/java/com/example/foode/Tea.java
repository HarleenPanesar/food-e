package com.example.foode;

import android.support.v7.app.AppCompatActivity;
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
import android.content.SharedPreferences;
import android.util.Log;

public class Tea extends AppCompatActivity {

    EditText e20,e21,e22;
    CheckBox c20,c21,c22;
    TextView t20,t21,t22;
    Button b1;
    String num20="0",num21="0",num22="0";
    String url="http://192.168.43.115/foodie/cart.php";
    ArrayList<String> product=new ArrayList<>();
    ArrayList<String> singlePrice=new ArrayList<>();
    ArrayList<String> quantity=new ArrayList<>();
    ArrayList<String> total=new ArrayList<>();
    SharedPreferences sp6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea);

        e20=(EditText)findViewById(R.id.e20);
        e21=(EditText)findViewById(R.id.e21);
        e22=(EditText)findViewById(R.id.e22);
        c20=(CheckBox)findViewById(R.id.c20);
        c21=(CheckBox)findViewById(R.id.c21);
        c22=(CheckBox)findViewById(R.id.c22);
        t20=(TextView)findViewById(R.id.t20);
        t21=(TextView)findViewById(R.id.t21);
        t22=(TextView)findViewById(R.id.t22);
        b1=(Button)findViewById(R.id.b1);
        sp6=getSharedPreferences("userDetails",MODE_PRIVATE);

        c20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=c20.isChecked();
                if (checked){
                    e20.setVisibility(View.VISIBLE);
                    c20.setChecked(true);
                }
                else {
                    e20.setVisibility(View.INVISIBLE);
                    c20.setChecked(false);
                }
            }
        });

        c21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c21).isChecked();
                if (checked){
                    e21.setVisibility(View.VISIBLE);
                    c21.setChecked(true);
                }
                else {
                    e21.setVisibility(View.INVISIBLE);
                    c21.setChecked(false);
                }
            }
        });

        c22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c22).isChecked();
                if (checked){
                    e22.setVisibility(View.VISIBLE);
                    c22.setChecked(true);
                }
                else {
                    e22.setVisibility(View.INVISIBLE);
                    c22.setChecked(false);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e20.getVisibility()==View.VISIBLE)
                {
                    num20=e20.getText().toString();
                    product.add("Green Tea");
                    singlePrice.add("20");
                    quantity.add(""+num20);
                    int totalPrice=Integer.parseInt(num20)*20;
                    total.add(""+totalPrice);

                }

                if (e21.getVisibility()==View.VISIBLE)
                {
                    num21=e21.getText().toString();
                    product.add("Ice Tea");
                    singlePrice.add("30");
                    quantity.add(""+num21);
                    int totalPrice=Integer.parseInt(num21)*30;
                    total.add(""+totalPrice);

                }

                if (e22.getVisibility()==View.VISIBLE)
                {
                    num22=e22.getText().toString();
                    product.add("Normal Tea");
                    singlePrice.add("10");
                    quantity.add(""+num22);
                    int totalPrice=Integer.parseInt(num22)*10;
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
                                Toast.makeText(Tea.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Tea.this, "Some error occured", Toast.LENGTH_SHORT).show();
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
                        params.put("email",sp6.getString("email",""));
                        params.put("product",productData);
                        params.put("quantity",quantityData);
                        params.put("single",singleData);
                        params.put("total",totalData);

                        return params;
                    }
                };

                RequestQueue queue=Volley.newRequestQueue(Tea.this);
                queue.add(request);

            }
        });
    }
}
