package com.zncxi.huaxi.util.upload;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public class UploadImg {

	@SuppressWarnings("unchecked")
	public String uploadImg(MultipartFile imgfile,String savePath,String saveUrl){
		try {
			if(imgfile != null){
				String fileName = imgfile.getOriginalFilename();
				File uploadDir = new File(savePath);
				if(!uploadDir.isDirectory()){
					uploadDir.mkdirs();
				}
				if(!uploadDir.canWrite()){
					String error = UploadError.getError("上传目录没有写权限。");
					return error;
				}
				@SuppressWarnings("unused")
				String separator = File.separator;
				File dirFile = new File(savePath);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
					File uploadedFile = new File(savePath, newFileName);
					try {
						imgfile.transferTo(uploadedFile);
					} catch (Exception e) {
						e.printStackTrace();
						String error = UploadError.getError("上传文件失败。");
						return error;
					} 
				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + newFileName);
				obj.put("savePath", savePath + newFileName);
				return obj.toJSONString();
			}else {
				String error = UploadError.getError("请选择文件。");
				return error;
			}
		} catch (Exception e) {
			e.printStackTrace();
			String error = UploadError.getError("上传文件失败。");
			return error;
		}		
	}
}
