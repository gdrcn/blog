package com.rdc.dao;

import com.rdc.entity.Photo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhotoDao {

    /**
     * 上传照片到相册
     * @param photo
     * @return
     */
    Integer uploadPhotos(Photo photo);
}
