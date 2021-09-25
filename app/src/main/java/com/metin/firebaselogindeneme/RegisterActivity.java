package com.metin.firebaselogindeneme;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Arrays;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends AppCompatActivity {

    private ExperienceActivity experienceActivity = new ExperienceActivity();
    private PurposeActivity purposeActivity = new PurposeActivity();
    private LessonTimeActivity lessonTimeActivity = new LessonTimeActivity();
    private HowManyDayActivity howManyDayActivity = new HowManyDayActivity();

    private Button btnRegLogin;

    private LoginButton login;
    private Toolbar toolbar;
    public Uri imageUri;

    private Button btnkayitolmadan , btnregister;
    private EditText inputPassword, inputEmail;

    private FirebaseAuth mAuth;
    private SignInButton btnGoogle;

    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 101;

    CallbackManager callbackManager;

    FirebaseUser mUser;
    DatabaseReference mRef;
    StorageReference StorgaeRef;

    ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users");

        btnRegLogin = findViewById(R.id.btnRegLogin);
        btnregister = findViewById(R.id.btnRegister);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        btnGoogle = findViewById(R.id.btnGoogle);
        mLoadingBar = new ProgressDialog(this);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if (email.isEmpty() || !email.contains("@")){
                    showError(inputEmail,"Email is not valid");
                }
                else if (password.isEmpty() || password.length()<7){
                    showError(inputPassword,"Password must be 7 character");
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"Successfully Registration",Toast.LENGTH_SHORT).show();
                                LoginActivity loginActivity = new LoginActivity();
                                loginActivity.loginis = 1;

                                btnregister.setVisibility(View.INVISIBLE);
                                btnRegLogin.setVisibility(View.VISIBLE);
                                //Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                //startActivity(intent);
                            }
                            else {
                                Toast.makeText(RegisterActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
        btnRegLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(RegisterActivity.this,GoogleSignInActivity.class);
                //startActivity(intent);
                signIn();
            }
        });

        //printKeyHash();
        login = findViewById(R.id.login);
        toolbar = findViewById(R.id.toolbar);
        btnkayitolmadan = findViewById(R.id.btnkayitolmadan);

        toolbar.setTitle("TrueYogi Register");
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
                Toast.makeText(RegisterActivity.this, "facebook:onCancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(RegisterActivity.this, "facebook:onError", Toast.LENGTH_SHORT).show();
            }
        });


        btnkayitolmadan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity loginActivity = new LoginActivity();
                loginActivity.loginis = 0;
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    /*
    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.metin.trueyogi", PackageManager.GET_SIGNATURES);
            for (Signature signature:info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        }catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }
    */
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void SaveData(){
        String experience = experienceActivity.experience;
        String purpose = purposeActivity.purpose;
        String lessontime = lessonTimeActivity.lessontime;
        String howmanyday = howManyDayActivity.howmanyday;

        if (experience.isEmpty() || purpose.isEmpty() || lessontime.isEmpty() || howmanyday.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "İçleri boş Register Activity SaveData methodu", Toast.LENGTH_SHORT).show();
        }
        else {
            mLoadingBar.setTitle("adding Setup Profile");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            HashMap hashMap = new HashMap();
            hashMap.put("experience",experience);
            hashMap.put("purpose",purpose);
            hashMap.put("lessontime",lessontime);
            hashMap.put("howmanyday",howmanyday);
            hashMap.put("username","Yoga Lover");
            hashMap.put("totalyoga",0);
            hashMap.put("howmanylesson",0);
            hashMap.put("okeimage","0");
            hashMap.put("okeusername","0");
            hashMap.put("status","offline");

            mAuth = FirebaseAuth.getInstance();
            mUser = mAuth.getCurrentUser();
            mRef = FirebaseDatabase.getInstance().getReference().child("Users");
            if (mUser == null){
                SenUserToLoginActivity();
            }
            else {
                mRef.child(mUser.getUid()).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        mLoadingBar.dismiss();
                        Toast.makeText(RegisterActivity.this, "Setup Profile completed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mLoadingBar.dismiss();
                        Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }

    }

    private void SenUserToLoginActivity(){
        Intent intent = new Intent(RegisterActivity.this,RegisterActivity.class);
        startActivity(intent);
        finish();
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
                            Toast.makeText(RegisterActivity.this, ""+task.getException(),
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void showError(EditText input, String s){
        input.setError(s);;
        input.requestFocus();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this,user.getEmail()+user.getDisplayName(),Toast.LENGTH_SHORT).show();
                            LoginActivity loginActivity = new LoginActivity();
                            loginActivity.loginis = 1;
                            SaveData();
                        } else {
                            Toast.makeText(RegisterActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }

}