package com.example.guest.ourreddit.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.ourreddit.Constants;
import com.example.guest.ourreddit.R;
import com.example.guest.ourreddit.models.Category;
import com.example.guest.ourreddit.ui.PostsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = FirebaseCategoryViewHolder.class.getSimpleName();
    private static final int MAX_WIDTH = 40;
    private static final int MAX_HEIGHT = 40;

    View mView;
    Context mContext;

    public FirebaseCategoryViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindCategory(Category category) {
        ImageView categoryImageView = (ImageView) mView.findViewById(R.id.categoryImageView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);

        Picasso.with(mContext)
                .load(category.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(categoryImageView);

        categoryTextView.setText("r/" + category.getName());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Category> categories = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CATEGORIES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    categories.add(snapshot.getValue(Category.class));
                }
                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, PostsActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("categories", Parcels.wrap(categories));
                mContext.startActivity(intent);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
