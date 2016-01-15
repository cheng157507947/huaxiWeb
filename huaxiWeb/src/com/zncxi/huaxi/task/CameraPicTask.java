package com.zncxi.huaxi.task;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zncxi.huaxi.service.growpc.PicPredictService;

/**
 * 摄像头抓拍图片定时扫描任务
 * @author xiaoCheng
 *
 */
@Component("cameraPicTask")
public class CameraPicTask {
	
	@Autowired
	private PicPredictService picPredictService;
	
	private Logger logger = LoggerFactory.getLogger(CameraPicTask.class);
	private FileFilter fileFilter = new FileFilter() {
		@Override
		public boolean accept(File file) {
			String fileName = file.getName();
			if(fileName.endsWith(".jpg")){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String nowDate = format.format(new Date());
				if(Pattern.matches("^((full)|(single))(-"+nowDate+"-).*$", fileName)){
					return true;
				}
			}
			return false;
		}
	};

	/**
	 * 扫描任务
	 */
	public void execute(){
		logger.info("进入抓拍上传图片扫描...");
		
		String path = this.getUploadPath();
		if(path != null){
			File file = new File(path);
			if(!file.exists() && !file.isDirectory()){
				logger.error("抓拍上传配置的路径不存在或不是文件夹");
				return;
			}
			
			File[] imgs = file.listFiles(fileFilter); //获得文件夹
			if(imgs.length < 2){
				logger.error("当天抓拍上传的图片数量不足，无法添加至数据库");
				return;
			}
			File fullImg = null;
			File singleImg = null;
			for(File img: imgs){
				if(fullImg == null && img.getName().indexOf("full-") == 0){
					fullImg = img;
				}
				if(singleImg == null && img.getName().indexOf("single-") == 0){
					singleImg = img;
				}
			}
			
			if(fullImg == null || singleImg == null){
				logger.error("当天抓拍上传的图片缺少单株或全景图，无法添加至数据库");
				return;
			}
			
			String separator = File.separator;
			String uploadPath = this.getClass().getResource(File.separator).getPath();
			uploadPath = (uploadPath.substring(1, uploadPath.indexOf("WEB-INF"))).replaceAll("/", "\\"+separator);
			uploadPath = uploadPath+"images"+separator+"growPc"+separator+"uploadImg"+separator;
			
			if(this.fileMove(new File[]{fullImg, singleImg}, uploadPath)){
				this.picPredictService.save(uploadPath+fullImg.getName(), uploadPath+singleImg.getName());
			}
		}
	}
	
	/**
	 * 抓拍图片转移
	 * @param files
	 * @param path
	 * @return
	 */
	public boolean fileMove(File[] files, String path){
		boolean normal = true;
		for(File file: files){
			if (file != null) {  
			    try {  
			        FileInputStream fin = new FileInputStream(file);  
			        //int filesize = fin.available();  
			        byte[] buffer = new byte[1024];  
			        
			        //System.out.println(path+file.getName());
			        File file2 = new File(path+file.getName());
			        file2.createNewFile();
			        FileOutputStream fout = new FileOutputStream(file2);  
			        int readCount = 0;  
			        while ((readCount = fin.read(buffer)) >= 0)  //最多读取buffer的长度  
			        {  
			            fout.write(buffer, 0, readCount);  
			        }  
			        fin.close();  
			        fout.close();
			        //file.delete();
			    } catch (Exception e) {  
			        logger.error("当天抓拍上传的图片转移过程中出现错误",e);
			        normal = false;
			    }  
			}  
		}
		return normal;
	}
	
	/**
	 * 获得抓拍上传路径
	 * @return
	 */
	public String getUploadPath(){
		try {
			InputStream in = CameraPicTask.class.getClassLoader().getResourceAsStream("application.properties");
			Properties config = new Properties();
			config.load(in);
			return config.getProperty("camera.up_path");
		} catch (IOException e) {
			logger.error("读取application.properties文件时出现未知错误！",e);
		}
		return null;
	}
	
	public String path(){
		return this.getClass().getResource("\\"+File.separator).getPath();
	}
	
	public static void main(String[] args) throws IOException {
		CameraPicTask task = new CameraPicTask();
		System.out.println(task.getUploadPath());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = format.format(new Date());
		System.out.println(nowDate);
		System.out.println(Pattern.matches("^((full)|(single))(-"+nowDate+"-).*$", "full-2015-06-25-15-07-08_02.jpg"));
		System.out.println("full-2015-06-25-15-07-08_02.jpg".indexOf("full-"));
		System.out.println((task.path().substring(1, task.path().indexOf("WEB-INF"))).replaceAll("/", "\\"+File.separator));
		System.out.println(task.path());
		
		File file = new File("F:\\java工具包\\apache-tomcat-6.0.41\\webapps\\huaxiWeb\\images\\growPc\\uploadImg\\full-2015-06-25-15-07-08_02.jpg");
		file.createNewFile();
		System.out.println(file.getAbsolutePath());
	}
}
