package com.example.tutorial_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserData extends AppCompatActivity {

    //TextView Creation part start here...
    TextView textViewName;
    TextView textViewUsername;
    TextView textViewEmail;
    TextView textViewStreet;
    TextView textViewSuite;
    TextView textViewCity;
    TextView textViewZipcode;
    TextView textViewLat;
    TextView textViewLng;
    TextView textViewPhone;
    TextView textViewWebsite;
    TextView textViewCompName;
    TextView textViewCompCatchPhrase;
    TextView textViewCompBs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        this.setTitle("User Data");

        Intent intent=getIntent();
        //TextView Initialization part start here...
        textViewName = findViewById(R.id.txtName);
        textViewUsername=findViewById(R.id.txtUsername);
        textViewEmail = findViewById(R.id.txtEmail);
        textViewStreet=findViewById(R.id.txtStreet);
        textViewSuite = findViewById(R.id.txtSuite);
        textViewCity=findViewById(R.id.txtCity);
        textViewZipcode = findViewById(R.id.txtZipcode);
        textViewLat=findViewById(R.id.txtLat);
        textViewLng = findViewById(R.id.txtLng);
        textViewPhone=findViewById(R.id.txtPhone);
        textViewWebsite=findViewById(R.id.txtWebsite);
        textViewCompName = findViewById(R.id.txtCompName);
        textViewCompCatchPhrase=findViewById(R.id.txtCompCatchPhrase);
        textViewCompBs = findViewById(R.id.txtCompBs);
        //TextView Initialization part finished here...
        int i=intent.getIntExtra("Userdata",0);

        try {
            JSONObject userobj=MyUtil.userdata.getJSONObject(i);
            textViewName.setText("Name : "+userobj.getString("name"));
            textViewUsername.setText("Username : "+userobj.getString("username"));
            textViewEmail.setText("Email : "+userobj.getString("email"));
            textViewPhone.setText("Phone : "+userobj.getString("phone"));
            textViewWebsite.setText("Website : "+userobj.getString("website"));

            //Fetching Address JSON Object Values....
            JSONObject jsonObjectAddress=userobj.getJSONObject("address");

            textViewStreet.setText("Street : "+jsonObjectAddress.getString("street"));
            textViewSuite.setText("Suite : "+jsonObjectAddress.getString("suite"));
            textViewCity.setText("City : "+jsonObjectAddress.getString("city"));
            textViewZipcode.setText("Zipcode : "+jsonObjectAddress.getString("zipcode"));
            //--------------------------------------

            //Fetching geo JSON Object Values....
            JSONObject jsonObjectGeo=jsonObjectAddress.getJSONObject("geo");

            textViewLat.setText("Lat : "+jsonObjectGeo.getString("lat"));
            textViewLng.setText("Lng : "+jsonObjectGeo.getString("lng"));
            //--------------------------------------

            //Fetching Company JSON Object Values....
            textViewCompName.setText("Name : "+userobj.getJSONObject("company").getString("name"));
            textViewCompCatchPhrase.setText("Catch Phrase : "+userobj.getJSONObject("company").getString("catchPhrase"));
            textViewCompBs.setText("bs : "+userobj.getJSONObject("company").getString("bs"));
            //--------------------------------------

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}