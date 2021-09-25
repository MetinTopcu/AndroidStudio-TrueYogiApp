package com.metin.firebaselogindeneme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.metin.firebaselogindeneme.dao.MantraDao;
import com.metin.firebaselogindeneme.models.Mantra;
import com.metin.firebaselogindeneme.util.Veritabani;

import java.util.ArrayList;
import java.util.Random;

public class MantraActivity extends AppCompatActivity {

    private Button mantraonbtn;
    private Toolbar toolbar;
    private Context mcontext;
    private ArrayList<Mantra> mantraArrayList;
    private Veritabani vt;
    private Mantra dogruSoru;
    private ArrayList<Mantra> mantra;
    private int[] a = new int[]{11,11,11,11,11,11,11,11,11,11};
    private int sayac = 0;
    private int run = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantra);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Mantra");
        setSupportActionBar(toolbar);
        mantraonbtn = findViewById(R.id.mantraonbtn);

        mantra = new ArrayList<>();

        sayac = getSharedPreference("sayac",0);
        a[0] = getSharedPreference("a0",11);
        a[1] = getSharedPreference("a1",11);
        a[2] = getSharedPreference("a2",11);
        a[3] = getSharedPreference("a3",11);
        a[4] = getSharedPreference("a4",11);
        a[5] = getSharedPreference("a5",11);
        a[6] = getSharedPreference("a6",11);
        a[7] = getSharedPreference("a7",11);
        a[8] = getSharedPreference("a8",11);
        a[9] = getSharedPreference("a9",11);

        Mantra m = new Mantra(1,"Olağanüstü şeyler başarabilirim.");
        Mantra m1 = new Mantra(2,"Amacım sevgi.");
        Mantra m2 = new Mantra(3,"Duygularımın kontrolü benim elimde.");
        Mantra m3 = new Mantra(4,"Bugün olduğum insan olmam gereken insan.");
        Mantra m4 = new Mantra(5,"Ben muhteşemim.");
        Mantra m5 = new Mantra(6,"Bedenimle barışığım.");
        Mantra m6 = new Mantra(7,"Başkasını mutlu etmek beni mutlu eder.");
        Mantra m7 = new Mantra(8,"Azimliyim.");
        Mantra m8 = new Mantra(9,"Korkunun ya da kızgınlığın beni ele geçirmesine izin vermeyeceğim.");
        Mantra m9 = new Mantra(10,"Ben yeniyim.");

        mantra.add(m);
        mantra.add(m1);
        mantra.add(m2);
        mantra.add(m3);
        mantra.add(m4);
        mantra.add(m5);
        mantra.add(m6);
        mantra.add(m7);
        mantra.add(m8);
        mantra.add(m9);

        vt = new Veritabani(this);

        mantraArrayList = new MantraDao().tumMantra(vt);

        if (run == 0){
            while (true){
                if (sayac == 9){
                    sayac = 0;
                    for (int i = 0;i < 10; i++){
                        a[i] = 11;
                    }
                }
                Random rand = new Random();
                int number = rand.nextInt(10);
                if (number == a[0] || number == a[1]|| number == a[2]|| number == a[3]|| number == a[4]|| number == a[5] || number == a[6]|| number == a[7]|| number == a[8]|| number == a[9]){

                }
                else {
                    dogruSoru = mantra.get(number);
                    //mantratxt.setText(dogruSoru.getMantra());
                    alertGoster2();
                    a[sayac] = number;
                    sayac++;
                    setSharedPreference("sayac",sayac);
                    setSharedPreference("a0",a[0]);
                    setSharedPreference("a1",a[1]);
                    setSharedPreference("a2",a[2]);
                    setSharedPreference("a3",a[3]);
                    setSharedPreference("a4",a[4]);
                    setSharedPreference("a5",a[5]);
                    setSharedPreference("a6",a[6]);
                    setSharedPreference("a7",a[7]);
                    setSharedPreference("a8",a[8]);
                    setSharedPreference("a9",a[9]);
                    break;
                }
            }
            run = 1;
        }


        mantraonbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void alertGoster2(){
        LayoutInflater layout = LayoutInflater.from(this);
        View view = layout.inflate(R.layout.aler_tasarim1,null);

        final TextView textViewMoodCode = view.findViewById(R.id.textViewMoodCode);

        textViewMoodCode.setText(dogruSoru.getMantra());

        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Mantra");
        ad.setView(view);
        ad.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MantraActivity.this, MainActivity.class));
                finish();
            }
        });
        ad.create().show();
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

    public void clearSharedPreference(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.clear();
        edit.commit();
    }

    public void removeSharedPreference(Context context, String key){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.remove(key);
        edit.commit();
    }
}