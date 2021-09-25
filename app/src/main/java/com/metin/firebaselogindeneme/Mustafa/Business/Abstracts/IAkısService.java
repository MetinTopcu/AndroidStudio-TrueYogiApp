package com.metin.firebaselogindeneme.Mustafa.Business.Abstracts;



import com.metin.firebaselogindeneme.Mustafa.Entities.Concretes.Akıs;

import java.util.ArrayList;

public interface IAkısService {
    ArrayList<Akıs> GetAll();
    void Add(Akıs akıs);
    void Delete(Akıs akıs);
    void Update(Akıs akıs);

}
