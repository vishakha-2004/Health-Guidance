package com.example.healthguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class orderDetailsActivity extends AppCompatActivity {

    private String[][] orderdetails = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter simpleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        ListView lst=findViewById(R.id.list);

        Button exit=findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(orderDetailsActivity.this , HomeActivity.class));
            }
        });
        Database db = new Database(getApplicationContext() , "healthrecommendation" , null,1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_pre", Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("username","").toString();
        ArrayList Data = db.getOrderData(username);

        orderdetails = new String[Data.size()][];
        for(int i=0;i<orderdetails.length;i++){
            orderdetails[i]=new String[5];  //data is stored in orderdetails in 5 columns bcz we are again using multilines layout
            String arrData = Data.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            orderdetails[i][0]=strData[0];
            orderdetails[i][1]=strData[1];
            if(strData[7].compareTo("medicine")==0){
                orderdetails[i][3]="Del:"+strData[4];
            } else{
                orderdetails[i][3]="Del:"+strData[4]+" "+strData[5];
            }
//            orderdetails[i][2]= "Rs."+strData[6];
//            orderdetails[i][4]=strData[7];
        }

        list = new ArrayList();
        for(int i=0;i<orderdetails.length;i++){
            item=new HashMap<String,String>();
            item.put("line 1",orderdetails[i][0]);
            item.put("line 2",orderdetails[i][1]);
            item.put("line 3",orderdetails[i][2]);
            item.put("line 4",orderdetails[i][3]);
            item.put("line 5",orderdetails[i][4]);
            list.add(item);
        }

        simpleAdapter= new SimpleAdapter(this , list,
                R.layout.multi_lines,
                new String[] {"line 1","line 2","line 3","line 4","line 5"},
                new int[] {R.id.line1,R.id.line2,R.id.line3a,R.id.line3b,R.id.line3c});
        //ListView lst = findViewById(R.id.list);
        lst.setAdapter(simpleAdapter);
            }


    }

