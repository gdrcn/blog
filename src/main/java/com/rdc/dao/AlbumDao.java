package com.rdc.dao;

import com.rdc.entity.Album;
import com.rdc.entity.Photo;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Map;

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


    /**
     * 得到用户相册的总数
     *
     * @param album
     * @return
     */
    Integer getAlbumNum(Album album);

    /**
     * 修改相册的名字
     *
     * @param album
     */
    Integer updateAlbumName(Album album);

    /**
     * 得到相册最新的照片作为封面
     *
     * @param id
     * @return
     */
    String getAlbumCover(Integer id);

    /**
     * 得到点赞状况
     *
     * @param map
     * @return
     */
    Integer getAlbumUpStatus(Map<String, Integer> map);

    /**
     * 得到相册点赞状况
     *
     * @param map
     * @return
     */
    Integer isUpAlbum(Map<String, Integer> map);

    /**
     * 取消点赞
     *
     * @param map
     */
    Integer cancelUp(Map<String, Integer> map);

    /**
     * 得到点赞人id
     *
     * @param albumId
     * @return
     */
    Integer getUserIdByAlbum(Integer albumId);

    /**
     * 点赞相册
     *
     * @param map
     * @return
     */
    Integer upAlbum(Map<String, Integer> map);
}
