package com.example.foode;

import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    Button login;
    TextView newuser;
    String emailv,passwordv;
    CheckBox c2;
    final String url = "http://192.168.43.115/foodie/androidLogin.php";
    SharedPreferences sp1,sp9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp1 = getSharedPreferences("userDetails",MODE_PRIVATE);
        sp9=getSharedPreferences("loggedIN",MODE_PRIVATE);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        newuser=(TextView)findViewById(R.id.newuser);
        c2=(CheckBox)findViewById(R.id.c2);

        if (sp9.getString("logged","").equals("logged"))
        {
            Intent i=new Intent(getApplicationContext(),Navigation.class);
            startActivity(i);
        }

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),register.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailv = email.getText().toString();
                passwordv=password.getText().toString();

                if (c2.isChecked())
                {
                    SharedPreferences.Editor edit=sp9.edit();
                    edit.putString("logged","logged");
                    edit.commit();
                }

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject o = new JSONObject(response.toString());

                            JSONArray user = o.getJSONArray("info");

                            JSONObject c = user.getJSONObject(0);

                            final String resultJ = c.getString("result");
                            final String nameJ = c.getString("user");
                            final String emailJ = c.getString("email");

                            SharedPreferences.Editor editor = sp1.edit();
                            editor.putString("name",nameJ);
                            editor.putString("email",emailJ);
                            editor.commit();


                            if(resultJ.equals("success")){
                                Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getApplicationContext(),Navigation.class);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                        Log.e("error",e.toString());
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error",error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String>  params = new HashMap<>();
                        params.put("email", emailv);
                        params.put("password", passwordv);

                        return params;

                    }
                };

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(request);

            }
        });
    }
}
