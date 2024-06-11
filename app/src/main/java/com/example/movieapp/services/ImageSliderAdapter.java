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
    import com.example.movieapp.model.api.MovieDetail;

    import java.util.List;
    
    public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ViewHolder> {
        private List<MovieDetail> imageUrls;
        private Context context;

    
        public ImageSliderAdapter(Context context, List<MovieDetail> imageUrls) {
            this.context = context;
            this.imageUrls = imageUrls;
        }

        @SuppressLint("MissingInflatedId")
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            int actualPosition = position % imageUrls.size();
            Glide.with(context)
                    .load(imageUrls.get(actualPosition).getPosterURL())
                    .into(holder.imageView);

            holder.setImgViewClickListener(imageUrls.get(actualPosition).getSlug(), context);
        }
    
        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }
    
        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
            }

            public void setImgViewClickListener(final String slug, Context context) {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, MovieDetailActivity.class);
                        Bundle bundle = new Bundle();
                        Log.d(TAG, "SLUG: " + slug);
                        bundle.putString("slug", slug);
                        intent.putExtra("bundle", bundle);
                        context.startActivity(intent);
                    }
                });
            }
        }
    }
