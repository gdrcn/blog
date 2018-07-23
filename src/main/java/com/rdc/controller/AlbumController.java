package com.rdc.controller;

import com.rdc.bean.Msg;
import com.rdc.dao.AlbumDao;
import com.rdc.entity.Album;
import com.rdc.service.AlbumService;
import com.rdc.util.GsonUtil;
import com.rdc.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


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
    public String uploadPhotos(@RequestParam("file") MultipartFile file, Album album) {
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
    @RequestMapping(value = "deletePhoto", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String deletePhoto(@RequestParam("photoId") Integer photoId){
        String msg = albumService.deletePhoto(photoId);
        if(msg == null){
            return GsonUtil.getErrorJson();
        }
        return GsonUtil.getSuccessJson();
    }

}
