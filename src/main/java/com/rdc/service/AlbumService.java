package com.rdc.service;

import com.rdc.dao.AlbumDao;
import com.rdc.dao.PhotoDao;
import com.rdc.entity.Album;
import com.rdc.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private PhotoDao photoDao;

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * 上传图片
     * Created by Ning
     * time 2018/7/22 19:39
     *
     * @param album
     * @param hashName
     * @return
     */
    public void uploadPhotos(Album album, String hashName) {
        Photo photo = new Photo();
        if (album.getId() == null) {
            photo.setAlbumId(albumDao.getDefaultAlbumIdByUserId(album.getUserId()));
            photo.setPhotoHash(hashName);
            albumDao.insertPhotoToAlbum(photo);
        } else {
            photo.setAlbumId(album.getId());
            photo.setPhotoHash(hashName);
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            photo.setPushTime(simpleDateFormat.format(date));
            albumDao.insertPhotoToAlbum(photo);
        }
    }

    /**
     * Created by Ning
     * time 2018/7/23 22：25
     * 删除相册照片
     *
     * @param photoId
     */
    public String deletePhoto(Integer photoId) {
        if (albumDao.finePhotoByPhotoId(photoId) == null) {
            return null;
        }
        albumDao.deletePhotoByPhotoId(photoId);
        return "success";
    }

    /**
     * 删除相册
     *
     * @param album
     * @return
     */
    public String deleteAlbum(Album album) {
        albumDao.deleteAlbum(album);
        return "success";
    }

    /**
     * 新建相册
     *
     * @param userId
     * @param albumName
     * @return
     */
    public String newAlbum(Integer userId, String albumName) {
        Album album = new Album();
        album.setUserId(userId);
        album.setAlbumName(albumName);
        if (albumDao.findTheSameAlbumByUserId(album) != null) {
            return "error";
        }
        albumDao.insertNewAlbum(album);
        return null;
    }

    /**
     * 点赞图片
     *
     * @param userId
     * @param photoId
     */
    public String upPhoto(Integer userId, Integer photoId) {
        Map<String, Integer> map = new HashMap();
        map.put("userId", userId);
        map.put("photoId", photoId);
        Integer isUpPhoto = photoDao.isUpPhoto(map);
        if (isUpPhoto != null) {
            photoDao.cancelUp(map);
            return "已取消";
        } else {
            Integer beUserId = photoDao.getUserIdByPhotoId(photoId);
            map.put("beUserId", beUserId);
            photoDao.upPhoto(map);
            return "已点赞";
        }
    }

    /**
     * 添加相片的评论
     * Created by Ning
     *
     * @param userId
     * @param photoId
     * @param comments
     * @return
     */
    public String addPhotoComments(Integer userId, Integer photoId, String comments) {
        if (comments.length() > 100) {
            return "error";
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Map<String, Object> map = new HashMap();
        map.put("userId", userId);
        map.put("photoId", photoId);
        map.put("comments", HtmlUtils.htmlEscape(comments));
        map.put("time", simpleDateFormat.format(date));
        photoDao.addPhotoComment(map);
        return "success";
    }
}

