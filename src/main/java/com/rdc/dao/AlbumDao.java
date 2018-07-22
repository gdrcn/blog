package com.rdc.dao;

import com.rdc.entity.Album;
import com.rdc.entity.Photo;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface AlbumDao {
    /**
     * 得到用户相册
     * @param userId
     * @return
     */
    ArrayList<Album> getUserAlbum(int userId);

    /**
     * 插入初始相册
     * @param userId
     * @return
     */
    Integer insertDefaultAlbum(int userId);

    /**
     * 得到用户默认相册id
     * @return
     */
    Integer getDefaultAlbumIdByUserId(int userId);

    /**
     * 向相册插入图片
     * @param photo
     * @return
     */
    Integer insertPhotoToAlbum(Photo photo);

    /**
     * 删除照片
     * @param photoId
     */
    void deletePhotoByPhotoId(Integer photoId);

    /**
     * 查找照片
     * @param photoId
     * @return
     */
    Integer finePhotoByPhotoId(Integer photoId);
}
