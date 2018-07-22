package com.rdc.controller;

import com.google.gson.Gson;
import com.rdc.service.BlogService;
import com.rdc.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	@RequestMapping(value="")
	public String addBlog(){


		return "";
	}

	//图片上传
	@ResponseBody
	@RequestMapping(value="imgUpload.do",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String imgUpload(@RequestParam("file")MultipartFile file){
		Gson gson=new Gson();
		Map<String,String> map = new HashMap<>();
		if(!UploadUtil.suffixMatch(file.getOriginalFilename())){
			map.put("error","不支持此文件类型");
		}else{
			String hashName = UploadUtil.getFileHash(file.getOriginalFilename());
			System.out.print(hashName);
			UploadUtil.imgUpload(hashName,file);
			map.put("imgName",hashName);
		}
		return gson.toJson(map);
	}


}
