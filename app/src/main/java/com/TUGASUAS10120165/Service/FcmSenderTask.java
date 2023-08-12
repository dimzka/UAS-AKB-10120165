package com.TUGASUAS10120165.Service;
// 10120165 - Muhamad Dimas Azka Syarif Umair - IF4
import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class FcmSenderTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = "FcmSenderTask";

    private String token;
    private  String serverKey = "AAAA4_bNyDM:APA91bEnSTNFDt8ViPV5dSotoj_vL9hWcMWjqags5zNueEVGMlvL_AyFadv5U_OqZluj6FyoLzeW6Y1y99d1DU9WO9lYHcnKdqeK42T9aGybX_YC8Ai2JdzAAEuG3LIoeS6_2WgF7KrJ";

    private String title;

    private String body;

    public FcmSenderTask(String token,  String title, String body) {
        this.token = token;
        this.title = title;
        this.body = body;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            String url = "https://fcm.googleapis.com/fcm/send";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set request method
            con.setRequestMethod("POST");

            // Set request headers
            con.setRequestProperty("Authorization", "key=" + serverKey);
            con.setRequestProperty("Content-Type", "application/json");

            // Enable input and output streams
            con.setDoOutput(true);

            // JSON payload
            String payload = "{"
                    + "\"to\": \"" + token + "\","
                    + "\"data\": {"
                    + "\"body\": \""+body+"\","
                    + "\"title\": \""+title+"\","
                    + "}"
                    + "}";

            // Send POST request
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(payload);
            wr.flush();
            wr.close();

            // Get response code
            int responseCode = con.getResponseCode();


            // Read response
            Scanner scanner = new Scanner(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            Log.d(TAG, "Notif Masuk");
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "Notif Gagal");
            return null;
        }
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        if (response != null) {
            Log.d(TAG, "Response: " + response);
        } else {
            Log.e(TAG, "Error sending FCM message.");
        }
    }
}
// 10120165 - Muhamad Dimas Azka Syarif Umair - IF4