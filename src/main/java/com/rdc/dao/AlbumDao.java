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
    ArrayList<Album> getUserAlbumInfo(int userId);

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

    /**
     * 查找相册
     * @param album
     * @return
     */
    Integer findTheSameAlbumByUserId(Album album);

    /**
     * 新建相册
     * @param album
     * @return
     */
    Integer insertNewAlbum(Album album);

    /**
     * 得到用户的全部相册标签信息
     * @param userId
     * @return
     */
    ArrayList<Album> getUserAlbumList(Integer userId);

    /**
     * 得到用户的全部照片
     * @param userId
     * @return
     */
    ArrayList<Photo> getUserAllPhoto(Integer userId);

    /**
     * 得到特定类别标签的图片
     * @param albumId
     * @return
     */
    ArrayList<Photo> getSpecificPhoto(Integer albumId);

    /**
     * 删除图片标签
     *
     * @param album
     * @return
     */
    Integer deleteAlbum(Album album);
}
