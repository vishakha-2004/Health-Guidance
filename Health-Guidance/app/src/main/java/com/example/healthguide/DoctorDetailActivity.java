package com.example.healthguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailActivity extends AppCompatActivity {


    private String[][] physician = {
            {"Doctor Name: Dr. Arun Kumar", "Hospital Address: 123 MG Road", "City: Bengaluru", "Experience: 15 years","1000", "Mobile No: 9876543210" },
            {"Doctor Name: Dr. Priya Singh", "Hospital Address: 456 Park Street", "City: Kolkata", "Experience: 8 years", "1050","Mobile No: 9876543211"},
            {"Doctor Name: Dr. Rajesh Patel", "Hospital Address: 789 Lajpat Nagar", "City: Delhi", "Experience: 10 years", "1500","Mobile No: 9876543212"},
            {"Doctor Name: Dr. Suman Gupta", "Hospital Address: 321 Marine Drive", "City: Mumbai", "Experience: 12 years","1500", "Mobile No: 9876543213"},
            {"Doctor Name: Dr. Vikram Desai", "Hospital Address: 654 MG Road", "City: Delhi", "Experience: 20 years", "2000","Mobile No: 9876543214"},{"Doctor Name: Dr. Ravi Shah", "Hospital Address: 987 Nungambakkam", "City: Chennai", "Experience: 17 years","1500", "Mobile No: 9876543225"},
            {"Doctor Name: Dr. Parul Desai", "Hospital Address: 123 Malad West", "City: Mumbai", "Experience: 6 years", "1400","Mobile No: 9876543226"},
            {"Doctor Name: Dr. Nisha Kaur", "Hospital Address: 456 Banjara Hills", "City: Hyderabad", "Experience: 9 years","4000", "Mobile No: 9876543227"},
            {"Doctor Name: Dr. Akash Roy", "Hospital Address: 789 Salt Lake", "City:Surat", "Experience: 13 years", "1050","Mobile No: 9876543228"},
            {"Doctor Name: Dr. Sneha Patel", "Hospital Address: 321 Vastrapur", "City: Ahmedabad", "Experience: 11 years", "2000","Mobile No: 9876543229"}
    };

   private String[][] cardiologist = {
            {"Doctor Name: Dr. Anjali Sharma", "Hospital Address: 987 Residency Road", "City: Hyderabad", "Experience: 9 years", "1000","Mobile No: 9876543215"},
            {"Doctor Name: Dr. Amitabh Joshi", "Hospital Address: 123 Rajpath", "City: Ahmedabad", "Experience: 11 years","1300", "Mobile No: 9876543216"},
            {"Doctor Name: Dr. Kavita Rao", "Hospital Address: 456 Nehru Street", "City: Chennai", "Experience: 13 years", "2000","Mobile No: 9876543217"},
            {"Doctor Name: Dr. Sanjay Kulkarni", "Hospital Address: 789 Mahatma Gandhi Road", "City: Jaipur", "Experience: 7 years", "1200","Mobile No: 9876543218"},
            {"Doctor Name: Dr. Meera Nair", "Hospital Address: 321 Brigade Road", "City: Kochi", "Experience: 18 years", "1060","Mobile No: 9876543219"},
           {"Doctor Name: Dr. Arun Kumar", "Hospital Address: 123 MG Road", "City: Bengaluru", "Experience: 15 years","1000", "Mobile No: 9876543210" },
           {"Doctor Name: Dr. Priya Singh", "Hospital Address: 456 Park Street", "City: Kolkata", "Experience: 8 years", "1050","Mobile No: 9876543211"},
           {"Doctor Name: Dr. Rajesh Patel", "Hospital Address: 789 Lajpat Nagar", "City: Delhi", "Experience: 10 years", "1500","Mobile No: 9876543212"},
           {"Doctor Name: Dr. Suman Gupta", "Hospital Address: 321 Marine Drive", "City: Mumbai", "Experience: 12 years","1500", "Mobile No: 9876543213"},
           {"Doctor Name: Dr. Vikram Desai", "Hospital Address: 654 MG Road", "City: Pune", "Experience: 20 years", "2000","Mobile No: 9876543214"}

   };

    private String[][] dietitian = {
            {"Doctor Name: Dr. Kiran Reddy", "Hospital Address: 456 Mount Road", "City: Chennai", "Experience: 10 years","1060", "Mobile No: 9876543220"},
            {"Doctor Name: Dr. Sunil Verma", "Hospital Address: 123 Gachibowli", "City: Hyderabad", "Experience: 14 years", "1400","Mobile No: 9876543221"},
            {"Doctor Name: Dr. Aditi Sharma", "Hospital Address: 456 Brigade Road", "City: Bengaluru", "Experience: 5 years","2000", "Mobile No: 9876543222"},
            {"Doctor Name: Dr. Manish Tiwari", "Hospital Address: 789 Park Street", "City: Kolkata", "Experience: 8 years", "1000","Mobile No: 9876543223"},
            {"Doctor Name: Dr. Ritu Mehra", "Hospital Address: 321 Connaught Place", "City: Delhi", "Experience: 20 years", "2000","Mobile No: 9876543224"},
            {"Doctor Name: Dr. Anjali Sharma", "Hospital Address: 987 Residency Road", "City: Hyderabad", "Experience: 9 years", "1000","Mobile No: 9876543215"},
            {"Doctor Name: Dr. Amitabh Joshi", "Hospital Address: 123 Rajpath", "City: Ahmedabad", "Experience: 11 years","1300", "Mobile No: 9876543216"},
            {"Doctor Name: Dr. Kavita Rao", "Hospital Address: 456 Nehru Street", "City: Chennai", "Experience: 13 years", "2000","Mobile No: 9876543217"},
            {"Doctor Name: Dr. Sanjay Kulkarni", "Hospital Address: 789 Mahatma Gandhi Road", "City: Jaipur", "Experience: 7 years", "1200","Mobile No: 9876543218"},
            {"Doctor Name: Dr. Meera Nair", "Hospital Address: 321 Brigade Road", "City: Kochi", "Experience: 18 years", "1060","Mobile No: 9876543219"},

    };

    private String[][] dentist = {
            {"Doctor Name: Dr. Ravi Shah", "Hospital Address: 987 Nungambakkam", "City: Chennai", "Experience: 17 years","1500", "Mobile No: 9876543225"},
            {"Doctor Name: Dr. Parul Desai", "Hospital Address: 123 Malad West", "City: Mumbai", "Experience: 6 years", "1400","Mobile No: 9876543226"},
            {"Doctor Name: Dr. Nisha Kaur", "Hospital Address: 456 Banjara Hills", "City: Hyderabad", "Experience: 9 years","4000", "Mobile No: 9876543227"},
            {"Doctor Name: Dr. Akash Roy", "Hospital Address: 789 Salt Lake", "City: Kolkata", "Experience: 13 years", "1050","Mobile No: 9876543228"},
            {"Doctor Name: Dr. Sneha Patel", "Hospital Address: 321 Vastrapur", "City: Ahmedabad", "Experience: 11 years", "2000","Mobile No: 9876543229"},
            {"Doctor Name: Dr. Kiran Reddy", "Hospital Address: 456 Mount Road", "City: Chennai", "Experience: 10 years","1060", "Mobile No: 9876543220"},
            {"Doctor Name: Dr. Sunil Verma", "Hospital Address: 123 Gachibowli", "City: Hyderabad", "Experience: 14 years", "1400","Mobile No: 9876543221"},
            {"Doctor Name: Dr. Aditi Sharma", "Hospital Address: 456 Brigade Road", "City: Bengaluru", "Experience: 5 years","2000", "Mobile No: 9876543222"},
            {"Doctor Name: Dr. Manish Tiwari", "Hospital Address: 789 Park Street", "City: Kolkata", "Experience: 8 years", "1000","Mobile No: 9876543223"},
            {"Doctor Name: Dr. Ritu Mehra", "Hospital Address: 321 Connaught Place", "City: Delhi", "Experience: 20 years", "2000","Mobile No: 9876543224"},

    };

    private String[][] surgeon = {
            {"Doctor Name: Dr. Vikram Khanna", "Hospital Address: 987 Goregaon", "City: Mumbai", "Experience: 19 years","1000", "Mobile No: 9876543230"},
            {"Doctor Name: Dr. Shweta Menon", "Hospital Address: 123 Sector 62", "City: Noida", "Experience: 12 years", "1300","Mobile No: 9876543231"},
            {"Doctor Name: Dr. Arjun Singh", "Hospital Address: 456 MG Road", "City: Indore", "Experience: 7 years", "1050","Mobile No: 9876543232"},
            {"Doctor Name: Dr. Pallavi Reddy", "Hospital Address: 789 Hitech City", "City: Hyderabad", "Experience: 15 years", "2000","Mobile No: 9876543233"},
            {"Doctor Name: Dr. Anil Gupta", "Hospital Address: 321 Jayanagar", "City: Bengaluru", "Experience: 18 years","1500", "Mobile No: 9876543234"},
            {"Doctor Name: Dr. Ravi Shah", "Hospital Address: 987 Nungambakkam", "City: Chennai", "Experience: 17 years","1500", "Mobile No: 9876543225"},
            {"Doctor Name: Dr. Parul Desai", "Hospital Address: 123 Malad West", "City: Mumbai", "Experience: 6 years", "1400","Mobile No: 9876543226"},
            {"Doctor Name: Dr. Nisha Kaur", "Hospital Address: 456 Banjara Hills", "City: Hyderabad", "Experience: 9 years","4000", "Mobile No: 9876543227"},
            {"Doctor Name: Dr. Akash Roy", "Hospital Address: 789 Salt Lake", "City: Kolkata", "Experience: 13 years", "1050","Mobile No: 9876543228"},
            {"Doctor Name: Dr. Sneha Patel", "Hospital Address: 321 Vastrapur", "City: Ahmedabad", "Experience: 11 years", "2000","Mobile No: 9876543229"},
    };

    private String[][] neurologists = {
            {"Doctor Name: Dr. Anil Gupta", "Hospital Address: 101 Neuro Clinic, Bandra", "City: Mumbai", "Experience: 15 years", "1200", "Mobile No: 9876543210"},
            {"Doctor Name: Dr. Priya Mehta", "Hospital Address: 202 Neuro Care Center, Andheri", "City: Mumbai", "Experience: 10 years", "1000", "Mobile No: 9876543211"},
            {"Doctor Name: Dr. Rajesh Kumar", "Hospital Address: 303 Neuro Health Clinic, Malad", "City: Mumbai", "Experience: 20 years", "1500", "Mobile No: 9876543212"},
            {"Doctor Name: Dr. Seema Sharma", "Hospital Address: 404 Brain Specialist, Dadar", "City: Mumbai", "Experience: 12 years", "1100", "Mobile No: 9876543213"},
            {"Doctor Name: Dr. Vikram Joshi", "Hospital Address: 505 Neuro Care Hospital, Powai", "City: Mumbai", "Experience: 18 years", "1300", "Mobile No: 9876543214"},
            {"Doctor Name: Dr. Aarti Sharma", "Hospital Address: 101 Neuro Center, Connaught Place", "City: Delhi", "Experience: 14 years", "1100", "Mobile No: 9876543220"},
            {"Doctor Name: Dr. Rajeev Kumar", "Hospital Address: 202 Brain Clinic, South Delhi", "City: Delhi", "Experience: 22 years", "1400", "Mobile No: 9876543221"},
            {"Doctor Name: Dr. Neelam Singh", "Hospital Address: 303 Neuro Wellness Center, Rohini", "City: Delhi", "Experience: 11 years", "1000", "Mobile No: 9876543222"},
            {"Doctor Name: Dr. Suresh Reddy", "Hospital Address: 101 Neuro Hospital, Nungambakkam", "City: Chennai", "Experience: 16 years", "1200", "Mobile No: 9876543230"},
            {"Doctor Name: Dr. Meena Iyer", "Hospital Address: 202 Brain Clinic, Adyar", "City: Chennai", "Experience: 9 years", "950", "Mobile No: 9876543231"},
            {"Doctor Name: Dr. Arun Raj", "Hospital Address: 303 Neuro Care Institute, T. Nagar", "City: Chennai", "Experience: 14 years", "1100", "Mobile No: 9876543232"},
            {"Doctor Name: Dr. Ravi Menon", "Hospital Address: 101 Neuro Clinic, Whitefield", "City: Bangalore", "Experience: 12 years", "1050", "Mobile No: 9876543240"},
            {"Doctor Name: Dr. Lakshmi Nair", "Hospital Address: 202 Brain Center, Koramangala", "City: Bangalore", "Experience: 15 years", "1150", "Mobile No: 9876543241"},
            {"Doctor Name: Dr. Sunitha Rao", "Hospital Address: 101 Neuro Center, Banjara Hills", "City: Hyderabad", "Experience: 13 years", "1000", "Mobile No: 9876543250"},
            {"Doctor Name: Dr. Kiran Kumar", "Hospital Address: 202 Brain Clinic, Hitech City", "City: Hyderabad", "Experience: 17 years", "1200", "Mobile No: 9876543251"},
            {"Doctor Name: Dr. Priyanka Ghosh", "Hospital Address: 101 Neuro Hospital, Salt Lake", "City: Kolkata", "Experience: 10 years", "950", "Mobile No: 9876543260"},
            {"Doctor Name: Dr. Anirban Roy", "Hospital Address: 202 Brain Care Clinic, Park Street", "City: Kolkata", "Experience: 20 years", "1300", "Mobile No: 9876543261"}
    };

    private String[][] eyeSpecialists = {
            {"Doctor Name: Dr. Ramesh Patel", "Hospital Address: 101 Eye Care Clinic, Colaba", "City: Mumbai", "Experience: 20 years", "1500", "Mobile No: 9876543310"},
            {"Doctor Name: Dr. Neha Agarwal", "Hospital Address: 202 Vision Center, Malabar Hill", "City: Mumbai", "Experience: 12 years", "1200", "Mobile No: 9876543311"},
            {"Doctor Name: Dr. Sameer Khan", "Hospital Address: 303 Eye Hospital, Borivali", "City: Mumbai", "Experience: 18 years", "1400", "Mobile No: 9876543312"},
            {"Doctor Name: Dr. Sneha Desai", "Hospital Address: 404 Retina Care Center, Ghatkopar", "City: Mumbai", "Experience: 15 years", "1300", "Mobile No: 9876543313"},
            {"Doctor Name: Dr. Anil Sinha", "Hospital Address: 505 Ophthalmology Clinic, Andheri", "City: Mumbai", "Experience: 22 years", "1600", "Mobile No: 9876543314"},
            {"Doctor Name: Dr. Aarti Verma", "Hospital Address: 101 Eye Clinic, Greater Kailash", "City: Delhi", "Experience: 14 years", "1200", "Mobile No: 9876543320"},
            {"Doctor Name: Dr. Sanjay Gupta", "Hospital Address: 202 Vision Hospital, Dwarka", "City: Delhi", "Experience: 17 years", "1400", "Mobile No: 9876543321"},
            {"Doctor Name: Dr. Meera Jain", "Hospital Address: 303 Eye Care Center, Saket", "City: Delhi", "Experience: 10 years", "1000", "Mobile No: 9876543322"},
            {"Doctor Name: Dr. Rajesh Reddy", "Hospital Address: 101 Eye Clinic, T. Nagar", "City: Chennai", "Experience: 16 years", "1300", "Mobile No: 9876543330"},
            {"Doctor Name: Dr. Lakshmi Kumar", "Hospital Address: 202 Vision Center, Mylapore", "City: Chennai", "Experience: 14 years", "1150", "Mobile No: 9876543331"},
            {"Doctor Name: Dr. Arun Kumar", "Hospital Address: 303 Ophthalmology Center, Adyar", "City: Chennai", "Experience: 12 years", "1100", "Mobile No: 9876543332"},
            {"Doctor Name: Dr. Ravi Sharma", "Hospital Address: 101 Eye Care Center, Indiranagar", "City: Bangalore", "Experience: 15 years", "1200", "Mobile No: 9876543340"},
            {"Doctor Name: Dr. Suma Reddy", "Hospital Address: 202 Vision Clinic, Koramangala", "City: Bangalore", "Experience: 18 years", "1400", "Mobile No: 9876543341"},
            {"Doctor Name: Dr. Sunil Mehta", "Hospital Address: 101 Eye Hospital, Banjara Hills", "City: Hyderabad", "Experience: 13 years", "1100", "Mobile No: 9876543350"},
            {"Doctor Name: Dr. Nisha Rao", "Hospital Address: 202 Vision Center, Hitech City", "City: Hyderabad", "Experience: 16 years", "1300", "Mobile No: 9876543351"},
            {"Doctor Name: Dr. Priyanka Sen", "Hospital Address: 101 Eye Clinic, Salt Lake", "City: Kolkata", "Experience: 11 years", "1050", "Mobile No: 9876543360"},
            {"Doctor Name: Dr. Ankit Roy", "Hospital Address: 202 Vision Care Center, Park Street", "City: Kolkata", "Experience: 19 years", "1400", "Mobile No: 9876543361"}
    };

    private String[][] gynecologists = {
            {"Doctor Name: Dr. Ayesha Khan", "Hospital Address: 101 Women’s Health Clinic, Bandra", "City: Mumbai", "Experience: 22 years", "1500", "Mobile No: 9876543410"},
            {"Doctor Name: Dr. Priya Mehta", "Hospital Address: 202 Gynecology Center, Juhu", "City: Mumbai", "Experience: 15 years", "1300", "Mobile No: 9876543411"},
            {"Doctor Name: Dr. Ritu Sharma", "Hospital Address: 303 Maternity Clinic, Malad", "City: Mumbai", "Experience: 18 years", "1400", "Mobile No: 9876543412"},
            {"Doctor Name: Dr. Meenal Deshmukh", "Hospital Address: 404 Women’s Health Center, Worli", "City: Mumbai", "Experience: 20 years", "1350", "Mobile No: 9876543413"},
            {"Doctor Name: Dr. Neha Jain", "Hospital Address: 505 Gynecology Clinic, Andheri", "City: Mumbai", "Experience: 17 years", "1250", "Mobile No: 9876543414"},
            {"Doctor Name: Dr. Anjali Verma", "Hospital Address: 101 Women’s Health Clinic, South Delhi", "City: Delhi", "Experience: 19 years", "1400", "Mobile No: 9876543420"},
            {"Doctor Name: Dr. Ritu Gupta", "Hospital Address: 202 Gynecology Center, Vasant Kunj", "City: Delhi", "Experience: 16 years", "1300", "Mobile No: 9876543421"},
            {"Doctor Name: Dr. Suman Arora", "Hospital Address: 303 Maternity Clinic, Janakpuri", "City: Delhi", "Experience: 14 years", "1200", "Mobile No: 9876543422"},
            {"Doctor Name: Dr. Swathi Reddy", "Hospital Address: 101 Women’s Clinic, T. Nagar", "City: Chennai", "Experience: 18 years", "1350", "Mobile No: 9876543430"},
            {"Doctor Name: Dr. Lakshmi Suresh", "Hospital Address: 202 Gynecology Center, Adyar", "City: Chennai", "Experience: 15 years", "1250", "Mobile No: 9876543431"}
    };
    private String[][] dermatologists = {
            {"Doctor Name: Dr. Ankur Agarwal", "Hospital Address: 101 Skin Care Clinic, Bandra", "City: Mumbai", "Experience: 21 years", "1500", "Mobile No: 9876543510"},
            {"Doctor Name: Dr. Shweta Desai", "Hospital Address: 202 Dermatology Center, Juhu", "City: Mumbai", "Experience: 18 years", "1400", "Mobile No: 9876543511"},
            {"Doctor Name: Dr. Rahul Patel", "Hospital Address: 303 Skin Specialist Clinic, Malad", "City: Mumbai", "Experience: 20 years", "1450", "Mobile No: 9876543512"},
            {"Doctor Name: Dr. Priya Kapoor", "Hospital Address: 404 Skin Clinic, Worli", "City: Mumbai", "Experience: 16 years", "1300", "Mobile No: 9876543513"},
            {"Doctor Name: Dr. Meera Reddy", "Hospital Address: 505 Dermatology Clinic, Andheri", "City: Mumbai", "Experience: 17 years", "1350", "Mobile No: 9876543514"},
            {"Doctor Name: Dr. Rajesh Kumar", "Hospital Address: 101 Skin Care Clinic, South Delhi", "City: Delhi", "Experience: 19 years", "1400", "Mobile No: 9876543520"},
            {"Doctor Name: Dr. Neelam Jain", "Hospital Address: 202 Dermatology Center, Vasant Kunj", "City: Delhi", "Experience: 14 years", "1250", "Mobile No: 9876543521"},
            {"Doctor Name: Dr. Alok Verma", "Hospital Address: 303 Skin Specialist Clinic, Janakpuri", "City: Delhi", "Experience: 22 years", "1550", "Mobile No: 9876543522"},
            {"Doctor Name: Dr. Anitha Subramanian", "Hospital Address: 101 Skin Care Clinic, T. Nagar", "City: Chennai", "Experience: 20 years", "1350", "Mobile No: 9876543530"},
            {"Doctor Name: Dr. Divya Nair", "Hospital Address: 202 Dermatology Center, Adyar", "City: Chennai", "Experience: 17 years", "1300", "Mobile No: 9876543531"}
    };

    private String[][] hairSpecialists = {
            {"Doctor Name: Dr. Sanjay Sharma", "Hospital Address: 101 Hair Restoration Clinic, Bandra", "City: Mumbai", "Experience: 15 years", "1600", "Mobile No: 9876543610"},
            {"Doctor Name: Dr. Neha Joshi", "Hospital Address: 202 Hair Care Center, Andheri", "City: Mumbai", "Experience: 12 years", "1500", "Mobile No: 9876543611"},
            {"Doctor Name: Dr. Arjun Singh", "Hospital Address: 303 Hair Specialist Clinic, Lower Parel", "City: Mumbai", "Experience: 18 years", "1700", "Mobile No: 9876543612"},
            {"Doctor Name: Dr. Meenal Agarwal", "Hospital Address: 404 Hair Clinic, Powai", "City: Mumbai", "Experience: 14 years", "1550", "Mobile No: 9876543613"},
            {"Doctor Name: Dr. Rohit Patel", "Hospital Address: 505 Hair Restoration Center, Malad", "City: Mumbai", "Experience: 16 years", "1600", "Mobile No: 9876543614"},
            {"Doctor Name: Dr. Aisha Khan", "Hospital Address: 101 Hair Care Clinic, South Delhi", "City: Delhi", "Experience: 13 years", "1450", "Mobile No: 9876543620"},
            {"Doctor Name: Dr. Rajeev Bhat", "Hospital Address: 202 Hair Specialist Center, Saket", "City: Delhi", "Experience: 17 years", "1500", "Mobile No: 9876543621"},
            {"Doctor Name: Dr. Priya Sharma", "Hospital Address: 303 Hair Restoration Clinic, Rohini", "City: Delhi", "Experience: 19 years", "1550", "Mobile No: 9876543622"},
            {"Doctor Name: Dr. Arvind Kumar", "Hospital Address: 101 Hair Clinic, T. Nagar", "City: Chennai", "Experience: 14 years", "1400", "Mobile No: 9876543630"},
            {"Doctor Name: Dr. Sushmita Reddy", "Hospital Address: 202 Hair Care Center, Adyar", "City: Chennai", "Experience: 16 years", "1450", "Mobile No: 9876543631"}
    };




    String[][] doctors = {};     //creating one more 2d empty array
    //depending on type of subtitle we set array to doctors array
    ArrayList list;
    SimpleAdapter simpleAdapter;
    HashMap<String,String> item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);


        TextView tv1 = findViewById(R.id.text);
        Intent i1 = getIntent();
        String doctorCategory = i1.getStringExtra("doctor_category");//get key name "title"
        String selectedCity = i1.getStringExtra("city");
        tv1.setText(doctorCategory);                        // and set that title to the text subtitle

        Button exit = findViewById(R.id.exitbtn);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailActivity.this , FindDocActivity.class));
            }
        });

        if(doctorCategory.compareTo("Physician")==0)
            doctors = physician;
        else
        if(doctorCategory.compareTo("Cardiologist")==0)
            doctors = cardiologist;
        else
        if(doctorCategory.compareTo("Dietitian")==0)
            doctors = dietitian;
        else
        if(doctorCategory.compareTo("Dentist")==0)
            doctors = dentist;
        else
        if(doctorCategory.compareTo("Neurologist")==0)
            doctors = neurologists;
        else
        if(doctorCategory.compareTo("Hair Specialist")==0)
            doctors = hairSpecialists;
        else
        if(doctorCategory.compareTo("Dermatologist")==0)
            doctors = dermatologists;
        else
        if(doctorCategory.compareTo("Gynaecologist")==0)
            doctors = gynecologists;
        else
        if(doctorCategory.compareTo("Eye Specialist")==0)
            doctors = eyeSpecialists;

        else
            doctors = surgeon;


