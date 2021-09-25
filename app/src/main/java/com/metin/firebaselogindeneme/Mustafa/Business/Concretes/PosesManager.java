package com.metin.firebaselogindeneme.Mustafa.Business.Concretes;



import com.metin.firebaselogindeneme.Mustafa.Business.Abstracts.IPosesService;
import com.metin.firebaselogindeneme.Mustafa.DataAccess.Abstracts.IPosesDao;
import com.metin.firebaselogindeneme.Mustafa.Entities.Concretes.Poses;

import java.util.ArrayList;

public class PosesManager implements IPosesService {
    private IPosesDao _posesDto;

    public PosesManager(IPosesDao posesDto) {
        this._posesDto = posesDto;
    }

    @Override
    public ArrayList<Poses> GetAll() {
        return _posesDto.GetAll();
    }

    @Override
    public void Add(Poses pose) {
        _posesDto.Add(pose);
    }



}
