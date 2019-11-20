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

public class Patties extends AppCompatActivity {

    EditText e18,e19;
    CheckBox c18,c19;
    TextView t18,t19;
    Button b1;
    String num18="0",num19="0";
    String url="http://192.168.43.115/foodie/cart.php";
    ArrayList<String> product=new ArrayList<>();
    ArrayList<String> singlePrice=new ArrayList<>();
    ArrayList<String> quantity=new ArrayList<>();
    ArrayList<String> total=new ArrayList<>();
    SharedPreferences sp5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patties);

        e18=(EditText)findViewById(R.id.e18);
        e19=(EditText)findViewById(R.id.e19);
        c18=(CheckBox)findViewById(R.id.c18);
        c19=(CheckBox)findViewById(R.id.c19);
        t18=(TextView)findViewById(R.id.t18);
        t19=(TextView)findViewById(R.id.t19);
        b1=(Button)findViewById(R.id.b1);
        sp5=getSharedPreferences("userDetails",MODE_PRIVATE);

        c18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c18).isChecked();
                if (checked){
                    e18.setVisibility(View.VISIBLE);
                    c18.setChecked(true);
                }
                else {
                    e18.setVisibility(View.INVISIBLE);
                    c18.setChecked(false);
                }
            }
        });

        c19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked=((CheckBox)c19).isChecked();
                if (checked){
                    e19.setVisibility(View.VISIBLE);
                    c19.setChecked(true);
                }
                else {
                    e19.setVisibility(View.INVISIBLE);
                    c19.setChecked(false);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e18.getVisibility()==View.VISIBLE)
                {
                    num18=e18.getText().toString();
                    product.add("Aloo Patty");
                    singlePrice.add("30");
                    quantity.add(""+num18);
                    int totalPrice=Integer.parseInt(num18)*30;
                    total.add(""+totalPrice);

                }

                if (e19.getVisibility()==View.VISIBLE)
                {
                    num19=e19.getText().toString();
                    product.add("Paneer Patty");
                    singlePrice.add("35");
                    quantity.add(""+num19);
                    int totalPrice=Integer.parseInt(num19)*35;
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
                                Toast.makeText(Patties.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Patties.this, "Some error occured", Toast.LENGTH_SHORT).show();
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
                        params.put("email",sp5.getString("email",""));
                        params.put("product",productData);
                        params.put("quantity",quantityData);
                        params.put("single",singleData);
                        params.put("total",totalData);

                        return params;
                    }
                };

                RequestQueue queue=Volley.newRequestQueue(Patties.this);
                queue.add(request);

            }
        });
    }
}
