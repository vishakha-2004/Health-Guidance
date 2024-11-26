package com.example.healthguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticlesActivity extends AppCompatActivity {

    private String[][] health_details = {
            {"Dietary Guidelines","","","","https://health.gov/our-work/nutrition-physical-activity/dietary-guidelines"},
            {"Coronavirus","","","","https://search.usa.gov/search?affiliate=hip&query=coronavirus"},
            {"Mental Health","","","","https://search.usa.gov/search?affiliate=hip&query=mental%20health"},
            {"Diabetes","","","","https://search.usa.gov/search?affiliate=hip&query=diabetes"},
            {"Symptoms for Diabetes","","","","https://www.niddk.nih.gov/health-information/diabetes/overview/symptoms-causes"},
            {"Respiratory Illnesses","","","","https://www.cdc.gov/respiratory-viruses/about/index.html"},
            {"Vaccines for Adults","","","","https://www.cdc.gov/vaccines-adults/recommended-vaccines/index.html"},
            {"Agriculture worker safety","","","","https://www.cdc.gov/niosh/agriculture/about/"},
            {"Alcohol during Pregnancy","","","","https://www.cdc.gov/alcohol-pregnancy/about/index.html"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);

        HashMap<String,String> item ;
        SimpleAdapter simpleAdapter;
        ArrayList list;

        Button exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesActivity.this , HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0;i<health_details.length;i++){
            item = new HashMap<>();
            item.put("line 1",health_details[i][0]);
            item.put("line 2",health_details[i][1]);
            item.put("line 3",health_details[i][2]);
            item.put("line 4",health_details[i][3]);
            item.put("line 5","Click for more details");
            list.add(item);
        }

        simpleAdapter = new SimpleAdapter(this , list,
                R.layout.multi_lines,
                new String[]{"line 1","line 2","line 3","line 4","line 5"},
                new int[]{R.id.line1,R.id.line2,R.id.line3a,R.id.line3b,R.id.line3c});
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(simpleAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = health_details[position][4];  // Get the URL from the details array
                if (!url.isEmpty()) {
                    goToUrl(url);
                } else {
                    Toast.makeText(getApplicationContext(), "No website linked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void goToUrl(String url) {
        try {
            Uri uri = Uri.parse(url);  // Parse the string into a Uri or convert the URL string to a Uri object
            startActivity(new Intent(Intent.ACTION_VIEW, uri)); //use the Uri to open the URL in relevant app or browser by passing its object to intent
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Invalid URL", Toast.LENGTH_SHORT).show();
        }
    }

//    private int[] images = {
//            R.drawable.health1,
//            R.drawable.health2,
//            R.drawable.health3,
//            R.drawable.health4,
//            R.drawable.health5
//    };
// listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent i = new Intent(HealthArticlesActivity.this ,ArticleDetailsActivity.class);
//                i.putExtra("text 1",health_details[position][0]);
//                i.putExtra("text 2",images[position]);
//                startActivity(i);
//            }
//        });
}