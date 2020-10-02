package com.example.tutorial_10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    CustomAdapter adapter;
    ListView lstdata;
    //EditText Notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  Notes=findViewById(R.id.edtNotes);
        lstdata= (ListView) findViewById(R.id.lstdata);
       new MyAsyncTask().execute();
       lstdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent=new Intent(MainActivity.this,UserData.class);
               intent.putExtra("Userdata",i);
               startActivity(intent);
             //  finish();
           }
       });

    }

    class MyAsyncTask extends AsyncTask {
        ProgressDialog dialog;
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
          //  Loading Dailogue circle Create
          dialog=new ProgressDialog(MainActivity.this);
          dialog.show();
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            StringBuffer response= new StringBuffer();
            try {
                URL url = new URL(MyUtil.URL_USERS);
                HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();
                InputStreamReader ir =new InputStreamReader(urlConnection.getInputStream());
                BufferedReader br =new BufferedReader(ir);
                String inputLine=null;

                while ((inputLine=br.readLine()) !=null){
                    response.append(inputLine);
            }
                br.close();
                ir.close();
                MyUtil.userdata =new JSONArray(response.toString());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
           // initiate custom list adapter
            adapter=new CustomAdapter(MainActivity.this,MyUtil.userdata);
            lstdata.setAdapter(adapter);
            if(dialog.isShowing())dialog.dismiss();
        }
    }
}

