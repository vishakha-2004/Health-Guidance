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
public class MedicalBooksActivity extends AppCompatActivity {

    private String[][] book_details = {
            {"The Doctor's Book of Home Remedies","","","","https://archive.org/details/doctorsbookofhom00roda"},
            {"How Not to Die ","","","","https://search.usa.gov/search?affiliate=hip&query=coronavirus"},
            {"Mind Over Medicine","","","","https://archive.org/details/how-not-to-die-michael-greger"},
            {"Eat to Beat Disease","","","","https://drwilliamli.com/wp-content/uploads/2019/01/A-Plant-Based-Approach-to-Eating-To-Beat-Disease.pdf"},
            {"The Complete Guide to Fasting","","","","https://pdfroom.com/books/the-complete-guide-to-fasting-heal-your-body-through-intermittent-alternate-day-and-extended/p0q2JLNodxE"},
            {"The Science of Yoga","","","","https://archive.org/details/TheScienceOfYoga"},
            {"You Are Your Own Gym","","","","https://www.academia.edu/33497617/You_Are_Your_Own_Gym_pdf"},
            {"The Happiness Trap","","","","https://thehappinesstrap.com/wp-content/uploads/2017/06/The_Happiness_Trap_-_Introduction_and_Chapter_one.pdf"},
            {"Botanical Medicine for Women's Health","","","","https://akusher-lib.ru/wp-content/uploads/2019/05/Botanical-medicine-for-womens-health.pdf"},
            {"Understanding Your Menstrual Cycle","","","","https://femalehealthawareness.org/en/home/"},
            {"The Happy Hormone Guide","","","","https://samaritannj.org/wp-content/uploads/Happy-Hormones-at-Work.pdf"},
            {"Your Pregnancy Week by Week","","","","https://poliklinika-harni.hr/images/uploads/249/trudnoca-po-tjednima.pdf"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_books);

        HashMap<String,String> item ;
        SimpleAdapter simpleAdapter;
        ArrayList list;

        Button exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MedicalBooksActivity.this , HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0;i<book_details.length;i++){
            item = new HashMap<>();
            item.put("line 1",book_details[i][0]);
            item.put("line 2",book_details[i][1]);
            item.put("line 3",book_details[i][2]);
            item.put("line 4",book_details[i][3]);
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
                String url = book_details[position][4];  // Get the URL from the details array
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
}
