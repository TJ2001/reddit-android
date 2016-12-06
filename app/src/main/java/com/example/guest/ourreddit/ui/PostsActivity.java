package com.example.guest.ourreddit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.guest.ourreddit.R;
import com.example.guest.ourreddit.models.Category;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostsActivity extends AppCompatActivity {
    private String TAG = PostsActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mCategoryReference;
    private ArrayList<Category> mCategories;
    private int position;

    @Bind(R.id.postRecyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);
        mCategories = Parcels.unwrap(intent.getParcelableExtra("categories"));
        Log.d(TAG, position + "");
        Category category = mCategories.get(position);
        Log.d(TAG, category.getName());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        ButterKnife.bind(this);

    }




}
