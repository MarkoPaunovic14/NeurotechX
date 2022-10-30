package com.example.alertonroad;

import static com.example.alertonroad.MainActivity.mp;

import android.os.AsyncTask;
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
//                System.out.println("Result = " + result);
            //notify
            System.out.println("Notify = " + result.charAt(result.length() - 16));
            if ("1".charAt(0) == result.charAt(result.length() - 16)) {
//                Toast.makeText(MainActivity.this, "DANGEROUS DRIVER NEARBY!", Toast.LENGTH_SHORT).show();
                // Moze notifikacija
                // TODO Sound upozorenja, UPDATE server
            }

            //sleepy
            System.out.println("Sleepy = " + result.charAt(result.length() - 3));
            if ("1".charAt(0) == result.charAt(result.length() - 3)) {
//                Toast.makeText(MainActivity.this, "Pull Over And Take A Power Nap!", Toast.LENGTH_SHORT).show();
                if (mp.isPlaying()) {
                    mp.seekTo(0);
                } else {
                    mp.start();
                    MainActivity.v.vibrate(3000);
                }
            }
        }
    }
}