//        list = new ArrayList();
//        for(int i=0;i<doctors.length;i++){
//            item = new HashMap<String,String>();  //hashmap object
//            item.put("line 1",doctors[i][0]);
//            item.put("line 2",doctors[i][1]);
//            item.put("line 3",doctors[i][2]);
//            item.put("line 4",doctors[i][3]);
//            item.put("line 5","consult fees:" +doctors[i][4]+"/-");
//            item.put("line 6",doctors[i][5]);
//            list.add(item);
//        }
            //ArrayList<>()--tis will hold multiple hashmap entries
        list = new ArrayList<>();//list--ArrayList that will store data in the form of HashMap objects
        for (String[] doctor : doctors) {
            if (doctor[2].contains(selectedCity)) {
                item = new HashMap<>();
                item.put("line 1", doctor[0]);
                item.put("line 2", doctor[1]);
                item.put("line 3", doctor[2]);
                item.put("line 4", doctor[3]);
                item.put("line 5", "consult fees:" + doctor[4] + "/-");
                item.put("line 6", doctor[5]);
                list.add(item);
            }
        }
        if(list.isEmpty()){
            Toast.makeText(DoctorDetailActivity.this , "No "+doctorCategory+" found in "+selectedCity,Toast.LENGTH_SHORT).show();
        }
        simpleAdapter = new SimpleAdapter(this ,list,
                R.layout.multi_lines,
                new String[]{"line 1","line 2","line 3","line 4","line 5", "line 6"},             //pick details from line1 ,line2 etc and
                new int[]{R.id.line1,R.id.line2,R.id.line3a,R.id.line3b,R.id.line3c,R.id.line4}  //map the details to multi_lines xml layout file
        );

        ListView lst = findViewById(R.id.list_item);
        lst.setAdapter(simpleAdapter);// set details to the listview
        // when we click on any list item , below code will redirect to book appointment page
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent i1 = new Intent(DoctorDetailActivity.this , appointmentActivity.class);
                i1.putExtra("text1" , doctorCategory);
                i1.putExtra("text2" , doctors[i][0]);
                i1.putExtra("text3" , doctors[i][1]);
                i1.putExtra("text4" , doctors[i][2]);
                //i1.putExtra("text5" , doctors[i][3]);
                i1.putExtra("text6" , doctors[i][4]);
                i1.putExtra("text7" , doctors[i][5]);
                startActivity(i1);
                //startActivity(new Intent(DoctorDetailActivity.this , appointmentActivity.class));
            }
      });
    }
}