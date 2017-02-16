package com.gridview;

/**
 * Created by ramu on 16/02/17.
 */
        import android.app.Activity;
        import android.os.AsyncTask;
        import android.util.Log;

        import org.json.JSONArray;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

public abstract class Get extends AsyncTask<String, Void , String> {

    Activity activity;
    public String ourresult;

    private String TAG = "Get";

    protected Get(Activity activity) {
        this.activity = activity;
    }


    @Override
    protected String doInBackground(String... params) {

        Log.i(TAG,"do in bag");


        Integer result = 0;
        HttpURLConnection urlConnection;
        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            int statusCode = urlConnection.getResponseCode();
            Log.i(TAG,"status code"+statusCode);
             // 200 represents HTTP OK
            if (statusCode == 201 || statusCode == 200) {
                BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    response.append(line);
                }
                ourresult = response.toString();
                return ourresult;
            }
            else if (statusCode == 400 || statusCode == 401 || statusCode == 404) {

                JSONObject jsonObject=new JSONObject();
                jsonObject.put("Response",2);
                ourresult=jsonObject.toString();
            }
            else if(statusCode == 500)
            {
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("Response",3);
                ourresult=jsonObject.toString();
            }
            else
            {
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("Response",4);
                ourresult=jsonObject.toString();
            }
        } catch (Exception e) {
        }
        return ourresult; //"Failed to fetch data!";
    }


    @Override
    protected void onPostExecute(String result) {
        JSONArray json;
        try {
            Log.i(TAG,"post exec"+result);
            json = new JSONArray(result);
            onResponseReceived(json);
            //    setResult(json);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public abstract void onResponseReceived(JSONArray result);
}