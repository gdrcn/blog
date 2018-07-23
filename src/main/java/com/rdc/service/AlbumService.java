package com.rdc.service;

import com.rdc.bean.Msg;
import com.rdc.dao.AlbumDao;
import com.rdc.dao.PhotoDao;
import com.rdc.entity.Album;
import com.rdc.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private PhotoDao photoDao;

    /**
     * Created by Ning
     * time 2018/7/22 19:39
     * @param album
     * @param hashName
     * @return
     */
    public void uploadPhotos(Album album, String hashName) {
        Photo photo = new Photo();
        if(album.getId() == null){
            photo.setAlbumId(albumDao.getDefaultAlbumIdByUserId(album.getUserId()));
            photo.setPhotoHash(hashName);
            albumDao.insertPhotoToAlbum(photo);
        }else{
            photo.setAlbumId(album.getId());
            photo.setPhotoHash(hashName);
            albumDao.insertPhotoToAlbum(photo);
        }
    }

    /**
     * 删除相册照片
     * @param photoId
     */
    public String deletePhoto(Integer photoId) {
        if(albumDao.finePhotoByPhotoId(photoId) == null){
            return null;
        }
        albumDao.deletePhotoByPhotoId(photoId);
        return "success";
    }
}
