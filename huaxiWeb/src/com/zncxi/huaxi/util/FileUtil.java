package com.zncxi.huaxi.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件操作工具类
 * @author xiaoCheng
 *
 */
public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * 文件删除
	 * @param path  文件绝对路径
	 */
	public static void delete(String path){
		try {
			File file = new File(path);
			if(!file.isFile() || !file.exists()){
				return;
			}
			file.delete();
		} catch (Exception e) {
			logger.error("文件"+path+"-删除操作出现未知异常！",e);
		}
	}
}
