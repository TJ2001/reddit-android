package com.example.guest.ourreddit.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.guest.ourreddit.R;

public class PostsActivity extends AppCompatActivity {
    private String TAG = PostsActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        String categoryId = getIntent().getStringExtra("categoryId");
        Log.d(TAG, categoryId);
    }
}
