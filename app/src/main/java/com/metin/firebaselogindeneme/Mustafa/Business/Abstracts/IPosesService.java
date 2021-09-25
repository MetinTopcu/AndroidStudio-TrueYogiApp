package com.metin.firebaselogindeneme.Mustafa.Business.Abstracts;


import com.metin.firebaselogindeneme.Mustafa.Entities.Concretes.Poses;

import java.util.ArrayList;

public interface IPosesService {
    ArrayList<Poses> GetAll();
    void Add(Poses pose);
}
