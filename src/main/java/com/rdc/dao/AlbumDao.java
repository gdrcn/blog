package com.rdc.dao;

import com.rdc.entity.Album;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;


public interface AlbumDao {
    /**
     * 得到用户相册
     * @param userId
     * @return
     */
    ArrayList<Album> getUserAlbum(int userId);
}
