package com.rdc.controller;

import com.google.gson.Gson;
import com.rdc.bean.Msg;
import com.rdc.entity.Blog;
import com.rdc.entity.User;
import com.rdc.service.BlogService;
import com.rdc.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestMapping("/blog")
@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	private Gson gson=new Gson();
	private Msg msg;

	//根据id找博客
	@ResponseBody
	@RequestMapping(value="/blog/{blogId}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	public String showBlogById(@PathVariable int blogId){

		Blog blog = blogService.showBlogById(12);
		System.out.println(blog);
		return "";
	}

	//修改博客
	@ResponseBody
	@RequestMapping(value="/modifyBlog")
	public String modifyBlog(Blog blog, HttpServletResponse response, @RequestParam("categoryId")String[] category, HttpSession session) throws IOException {

		User user=(User)session.getAttribute("user");
		blog.setUser(user);

		if(blogService.modify(blog,category)){
			return null;
		}

		return gson.toJson(new Msg("error","修改失败"));
	}

	//删除博客
	@ResponseBody
	@RequestMapping(value="/deleteBlog",method = RequestMethod.POST )
	public String deleteBlog(@RequestParam("blogId") int blogId,HttpSession session){

		User user=(User) session.getAttribute("user");
		if(!blogService.delete(user.getId(),blogId)){
			return gson.toJson(new Msg("error","删除失败"));
		}
		return null;
	}
	//发表博客
	@ResponseBody
	@RequestMapping(value="/addBlog",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String addBlog(Blog blog, HttpServletResponse response, @RequestParam("categoryId")String[] categoryId , HttpSession session) throws IOException {

		User user = (User)session.getAttribute("user");
		blog.setUser(user);

		int result = blogService.add(blog,categoryId);
		if(result!=0){
			return gson.toJson(new Msg("success",result));
		}

		return gson.toJson(new Msg("error","发表失败"));
	}


	//图片上传
	@ResponseBody
	@RequestMapping(value="/imgUpload",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String imgUpload(@RequestParam("file")MultipartFile file){
		if(!UploadUtil.suffixMatch(file.getOriginalFilename())){
			msg = new Msg("error","不支持此文件类型");
		}else{
			String hashName = UploadUtil.getFileHash(file.getOriginalFilename());
			UploadUtil.imgUpload(hashName,file);
			msg = new Msg("success",hashName);
		}
		return gson.toJson(msg);
	}

}
