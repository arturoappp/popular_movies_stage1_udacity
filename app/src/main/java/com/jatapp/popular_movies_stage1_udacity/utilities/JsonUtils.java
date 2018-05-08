package com.jatapp.popular_movies_stage1_udacity.utilities;


import com.jatapp.popular_movies_stage1_udacity.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String KEY_RESULTS_LIST_MOVIES = "results";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ORIGINAL_TITLE = "original_title";
    private static final String KEY_POSTER = "poster_path";
    private static final String KEY_SYNOPSIS = "overview";
    private static final String KEY_USER_RATING = "vote_average";
    private static final String KEY_RELASE_DATE = "release_date";

    public static List<Movie> parseMovieJson(String json) throws JSONException {
        List<Movie> movieList = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArrayMovies = jsonObject.optJSONArray(KEY_RESULTS_LIST_MOVIES);

        for (int i = 0; i < jsonArrayMovies.length(); i++) {
            JSONObject movieJSON = jsonArrayMovies.optJSONObject(i);
            String id = movieJSON.optString(KEY_ID);
            String title = movieJSON.optString(KEY_TITLE);
            String original_title = movieJSON.optString(KEY_ORIGINAL_TITLE);
            String poster = movieJSON.optString(KEY_POSTER);
            String overview = movieJSON.optString(KEY_SYNOPSIS);
            String vote_average = movieJSON.optString(KEY_USER_RATING);
            String release_date = movieJSON.optString(KEY_RELASE_DATE);

            Movie movie = new Movie(id, title, original_title, poster, overview, vote_average, release_date);
            movieList.add(movie);
        }
        return movieList;
    }

    private static List<String> jSonArrayToList(JSONArray jsonArray) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0, l = jsonArray.length(); i < l; i++) {
            list.add(jsonArray.optString(i));
        }
        return list;
    }
}
