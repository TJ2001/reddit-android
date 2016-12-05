package com.example.guest.ourreddit.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guest.ourreddit.Constants;
import com.example.guest.ourreddit.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String TAG = MainActivity.class.getSimpleName();

    private DatabaseReference mSearchedLocationReference;

    @Bind(R.id.loginButton) Button mLoginButton;
    @Bind(R.id.usernameText) EditText mUsernameText;
    @Bind(R.id.passwordText) EditText mPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_USERNAME);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v == mLoginButton) {
            String username = mUsernameText.getText().toString();

            saveLocationToFirebase(username);

            if(!(username).equals("")) {
                //logging in for the first time
                addToSharedPreferences(username);
                Intent intent = new Intent(MainActivity.this, RedditCategoriesActivity.class);
                startActivity(intent);
            }else if(mSharedPreferences.getString(Constants.PREFERENCES_USERNAME, null) == null){
                //username was not filled out but we have a preference saved
                Toast.makeText(MainActivity.this, "Enter a valid username", Toast.LENGTH_SHORT).show();
            }else{
                //username was stored in preference becasue user has never logged in or we manually logged out
                Intent intent = new Intent(MainActivity.this, RedditCategoriesActivity.class);
                startActivity(intent);
            }
            mUsernameText.setText("");
        }
    }

    private void addToSharedPreferences(String username){
        mEditor.putString(Constants.PREFERENCES_USERNAME, username).apply();
    }

    private void saveLocationToFirebase(String username){
        mSearchedLocationReference.setValue(username);
    }
}
