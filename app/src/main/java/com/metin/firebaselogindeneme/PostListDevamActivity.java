package com.metin.firebaselogindeneme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.metin.firebaselogindeneme.Mustafa.Business.Concretes.AkısManager;
import com.metin.firebaselogindeneme.Mustafa.DataAccess.Concretes.SQLite.DataBaseConnection;
import com.metin.firebaselogindeneme.Mustafa.DataAccess.Concretes.SQLite.SQLiteAkısDao;
import com.metin.firebaselogindeneme.Mustafa.DataAccess.Concretes.SQLite.SQLitePosesDao;
import com.metin.firebaselogindeneme.Mustafa.Entities.Concretes.Poses;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostListDevamActivity extends AppCompatActivity {
    private DataBaseConnection vt;
    public TextView txtTime;
    private TextView txtPoseName;
    private ImageView imgPose;
    private Handler h;
    private Handler h2;
    private boolean stop=true;
    CountDownTimer countDownTimer;
    private  int sayac=50;
    private  int dinlen=10;
    private  int duraklat=0;
    private ArrayList<Poses> pozlar=new ArrayList<>();
    private String str;
    private int poseNum=0;
    private  int time;
    private ImageView img;
    private String imgId="anonim";
    private int temp = 0;
    private ImageView imgClose;

    private FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mRef;
    StorageReference StorgaeRef;
    private String experience;
    private int level;
    Map<String,Object> bilgiler = new HashMap<>();
    Map<String,Object> dersler = new HashMap<>();
    private String totaltime;
    private String howmanylesson;
    private int toplam = 0;
    private int ders;
    private int teksefer = 0;
    private int teksefer1 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list_devam);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users");
        StorgaeRef = FirebaseStorage.getInstance().getReference().child("ProfileImages");

        vt=new DataBaseConnection(this);
        imgClose = findViewById(R.id.imgclose);


        img = findViewById(R.id.imagePost);
        // img.setImageURI(https://galeri13.uludagsozluk.com/694/engellenmek_1211392_m.png);
        img.setImageResource(R.drawable.com_facebook_profile_picture_blank_portrait);


        txtTime=findViewById(R.id.txtTime);
        txtPoseName=findViewById(R.id.txtPoseName);

        String gelen=getIntent().getStringExtra("süre");

        AkısManager akısManager=new AkısManager(new SQLiteAkısDao(vt),new SQLitePosesDao(vt));

        time=Integer.parseInt(gelen);
        h=new Handler();
        h2=new Handler();

        //userlevel Firebasedeki user leveli

        if (mUser!= null)
        {
            mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    experience = snapshot.child("experience").getValue().toString();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
            if (experience == "Beginner"){
                level = 1;
            }
            else if (experience == "Intermediate")
            {
                level = 2;
            }
            else if (experience == "Advanced")
            {
                level = 3;
            }
            else if (experience == null) {
                level = 1;
            }
            pozlar=akısManager.GetPose(level,time);
        }
        else {
            pozlar=akısManager.GetPose(1,time);
        }


        h.postDelayed(runnable,1000);
        str=pozlar.get(poseNum).getNameIng();
        txtPoseName.setText(str);
        poseNum++;

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser != null){
                    mRef.child(mUser.getUid()).updateChildren(bilgiler);
                }
                h.removeCallbacks(runnable);
                h2.removeCallbacks(runnable2);
                startActivity(new Intent(PostListDevamActivity.this,LoginActivity.class));
            }
        });
    }

    private final Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if (poseNum==time){
                h.postDelayed(runnable,1000);

                str=pozlar.get(poseNum).getNameIng();



                h.removeCallbacks(runnable);


            }
            Log.e("Kalan süre: ",String.valueOf(sayac));
            txtTime.setText("Kalan süre: "+String.valueOf(sayac));
            sayac--;
            h.postDelayed(runnable,1000);
            str=pozlar.get(poseNum).getNameIng();
            imgId=String.valueOf(pozlar.get(poseNum).getId());
            //for (Poses p:pozlar){
              //  Log.e(String.valueOf(poseNum),p.getNameIng());
             //}
            if (sayac==0) {
                //txtPoseName.setText(String.valueOf(pozlar.get(poseNum).getNameIng()));
                poseNum++;
                //Log.e("Hareket adı",str);
                h.removeCallbacks(runnable);
                dinlen=10;
                h2.postDelayed(runnable2,1000);
            }
            if(time == poseNum+1){
                return;
            }
        }
    };
    private final Runnable runnable2=new Runnable() {
        @Override
        public void run() {
            txtPoseName.setText(str);

            Log.e("Dinlen! Kalan süre: ",String.valueOf(dinlen));
            txtTime.setText("Dinlen! Kalan süre: "+String.valueOf(dinlen));
            dinlen--;
            h2.postDelayed(runnable2,1000);
            if (dinlen==0){
                if (mUser != null){
                    try {
                        //Log.v("girdimi","girdi");
                        mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                totaltime = snapshot.child("totalyoga").getValue().toString();
                                //Log.e("totaltime=",totaltime);
                                //Log.v("oldu","evetoldu");
                                if (totaltime == null){
                                    //Log.v("totaltime","null");
                                }
                                else {
                                    //Log.v("totaltime","değerli");
                                }
                                if (teksefer == 0){
                                    toplam += Integer.parseInt(totaltime);
                                    teksefer = 1;
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                //Log.v("hatavar","hata");
                            }
                        });
                        //Log.e("totaltime",totaltime);
                        //int toplam = Integer.parseInt(ab);
                        toplam += 60;
                        Log.e("toplam=" , String.valueOf(toplam));
                        bilgiler.put("totalyoga",toplam);
                        //mRef.child(mUser.getUid()).updateChildren(bilgiler);
                    }catch (Exception e){
                        //Log.e("trycat",e.toString());
                    }
                }
// total yoga time 60 sn ekle
                h2.removeCallbacks(runnable2);
                sayac=50;
                if (time!=poseNum){
                    h.postDelayed(runnable,1000);
                }
            }
            if (poseNum == time) {
                //kaç ders yaptığı +1
                if (mUser != null){
                    //Log.v("girdimi","girdi");
                    mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            howmanylesson = snapshot.child("howmanylesson").getValue().toString();
                            //Log.v("oldu","evetoldu");
                            if (howmanylesson == null){
                                //Log.v("howmanylesson","null");
                            }
                            else {
                                Log.v("howmanylesson","değerli");
                            }
                            if (teksefer1 == 0){
                                ders += Integer.parseInt(howmanylesson);
                                teksefer1 = 1;
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.v("hatavar","hata");
                        }
                    });
                    if (dinlen == 1){
                        dersler.put("howmanylesson",ders + 1);
                        bilgiler.put("totalyoga",toplam + 60);
                        mRef.child(mUser.getUid()).updateChildren(bilgiler);
                        mRef.child(mUser.getUid()).updateChildren(dersler);
                        startActivity(new Intent(PostListDevamActivity.this,LoginActivity.class));
                    }

                }

            }
        }
    };
}