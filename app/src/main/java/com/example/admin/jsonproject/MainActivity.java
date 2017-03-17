package com.example.admin.jsonproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView list_view;
    ArrayList arraylist;
    ProgressDialog mProgressDialog;

  Button button,button2;
    TextView text_view;
    String JSON_STRING;
    String json_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        text_view=(TextView)findViewById(R.id.text_view);
    }

      public void getJSON(View view){

        new BackgroundTask().execute();
      }
    class BackgroundTask extends AsyncTask<Void,Void,String>{

        String json_url;
        @Override
        protected void onPreExecute() {
            json_url="http://api.worldweatheronline.com/free/v1/weather.ashx?q=London&format=json&num_of_days=5&key=8mqumbup9fub7bcjtsxbzxx9";
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while ((JSON_STRING=bufferedReader.readLine())!=null){
                 stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textView=(TextView)findViewById(R.id.text_view);
            textView.setText(result);
            json_string=result;
        }
    }

    public void parseJSON(View view){
        if(json_string==null){
            Toast.makeText(getApplicationContext(),"JSON PARSE",Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent=new Intent(this,ListView.class);
            intent.putExtra("JSON DATA",json_string);
            startActivity(intent);
        }

    }
}

