package com.helperClass;

public class Constants {
	public static final String USER_NAME = "username";
	public static final String PASS_WORD = "password";
	public static final String HOME_MAC_ADDRESS = "macaddress";
	public static final String HOME_NAME = "name";
	public static final String HOME_EMAIL_ADDRESS = "email";
	public static final String HOME_PHONE_NUMBER = "phone";
	public static final String HOME_PASSWORD = "password";
	public static final String CMD_TYPE = "CommandType";
	public static final String CMD_SCAN = "SCAN";
	public static final String CMD_PAIR = "PAIR";
	
	public static final String TAG_SCAN_DEV_LIST  = "device_list";
	public static final String TAG_DEV_MAC_ADDR  = "mac_addr";
	public static final String TAG_DEV_ID           = "id";
	public static final String TAG_DEV_NAME         = "name";
	public static final String TAG_DEV_TYPE	= "type";
	public static final String TAG_DEV_DATA          = "data";
	public static final String TAG_DEV_DESCRIPTION       = "description";
	
	
	public static final String LOGIN_URL = "http://tikirobot.co/server/login";
	public static final String REGISTER_URL = "http://tikirobot.co/server/register";
	public static final String PAIR_URL = "http://tikirobot.co/server/pair";
	public static final String SCAN_URL = "http://tikirobot.co/server/scan-network";
	
	
	public static final String TAG_STATUS = "status";
	public static final String TAG_MESSAGE = "message";
	public static final String DEVICE_TYPE_PLUG ="PLUG";
	public static final String DEVICE_TYPE_SENSOR ="SENSOR";
	public static final String DEVICE_TYPE_UNKNOWN ="UNKNOWN";
	
	public static final int DATABASE_VERSION = 1;  
	public static final String DATABASE_NAME = "kiotec_home_mng";  
	public static final String TABLE_PAIRED_DEV = "paired_devices";  
	public static final String PAIRED_DEV_DEV_ID = "device_id";  
	public static final String PAIRED_DEV_DEV_NAME = "device_name";  
	public static final String PAIRED_DEV_DEV_TYPE = "device_type";  
	public static final String PAIRED_DEV_DEV_STATUS = "device_status";  
	public static final String PAIRED_DEV_DEV_MAC = "device_mac_addr";  
	public static final String PAIRED_DEV_DEV_DESCRIPTION = "device_description";  
	
	public static final String TABLE_PLUG_DEV = "tblPlug";  
	public static final String PLUG_ID = "plug_id";  
	public static final String PLUG_NAME = "plug_name";  
	public static final String PLUG_MAC = "plug_mac";  
	public static final String PLUG_CURR_STATUS = "plug_current_status";    
	public static final String PLUG_DESCR = "plug_description";   
	
	public static final String TABLE_SENSOR_DEV = "tblSensor";  
	public static final String SENSOR_ID = "sensor_id";  
	public static final String SENSOR_NAME = "sensor_name";  
	public static final String SENSOR_MAC = "sensor_mac";  
	public static final String SENSOR_CURR_TEMP = "sensor_current_temp";    
	public static final String SENSOR_CURR_HID = "sensor_currtent_hid"; 
	public static final String SENSOR_CURR_GAS = "sensor_currtent_gas"; 
	public static final String SENSOR_DESCR = "sensor_description"; 
	public static final String CTX_MENU_UNPAIR = "Unpair"; 
	public static final String CTX_MENU_EDIT = "Edit"; 
	
	public static final String TABLE_USER = "tblUser";
	public static final String NAME = "user_name";
	public static final String PASSWORD = "password";
	public static final String EMAIL  = "email";
	public static final String PHONE_NUMBER = "phone_number";
	
	
	
	

}
