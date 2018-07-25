package com.rdc.service;

import com.rdc.bean.BlogBean;
import com.rdc.dao.BlogDao;
import com.rdc.entity.Blog;
import com.rdc.util.ConvertUtil;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	private final int PAGE_SIZE=10;

	/**
	 * Asce 2018/7/25
	 * 从用户id取博客列表
	 * @param userId
	 * @param page
	 * @return
	 */
	public ArrayList<Blog> getBlogByUser(int userId,int page){
		Map<String,Integer> map = new HashMap<>();
		map.put("userId",userId);
		int begin = page*PAGE_SIZE;
		map.put("begin",begin);
		ArrayList<Blog> blogs = blogDao.findBlogByUser(map);
		return blogs;
	}
	/**
	 * Asce 2018/7/25
	 * 搜索提示
	 * @param input
	 * @return
	 */
	public ArrayList<Blog> search(String input,int page){
		String usernameRegularExpression = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
		if (!input.matches(usernameRegularExpression)){
			return null;
		}
		int begin = page*PAGE_SIZE;
		Map<String,Object> map = new HashMap<>();
		map.put("input",input);
		map.put("begin",begin);
		ArrayList<Blog> blogs = blogDao.search(map);
		return blogs;
	}

	/**
	 * Asce 2018/7/25
	 * 搜索提示
	 * @param input
	 * @return
	 */
	public Map<String,Integer> searchPoint(String input){
		String usernameRegularExpression = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
		if (!input.matches(usernameRegularExpression)){
			return null;
		}
		ArrayList<Blog> blog = blogDao.searchPoint(input);
		Map<String,Integer> map=new HashMap<>();
		for(int i=0;i<blog.size();i++){
			map.put(blog.get(i).getTitle(),blog.get(i).getId());
		}
		return map;
	}
	/**
	 *Asce 2018-07-22
	 * 通过博客id取博客
	 * @param blogId
	 * @return
	 */
	public Blog showBlogById(int blogId){

		Blog blog = blogDao.findBlogById(blogId);
		if (blog==null){
			return null;
		}
		//时间格式化
		blog.setFinishTime(ConvertUtil.msecToMinutes(blog.getFinishTime()));

		return blog;
	}
	/**
	 * Asce 2018-07-21
	 * 删除博客
	 * @param userId
	 * @param blogId
	 * @return
	 * @throws BindingException
	 */
	public Boolean delete(int userId,int blogId)throws BindingException {

		if(userId==blogDao.findUserId(blogId)){
			int result = blogDao.delete(blogId);

			if(result==1)
				return true;
		}
		return false;
	}
	/**
	 * Asce 2018-07-21
	 * 修改博客
	 * @param blog
	 * @return
	 */
	@Transactional
	public Boolean modify(Blog blog){
		//本人判断
		if(blog.getUserBean().getId()!=blogDao.findUserId(blog.getId())){
			return false;
		}
		blog=blogConvert(blog);	//博客判断
		if(blog==null)
			return false;
		blogDao.modify(blog);
		return true;
	}

	/**
	 * Asce 2018-07-21
	 * 发表博客
	 * @param blog
	 * @return
	 * @throws DataIntegrityViolationException
	 */
	@Transactional
	public int add(Blog blog) throws DataIntegrityViolationException {

		blog=blogConvert(blog);	//博客判断

		if(blog==null)
			return 0;

		blogDao.add(blog);
		return blog.getId();
	}

	/**
	 * Asce 2018-07-21
	 * 博客验证
	 * @param blog
	 * @return
	 */
	public Blog blogConvert(Blog blog){

		if(blog.getTitle().length()>50||blog.getTitle().length()<5){
			return null;
		}
		if(!blog.getCategory().equals("后台")){
			if(!blog.getCategory().equals("安卓")){
				if(!blog.getCategory().equals("大数据")){
					if(!blog.getCategory().equals("前端")){
						return null;
					}
				}
			}
		}
		blog.setTitle(HtmlUtils.htmlEscape(blog.getTitle()));
		blog.setArticle(HtmlUtils.htmlEscape(blog.getArticle()));

		Calendar cal=Calendar.getInstance();
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		blog.setFinishTime(date.format(cal.getTime()));

		return blog;
	}
}
