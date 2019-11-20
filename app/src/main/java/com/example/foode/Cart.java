package com.example.foode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Cart extends AppCompatActivity {

    TextView count1,count2,count3,count4,total1,total2,total3,total4,gTotal;
    int price1=15,price2=20,price3=15,price4=25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Bundle b=getIntent().getExtras();

        int countItem1=Integer.parseInt(b.getString("data1"));
        int countItem2=Integer.parseInt(b.getString("data2"));
        int countItem3=Integer.parseInt(b.getString("data3"));
        int countItem4=Integer.parseInt(b.getString("data4"));

        int totalPrice1=countItem1*price1;
        int totalPrice2=countItem2*price2;
        int totalPrice3=countItem3*price3;
        int totalPrice4=countItem4*price4;

        int totalPrice=totalPrice1+totalPrice2+totalPrice3+totalPrice4;

        count1=(TextView)findViewById(R.id.count1);
        count2=(TextView)findViewById(R.id.count2);
        count3=(TextView)findViewById(R.id.count3);
        count4=(TextView)findViewById(R.id.count4);

        total1=(TextView)findViewById(R.id.total1);
        total2=(TextView)findViewById(R.id.total2);
        total3=(TextView)findViewById(R.id.total3);
        total4=(TextView)findViewById(R.id.total4);

        gTotal=(TextView)findViewById(R.id.gTotal) ;

        count1.setText(""+countItem1);
        count2.setText(""+countItem2);
        count3.setText(""+countItem3);
        count4.setText(""+countItem4);

        total1.setText(""+totalPrice1);
        total2.setText(""+totalPrice2);
        total3.setText(""+totalPrice3);
        total4.setText(""+totalPrice4);

        gTotal.setText(""+totalPrice);

    }
}
