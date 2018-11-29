package com.maxcar.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 合同编号生成
 * 规则如下: 固定编号NO+年月日+4为生成数+市场id
 * @author yangsj
 *
 */
public class ContractNoGenerater {
	private static ContractNoGenerater contractNoGenerater = null;
	
	private static final String PREFIX = "NO";
	
	private static final String ZERO = "0";
	
	private static final String DATE = "yyyyMMdd";
	
	private static final String FOUR_ZERO = "0000";
	
    private ContractNoGenerater() {
    }

    /**
     * 懒汉式单例实现
     *
     * @return
     */
    public static ContractNoGenerater getInstance() {
        if (contractNoGenerater == null) {
            synchronized (ContractNoGenerater.class) {
                if (contractNoGenerater == null) {
                	contractNoGenerater = new ContractNoGenerater();
                }
            }
        }
        return contractNoGenerater;
    }
    
    /**
     *  生成下一个编号
     * @param sno    传最大的编号，才能返回累加后的!
     * @param prefix 生成的编号前缀
     * @return
     */
    public String generaterNextNumber(String suffix,String nowContract) {
    	String id = null;
    	synchronized (this) {
    		Date date = new Date();
    		SimpleDateFormat formatter = new SimpleDateFormat(DATE);
    		StringBuffer sb = new StringBuffer(PREFIX);
    		
    		if (StringUtils.isNotBlank(nowContract)) {
    			String subCno = nowContract.substring(nowContract.length()-7,
    					nowContract.length()-3);
    			Integer cNoInt = Integer.valueOf(subCno);
    			AtomicInteger atomicCno = new AtomicInteger(cNoInt);
    			atomicCno.getAndIncrement();
    			StringBuffer zero = new StringBuffer();
    			String cNoStr = String.valueOf(atomicCno);
    			for (int i = 0; i < 4-cNoStr.length(); i++) {
    				zero.append(ZERO);
    			}
    			id = sb.append(formatter.format(date)).append(zero).append(cNoStr).append(suffix).toString();
    		}
    		if (StringUtils.isBlank(nowContract)) {
    			id = sb.append(formatter.format(date)).append(FOUR_ZERO).append(suffix).toString();
    		}
		}
        return id;
    }
    
    public static void main(String[] args) {
    	/*String num = "NO201805230001007";
    	String subNum = num.substring(num.length()-7,num.length()-3);
    	System.out.println(subNum);
    	Integer subNumInt = Integer.valueOf(subNum);
    	subNumInt++;
    	System.out.println(String.valueOf(subNumInt).length());
    	System.out.println(subNumInt);
    	System.out.println(Integer.valueOf("0987"));
    	Integer cNoInt = 678;
    	String cc = String.valueOf(cNoInt);
    	StringBuffer zero = new StringBuffer();
		int length = String.valueOf(cNoInt).length();
		for (int i = 0; i < 4-length; i++) {
			zero.append("0");
		}
		System.out.println(zero.append(cc));*/
    	AtomicInteger atomicCno = new AtomicInteger(12);
		atomicCno.getAndIncrement();
		System.out.println(atomicCno);
	}
}
