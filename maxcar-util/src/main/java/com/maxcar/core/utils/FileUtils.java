package com.maxcar.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.async.CarefulRunnableQueue;

public class FileUtils {
	/**
	 * 网络资源下载到本地
	 * @param urlString
	 * @param savePath
	 * @return
	 */
	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);
	public static String download(String fileName, String urlString, String savePath) {
		try {
			// 构造URL
			URL url = new URL(urlString);
			logger.info("######################url" + url);
			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			InputStream is = con.getInputStream();

			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			logger.info("######################savePath" + savePath);
			File sf = new File(savePath);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			//fileName = urlString.substring(urlString.lastIndexOf("/")+1);
			logger.info("######################fileName" + fileName);
			logger.info("######################fileName" + sf.getPath() + "/" + fileName);
			OutputStream os = new FileOutputStream(sf.getPath() + "/" + fileName);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}  
	/**
	 * 删除文件
	 * @param path
	 * @param filename
	 */
	public static void delFile(String path, String filename) {
		File file = new File(path + filename);
		if (file.exists() && file.isFile())
			file.delete();
	}
	/**
	 * 创建文件
	 * @param path
	 */
	public static void createDir(String path) {
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdir();
	}
	/**
     * 根据java.io.*的流获取文件大小
     * @param file
     */
    public static float getFileSize(File file){
        FileInputStream fis  = null;
        float flot = 0;
        try {
            if(file.exists() && file.isFile()){
                String fileName = file.getName();
                fis = new FileInputStream(file);
                flot = Float.parseFloat(fis.available()+"")/1024F/1024F;
                System.out.println("文件"+fileName+"的大小是："+Float.parseFloat(fis.available()+"")/1024F/1024F+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(null!=fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return flot;
    }
	//维真拼接加密
	public static String sign(String vin ,String time,String APP_ID , String MC_NAME , String APP_KEY) {
		StringBuffer md5 = new StringBuffer();
		md5.append(MC_NAME).append(vin).append(APP_ID).append(APP_KEY).append(time);
		String sign = MD5Util.MD5(String.valueOf(md5)).toLowerCase();
		return sign;
	}
    public static void main(String[] args) {
    	File f = new File("C:\\Users\\Administrator\\Desktop\\微信图片_20171110163604.jpg");
		getFileSize(f);
	}
}
