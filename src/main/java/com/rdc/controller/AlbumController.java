package com.rdc.controller;

import com.rdc.bean.Msg;
import com.rdc.entity.Album;
import com.rdc.entity.User;
import com.rdc.service.AlbumService;
import com.rdc.util.GsonUtil;
import com.rdc.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/blog")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    /**
     * Created by Ning
     * time 2018/7/22 18:46
     * 上传图片
     */
    @ResponseBody
    @RequestMapping(value = "uploadPhotos", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String uploadPhotos(@RequestParam("file") MultipartFile file, Album album, HttpSession session) {
        User user = (User) session.getAttribute("user");
        album.setUserId(user.getId());
        Msg message = new Msg();
        if (!UploadUtil.suffixMatch(file.getOriginalFilename())) {
            message.setResult("error");
            message.setMessage("不支持此文件类型");
            return GsonUtil.getErrorJson(message);
        } else {
            String hashName = UploadUtil.getFileHash(file.getOriginalFilename());
            UploadUtil.imgUpload(hashName, file);
            albumService.uploadPhotos(album, hashName);
            message.setResult("success");
            message.setMessage("/img/" +hashName);
            return GsonUtil.getSuccessJson(message);
        }
    }

    /**
     * Created by Ning
     * time 2018/7/22 20:39
     * 删除照片
     */
    @ResponseBody
    @RequestMapping(value = "/deletePhoto/{photoId}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String deletePhoto(@PathVariable Integer photoId){
        String msg = albumService.deletePhoto(photoId);
        if(msg == null){
            return GsonUtil.getErrorJson();
        }
        return GsonUtil.getSuccessJson();
    }

    /**
     * 新建相册
     * Created by Ning
     * time 2018/7/23 9:50
     */
    @ResponseBody
    @RequestMapping(value = "/newAlbum", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String newAlbum(@RequestParam("albumName") String albumName, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String msg = albumService.newAlbum(user.getId(), albumName);
            if(msg == null){
                return GsonUtil.getSuccessJson();
            }
        return GsonUtil.getErrorJson("你已存在同名标签的照片库");
    }

    /**
     * Created by Ning
     * time 2018/7/24 12:15
     * 删除相册或者标签
     *
     * @param album
     * @return gson
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAlbum", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String deleteAlbum(Album album) {
        String msg = albumService.deleteAlbum(album);
        if ("success".equals(msg)) {
            return GsonUtil.getSuccessJson();
        } else {
            return GsonUtil.getErrorJson();
        }
    }

    /**
     * 点赞照片
     * Created by Ning
     * time 2018/7/23 19:08
     */
    @ResponseBody
    @RequestMapping(value = "/likeThisPhoto", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String upPhoto(@RequestParam("photoId") Integer photoId, HttpSession session) {
        User realUser = (User) session.getAttribute("user");
        return GsonUtil.getSuccessJson(albumService.upPhoto(realUser.getId(), photoId));
    }

    /**
     * 评论照片
     * Created by Ning
     * time 2018/7/23 21:25
     */
    @ResponseBody
    @RequestMapping(value = "/discussPhoto", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String discussPhoto(@RequestParam("userId") Integer userId, @RequestParam("photoId") Integer photoId, @RequestParam("comments") String comments) {
        String msg = albumService.addPhotoComments(userId, photoId, comments);
        if ("success".equals(msg))
            return GsonUtil.getSuccessJson();
        else {
            return GsonUtil.getErrorJson();
        }
    }
}