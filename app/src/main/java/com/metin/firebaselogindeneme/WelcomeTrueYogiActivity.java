package com.metin.firebaselogindeneme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metin.firebaselogindeneme.Mustafa.Business.Concretes.PosesManager;
import com.metin.firebaselogindeneme.Mustafa.DataAccess.Concretes.SQLite.DataBaseConnection;
import com.metin.firebaselogindeneme.Mustafa.DataAccess.Concretes.SQLite.SQLitePosesDao;
import com.metin.firebaselogindeneme.Mustafa.Entities.Concretes.Poses;

public class WelcomeTrueYogiActivity extends AppCompatActivity {

    private Toolbar toolbar;

    FirebaseAuth mAuth;
    DatabaseReference mRef;
    FirebaseUser mUser;
    private DataBaseConnection vt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_true_yogi);
        vt=new DataBaseConnection(this);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Welcome to TrueYogi");
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mUser != null){
                    mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                LoginActivity loginActivity = new LoginActivity();
                                loginActivity.loginis = 1;
                                Intent intent = new Intent(WelcomeTrueYogiActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                //Intent intent = new Intent(WelcomeTrueYogiActivity.this,LoginActivity.class);
                                //startActivity(intent);
                                //finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else {
                    //Intent intent = new Intent(WelcomeTrueYogiActivity.this,WelcomeTrueYogiActivity.class);
                    //startActivity(intent);
                    //finish();
                }
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable,100);

        String [] NAME_EN={"Balancing Table Pose","Big Toe Pose","Bridge Pose","Butterfly Pose","Cat Pose","Chair Pose",
                "Chaturanga","Child’s Pose","Cobra Pose","Cow Pose","Dolphin Plank Pose","Downward-facing Dog","Easy Pose",
                "Extended Side Angle Pose","Fish Pose","Garland Pose","Half Lord of the Fishes Pose","Half Standing Forward Bend",
                "High Lunge","Mountain Pose","One-Legged Boat Pose","Palm Tree Pose (Upward Salute)","Plank Pose","Reverse Table Top Pose",
                "Sphinx Pose","Staff Pose","Standing Forward Bend","Table Pose","Triangle Pose","Upward-facing Dog","Archer Pose",
                "Bound Twisted Side Angle Pose","Bow Pose","Camel Pose","Compass Pose","Crane Pose","Crow Pose","Eagle Pose","Ear Pressure Pose",
                "Elephant Trunk Pose","Extended Hand-to-Big-Toe Pose","Full Boat Pose","Half Boat Pose","Half Lotus Pose","Half Reclined Hero",
                "Half-Moon Pose","Head To Knee Pose","Hero Pose","Heron Pose","Lizard Pose","Lord of the Dance Pose","Lotus Pose","Marichi’s Pose I",
                "Marichi’s Pose II","Marichi’s Pose III","Monkey Pose","One-Legged King Pigeon Pose","Pigeon Pose","Plow Pose","Reclining Hero Pose",
                "Reverse Warrior Pose","Scale Pose","Side Crow Pose","Side Plank Pose","Standing Split Pose","Supported Headstand Pose",
                "Supported Shoulder Stand","Tree pose","Twisted Chair Pose","Twisted Head-to-Knee Pose","Twisted Marichi’s Pose IV","Twisted Triangle Pose",
                "Revolved Side Angle Pose","Wheel Pose","Warrior I Pose","Warrior II Pose","Warrior III Pose","Wild Thing","Handstand Pose",
                "Rabbit Pose","Half Frog Pose","Frog Pose","Bharadvaja’s Twist","Bird of Paradise Pose","Eight Angle Pose","Feathered Peacock Pose",
                "Firefly Pose","Flying Pigeon Pose","Full Lord of the Fishes Pose","Little Thunderbolt Pose","One-Legged Crane Pose","One-Legged King Pigeon Pose II",
                "Peacock Pose","Sage Half Bound Lotus Pose","Sage Koundiya I Pose","Sage Koundiya II Pose","Scorpion Pose","Shoulder Pressing Pose",
                "Upward-facing Two-Footed Staff Pose","Legs Up The Wall Pose","Thunderbolt","Happy Baby","Wide Splits","Cow Face","Wide Legged Forward Bend",
                "Pyramid with Arms Extended","Seated Forward Bend","Wind Removing with Head Tucked","Corpse Pose","Locust Pose","Revolved Downward-Facing Dog","Revolved Child's",
                "Supine Spinal Twist","Revolved Wide Legged Forward Bend","Crescent Lunge Twist","Crescent Lunge on the Knee","Crescent Lunge Twist on the Knee",
                "Supine Bound Angle","Reclining Thread-the-Needle Pose","Goddess Pose","Front Corpse","Five Pointed Star","Eight Point",
                "Bound Side Angle","Revolved Bird of Paradise","Wide Child's","Dolphin","Seated Cat","Seated Cow","Plank with Knee to Tricep",
                "Plank with Knee to Tricep","Plank with Knee to Chest","Seated Twist","Three Legged Downward-Facing Dog","Three Legged Downward-Facing Dog with Hip Opener",
                "Extended Child's","Half Splits","Half Splits"};
        String [] NAME_LEVEL={"beginner","ıntermidiate","Intermediate","Advanced","Beginner","Beginner","Intermediate","Beginner",
                "Intermediate","Beginner","Intermediate","Beginner","Beginner","Intermediate","Advanced","Beginner","Advanced","Beginner",
                "Intermediate","Beginner","Intermediate","Beginner","Intermediate","Beginner","Beginner","Beginner","Beginner","Beginner",
                "Beginner","Intermediate","Intermediate","Advanced","Intermediate","Intermediate","Advanced","Intermediate","Intermediate",
                "Intermediate","Advanced","Advanced","Advanced","Intermediate","Intermediate","Beginner","Beginner","Intermediate","Intermediate",
                "Beginner","Beginner","Advanced","Intermediate","Advanced","Intermediate","Intermediate","Advanced","Intermediate","Advanced",
                "Advanced","Advanced","Intermediate","Intermediate","Intermediate","Advanced","Advanced","Intermediate","Intermediate",
                "Advanced","Intermediate","Intermediate","Intermediate","Intermediate","Advanced","Intermediate","Intermediate","Advanced",
                "Beginner","Beginner","Advanced","Intermediate","Advanced","Advanced","Intermediate","Intermediate","Intermediate",
                "Advanced","Advanced","Advanced","Advanced","Advanced","Advanced","Beginner","Advanced","Advanced","Advanced","Advanced",
                "Intermediate","Advanced","Advanced","Advanced","Advanced","Advanced","Beginner","Beginner","Beginner","Beginner","Advanced",
                "Intermediate","Intermediate","Beginner","Beginner","Intermediate","Intermediate","Beginner","Beginner","Intermediate",
                "Intermediate","Beginner","Beginner","Beginner","Beginner","Beginner","Beginner","Beginner","Intermediate","Advanced",
                "Advanced","Beginner","Beginner","Beginner","Beginner","Intermediate","Intermediate","Intermediate","Beginner","Beginner",
                "Beginner","Beginner","Intermediate","Advanced"};
        String [] NAME_SAN={"Utthita Cakravakasana","Padangusthasana","Setu Bandhasana","Baddha Konasana","Marjaryasana","Utkatasana",
                "Chaturanga Dandasana","Balasana","Bhujangasana","Bitilasana","Catur Svanasana","Adho Mukha Svanasana","Sukhasana",
                "Utthita Parsvakonasana","Matsyasana","Malasana","Ardha Matsyendrasana","Ardha Uttanasana","Utthita Ashwa Sanchalanasana",
                "Tadasana","Ekapada Navasana","Urdhva Hastasana","Phalakasana","Ardha Purvottanasana","Salamba Bhujangasana","Dandasana",
                "Uttanasana","Bharmanasana","Utthita Trikonasana","Urdhva Mukha Svanasana","Akarna Dhanurasana","Baddha Parivrtta Parsvakonasana",
                "Dhanurasana","Ustrasana","Parivrtta Surya Yantrasana","Bakasana","Kakasana","Garudasana","Karnapidasana","Eka Hasta Bhujasana",
                "Utthita Hasta Padangusthasana","Navasana","Ardha Navasana","Ardha Padmasana","Ardha Supta Virasana","Ardha Chandrasana",
                "Janu Sirsasana","Virasana","Krounchasana","Utthan Pristhasana","Natarajasana","Padmasana","Marichyasana 1","Marichyasana 2",
                "Marichyasana 3","Hanumanasana","Eka Pada Rajakapotasana","Kapotasana","Halasana","Supta Virasana","Parsva Virabhadrasana",
                "Tolasana","Parsva Bakasana","Vasisthasana","Urdhva Prasarita Eka Padasana","Salamba Sirsasana","Salamba Sarvangasana",
                "Vrksasana","Parivrtta Utkatasana","Parivrtta Janu Sirsasana","Marichyasana 4","Parivrtta Trikonasana","Parivrtta Parsvakonasana",
                "Urdhva Dhanurasana","Virabhadrasana 1","Virabhadrasana 2","Virabhadrasana 3","Camatkarasana","Adho Mukha Vrksasana",
                "Sasangasana","Ardha Bhekasana","Bhekasana","Bharadvajasana","Svarga Dvidasana","Astavakrasana","Pincha Mayurasana",
                "Tittibhasana","Eka Pada Galavasana","Purna Matsyendrasana","Laghu Vajrasana","Eka Pada Bakasana","Eka Pada Rajakapotasana II",
                "Mayurasana","Kasyapasana","Eka Pada Koundiyanasana I","Eka Pada Koundiyanasana II","Vrschikasana","Bhujapidasana","Dwi Pada Viparita Dandasana",
                "Viparita Karani","Vajrasana","Ananda Balasana","Upavistha Konasana","Gomukhasana","Prasarita Padottanasana","Parsvottanasana",
                "Paschimottanasana","Apanasana","Savasana","Salabhasana","Parivrtta Adho Mukha Svanasana","Succhirandrasana","Jathara Parivartanasana",
                "Parivrtta Prasarita Padottanasana","Parivrtta Utthita Ashwa Sanchalasana","Anjaneyasana","Parivrtta Anjaneyasana","Supta Baddha Konasana",
                "Supta Succhirandrasana","Utkata Konasana","Advasana","Five Pointed Star","Ashtangasana","Baddha Utthita Parsvakonasana",
                "Parivrtta Svarga Dvidasana","Utthita Balasana 2","Ardha Pincha Mayurasana","Marjaryasana 2","Bitilasana 2","Phalakasana 2",
                "Phalakasana 3","Phalakasana 4","Parivrtta Sukhasana","Adho Mukha Svanasana 2","Adho Mukha Svanasana 3","Utthita Balasana",
                "Ardha Hanumanasana","Pasasana"};




        int i=0;
        Poses pose;
        PosesManager posesManager=new PosesManager(new SQLitePosesDao(vt));
        while (i>138){
            pose=new Poses();
            pose.setNameIng(NAME_EN[i]);
            pose.setNameSnk(NAME_SAN[i]);
            String lvl= NAME_LEVEL[i];
            if (lvl=="beginner"||lvl=="Beginner"){pose.setLevelId(1);}
            else if(lvl=="Intermediate"||lvl=="intermediate"){pose.setLevelId(2);}
            else if(lvl=="Advanced"||lvl=="advanced"){pose.setLevelId(3);}
            else{pose.setLevelId(4);}
            Log.e("sdasda","baba problemaa");
            posesManager.Add(pose);
            i=i+1;
        }


    }

    public void Login(View view) {
        startActivity(new Intent(WelcomeTrueYogiActivity.this,LoginAsilActivity.class));
    }

    public void register(View view) {
        startActivity(new Intent(WelcomeTrueYogiActivity.this,ExperienceActivity.class));
    }



}