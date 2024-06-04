package com.example.movieapp.dao;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.movieapp.model.db.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static UserDao instance;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userRef = database.getReference("Users");

    public UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) instance = new UserDao();
        return instance;
    }

    public void insertUser(User user) {
        userRef.child(user.getEmail()).setValue(user);
    }

    public List<User> selectAllUser(FirebaseCallback callback) {
        userRef = database.getReference("Users");
        List<User> userList = new ArrayList<>();

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    if (user != null) {
                        userList.add(user);
                    }
                }
                callback.onDataReceivedList(userList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });


        return userList;
    }

    public void select(String key, final FirebaseCallback callback) {
        userRef.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User userDB = dataSnapshot.getValue(User.class);

                if (userDB != null) {
                    callback.onDataReceived(userDB);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }

}
