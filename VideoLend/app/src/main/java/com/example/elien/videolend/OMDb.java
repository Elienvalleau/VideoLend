package com.example.elien.videolend;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OMDb {

    private final String LOG_TAG = String.valueOf(this.getClass());

    OMDbListener mListner;

    public OMDb(OMDbListener listener) {
        this.mListner= listener;
    }

    private String search = "batman";
    private int page = 1;

    @Override
    protected String[] doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;


        try {

            final String FORECAST_BASE_URL =
                    "http://www.omdbapi.com/?";
            final String SEARCH_PARAM = "s";
            final String PAGE_PARAM = "page";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(SEARCH_PARAM,search)
                    .appendQueryParameter(PAGE_PARAM,Integer.toString(page))
                    .build();

            URL url = new URL(builtUri.toString());


            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            forecastJsonStr = buffer.toString();
            try {
                return JsonParser.getMovieDataFromJson(forecastJsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.e(LOG_TAG,forecastJsonStr);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }


        return null;
    }


    @Override
    protected void onPostExecute(String[] result) { mListner.didGetData(result); }
}
