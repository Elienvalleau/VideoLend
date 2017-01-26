package com.example.elien.videolend;

        import android.text.format.Time;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.text.SimpleDateFormat;


public class JsonParser {

    final String LOG_TAG = JsonParser.class.getSimpleName();

    static String[] getMovieDataFromJson(String movie) throws JSONException {

        final String MDB_LIST = "list";
        final String MDB_TITLE = "title";
        final String MDB_YEAR = "year";
        final String MDB_IMDBID = "imdbID";
        final String MDB_TYPE = "Type";
        final String MDB_POSTER = "Poster";
        final String MDB_RUNTIME = "Runtime";
        final String MDB_GENRE = "Genre";
        final String MDB_DIRECTOR = "Director";
        final String MDB_WRITER = "Writer";
        final String MDB_ACTORS = "Actors";
        final String MDB_PLOT = "Plot";
        final String MDB_LANGUAGE = "Language";
        final String MDB_COUNTRY = "Country";
        final String MDB_AWARDS = "Awards";

        JSONObject forecastJSON = new JSONObject(movie);
        JSONArray movieArray = forecastJSON.getJSONArray(MDB_LIST);

    }
}