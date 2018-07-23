package com.rdc.service;

import com.rdc.dao.BlogDao;
import com.rdc.dao.CategoryDao;
import com.rdc.dao.UpDao;
import com.rdc.entity.Blog;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	/**
	 *Asce 2018-07-22
	 * @param blogId
	 * @return
	 */
	public Blog showBlogById(int blogId){

		Blog blog = blogDao.findBlogById(blogId);
		if (blog==null){
			return null;
		}
		//时间格式化
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		blog.setFinishTime(date.format(cal.getTime()));

		return blog;
	}

	/**
	 * Asce 2018-07-21
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
	 * @param blog
	 * @param categoryId
	 * @return
	 */
	@Transactional
	public Boolean modify(Blog blog, String[] categoryId){
		//本人判断
		if(blog.getUserBean().getId()!=blogDao.findUserId(blog.getId())){
			return false;
		}

		blog=blogConvert(blog,categoryId);	//博客判断

		if(blog==null)
			return false;
		blogDao.modify(blog);
		//删除原有类别
		categoryDao.delete(blog.getId());
		//添加新类别
		Map<String,Map<String,String>> mapMap = getCategoryMap(categoryId,blog.getId());
		categoryDao.add(mapMap);

		return true;
	}

	/**
	 * Asce 2018-07-21
	 * @param blog
	 * @param categoryId
	 * @return
	 * @throws DataIntegrityViolationException
	 */
	@Transactional
	public int add(Blog blog, String[] categoryId) throws DataIntegrityViolationException {

		blog=blogConvert(blog,categoryId);	//博客判断

		if(blog==null)
			return 0;

		blogDao.add(blog);
		//批量插入博客类
		Map<String,Map<String,String>> mapMap = getCategoryMap(categoryId,blog.getId());
		//插入类别
		categoryDao.add(mapMap);

		return blog.getId();
	}

	/**
	 * Asce 2018-07-21
	 * @param blog
	 * @param category
	 * @return
	 */
	public Blog blogConvert(Blog blog, String[] category){

		if(blog.getTitle().length()>50||blog.getTitle().length()<5){
			return null;
		}
		if(category.length==0||category.length>5){
			return null;
		}

		blog.setTitle(HtmlUtils.htmlEscape(blog.getTitle()));
		blog.setArticle(HtmlUtils.htmlEscape(blog.getArticle()));

		Calendar cal=Calendar.getInstance();
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		blog.setFinishTime(date.format(cal.getTime()));

		return blog;
	}

	/**
	 * Asce 2018-07-21
	 * @param categoryId
	 * @param blogId
	 * @return
	 */
	public Map<String,Map<String,String>> getCategoryMap(String[] categoryId,int blogId){

		Map<String,String> map=new HashMap<>();
		Map<String,Map<String,String>> mapMap=new HashMap<>();

		for(int i=0;i<categoryId.length;i++){
			map.put(categoryId[i],blogId+"");
		}
		mapMap.put("keys",map);

		return mapMap;
	}

}
