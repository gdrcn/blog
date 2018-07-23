package com.rdc.dao;

import com.rdc.entity.Photo;
import org.apache.ibatis.annotations.Mapper;

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
}
