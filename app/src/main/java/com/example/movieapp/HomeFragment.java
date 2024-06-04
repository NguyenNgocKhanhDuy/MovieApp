package com.example.movieapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.movieapp.services.ImageSliderAdapter;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);
        List<String> imageUrls = Arrays.asList(
                "data:image/jpeg;base64,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",
                "data:image/jpeg;base64,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",
                "https://d1j8r0kxyu9tj8.cloudfront.net/images/1566809317niNpzY2khA3tzMg.jpg"
        );

        ImageSliderAdapter adapter = new ImageSliderAdapter(getContext(), imageUrls);
        viewPager.setAdapter(adapter);


        // Adjust offscreenPageLimit to keep more items loaded
        viewPager.setOffscreenPageLimit(3);

        // Set initial position to the middle to allow for smooth infinite scrolling
        viewPager.setCurrentItem(adapter.getItemCount() / 2, false);
        // Add page change callback to reset position if needed
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                // Logic to handle page selection
                // Example logic to prevent swiping out of bounds
                if (position == 0 || position == adapter.getItemCount() - 1) {
                    // Don't allow swiping out of bounds
                    viewPager.setCurrentItem(position, false);
                }
            }
        });
        return view;
    }
}
