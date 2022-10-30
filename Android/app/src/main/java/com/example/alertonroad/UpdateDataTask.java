package com.example.alertonroad;

import static com.example.alertonroad.MainActivity.mpNotify;
import static com.example.alertonroad.MainActivity.mpSleepy;

import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateDataTask extends AsyncTask<String, String, String> {


    protected void onPreExecute() {
        super.onPreExecute();
//
//        pd = new ProgressDialog(MainActivity.this);
//        pd.setMessage("Please wait");
//        pd.setCancelable(true);
//        pd.show();
    }


    protected String doInBackground(String... params) {


        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                //Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

            }
//
            String result = buffer.toString();
            result = result.replaceAll("<[^>]*>", "");

            return result;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
//        if (pd.isShowing()){
//            pd.dismiss();
//        }
        if(result != null) {

            result = result.trim();

            System.out.println("Notify = " + result.charAt(result.length() - 16));
            if ("1".charAt(0) == result.charAt(result.length() - 16)) {
                mpNotify.start();
                MainActivity.v.vibrate(1000);

            }

            System.out.println("Sleepy = " + result.charAt(result.length() - 3));
            if ("1".charAt(0) == result.charAt(result.length() - 3)) {
                if (mpSleepy.isPlaying()) {
                    mpSleepy.seekTo(0);
                } else {
                    mpSleepy.start();
                    MainActivity.v.vibrate(3000);
                }
            }
        }
    }
}
