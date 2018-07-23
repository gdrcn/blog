package com.rdc.controller;

import com.google.gson.Gson;
import com.rdc.bean.Msg;
import com.rdc.entity.Blog;
import com.rdc.entity.Comment;
import com.rdc.entity.Reply;
import com.rdc.entity.User;
import com.rdc.service.BlogService;
import com.rdc.service.CollectionService;
import com.rdc.service.CommentService;
import com.rdc.service.UpService;
import com.rdc.util.GsonUtil;
import com.rdc.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/blog")
@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UpService upService;
	@Autowired
	private CollectionService collectionService;

	private Gson gson=new Gson();
	private Msg msg;


	/**
	 * 回复评论
	 * Asce 2018-07-23
	 * @param reply
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/commentReply",method = RequestMethod.POST)
	public String commentReply(Reply reply,HttpSession session){
		User user = (User)session.getAttribute("user");
		int result = commentService.addCommentReply(2,reply);
		if(result != 0){
			return GsonUtil.getSuccessJson(result);
		}
		return GsonUtil.getErrorJson();
	}
	/**
	 * 发表评论
	 * Asce 2018-07-23
	 * @param comment
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/blogComment",method = RequestMethod.POST)
	public String blogComment(Comment comment,HttpSession session){

		User user = (User)session.getAttribute("user");
		int result = commentService.addBlogComment(user.getId(),comment);
		if(result != 0){
			return GsonUtil.getSuccessJson(result);
		}
		return GsonUtil.getErrorJson();
	}

	/**
	 *
	 * Asce 2018-07-23
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/blogFromUser",method = RequestMethod.GET)
	public String blogFromUser(@RequestParam("userId")int userId){

		return "";
	}
	/**
	 * Asce 2018-07-22
	 * @param blogId
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/blogCollect",method = RequestMethod.POST)
	public String blogCollect(@RequestParam("blogId")int blogId,HttpSession session){
		User user = (User) session.getAttribute("user");
		if (collectionService.addCollection(user.getId(),blogId)){
			return GsonUtil.getSuccessJson();
		}
		return GsonUtil.getErrorJson();
	}
	/**
	 * Asce 2018-07-22
	 * @param blogId
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/blogIgnore",method = RequestMethod.POST)
	public String blogIgnore(@RequestParam("blogId")int blogId,HttpSession session){
		User user = (User) session.getAttribute("user");
		if(collectionService.deleteCollection(user.getId(),blogId)){
			return GsonUtil.getSuccessJson();
		}
		return GsonUtil.getErrorJson();
	}

	/**
	 * Asce 2018-07-22
	 * @param blogId
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/blogDown",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String blogDown(@RequestParam("blogId")int blogId, HttpSession session){
		User user = (User) session.getAttribute("user");
		if(upService.blogDown(user.getId(),blogId)){
			return GsonUtil.getSuccessJson();
		}
		return GsonUtil.getErrorJson();
	}

	/**
	 * Asce 2018-07-22
	 * @param blogId
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/blogUp",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String blogUp(@RequestParam("blogId")int blogId,HttpSession session){
		User user = (User)session.getAttribute("user");
		if(upService.blogUp(user.getId(),blogId)){
			return GsonUtil.getSuccessJson();
		}
		return GsonUtil.getErrorJson();
	}
	/**
	 * Asce 2018-07-22
	 *根据id找博客
	 * @param blogId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/blog/{blogId}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	public String showBlogById(@PathVariable int blogId,HttpSession session){

		Blog blog = blogService.showBlogById(blogId);
		if (blog==null){
			return GsonUtil.getErrorJson();		//没有此博客
		}
		//点赞数
		int upCount = upService.blogUpCount(blogId);
		//收藏数
		int collectionCount = collectionService.getCollectionCount(blogId);
		//评论数
		int commentCount = commentService.getBlogCommentCount(blogId);
		//是否收藏
		Boolean isCollect;
		//是否点赞
		Boolean isUp;
		User user = (User) session.getAttribute("user");
		if(user!=null) {
			isCollect = collectionService.isCollect(user.getId(),blogId);
			isUp = upService.isBlogUp(user.getId(),blogId);
		}else{
			isCollect=false;
			isUp=false;
		}
		Map<String,Object> map = new HashMap<>();
		map.put("blog",blog);
		map.put("upCount",upCount);
		map.put("collectionCount",collectionCount);
		map.put("commentCount",commentCount);
		map.put("isCollect",isCollect);
		map.put("isUp",isUp);
		return GsonUtil.getSuccessJson(map);
	}

	/**
	 * Asce 2018-07-21
	 * @param blog
	 * @param category
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="/modifyBlog")
	public String modifyBlog(Blog blog, @RequestParam("categoryId")String[] category, HttpSession session) throws IOException {

		User user=(User)session.getAttribute("user");
		blog.setUser(user);

		if(blogService.modify(blog,category)){
			return null;
		}

		return gson.toJson(new Msg("error","修改失败"));
	}

	/**
	 * Asce 2018-07-21
	 * @param blogId
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteBlog",method = RequestMethod.POST )
	public String deleteBlog(@RequestParam("blogId") int blogId,HttpSession session){

		User user=(User) session.getAttribute("user");
		if(!blogService.delete(user.getId(),blogId)){
			return gson.toJson(new Msg("error","删除失败"));
		}
		return null;
	}

	/**
	 * Asce 2018-07-21
	 * @param blog
	 * @param response
	 * @param categoryId
	 * @param session
	 * @return
	 * @throws IOException
	 */
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

	/**
	 * Asce 2018-07-20
	 * @param file
	 * @return
	 */
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
