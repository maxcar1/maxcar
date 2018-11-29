package com.maxcar.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 生成html
 * 
 * @author Administrator
 *
 */
public class GenerateHtmlUtils {
	public static ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

	/**
	 * 
	 * @param obj
	 *            填充ftl的数据对象
	 * @param request
	 * @param ftl
	 *            ftl名称
	 * @return
	 * @throws Exception
	 */
	public static String genarateHTML(HttpServletRequest request, String ftl, String serverUrl) {
		Writer out = null;
		String htmlUrl = null;
		try {
			/**
			 * 利用freemarker，填充数据
			 */
			// 第一步：实例化Freemarker的配置类
			Configuration conf = new Configuration();
			// 模版读取
			StringBuffer indexPath = new StringBuffer(request.getSession().getServletContext().getRealPath("/"));
			conf.setDirectoryForTemplateLoading(new File(indexPath.append("template").toString()));
			Template template = conf.getTemplate(ftl, "utf-8");
			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			// 在项目中创建文件夹
			StringBuffer filePath = new StringBuffer(request.getSession().getServletContext().getRealPath("/"));
			File file = new File(filePath.append("datas").append(File.separator).append(sdf.format(date)).toString());
			if (!file.exists()) {
				file.mkdirs();
			}

			Long time = System.currentTimeMillis();
			StringBuffer htmlPath = new StringBuffer();
			StringBuffer name = new StringBuffer("checkcar_result_");
			// 常量毫秒数连接加密
			String jiami = MD5Util.MD5(name.append(time).toString()).toLowerCase();
			out = new FileWriter(new File(
					htmlPath.append(file.getPath()).append(File.separator).append(jiami).append(".html").toString()));
			if (!map.isEmpty()) {
				template.process(map, out);
			}
			// 得到html路径
			StringBuffer url = new StringBuffer(map.get("url").toString());
			htmlUrl = String
					.valueOf(url.append("/datas/").append(sdf.format(date)).append("/").append(jiami).append(".html"));
			 if (null!=serverUrl && StringUtils.isNotEmpty(serverUrl)) {
				//服务保留备份,防止重启服务器数据丢失 
				StringBuffer source = new StringBuffer(serverUrl);
				File fUrl = new File(source.append(sdf.format(date)).toString());
				if (!fUrl.exists()) {
					fUrl.mkdirs();
				}
				StringBuffer mbSb = new StringBuffer(fUrl.getPath());
				File mbFile = new File(mbSb.append(File.separator).append(jiami).append(".html").toString());
			//	copyPicture(new File(htmlPath.toString()), fUrl);
				FileUtils.copyFile(new File(htmlPath.toString()), mbFile);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return htmlUrl;
	}

	// 用字节流--一次一个字节数组
	private static void copyPicture(File oUrl, File cUrl) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			// 封装数据源和目的地
			fis = new FileInputStream(oUrl);
			fos = new FileOutputStream(cUrl, true);
			// 读写
			byte[] bys = new byte[1024];
			int len = 0;
			while ((len = fis.read(bys)) != -1) {
				fos.write(bys, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 用字符流--一次一个字符
	private static void copyFile() throws IOException {
		// 封装数据源和目的地
		FileReader fr = new FileReader("mn.jpg");
		FileWriter fw = new FileWriter("d:\\mn.jpg");
		// 基本读写
		int ch = 0;
		while ((ch = fr.read()) != -1) {
			fw.write(ch);
			// fw.flush();
		}
		// 释放资源
		fw.close();
		fr.close();
	}
}
