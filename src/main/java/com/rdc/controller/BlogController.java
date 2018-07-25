package com.rdc.controller;

import com.google.gson.Gson;
import com.rdc.bean.BlogBean;
import com.rdc.bean.Msg;
import com.rdc.bean.UserBean;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
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
	 * Asce 2018/7/25
	 * 根据用户id找博客
	 * @param userId
	 * @param page
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="blogByUser/{userId}/{page}",method = RequestMethod.GET)
	public String BlogByUser(@PathVariable int userId,@PathVariable int page, HttpSession session){
		ArrayList<Blog> blogs = blogService.getBlogByUser(userId,page);

		User user = (User) session.getAttribute("user");
		ArrayList<BlogBean> blogBeans = getBlogBean(blogs,user.getId());

		return GsonUtil.getSuccessJson(blogBeans);
	}

	/**
	 * Asce 2018/7/25
	 * 封装BlogBean，通用方法
	 * @param userId
	 * @return
	 */
	public ArrayList<BlogBean> getBlogBean(ArrayList<Blog> blogs,int userId){

		ArrayList<BlogBean> blogBeans = new ArrayList<>();
		for(int i=0; i<blogs.size(); i++) {
			BlogBean blogBean = new BlogBean();
			//点赞数
			blogBean.setUpCount(upService.blogUpCount(blogs.get(i).getId()));
			//收藏数
			blogBean.setCollectionCount(collectionService.getCollectionCount(blogs.get(i).getId()));
			//评论数
			blogBean.setCommentCount(commentService.getBlogCommentCount(blogs.get(i).getId()));
			blogBean.setIsCollect(collectionService.isCollect(userId,blogs.get(i).getId()));
			blogBean.setIsUp(upService.isBlogUp(userId, blogs.get(i).getId()));
			blogBean.setBlog(blogs.get(i));
			blogBeans.add(blogBean);
		}
		return blogBeans;
	}

	/**
	 * Asce 2018/7/25
	 * 搜索结果
	 * @param input
	 * @param page
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/blogSearch/{input}/{page}",method = RequestMethod.GET)
	public String blogSearch(@PathVariable String input,@PathVariable int page,HttpSession session){
		ArrayList<Blog> blogs = blogService.search(input,page);
		User user = (User) session.getAttribute("user");
		return GsonUtil.getSuccessJson(getBlogBean(blogs,user.getId()));
	}

	/**
	 * Asce 2018/7/25
	 * 搜索提示
	 * @param input
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/blogSearchPoint/{input}",method = RequestMethod.GET)
	public String blogSearchPoint(@PathVariable String input){
		Map<String,Integer> map = blogService.searchPoint(input);
		if(map==null){
			return GsonUtil.getErrorJson();
		}
		return GsonUtil.getSuccessJson(map);
	}

	/**
	 * Asce 2018/7/25
	 * 评论点赞
	 * @param commentId
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/commentUp",method = RequestMethod.POST)
	public String commentUp(@RequestParam int commentId,HttpSession session){
		User user = (User) session.getAttribute("user");
		if(upService.commentUp(user.getId(),commentId)){
			return GsonUtil.getSuccessJson();
		}
		return GsonUtil.getErrorJson();
	}

	/**
	 * Asce 2018/7/25
	 * 回复点赞
	 * @param replyId
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/replyUp",method = RequestMethod.POST)
	public String replyUp(@RequestParam int replyId,HttpSession session){
		User user = (User) session.getAttribute("user");
		if(upService.replyUp(user.getId(),replyId)){
			return GsonUtil.getSuccessJson();
		}
		return GsonUtil.getErrorJson();
	}

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
		int result = commentService.addCommentReply(user.getId(),reply);
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
	 * Asce 2018-07-22
	 * 收藏博客
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
	 * 博客点赞
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
	 *根据id找博客,安卓
	 * @param blogId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/blogByAndroid/{blogId}/{page}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	public String showBlogWithReply(@PathVariable int blogId,@PathVariable int page,HttpSession session) throws ParseException {
		return showBlogById(blogId,1,page,session);
	}

	/**
	 * Asce 2018/7/25
	 *根据id找博客，前端
	 * @param blogId
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/blog/{blogId}/{page}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	public String showBlogWithoutReply(@PathVariable int blogId, @PathVariable int page, HttpSession session) throws ParseException {
		return showBlogById(blogId,0,page,session);
	}

	/**
	 * Asce 2018/7/25
	 * 前端取得回复
	 * @param commentId
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="reply/{commentId}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	public String getReply(@PathVariable int commentId, HttpSession session) {
		User user = (User) session.getAttribute("user");
		return GsonUtil.getSuccessJson(commentService.getReply(commentId,user.getId()));
	}

	/**
	 * Asce 2018/7/25
	 * 根据取得博客
	 * @param blogId
	 * @param type	1为安卓，其他为前端
	 * @param page
	 * @param session
	 * @return
	 */
	public String showBlogById(int blogId, int type , int page, HttpSession session) throws ParseException {

		Blog blog = blogService.showBlogById(blogId);
		if (blog==null){
			return GsonUtil.getErrorJson();		//没有此博客
		}
		User user = (User) session.getAttribute("user");
		ArrayList<Blog> blogs = new ArrayList<>();	//为使用getBlogBean妥协
		blogs.add(blog);
		ArrayList<BlogBean> blogBean = getBlogBean(blogs,user.getId());
		//评论
		ArrayList<Comment> comments;
		Map<String,Object> map = new HashMap<>();
		if(type==1){	//安卓
			comments = commentService.getCommentWithReply(blogId,user.getId(),page);
		}else {		//前端
			comments = commentService.getCommentWithoutReply(blogId,user.getId(),page);
		}
		map.put("blogBean",blogBean.get(1));
		map.put("comments",comments);
		return GsonUtil.getSuccessJson(map);
	}

	/**
	 * Asce 2018-07-21
	 * 修改博客
	 * @param blog
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/modifyBlog")
	public String modifyBlog(Blog blog, HttpSession session) {

		User user=(User)session.getAttribute("user");
		UserBean userBean = new UserBean();
		userBean.setId(user.getId());
		blog.setUserBean(userBean);
		if(blogService.modify(blog)){
			return gson.toJson(new Msg("success","修改成功"));
		}

		return gson.toJson(new Msg("error","修改失败"));
	}

	/**
	 * Asce 2018-07-21
	 * 删除博客
	 * @param blogId
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteBlog",method = RequestMethod.POST )
	public String deleteBlog(@RequestParam("blogId") int blogId, HttpSession session){

		User user=(User) session.getAttribute("user");
		if(!blogService.delete(user.getId(),blogId)){
			return gson.toJson(new Msg("error","删除失败"));
		}
		return null;
	}

	/**
	 * Asce 2018-07-21
	 * 发表博客
	 * @param blog
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addBlog",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String addBlog(Blog blog, HttpSession session) {

		User user=(User)session.getAttribute("user");
		UserBean userBean = new UserBean();
		userBean.setId(user.getId());
		blog.setUserBean(userBean);

		int result = blogService.add(blog);
		if(result!=0){
			return gson.toJson(new Msg("success",result));
		}

		return gson.toJson(new Msg("error","发表失败"));
	}

	/**
	 * Asce 2018-07-20
	 * 上传图片
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
