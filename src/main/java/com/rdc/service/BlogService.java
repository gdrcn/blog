package com.rdc.service;

import com.rdc.dao.BlogDao;
import com.rdc.dao.CategoryDao;
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

	public Blog showBlogById(int blogId){


		return blogDao.findBlogById(blogId);
	}

	//删除博客
	public Boolean delete(int userId,int blogId)throws BindingException {

		if(userId==blogDao.findUserId(blogId)){
			int result = blogDao.delete(blogId);

			if(result==1)
				return true;
		}
		return false;
	}

	//修改博客
	@Transactional
	public Boolean modify(Blog blog, String[] categoryId){
		//本人判断
		if(blog.getUser().getId()!=blogDao.findUserId(blog.getId())){
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

	//发表博客
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
	//博客判断
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
	//制作博客类别map
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
