package com.jatapp.popular_movies_stage1_udacity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jatapp.popular_movies_stage1_udacity.model.Movie;
import com.jatapp.popular_movies_stage1_udacity.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity {
    @BindView(R.id.img_poster)
    ImageView img_poster;

    @BindView(R.id.title_tv)
    TextView title_tv;

    @BindView(R.id.user_rating_tv)
    TextView user_rating_tv;

    @BindView(R.id.release_date_tv)
    TextView release_date_tv;

    @BindView(R.id.synopsis_tv)
    TextView synopsis_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        Intent intentMovieDetail = getIntent();

        if (intentMovieDetail == null ||  !intentMovieDetail.hasExtra(MainActivity.MOVIE_DETAIL_INTENT)) {
                closeOnError();
        }else{
            Movie movie = intentMovieDetail.getParcelableExtra(MainActivity.MOVIE_DETAIL_INTENT);
            Toast.makeText(this,movie.getTitle(),Toast.LENGTH_LONG).show();
            setTitle(movie.getTitle());
            populateUI(movie);
        }

    }

    private void populateUI(Movie movie) {
        title_tv.setText(movie.getOriginal_title());
        user_rating_tv.setText(movie.getVote_average());
        release_date_tv.setText(movie.getRelease_date());
        synopsis_tv.setText(movie.getOverview());

        URL url = NetworkUtils.buildUrlImage(movie.getPoster());
        String img_path = url.toString();
        Picasso.with(this).load(img_path).into(img_poster);
    }


    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }
}
