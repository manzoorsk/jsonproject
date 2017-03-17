package com.example.admin.jsonproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListView extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ist_view);
        json_string=getIntent().getExtras().getString("JSON DATA");
        try {
            jsonObject=new JSONObject(json_string);
            jsonArray =jsonObject.getJSONArray("request");
            int count=0;
            while (count<jsonArray.length()){
                JSONObject jo=jsonArray.getJSONObject(count);
                

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
