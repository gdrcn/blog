package com.rdc.dao;

import com.rdc.entity.Photo;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface PhotoDao {

    /**
     * 上传照片到相册
     * @param photo
     * @return
     */
    Integer uploadPhotos(Photo photo);

    /**
     * 是否已点赞
     *
     * @param map
     */
    Integer isUpPhoto(Map<String, Integer> map);

    /**
     * 点赞照片
     *
     * @param map
     */
    Integer upPhoto(Map<String, Integer> map);

    /**
     * 取消点赞
     *
     * @param map
     * @return
     */
    Integer cancelUp(Map<String, Integer> map);

    /**
     * 通过照片得到用户id
     *
     * @param photoId
     * @return
     */
    Integer getUserIdByPhotoId(Integer photoId);

    /**
     * 添加相片评论
     *
     * @param map
     */
    Integer addPhotoComment(Map<String, Object> map);

    /**
     * 得到照片点赞个数
     *
     * @param photoId
     */
    Integer getPhotoUp(int photoId);

    /**
     * 获得用户最新的照片（6条）
     *
     * @param usreId
     */
    ArrayList<Photo> getSomePhoto(int usreId);

    /**
     * 得到分页效果
     *
     * @param map
     */
    ArrayList<Photo> getPhotoComments(Map<String, Integer> map);
}
