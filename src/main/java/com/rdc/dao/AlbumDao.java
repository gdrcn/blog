package com.rdc.dao;

import com.rdc.entity.Album;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface AlbumDao {
    ArrayList<Album> getUserAlbum(int userId);
}