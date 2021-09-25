package com.metin.firebaselogindeneme;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity  {

    private Button signoutbtn;
    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    private TextView txtexperience,txtpurpose,txtotalyoga,txthowmanylesson,txtid ,txtkullaniciadi;


    CircleImageView profileImageView;
    private static final int REQUEST_CODE = 101;
    public Uri imageUri;
    private Button btnSave;
    FirebaseUser mUser;
    DatabaseReference mRef;
    StorageReference StorgaeRef;

    ProgressDialog mLoadingBar;

    private LoginButton login;
    CallbackManager callbackManager;

    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        login = findViewById(R.id.login);
        txtexperience = findViewById(R.id.txtexperience);
        txtpurpose = findViewById(R.id.txtpurpose);
        txtotalyoga = findViewById(R.id.txttotalyoga);
        txthowmanylesson = findViewById(R.id.txthowmanylesson);
        txtid = findViewById(R.id.txtid);
        txtkullaniciadi = findViewById(R.id.txtkullaniciadi);

        mAuth = FirebaseAuth.getInstance();
        signoutbtn = findViewById(R.id.signoutbtn);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("User Profile");
        setSupportActionBar(toolbar);

        callbackManager = CallbackManager.Factory.create();



        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //LoginManager.getInstance().logInWithReadPermissions(RegisterActivity.this, Arrays.asList("public_profile"));
                handleFacebookAccessToken(loginResult.getAccessToken());
                //AccessToken accessToken = AccessToken.getCurrentAccessToken();
                //boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
            }

            @Override
            public void onCancel() {
                Toast.makeText(ProfileActivity.this, "facebook:onCancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(ProfileActivity.this, "facebook:onError", Toast.LENGTH_SHORT).show();
            }
        });

        signoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                revokeAccess();
                mAuth.signOut();
                Intent intent = new Intent(ProfileActivity.this,WelcomeTrueYogiActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });




        btnSave = findViewById(R.id.btnSave);
        profileImageView = findViewById(R.id.profile_image);
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users");
        StorgaeRef = FirebaseStorage.getInstance().getReference().child("ProfileImages");
        mLoadingBar = new ProgressDialog(this);

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE);

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            LoginActivity loginActivity = new LoginActivity();
                            loginActivity.loginis = 1;
                            SaveData();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(ProfileActivity.this, ""+task.getException(),
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    private void SaveData(){
        mLoadingBar.setTitle("adding Setup Profile");
        mLoadingBar.setCanceledOnTouchOutside(false);
        mLoadingBar.show();

        if (imageUri == null){
            Toast.makeText(this, "Pls select an image", Toast.LENGTH_SHORT).show();
            mLoadingBar.dismiss();
        }
        else {
            StorgaeRef.child(mUser.getUid()).putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()){
                        StorgaeRef.child(mUser.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                HashMap hashMap = new HashMap();
                                hashMap.put("profileImage",uri.toString());
                                mRef.child(mUser.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        Map<String,Object> bilgiler = new HashMap<>();
                                        bilgiler.put("okeimage","1");
                                        mRef.child(mUser.getUid()).updateChildren(bilgiler);

                                        mLoadingBar.dismiss();
                                        Toast.makeText(ProfileActivity.this, "Setup Profile completed", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        mLoadingBar.dismiss();
                                        Toast.makeText(ProfileActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data!=null){
            imageUri = data.getData();
            profileImageView.setImageURI(imageUri);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mUser == null){
            SendUserToLoginActivity();
        }
        else {
            mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String experience = snapshot.child("experience").getValue().toString();
                    txtexperience.setText(experience);
                    String id = mUser.getUid();
                    txtid.setText(id);
                    String purpose = snapshot.child("purpose").getValue().toString();
                    txtpurpose.setText(purpose);
                    String totalyoga = snapshot.child("totalyoga").getValue().toString();
                    txtotalyoga.setText(totalyoga);
                    String howmanylesson = snapshot.child("howmanylesson").getValue().toString();
                    txthowmanylesson.setText(howmanylesson);
                    String okemi = snapshot.child("okeimage").getValue().toString();
                    if (okemi.equals("1")){
                        String profileImageUrlV = snapshot.child("profileImage").getValue().toString();
                        Picasso.get().load(profileImageUrlV).into(profileImageView);
                    }
                    String username = snapshot.child("username").getValue().toString();
                    txtkullaniciadi.setText(username);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }
    private void SendUserToLoginActivity(){
        Intent intent = new Intent(ProfileActivity.this,ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    public void setSharedPreference(String key, int value) {
        SharedPreferences sharedPref = this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public int getSharedPreference(String key, int defaultValue) {
        return this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE).getInt(key, defaultValue);
    }

    public void btnEkle_A_Click(View view) {
        SaveFragment saveFragment = new SaveFragment();

        androidx.fragment.app.FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.grup,saveFragment,"A");
        ft.commit();
    }


}