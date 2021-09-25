package com.metin.firebaselogindeneme.Mustafa.Business.Concretes;

import android.icu.text.UFormat;
import android.util.Log;


import com.metin.firebaselogindeneme.Mustafa.Business.Abstracts.IAkısService;
import com.metin.firebaselogindeneme.Mustafa.DataAccess.Abstracts.IAkısDao;
import com.metin.firebaselogindeneme.Mustafa.DataAccess.Abstracts.IPosesDao;
import com.metin.firebaselogindeneme.Mustafa.Entities.Concretes.Akıs;
import com.metin.firebaselogindeneme.Mustafa.Entities.Concretes.Poses;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

public class AkısManager implements IAkısService {
    IAkısDao _akısDao;
    IPosesDao _poses;

    public AkısManager(IAkısDao _akısDao, IPosesDao _poses) {
        this._akısDao = _akısDao;
        this._poses = _poses;
    }

    @Override
    public ArrayList<Akıs> GetAll() {
        return _akısDao.GetAll();
    }

    @Override
    public void Add(Akıs akıs) {

        _akısDao.Add(akıs);
    }

    @Override
    public void Delete(Akıs akıs) {
        _akısDao.Delete(akıs);
    }


    @Override
    public void Update(Akıs akıs) {

    }

    public ArrayList<Poses> GetPose(int userLevel, int Time){

        ArrayList<Poses> Akis=new ArrayList<>();
        ArrayList<Poses> pozlar=new ArrayList<>();
        pozlar=_poses.GetAll();

        ArrayList<Poses> Beginner= new ArrayList<>();
        ArrayList<Poses> İntermediate= new ArrayList<>();
        ArrayList<Poses> Advanced= new ArrayList<>();

        for (Poses p: pozlar){
            if (p.getLevelId()==1){Beginner.add(p);}
            if (p.getLevelId()==2){İntermediate.add(p);}
            if (p.getLevelId()==3){Advanced.add(p);}
        }

        int i=0;
        int l=userLevel;
        for (i = 0; i < Time; i++){

            if (i==0){
                Akis.add(Get_Pose(Beginner));
                l++;
            }
            else if(i>0&&i<Time-1){
               l=RndLevel(l);
               if (l==1){
                   Akis.add(Get_Pose(Beginner));

               }
               else if (l==2){
                   Akis.add(Get_Pose(İntermediate));

               }
               else {
                   Akis.add(Get_Pose(Advanced));
               }
            }
            else {
                Akis.add(Get_Pose(Beginner));
            }
        }

        for (Poses p:Akis){
          Log.e("Hareket",p.getNameIng());
        }
        return Akis;

    }

   private Poses Get_Pose(ArrayList<Poses> p){
      Random rnd=new Random();
      Poses pose=new Poses();

      int limit=rnd.nextInt(p.size());

       int i=0;
       for (Poses ps:p){
           if (i==limit){ pose=ps; break;}
           i++;
       }

        return pose;
    }
    private int RndLevel(int l){
        int level=l;
        Random rnd=new Random();
        if (l > 1) {
            if (l<3){
                if (rnd.nextBoolean()){
                    level++;
                }
                else {
                    level--;
                }
            }
            else {
                level--;
            }
        }
        else {
            level++;
        }
        return level;
    }
}
