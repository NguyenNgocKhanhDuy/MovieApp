package com.example.movieapp.services;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.model.db.Movie;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MovieAdapter extends  RecyclerView.Adapter<MovieAdapter.MovieViewHolder> implements Filterable {
    private List<Movie> ListMovies;
    private List<Movie> ListMoviesOld;

    public MovieAdapter(List<Movie> listMovies) {

        this.ListMovies = listMovies;
        this.ListMoviesOld= listMovies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
       return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = ListMovies.get(position);
        if(movie==null){
            return;
        }
        holder.imgMovie.setImageResource(movie.getImage());
        holder.tvName.setText(movie.getName());
        holder.tvAddress.setText(movie.getAddress());

    }

    @Override
    public int getItemCount() {
        if(ListMovies!=null){
            return ListMovies.size();
        }
        return 0;
    }



    public class MovieViewHolder    extends RecyclerView.ViewHolder{
        private ImageView imgMovie;
        private TextView tvName;
        private TextView tvAddress;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.img_movie);
            tvName=itemView.findViewById(R.id.tv_name);
            tvAddress=itemView.findViewById(R.id.tv_address);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
               //Keyword seach tren search View
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    ListMovies= ListMoviesOld;
                }else {
                    List<Movie> list = new ArrayList<>();
                    for (Movie movie: ListMoviesOld){
                        if (movie.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(movie);
                        }
                    }
                    ListMovies=list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values= ListMovies;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                ListMovies=(List<Movie>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
