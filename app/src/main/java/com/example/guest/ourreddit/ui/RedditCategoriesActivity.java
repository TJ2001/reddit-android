package com.example.guest.ourreddit.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.guest.ourreddit.Constants;
import com.example.guest.ourreddit.R;
import com.google.firebase.auth.FirebaseAuth;

public class RedditCategoriesActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private String mUserName;

    private String TAG = RedditCategoriesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUserName = mSharedPreferences.getString(Constants.PREFERENCES_USERNAME, null);
        Log.d(TAG,"Shared Pref Username is " + mUserName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                Log.d(TAG, "logging out");
                //TODO clear username preference
                mSharedPreferences.edit().remove(Constants.PREFERENCES_USERNAME).commit();
                logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(RedditCategoriesActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}
