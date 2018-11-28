package com.maxcar.core.utils.file;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;

import net.sf.json.JSONObject;


import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;

public class FileDownLoadUtil {

	/**
	 * 下载远程文件并保存到本地
	 * @param remoteFilePath 远程文件路径
	 * @param localFilePath 本地文件路径
	 */
	public static void downloadFile(String remoteFilePath, String localFilePath,String fileName) {
		URL urlfile = null;
		HttpURLConnection httpUrl = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			File f = new File(localFilePath);
			if (!f.exists()) {
				FileUtils.forceMkdir(f);
			}
			urlfile = new URL(remoteFilePath);
			httpUrl = (HttpURLConnection)urlfile.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			bos = new BufferedOutputStream(new FileOutputStream(localFilePath+"/"+fileName));
			int len = 2048;
			byte[] b = new byte[len];
			while ((len = bis.read(b)) != -1)
			{
				bos.write(b, 0, len);
			}
			bos.flush();
			bis.close();
			httpUrl.disconnect();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bis.close();
				bos.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void main(String []args){
//		downloadFile("http://localhost:9995/upload/cv/100/1492157280385/17041416080038595035.doc","d:/temp/a.doc");
	}

}
