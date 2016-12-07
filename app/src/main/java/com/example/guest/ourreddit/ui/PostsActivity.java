package com.example.guest.ourreddit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.guest.ourreddit.Constants;
import com.example.guest.ourreddit.R;
import com.example.guest.ourreddit.adapters.PostListAdapter;
import com.example.guest.ourreddit.models.Category;
import com.example.guest.ourreddit.models.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostsActivity extends AppCompatActivity {
    private String TAG = PostsActivity.class.getSimpleName();
    private DatabaseReference mPostsRefrence;
    private List<Category> mCategories = new ArrayList<>();
    private PostListAdapter mAdapter;
    private ArrayList<Post> mPosts = new ArrayList<>();
    private Category mCategory;
    private int position;

    @Bind(R.id.postRecyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);
        mCategories = Parcels.unwrap(intent.getParcelableExtra("categories"));
        mCategory = mCategories.get(position);
        setTitle("r/" + mCategory.getName());

        mPostsRefrence = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_POSTS);

        mPostsRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DatabaseReference postRef = FirebaseDatabase
                            .getInstance()
                            .getReference(Constants.FIREBASE_CHILD_POSTS);
                    Post post = snapshot.getValue(Post.class);
                    if (post.getCategoryId().equals(mCategory.getPushId())) {
                        mPosts.add(post);
                        Log.d(TAG,post.getPushId());
                    }
                }
                Log.d(TAG, mPosts.size() + "");
                mAdapter = new PostListAdapter(getApplicationContext(), mPosts);
                mRecyclerView.setAdapter(mAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PostsActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setHasFixedSize(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_posts,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                Log.d(TAG, "logging out");
                logout();
                return true;
            case R.id.newPost:
                Log.d(TAG, "new post");
                Intent intent = new Intent(PostsActivity.this, NewPostActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("categories", Parcels.wrap(mCategories));
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(PostsActivity.this, LoginActivity.class);
        intent.putExtra("position", position);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
