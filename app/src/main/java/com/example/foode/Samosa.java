package com.example.foode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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


public class Samosa extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    CheckBox c1,c2,c3,c4;
    TextView t1,t2,t3,t4;
    Button b1;
    String num1="0",num2="0",num3="0",num4="0";
    String url="http://192.168.43.115/foodie/cart.php";
    ArrayList<String> product=new ArrayList<>();
    ArrayList<String> singlePrice=new ArrayList<>();
    ArrayList<String> quantity=new ArrayList<>();
    ArrayList<String> total=new ArrayList<>();
    SharedPreferences sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samosa);

        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        e4=(EditText)findViewById(R.id.e4);
        c1=(CheckBox)findViewById(R.id.c1);
        c2=(CheckBox)findViewById(R.id.c2);
        c3=(CheckBox)findViewById(R.id.c3);
        c4=(CheckBox)findViewById(R.id.c4);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
        t4=(TextView)findViewById(R.id.t4);
        b1=(Button)findViewById(R.id.b1);
        sp1 = getSharedPreferences("userDetails",MODE_PRIVATE);
//        c1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean checked=((CheckBox)c1).isChecked();
//                if (c1.isChecked()){
//                    e1.setVisibility(View.VISIBLE);
//                }
//                else {
//                    e1.setVisibility(View.INVISIBLE);
//                }
//            }
//        });
        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    e1.setVisibility(View.VISIBLE);
                }
                else
                {
                    e1.setVisibility(View.INVISIBLE);
                }
            }
        });

        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    e2.setVisibility(View.VISIBLE);
                }
                else
                {
                    e2.setVisibility(View.INVISIBLE);
                }
            }
        });


        c3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    e3.setVisibility(View.VISIBLE);
                }
                else
                {
                    e3.setVisibility(View.INVISIBLE);
                }
            }
        });


        c4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    e4.setVisibility(View.VISIBLE);
                }
                else
                {
                    e4.setVisibility(View.INVISIBLE);
                }
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e1.getVisibility()==View.VISIBLE)
                {
                    num1=e1.getText().toString();
                    product.add("Aloo Samosa");
                    singlePrice.add("15");
                    quantity.add(""+num1);
                    int totalPrice=Integer.parseInt(num1)*15;
                    total.add(""+totalPrice);

                }
                if (e2.getVisibility()==View.VISIBLE)
                {
                    num2=e2.getText().toString();
                    product.add("Manchurian Samosa");
                    singlePrice.add("25");
                    quantity.add(""+num2);
                    int totalPrice=Integer.parseInt(num2)*20;
                    total.add(""+totalPrice);

                }
                if (e3.getVisibility()==View.VISIBLE)
                {
                    num3=e3.getText().toString();
                    product.add("Noodles Samosa");
                    singlePrice.add("15");
                    quantity.add(""+num3);
                    int totalPrice=Integer.parseInt(num3)*15;
                    total.add(""+totalPrice);
                }
                if (e4.getVisibility()==View.VISIBLE)
                {
                    num4=e4.getText().toString();
                    product.add("Pasta Samosa");
                    singlePrice.add("25");
                    quantity.add(""+num4);
                    int totalPrice=Integer.parseInt(num4)*25;
                    total.add(""+totalPrice);
                }



                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject o = new JSONObject(response.toString());

                            JSONArray user = o.getJSONArray("info");

                            JSONObject c = user.getJSONObject(0);

                            final String resultJ = c.getString("result");
                            final String product = c.getString("product");
                            Log.e("product",product);

                            if(resultJ.equals("success")){
                                Toast.makeText(Samosa.this, "Added to cart", Toast.LENGTH_LONG).show();

                            }
                            else
                            {
                                Toast.makeText(Samosa.this, "Some error occurred!", Toast.LENGTH_LONG).show();
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
                        Map<String, String>  params = new HashMap<>();
                        String productData = new Gson().toJson(product);
                        String singleData = new Gson().toJson(singlePrice);
                        String quantityData = new Gson().toJson(quantity);
                        String totalData = new Gson().toJson(total);
                        params.put("email", sp1.getString("email",""));
                        params.put("product", productData);
                        params.put("quantity", quantityData);
                        params.put("single", singleData);
                        params.put("total", totalData);

                        return params;

                    }
                };

                RequestQueue queue = Volley.newRequestQueue(Samosa.this);
                queue.add(request);



            }
        });
    }
}
