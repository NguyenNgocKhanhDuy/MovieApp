package com.example.movieapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.movieapp.BaseFragment;
import com.example.movieapp.FragmentMoviesBinding;
import com.example.movieapp.MoviesViewModel;


public class MoviesFragment extends BaseFragment {

    private MoviesViewModel viewModel;
    private FragmentMoviesBinding viewDataBinding;

    public MoviesFragment() {
        super(false);
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        viewDataBinding = FragmentMoviesBinding.inflate(inflater, container, false);
//        viewDataBinding.setViewmodel(viewModel);
//        viewDataBinding.setLifecycleOwner(getViewLifecycleOwner());
//        return viewDataBinding.getRoot();
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        viewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
//        setupViewModelObservers();
//    }

//    private void setupViewModelObservers() {
//        viewModel.getSnackBarText().observe(getViewLifecycleOwner(), new EventObserver<>(text -> {
//            if (getView() != null) {
//                SnackbarUtils.showSnackBar(getView(), text);
//            }
//        }));
//
//        viewModel.getGoToMovieDetailsEvent().observe(getViewLifecycleOwner(), new EventObserver<>(event -> {
//            if (event != null) {
//                navigateToMovieDetails(event.getId(), event.getTitle());
//            }
//        }));
//    }
//
//    private void navigateToMovieDetails(int movieId, String movieTitle) {
//        MoviesFragmentDirections.ActionNavigationMoviesToMovieDetailsFragment action =
//                MoviesFragmentDirections.actionNavigationMoviesToMovieDetailsFragment(movieId, movieTitle);
//        NavHostFragment.findNavController(this).navigate(action);
//    }
}
