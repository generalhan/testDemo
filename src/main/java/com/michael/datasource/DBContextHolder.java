package com.michael.datasource;

/**
 * 多数据源配置
 * @author 
 *
 */
public class DBContextHolder {
	
	public static final String READ_DATA_SOURCE = "read_dataSource";
	public static final String WRITE_DATA_SOURCE = "write_dataSource";

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public static void setDBType(String dbType){
		contextHolder.set(dbType);
	}
	
	public static String getDBType(){
		return contextHolder.get();
	}
	
	public static void clearDBType(){
		contextHolder.remove();
	}
}

