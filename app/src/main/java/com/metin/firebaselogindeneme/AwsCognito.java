package com.metin.firebaselogindeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.facebook.AccessToken;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AwsCognito extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aws_cognito);

        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
        } catch (AmplifyException e) {
            e.printStackTrace();
        }
        try {
            Amplify.configure(getApplicationContext());
        } catch (AmplifyException e) {
            e.printStackTrace();
        }

    }
}