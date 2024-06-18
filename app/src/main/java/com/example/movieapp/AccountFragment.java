package com.example.movieapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class AccountFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        View editProfile = view.findViewById(R.id.edit_profile);
        View changePassword = view.findViewById(R.id.change_password);
        View notification = view.findViewById(R.id.notify);
        View security = view.findViewById(R.id.security);
        View language = view.findViewById(R.id.language);
        View legal = view.findViewById(R.id.legal);
        View help = view.findViewById(R.id.help);
        View logout = view.findViewById(R.id.logout);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) View history = view.findViewById(R.id.history);
        editProfile.setOnClickListener(View ->{
            startActivity(new Intent(getActivity(), EditProfile.class));
        });
        changePassword.setOnClickListener(View ->{
            startActivity(new Intent(getActivity(), ChangePassword.class));
        });
        language.setOnClickListener(View ->{
            startActivity(new Intent(getActivity(), ChangeLanguage.class));
        });
        history.setOnClickListener(View -> {
            startActivity(new Intent(getActivity(), HistoryActivity.class));
        });
        logout.setOnClickListener(View ->{
            FirebaseAuth.getInstance().signOut();
            getActivity().finish();
           Intent intent = new Intent(getActivity(), Login.class);
//            // Đặt lại cờ để tránh quay lại Fragment khi nhấn nút Back
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
        return view;
    }
}
