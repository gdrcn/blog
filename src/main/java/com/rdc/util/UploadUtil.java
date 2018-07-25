package com.rdc.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadUtil {

	public final static String[] allowFile = {"jpg","bmp","gif","png"};

	/**
	 * Asce 2018/7/25
	 * 校验图片后缀
	 * @param fileName
	 * @return
	 */
	public static Boolean suffixMatch(String fileName){

		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

		for(int i=0;i<allowFile.length;i++){
			if(allowFile[i].equals(suffix)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Asce 2018/7/25
	 * 获取图片名哈希值
	 * @param fileName
	 * @return
	 */
	public static String getFileHash(String fileName){

		String prefix=fileName.substring(0,fileName.lastIndexOf("."));
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

		int salt = (int)(Math.random()*1000);//加盐，防止文件重名
		String name=prefix+""+salt;
		return ConvertUtil.encryptMd5(name)+"."+suffix;
	}

	/**
	 * Asce 2018/7/25
	 * 上传图片
	 * @param name
	 * @param file
	 */
	public static void imgUpload(String name, MultipartFile file){
		try {
			String path = new Object() {
				public String getPath() {
					String path=this.getClass().getResource("/").getPath();
					//path=path.replace('/', '\\'); // 将/换成\
					path=path.replace("classes/", ""); //去掉classes
					//path=path.substring(1);
					return path;
				}
			}.getPath();
			file.transferTo(new File(path+"img/"+name));
			System.out.println(path+"img/"+name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
