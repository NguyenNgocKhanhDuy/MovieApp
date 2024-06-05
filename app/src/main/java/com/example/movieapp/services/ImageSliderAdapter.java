    package com.example.movieapp.services;
    
    import static android.content.ContentValues.TAG;

    import android.annotation.SuppressLint;
    import android.content.Context;
    import android.content.Intent;
    import android.os.Bundle;
    import android.os.Parcelable;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.LinearLayout;
    
    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;
    
    import com.bumptech.glide.Glide;
    import com.example.movieapp.MovieDetailActivity;
    import com.example.movieapp.R;
    import com.example.movieapp.model.api.Movie;

    import java.util.List;
    
    public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ViewHolder> {
        private List<Movie> imageUrls;
        private Context context;
    
        private LinearLayout watch;
    
    
        public ImageSliderAdapter(Context context, List<Movie> imageUrls) {
            this.context = context;
            this.imageUrls = imageUrls;
        }

        @SuppressLint("MissingInflatedId")
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
    
            watch = view.findViewById(R.id.watch);
    
            watch.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();
                    Intent intent = new Intent(context, MovieDetailActivity.class);
                    Movie movie = imageUrls.get(pos);
                    Bundle bundle = new Bundle();
                    bundle.putString("slug", movie.getSlug());
                    intent.putExtra("bundle", bundle);
                    context.startActivity(intent);
                }
            });

            watch.setTag(imageUrls.get(0));
    
            return new ViewHolder(view, watch);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            int actualPosition = position % imageUrls.size();
            Glide.with(context)
                    .load(imageUrls.get(actualPosition).getThumbURL())
                    .into(holder.imageView);


            holder.watch.setTag(actualPosition);
        }
    
        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }
    
        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            LinearLayout watch;
    
            public ViewHolder(@NonNull View itemView, LinearLayout watch) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
                this.watch = watch;
            }
        }
    }
