package com.metin.firebaselogindeneme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

import static android.app.Activity.RESULT_OK;


public class SaveFragment extends Fragment {

    private TextView txtexperience,txtpurpose,txtotalyoga,txthowmanylesson,txtid;
    private static final int REQUEST_CODE = 101;
    private Uri imageUri;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mRef;
    StorageReference StorgaeRef;

    ProgressDialog mLoadingBar;
    Map<String,Object> bilgiler = new HashMap<>();



    private Button btnfbeginner,btnfintermediate,btnfadvanced,btnfbuildstrength,btnfgetinshape,btnfrelaxyourmind,
    btnfimproveflexibility,btnfimproveposture;
    private Button btnf15,btnf1530,btnf30,btnf12,btnf34,btnf5ormore;
    private Button btnasilsave;
    private String strexperience,strpurpose,strlessontime,strhowmanyday;
    CircleImageView profileasil_image;
    private EditText editusername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_save,container,false);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users");
        StorgaeRef = FirebaseStorage.getInstance().getReference().child("ProfileImage");
        mLoadingBar = new ProgressDialog(getContext());

        btnfbeginner = rootView.findViewById(R.id.btnfbeginner);
        btnfintermediate = rootView.findViewById(R.id.btnfintermediate);
        btnfadvanced = rootView.findViewById(R.id.btnfadvanced);
        btnfbuildstrength = rootView.findViewById(R.id.btnfbuildstrength);
        btnfgetinshape = rootView.findViewById(R.id.btnfgetinshape);
        btnfrelaxyourmind = rootView.findViewById(R.id.btnfrelaxyourmind);
        btnfimproveflexibility = rootView.findViewById(R.id.btnfimproveflexibility);
        btnfimproveposture = rootView.findViewById(R.id.btnfimproveposture);
        btnf15 = rootView.findViewById(R.id.btnf15);
        btnf1530 = rootView.findViewById(R.id.btnf1530);
        btnf30 = rootView.findViewById(R.id.btnf30);
        btnf12 = rootView.findViewById(R.id.btnf12);
        btnf34 = rootView.findViewById(R.id.btnf34);
        btnf5ormore = rootView.findViewById(R.id.btnf5ormore);
        btnasilsave = rootView.findViewById(R.id.btnasilsave);
        profileasil_image = rootView.findViewById(R.id.profileasil_image);
        editusername = rootView.findViewById(R.id.editusername);

        btnfbeginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strexperience = "Beginner";
            }
        });
        btnfintermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strexperience = "Intermediate";
            }
        });
        btnfadvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strexperience = "Advanced";
            }
        });
        btnfbuildstrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strpurpose = "Build strength";
            }
        });
        btnfgetinshape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strpurpose = "Get in shape";
            }
        });
        btnfrelaxyourmind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strpurpose = "Relax your mind";
            }
        });
        btnfimproveflexibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strpurpose = "Improve flexibility";
            }
        });
        btnfimproveposture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strpurpose = "Improve posture";
            }
        });
        btnf15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strlessontime = "15 minutes";
            }
        });
        btnf1530.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strlessontime = "15-30 minutes";
            }
        });
        btnf30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strlessontime = "More than 30";
            }
        });
        btnf12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strhowmanyday = "1-2";
            }
        });
        btnf34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strhowmanyday = "3-4";
            }
        });
        btnf5ormore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strhowmanyday = "5 or more";
            }
        });

        profileasil_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        btnasilsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });


        return rootView;
    }

    private void SaveData(){
        if (imageUri == null) {
            if (strexperience != null){
                bilgiler.put("experience",strexperience);
            }
            else {
                mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String experience = snapshot.child("experience").getValue().toString();
                        bilgiler.put("experience",experience);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
            if (strpurpose != null){
                bilgiler.put("purpose",strpurpose);
                Log.e("purpose1=",strpurpose);
            }
            else {
                mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.e("purpose2=","qwepurpose");
                        String purpose = snapshot.child("purpose").getValue().toString();
                        bilgiler.put("purpose",purpose);
                        Log.e("purpose3=",purpose);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            if (strlessontime != null){
                bilgiler.put("lessontime",strlessontime);
            }
            else {
                mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String lessontime = snapshot.child("lessontime").getValue().toString();
                        bilgiler.put("lessontime",lessontime);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            if (strhowmanyday != null){
                bilgiler.put("howmanyday",strhowmanyday);
            }
            else {
                mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String howmanyday = snapshot.child("howmanyday").getValue().toString();
                        bilgiler.put("howmanyday",howmanyday);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            if (editusername != null){
                String username = editusername.getText().toString();
                bilgiler.put("username",username);
            }
            else {
                mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String username = snapshot.child("username").getValue().toString();
                        bilgiler.put("username",username);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            mRef.child(mUser.getUid()).updateChildren(bilgiler);

            FragmentManager manager = getFragmentManager();
            SaveFragment fa = (SaveFragment) manager.findFragmentByTag("A");
            FragmentTransaction transaction = manager.beginTransaction();
            if (fa != null){
                transaction.remove(fa);
                transaction.commit();
            }
            else {
                Toast.makeText(getActivity(), "Fragment Don't Exit", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            mLoadingBar.setTitle("adding Setup Profile");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            StorgaeRef.child(mUser.getUid()).putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()){
                        StorgaeRef.child(mUser.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                if (strexperience != null){
                                    bilgiler.put("experience",strexperience);
                                }
                                else {
                                    mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String experience = snapshot.child("experience").getValue().toString();
                                            bilgiler.put("experience",experience);
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                }
                                if (strpurpose != null){
                                    bilgiler.put("purpose",strpurpose);
                                }
                                else {
                                    mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String purpose = snapshot.child("purpose").getValue().toString();
                                            bilgiler.put("purpose",purpose);
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                                if (strlessontime != null){
                                    bilgiler.put("lessontime",strlessontime);
                                }
                                else {
                                    mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String lessontime = snapshot.child("lessontime").getValue().toString();
                                            bilgiler.put("lessontime",lessontime);
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                                if (strhowmanyday != null){
                                    bilgiler.put("howmanyday",strhowmanyday);
                                }
                                else {
                                    mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String howmanyday = snapshot.child("howmanyday").getValue().toString();
                                            bilgiler.put("howmanyday",howmanyday);
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                                if (editusername != null){
                                    String username = editusername.getText().toString();
                                    bilgiler.put("username",username);
                                }
                                else {
                                    mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String username = snapshot.child("username").getValue().toString();
                                            bilgiler.put("username",username);
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                                bilgiler.put("profileImage",uri.toString());

                                mRef.child(mUser.getUid()).updateChildren(bilgiler).addOnSuccessListener(new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        bilgiler.put("okeimage","1");
                                        mRef.child(mUser.getUid()).updateChildren(bilgiler);

                                        FragmentManager manager = getFragmentManager();
                                        SaveFragment fa = (SaveFragment) manager.findFragmentByTag("A");
                                        FragmentTransaction transaction = manager.beginTransaction();
                                        if (fa != null){
                                            transaction.remove(fa);
                                            transaction.commit();
                                        }
                                        else {
                                            Toast.makeText(getActivity(), "Fragment Don't Exit", Toast.LENGTH_SHORT).show();
                                        }
                                        mLoadingBar.dismiss();
                                        Toast.makeText(getActivity(), "Setup Profile Completed", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        mLoadingBar.dismiss();
                                        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null){
            imageUri = data.getData();
            profileasil_image.setImageURI(imageUri);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.child("username").getValue().toString();
                editusername.setText(username);

                String okemi = snapshot.child("okeimage").getValue().toString();

                if (okemi.equals("1")){
                    String profileImageUrlV = snapshot.child("profileImage").getValue().toString();
                    Picasso.get().load(profileImageUrlV).into(profileasil_image);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}