package com.app.util;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.object.ResponseObject;
import com.app.object.UploadImageObject;
import com.mortennobel.imagescaling.ResampleOp;
import java.io.ByteArrayInputStream;
import sun.misc.BASE64Decoder;

@Service
public class AssetUtil {

	public static final int STANDART_SIZE = 1280;
	public static final int THUMBS_SIZE = 140;
	
	
	private static final Logger log = LoggerFactory.getLogger(AssetUtil.class);

	@Autowired
	private ServletContext servletContext;

	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}

	public UploadImageObject saveImage(MultipartFile multipart) throws Exception{
			UploadImageObject response = new UploadImageObject();
			String tgl = new SimpleDateFormat("yyyy_MM_dd").format(Calendar.getInstance().getTime());
			String baseDir = getBaseDir();
			String dirPath = "/uploads/" + tgl;
			String fileName = UUID.randomUUID().toString() + "."
					+ multipart.getOriginalFilename().replaceAll(" ", "_");
			String path = dirPath + "/" + fileName;
			File dir = new File(baseDir + dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			log.info(baseDir + path);
			//log.info("LENGTH:"+multipart.getBytes().length);
			//File file = new File(baseDir + path);
			//FileUtils.writeByteArrayToFile(file, multipart.getBytes());
			response = saveStandardAndThumbImage(baseDir, dirPath, fileName, multipart.getInputStream(), STANDART_SIZE, THUMBS_SIZE);
			return response;
	}
        public UploadImageObject saveImage(String originalName,InputStream file) throws Exception{
			UploadImageObject response = new UploadImageObject();
			String tgl = new SimpleDateFormat("yyyy_MM_dd").format(Calendar.getInstance().getTime());
			String baseDir = getBaseDir();
			String dirPath = "/uploads/" + tgl;
			String fileName = UUID.randomUUID().toString() + "."
					+ originalName.replaceAll(" ", "_");
			String path = dirPath + "/" + fileName;
			File dir = new File(baseDir + dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			log.info(baseDir + path);
			//log.info("LENGTH:"+multipart.getBytes().length);
			//File file = new File(baseDir + path);
			//FileUtils.writeByteArrayToFile(file, multipart.getBytes());
			response = saveStandardAndThumbImage(baseDir, dirPath, fileName, file, STANDART_SIZE, THUMBS_SIZE);
			return response;
	}
	public String saveFile(MultipartFile multipart) throws Exception{
			String response = "";
			String tgl = new SimpleDateFormat("yyyy_MM_dd").format(Calendar.getInstance().getTime());
			String baseDir = getBaseDir();
			String dirPath = "/uploads/" + tgl;
			String fileName = UUID.randomUUID().toString() + "."
					+ multipart.getOriginalFilename().replaceAll(" ", "_");
			String path = dirPath + "/" + fileName;
			File dir = new File(baseDir + dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			log.info(baseDir + path);
			//log.info("LENGTH:"+multipart.getBytes().length);
			File file = new File(baseDir + path);
			FileUtils.writeByteArrayToFile(file, multipart.getBytes());
			response = path;
			return response;
	}
	public UploadImageObject saveStandardAndThumbImage(String baseDir,String fileStorePath, String newFileName, InputStream imageInputStream, final int standardSize,
            final int thumbSize) throws Exception {
			UploadImageObject response = new UploadImageObject();
			BufferedImage srcImage = ImageIO.read(imageInputStream);
			
			String extension = FilenameUtils.getExtension(newFileName);//newFileName.substring(newFileName.indexOf(".")+1, newFileName.length());
			int actualHeight = srcImage.getHeight();
			int actualWidth = srcImage.getWidth();
			double imageRatio = (double) actualWidth / (double) actualHeight;
			
			// First Save the standard size
			int height, width;
			if (actualHeight > standardSize || actualWidth > standardSize) {
			height = width = standardSize;
			if (imageRatio > 1) // 1 is standard ratio
			height = (int) (standardSize / imageRatio);
			else
			width = (int) (standardSize * imageRatio);
			} else {
			height = actualHeight;
			width = actualWidth;
			width = actualWidth;
			}
			ResampleOp resampleOp = new ResampleOp(width, height);
			BufferedImage scaledImage = resampleOp.filter(srcImage, null);
			ImageIO.write(scaledImage, extension, new File(baseDir+fileStorePath+"/"+newFileName));
			response.path = fileStorePath+"/"+newFileName;
			// Lets save the thumb size image now 
			resampleOp = null;
			scaledImage = null;
			if (actualHeight > thumbSize || actualWidth > thumbSize) { 
			height = width = thumbSize;
			if (imageRatio > 1)
			height = (int) (thumbSize / imageRatio);
			else
			width = (int) (thumbSize * imageRatio);
			} else {
			height = actualHeight;
			width = actualWidth;
			}
			resampleOp = new ResampleOp(width, height);
			scaledImage = resampleOp.filter(srcImage, null);
			ImageIO.write(scaledImage, extension, new File(baseDir+fileStorePath+"/"+ "s-" + newFileName));
			response.pathThumbnails = fileStorePath+"/"+ "s-" + newFileName;
			return response;
	}

	public String getBaseDir() {
		return servletContext.getRealPath("/").replace("\\", "/");
	}

	public String getBaseUploadDir() {
		return getBaseDir() + "/uploads";
	}
        
        public InputStream convertBase64ToInputStream(String sourceData) throws IOException{
            //def sourceData = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAPAAAADwCAYAAAA+VemSAAAgAEl...==';

            // tokenize the data
            String[] parts = sourceData.split(",");
            String imageString = parts[1];

            // create a buffered image
            BufferedImage image = null;
            byte[] imageByte;

            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            InputStream is = new ByteArrayInputStream(imageByte);
//            System.out.println("is:"+is.available());
            return is;
        }
}
