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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewCategoryActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.categoryNameText) TextView mCategoryTextView;
    @Bind(R.id.addCategoryButton) Button mButton;
    private Category mCategory;
    private String defaultCategoryImageUrl = "https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-reddit-square2-128.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);
        ButterKnife.bind(this);
        setTitle("New Category");
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        String categoryName = mCategoryTextView.getText().toString();
        mCategory = new Category(categoryName, defaultCategoryImageUrl, uid);
        DatabaseReference categoryRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_CATEGORIES);

        DatabaseReference pushRef = categoryRef.push();
        String pushId = pushRef.getKey();
        mCategory.setPushId(pushId);
        pushRef.setValue(mCategory);
        Intent intent = new Intent(NewCategoryActivity.this, CategoriesActivity.class);
        startActivity(intent);
    }
}
