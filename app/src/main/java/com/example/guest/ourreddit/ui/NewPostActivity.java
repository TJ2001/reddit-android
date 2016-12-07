package com.example.guest.ourreddit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guest.ourreddit.Constants;
import com.example.guest.ourreddit.R;
import com.example.guest.ourreddit.models.Category;
import com.example.guest.ourreddit.models.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewPostActivity extends AppCompatActivity implements View.OnClickListener{
    private int position;
    private List<Category> mCategories;

    @Bind(R.id.postNameText) TextView mPostTextView;
    @Bind(R.id.addPostButton) Button mButton;
    private Post mPost;
    private String defaultPostImageUrl = "https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-reddit-square2-128.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        ButterKnife.bind(this);
        setTitle("New Post");
        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);
        mCategories = Parcels.unwrap(intent.getParcelableExtra("categories"));
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        String postName = mPostTextView.getText().toString();
        String categoryId = mCategories.get(position).getPushId();
        mPost = new Post(postName, "test body blah blah blah blah", categoryId, uid, defaultPostImageUrl);
        DatabaseReference categoryRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_POSTS);

        DatabaseReference pushRef = categoryRef.push();
        String pushId = pushRef.getKey();
        mPost.setPushId(pushId);
        pushRef.setValue(mPost);

        pushRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_CATEGORIES)
                .child(categoryId).child("posts").child(mPost.getPushId());
        pushRef.setValue(true);

        Intent intent = new Intent(NewPostActivity.this, PostsActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("categories", Parcels.wrap(mCategories));
        startActivity(intent);
    }
}
