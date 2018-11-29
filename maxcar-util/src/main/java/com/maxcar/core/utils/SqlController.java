package com.maxcar.core.utils;

public class SqlController {
           
	    // 备份数据库  
	    public static void backup(String dbName, String filePath) {  
	        try {  
	            /*Process process = Runtime.getRuntime().exec(  
	                    "cmd  /c  mysqldump -uroot -pmaxcar2017 " + dbName + " > "  
	                            + filePath + "/" + new java.util.Date().getTime()  
	                            + ".sql"); */
	            String[] cmd=new String[]{"/usr/bin/mysqldump -uroot  -pmaxcar2017 "+dbName+" >"+filePath};
	            Runtime.getRuntime().exec(cmd);
	        } catch (Exception e) {  
	            // TODO Auto-generated catch block  
	            System.out.println("系统错误,找不到文件路径");  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    @SuppressWarnings("unused")  
	    // 恢复数据库  
	    public static void load(String dbName, String filePath) {  
	        try {  
	            
	            Process process = Runtime.getRuntime().exec(  
	                    "cmd  /c  mysql -uroot -pmaxcar2017 " + dbName + " < " + filePath);  
	        } catch (Exception e) {
	        	System.out.println("系统错误,找不到文件路径");
	            e.printStackTrace();  
	        }  
	    }  
	  
	    public static void main(String[] args) {  
	        try {  
	            // backup("mysql2009","d:/");  
	        	backup("maxcar", "/var/lib/mysql-files/maxcarplus.sql");  
	            System.out.println("ok");  
	        } catch (Exception e) {  
	            // TODO: handle exception  
	            e.printStackTrace(); 
	        }  
	  
	    }  
}
