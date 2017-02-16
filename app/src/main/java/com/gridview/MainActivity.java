package com.gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Get get = new Get(this) {
            @Override
            public void onResponseReceived(JSONArray result) {
                Log.i(TAG,"result is"+result);
                List<Map> mapList = new ArrayList<>();

                if (result!= null && result.length()>0){

                    for (int i=0;i<result.length();i++){

                        HashMap hashMap = new HashMap();
                        try {
                            JSONObject jsonObject = result.getJSONObject(i);
                            String title = (String) jsonObject.get("login");
                            String url = (String) jsonObject.get("avatar_url");
                            String repourl = (String) jsonObject.get("repos_url");
                            hashMap.put("title",title);
                            hashMap.put("imgurl",url);
                            hashMap.put("repourl",repourl);
                            mapList.add(hashMap);
                            }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                }

            }
                GridView gridView = (GridView) findViewById(R.id.gridview);
                GridViewAdapter gridViewAdapter = new GridViewAdapter(MainActivity.this,mapList );
                gridView.setAdapter(gridViewAdapter);

            };
            };

        get.execute("https://api.github.com/users/hadley/orgs");


    }
}
