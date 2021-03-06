package com.example.bongotalkies.ui.adapter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.example.bongotalkies.constants.Constants;
import com.example.bongotalkies.databinding.MovieListBinding;
import com.example.bongotalkies.model.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends BaseAdapter {
    private MovieListBinding movieListBinding;
    private List<MovieModel> movieModels = new ArrayList<>(2);
    private int layoutMovieListId;
    private MovieItemClickListener movieItemClickListener;
    private Context context;


    public MovieListAdapter(Context context, int layoutMovieListId, List<MovieModel> movieModels) {
        this.context = context;
        this.layoutMovieListId = layoutMovieListId;
        this.movieModels = movieModels;
    }

    public void setOnItemClickListener(MovieItemClickListener movieItemClickListener) {
        this.movieItemClickListener = movieItemClickListener;
    }

    @Override
    public int getCount() {
        if(movieModels != null && movieModels.size() > 0)
            return movieModels.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if(v == null){
            v = LayoutInflater.from(context).inflate(layoutMovieListId, null);
            movieListBinding = DataBindingUtil.bind(v);
            movieListBinding.setMovieItemClicked(movieItemClickListener);
            v.setTag(movieListBinding);
        }else{
            movieListBinding = (MovieListBinding) v.getTag();
        }

        DrawableCrossFadeFactory factory =
                new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();

        Glide.with(context)
                .load(Constants.BASE_IMAGE_URL+movieModels.get(i).getPosterPath())
                .transition(withCrossFade(factory))
                .into(movieListBinding.imagePoster);

        movieListBinding.setMoviemodel(movieModels.get(i));

        return movieListBinding.getRoot();
    }




    public interface MovieItemClickListener{
        void onMovieItemClicked(MovieModel movieModel);
    }
}

