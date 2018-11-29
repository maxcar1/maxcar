package com.maxcar.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.io.output.FileWriterWithEncoding;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * freeMark模板相关工具
* @ClassName: FreeMarkUtil 
* @author huangxu 
* @date 2018年1月25日 下午3:11:25 
*
 */
public class FreeMarkUtil {
	/**
	 * 根据数据生成html
	 * @param root
	 * @param templateName
	 * @return
	 */
	public static String getHtmlString(Map<String, Object> root, String templateName,String filePath) {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
		try {
			cfg.setDirectoryForTemplateLoading(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		String html = "";
		//获取一个模板
        try {
			Template temp = cfg.getTemplate(templateName+".ftl");
			//html
			FileWriterWithEncoding out = new FileWriterWithEncoding(filePath + templateName + ".html" ,"UTF-8");
			temp.process(root, out);
			html = readfile(filePath + "/" + templateName + ".html");
		} catch (Exception e) {
			e.printStackTrace();
		}
        html = html.replace("\n", "").replace("\t", "");
		return html;
	}

	public static String readfile(String filePath){
        File file = new File(filePath);  
        InputStream input = null;
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  
        StringBuffer buffer = new StringBuffer();  
        byte[] bytes = new byte[1024];
        try {
            for(int n ; (n = input.read(bytes))!=-1 ; ){  
                buffer.append(new String(bytes,0,n,"UTF-8"));  
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(buffer);
        return buffer.toString();  
    }
	public static String getLocalHtmlStream(String path) {
		File f = new File(path);
		
		if (!f.exists() && !f.getAbsolutePath().endsWith("html")) {
			return "";
		} //
		StringBuffer b = new StringBuffer();
		try {
			InputStream in = new FileInputStream(f);
			int c;
			while ((c = in.read()) != -1) {
				b.append((char) c);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(b.toString());
	}
}
