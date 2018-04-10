package com.example.seungeun.jungleproject2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by samsung on 2017-07-17.
 */



public class DataInsert extends AsyncTask<String, Void, String> {

    private static String TAG = "DataInsert ";
    private int Postresult = -10;
    private String postResponseResult = "";
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d(TAG, "POST response  - " + result);
        postResponseResult = result;
    }

    @Override
    protected String doInBackground(String... params) {

        int length = params.length;

        String serverURL = "";
        String postParameters = "";

        //serverURL = "http://10.0.2.2/" + params[0];
        serverURL = " http://jungle18.cafe24.com/" + params[0];
        for (int i = 1; i < length; i++) {
            postParameters += params[i];
            if (i % 2 == 1) {
                postParameters += "=";
            } else if ((i % 2 == 0) && (i != (length - 1))) {
                postParameters += "&";
            }
        }

        try {
            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // URL (http://10.0.2.2/user_information.php) 연결

            // timeout 설정
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("POST");
            //httpURLConnection.setRequestProperty("content-type", "application/json");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(postParameters.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            int responseStatusCode = httpURLConnection.getResponseCode();
            Log.d(TAG, "POST response code - " + responseStatusCode);
            Postresult = responseStatusCode;

            InputStream inputStream;
            if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
               // Log.d(TAG, "responseStatusCod == HTTP_OK");
            } else {
                inputStream = httpURLConnection.getErrorStream();
                //Log.d(TAG, "responseStatusCod != HTTP_OK");
            }


            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }


            bufferedReader.close();
            return sb.toString();


        } catch (Exception e) {

            Log.d(TAG, "Error ", e);

            return new String("Error: " + e.getMessage());
        }


    }

    public int getResultCode(){
        return Postresult;
    }
    public String getPostResponseResult() { return postResponseResult;}

}
