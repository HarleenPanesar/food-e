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

public class Sandwich extends AppCompatActivity {

    EditText e9,e10,e11;
    CheckBox c9,c10,c11;
    TextView t9,t10,t11;
    Button b1;
    String num9="0",num10="0",num11="0";
    String url="http://192.168.43.115/foodie/cart.php";
    ArrayList<String> product=new ArrayList<>();
    ArrayList<String> singlePrice=new ArrayList<>();
    ArrayList<String> quantity=new ArrayList<>();
    ArrayList<String> total=new ArrayList<>();
    SharedPreferences sp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich);

        e9=(EditText)findViewById(R.id.e9);
        e10=(EditText)findViewById(R.id.e10);
        e11=(EditText)findViewById(R.id.e11);
        c9=(CheckBox)findViewById(R.id.c9);
        c10=(CheckBox)findViewById(R.id.c10);
        c11=(CheckBox)findViewById(R.id.c11);
        t9=(TextView)findViewById(R.id.t9);
        t10=(TextView)findViewById(R.id.t10);
        t11=(TextView)findViewById(R.id.t11);
        b1=(Button)findViewById(R.id.b1);
        sp3=getSharedPreferences("userDetails",MODE_PRIVATE);

        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c9).isChecked();
                if (checked){
                    e9.setVisibility(View.VISIBLE);
                    c9.setChecked(true);
                }
                else {
                    e9.setVisibility(View.INVISIBLE);
                    c9.setChecked(false);
                }
            }
        });

        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c10).isChecked();
                if (checked){
                    e10.setVisibility(View.VISIBLE);
                    c10.setChecked(true);
                }
                else {
                    e10.setVisibility(View.INVISIBLE);
                    c10.setChecked(false);
                }
            }
        });

        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c11).isChecked();
                if (checked){
                    e11.setVisibility(View.VISIBLE);
                    c11.setChecked(true);
                }
                else {
                    e11.setVisibility(View.INVISIBLE);
                    c11.setChecked(false);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e9.getVisibility()==View.VISIBLE)
                {
                    num9=e9.getText().toString();
                    product.add("Veg Grilled Sandwich");
                    singlePrice.add("60");
                    quantity.add(""+num9);
                    int totalPrice=Integer.parseInt(num9)*60;
                    total.add(""+totalPrice);

                }

                if (e10.getVisibility()==View.VISIBLE)
                {
                    num10=e10.getText().toString();
                    product.add("Cheese Grilled Sanshwich");
                    singlePrice.add("80");
                    quantity.add(""+num10);
                    int totalPrice=Integer.parseInt(num10)*80;
                    total.add(""+totalPrice);

                }

                if (e11.getVisibility()==View.VISIBLE)
                {
                    num11=e11.getText().toString();
                    product.add("Corn Sandwich");
                    singlePrice.add("50");
                    quantity.add(""+num11);
                    int totalPrice=Integer.parseInt(num11)*50;
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
                                Toast.makeText(Sandwich.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Sandwich.this, "Some error occured", Toast.LENGTH_SHORT).show();
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
                        params.put("email",sp3.getString("email",""));
                        params.put("product",productData);
                        params.put("quantity",quantityData);
                        params.put("single",singleData);
                        params.put("total",totalData);

                        return params;
                    }
                };

                RequestQueue queue=Volley.newRequestQueue(Sandwich.this);
                queue.add(request);

            }
        });
    }
}
