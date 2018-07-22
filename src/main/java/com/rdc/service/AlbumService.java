package com.rdc.service;

import com.rdc.dao.AlbumDao;
import com.rdc.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    @Autowired
    private AlbumDao albumDao;

}
