package com.helperClass;

import java.util.ArrayList;
import com.devices.PairedDevice;
import com.devices.Sensor;
import com.devices.SmartPlug;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class SQLiteHelper extends SQLiteOpenHelper {


	    
	public SQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	public SQLiteHelper(Context context) {
		super(context, Constants.DATABASE_NAME, null,  Constants.DATABASE_VERSION);  
	}

	@Override
		public void onCreate(SQLiteDatabase db) {
			createPairingTable(db);
			createSensorTable(db);
			createPlugTable(db);
	  
		}
	
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        // Drop older table if existed  
	        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_PAIRED_DEV);  
	        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_PLUG_DEV);
	        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_SENSOR_DEV);
	   
	        // Create tables again  
	        onCreate(db);  
			
		}
		public void createPairingTable(SQLiteDatabase db)
		{
			String CREATE_PAIRED_DEV_TABLE = "CREATE TABLE " + Constants.TABLE_PAIRED_DEV + "("  
	                + Constants.PAIRED_DEV_DEV_ID + " TEXT,"  
	                + Constants.PAIRED_DEV_DEV_NAME + " TEXT," + Constants.PAIRED_DEV_DEV_TYPE + " TEXT," 
	                + Constants.PAIRED_DEV_DEV_MAC + " TEXT PRIMARY KEY,"  + Constants.PAIRED_DEV_DEV_STATUS + " TEXT,"  
	                + Constants.PAIRED_DEV_DEV_DESCRIPTION + " TEXT" + ")"; 
			db.execSQL(CREATE_PAIRED_DEV_TABLE);
	
		}
		
		public void createPlugTable (SQLiteDatabase db) {
			String CREATE_PLUG_DEV_TABLE = "CREATE TABLE " + Constants.TABLE_PLUG_DEV + "("  
	                +  Constants.PLUG_ID + " TEXT,"  
	                + Constants.PLUG_NAME + " TEXT," + Constants.PLUG_MAC + " TEXT PRIMARY KEY," 
	                + Constants.PLUG_CURR_STATUS + " BOOLEAN,"  + Constants.PLUG_DESCR + " TEXT" + ")" ; 
			db.execSQL(CREATE_PLUG_DEV_TABLE);
		}
		
		public void createSensorTable(SQLiteDatabase db)
		{
			String CREATE_SENSOR_DEV_TABLE = "CREATE TABLE " + Constants.TABLE_SENSOR_DEV + "("  
	                +  Constants.SENSOR_ID + " TEXT,"  				+ Constants.SENSOR_NAME + " TEXT," 
					+ Constants.SENSOR_MAC + " TEXT PRIMARY KEY," 	+ Constants.SENSOR_CURR_TEMP + " DOUBLE," 
	                + Constants.SENSOR_CURR_HID + " DOUBLE," 			+ Constants.SENSOR_CURR_GAS + " DOUBLE,"   
	                + Constants.SENSOR_DESCR + " TEXT" + ")"  ; 
			db.execSQL(CREATE_SENSOR_DEV_TABLE);

		}
		
		

	// ---------------------------PairedDevice (Unknown paired devices) -----------------------------
	 	boolean isExistInParingDeviceTbl(PairedDevice pDev) {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(Constants.TABLE_PAIRED_DEV, new String[] { Constants.PAIRED_DEV_DEV_ID,  
					Constants.PAIRED_DEV_DEV_NAME, Constants.PAIRED_DEV_DEV_MAC, Constants.PAIRED_DEV_DEV_STATUS, Constants.PAIRED_DEV_DEV_DESCRIPTION}, Constants.PAIRED_DEV_DEV_MAC + "=?",  
	                new String[] { String.valueOf(pDev.getDeviceMac_addr()) }, null, null, null, null);  
			
			if(cursor != null) {
				cursor.close();
				db.close();
				return true;
			} else 
			{	
				db.close();
				return false;
			}
		}
		public boolean addDevices(PairedDevice dev) {  
	        SQLiteDatabase db = this.getWritableDatabase();  
	   
	        ContentValues values = new ContentValues();  
	        values.put(Constants.PAIRED_DEV_DEV_ID, dev.getDeviceID()); 
	        values.put(Constants.PAIRED_DEV_DEV_NAME, dev.getDeviceName());
	        values.put(Constants.PAIRED_DEV_DEV_TYPE, dev.getDeviceType());
	        values.put(Constants.PAIRED_DEV_DEV_MAC, dev.getDeviceMac_addr());
	        values.put(Constants.PAIRED_DEV_DEV_STATUS, dev.getStatus());
	        values.put(Constants.PAIRED_DEV_DEV_DESCRIPTION, dev.getDescription());
	        try
	        {
	        	
	        	db.insertOrThrow(Constants.TABLE_PAIRED_DEV, null, values); 
	        	db.close();
	        	return true;
	        }catch(Exception e)
	        {
	        	db.close();
	        	Log.e("DUPLICATE PRM KEY",e.toString());
	        	return false;
	        }
	
	    }  
		public boolean updateDeviceInfor(PairedDevice dev) {
			boolean rs = false;
			if(!isExistInParingDeviceTbl(dev)) {
				rs = false;
			} else {
				try {
					SQLiteDatabase db = this.getWritableDatabase();  			   
			        ContentValues values = new ContentValues();  
			        values.put(Constants.PAIRED_DEV_DEV_ID, dev.getDeviceID()); 
			        values.put(Constants.PAIRED_DEV_DEV_NAME, dev.getDeviceName());
			        values.put(Constants.PAIRED_DEV_DEV_TYPE, dev.getDeviceType());
			        values.put(Constants.PAIRED_DEV_DEV_MAC, dev.getDeviceMac_addr());
			        values.put(Constants.PAIRED_DEV_DEV_STATUS, dev.getStatus());
			        values.put(Constants.PAIRED_DEV_DEV_DESCRIPTION, dev.getDescription());
			        int row =  db.update(Constants.TABLE_PAIRED_DEV, values, Constants.PAIRED_DEV_DEV_MAC + " = ?",  
			                new String[] { String.valueOf(dev.getDeviceMac_addr()) }); 
			        db.close();
			        if(row != 0) {
						rs = true;
					} else {
						rs = false;
					}
				} catch (Exception e) {
					Log.e("DB ERROR", e.toString());
					rs = false;
				}
		       
	    	}
	    	return rs;
	    } 
		public boolean deleteDevice(PairedDevice dev) {
			boolean rs = false;
			if(!isExistInParingDeviceTbl(dev)) {
				rs = false;
			} else {
				try {
					 SQLiteDatabase db = this.getWritableDatabase();  
				        int row = db.delete(Constants.TABLE_PAIRED_DEV, Constants.PAIRED_DEV_DEV_MAC + " = ?",  
				                new String[] { String.valueOf(dev.getDeviceMac_addr()) });  
				        db.close();
				        if(row != 0) {
							rs = true;
						} else {
							rs = false;
						}
					} catch (Exception e) {
						Log.e("DB ERROR", e.toString());
						rs = false;
					}
			       
		    	}
		    	return rs;
	    }
		public PairedDevice getDevices(String mac_addr)
		{
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(Constants.TABLE_PAIRED_DEV, new String[] { Constants.PAIRED_DEV_DEV_ID,  
					Constants.PAIRED_DEV_DEV_NAME, Constants.PAIRED_DEV_DEV_MAC, Constants.PAIRED_DEV_DEV_STATUS, Constants.PAIRED_DEV_DEV_DESCRIPTION}, Constants.PAIRED_DEV_DEV_MAC + "=?",  
	                new String[] { String.valueOf(mac_addr) }, null, null, null, null);  
	        if (cursor != null) {
	            cursor.moveToFirst();  
	        } else {
	        	return null;
	        }
	        String dev_id = cursor.getString(0);
	        String dev_name = cursor.getString(1);
	        String dev_tpe = cursor.getString(2);
	        String dev_mac = cursor.getString(3);
	        String dev_status = cursor.getString(4);
	        String dev_descr = cursor.getString(5);
	        cursor.close();
	        db.close();
	        
	        PairedDevice dev = new PairedDevice();	
	        dev.setDeviceName(dev_name);
	        dev.setDeviceID(dev_id);
	        dev.setDeviceMac_addr(dev_mac);
	        dev.setStatus(dev_status);
	        dev.setDescription(dev_descr);
	        dev.setDeviceType(dev_tpe);
	
	        // return paired dev  
	        return dev;  
			
		}
		public ArrayList<PairedDevice> getAllDevices()
		{
			ArrayList<PairedDevice> devicesList = new ArrayList<PairedDevice>();
			String selectQuery = "SELECT  * FROM " + Constants.TABLE_PAIRED_DEV;  
			   
	        SQLiteDatabase db = this.getWritableDatabase();  
	        Cursor cursor = db.rawQuery(selectQuery, null);  
	   
	        // looping through all rows and adding to list  
	        if (cursor.moveToFirst()) {  
	            do {  
	            	 String dev_id = cursor.getString(0);
	                 String dev_name = cursor.getString(1);
	                 String dev_tpe = cursor.getString(2);
	                 String dev_mac = cursor.getString(3);
	                 String dev_status = cursor.getString(4);
	                 String dev_descr = cursor.getString(5);
	                 PairedDevice dev = new PairedDevice();	
	                 dev.setDeviceName(dev_name);
	                 dev.setDeviceID(dev_id);
	                 dev.setDeviceMac_addr(dev_mac);
	                 dev.setStatus(dev_status);
	                 dev.setDescription(dev_descr);
	                 dev.setDeviceType(dev_tpe);
	                 
	                 devicesList.add(dev);  
	            } while (cursor.moveToNext());  
	        }  
	        cursor.close();
	        db.close(); 
	        // return paired list  
	        return devicesList;  
		}
		public int getDevivesCount() {  
		        String countQuery = "SELECT  * FROM " + Constants.TABLE_PAIRED_DEV;  
		        SQLiteDatabase db = this.getReadableDatabase();  
		        Cursor cursor = db.rawQuery(countQuery, null);
		        int count = cursor.getCount();
		        cursor.close();
		        db.close();
		        // return count
		        return count ;
	
		    } 
		 
	 
	 //----------------------------- plug ----------------------------
	 	boolean isExistInPlugTbl(SmartPlug plg) {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(Constants.TABLE_PLUG_DEV, 
									new String[] { Constants.PLUG_ID,  
					Constants.PLUG_NAME, Constants.PLUG_MAC, Constants.PLUG_CURR_STATUS, Constants.PLUG_DESCR}, 
					Constants.PLUG_MAC + "=?",  
	                new String[] { String.valueOf(plg.getDeviceMac_addr()) }, null, null, null, null);  
			if(cursor != null) {
				cursor.close();
				db.close();
				return true;
			} else 
			{	
				db.close();
				return false;
			}
		}
	    public boolean addPlug(SmartPlug plug) {
	    	boolean rs = false;
	    	Log.e("DB Message",plug.toString());
	    	if(isExistInPlugTbl(plug)) {
	    		Log.e("DB Message","tao co roi");
	    		rs = false;
	    	} else {
	    		Log.e("DB Message","tao o day");
		        SQLiteDatabase db = this.getWritableDatabase();  
		   
		        ContentValues values = new ContentValues();  
		        
		        values.put(Constants.PLUG_ID, plug.getDeviceID()); 
		        values.put(Constants.PLUG_NAME, plug.getDeviceName());
		        values.put(Constants.PLUG_MAC, plug.getDeviceMac_addr());
		        values.put(Constants.PLUG_CURR_STATUS,plug.getCurrentStatus());
		        values.put(Constants.PLUG_DESCR, plug.getDescription());
		 
		        try
		        {
		        	
		        	db.insertOrThrow(Constants.TABLE_PLUG_DEV, null, values);  
		        	db.close();
		        	rs = true;
		        }catch(Exception e)
		        {
		        	db.close();
		        	Log.e("DB Error",e.toString());
		        	rs = false;
		        }
		       
		    }
	    	return rs;
	    }		
	    public boolean updatePlugInfor(SmartPlug plg) {
	    	boolean rs = false;
	    	if(!isExistInPlugTbl(plg)) {
	    		rs =  false;
	    	} else {
	    		try {
	    			SQLiteDatabase db = this.getWritableDatabase();          
	 		        ContentValues values = new ContentValues();  
	 		        values.put(Constants.PLUG_ID, plg.getDeviceID()); 
	 		        values.put(Constants.PLUG_NAME, plg.getDeviceName());
	 		        values.put(Constants.PLUG_MAC, plg.getDeviceMac_addr());
	 		        values.put(Constants.PLUG_CURR_STATUS,plg.getCurrentStatus());
	 		        values.put(Constants.PLUG_DESCR, plg.getDescription());
	 		        // updating row  with new data
	 		        int row = db.update(Constants.TABLE_PLUG_DEV, values, Constants.PLUG_MAC + " = ?",  
	 		                new String[] { String.valueOf(plg.getDeviceMac_addr()) }); 
	 		       db.close();
					if(row != 0) {
						rs = true;
					} else {
						rs = false;
					}
				} catch (Exception e) {
					Log.e("DB ERROR", e.toString());
					rs = false;
				}
		       
	    	}
	    	return rs;
	    } 
	    public boolean deleteAPlug(SmartPlug  plg) { 
	    	boolean rs = false;
	    	if(!isExistInPlugTbl(plg)) {
	    		rs = false;
	    	} else {
	    		try {
	    			SQLiteDatabase db = this.getWritableDatabase();  
			        int row = db.delete(Constants.TABLE_PLUG_DEV, Constants.PLUG_MAC + " = ?",  
			                new String[] { String.valueOf(plg.getDeviceMac_addr()) });  
			        db.close(); 
			        
			        if(row ==0) {
			        	rs = false;
			        } else {
			        	rs = true;
			        }
				} catch (Exception e) {
					rs = false;
					Log.e("DB ERROR", e.toString());
				}
		        
	    	}
	    	return rs;
	    }
	    public SmartPlug getPlugBasedOnMAC_addr(String mac_addr)
		{
			SmartPlug plug = new SmartPlug();
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(Constants.TABLE_PLUG_DEV, 
									new String[] { Constants.PLUG_ID,  
					Constants.PLUG_NAME, Constants.PLUG_MAC, Constants.PLUG_CURR_STATUS, Constants.PLUG_DESCR}, 
					Constants.PLUG_MAC + "=?",  
	                new String[] { String.valueOf(mac_addr) }, null, null, null, null);  
	        if (cursor != null)
	        {
	            cursor.moveToFirst();
	        } else {	        	
	        	db.close();
	        	return null;
	        }
	        String plug_id = cursor.getString(0);
	        String plug_name = cursor.getString(1);
	        String plug_mac = cursor.getString(2);
	        String plug_curr_status = cursor.getString(3);
	        String plug_descr = cursor.getString(4);
	        cursor.close();
	        db.close();
	        boolean status = Utilities.parseStringToBool(plug_curr_status);
	        plug.setDeviceID(plug_id);
	        plug.setDeviceName(plug_name);
	        plug.setDeviceMac_addr(plug_mac);
	        plug.setStatus(status);
	        plug.setDescription(plug_descr);
	        return plug;  
			
		}
	    public ArrayList<SmartPlug> getAllPlugs()
		{
			
			SmartPlug plug = new SmartPlug();
			ArrayList<SmartPlug> plugList = new ArrayList<SmartPlug>();
			String selectQuery = "SELECT  * FROM " + Constants.TABLE_PLUG_DEV;  
			   
	        SQLiteDatabase db = this.getWritableDatabase();  
	        Cursor cursor = db.rawQuery(selectQuery, null);  
	   
	        // looping through all rows and adding to list  
	        if (cursor.moveToFirst()) {  
	            do {  
	            	 String plug_id = cursor.getString(0);
	     	        String plug_name = cursor.getString(1);
	     	        String plug_mac = cursor.getString(2);
	     	        String plug_curr_status = cursor.getString(3);
	     	        String plug_descr = cursor.getString(4);
	     	        boolean status = Utilities.parseStringToBool(plug_curr_status);
	     	        
	     	       plug.setDeviceID(plug_id);
	     	       plug.setDeviceName(plug_name);
	     	       plug.setDeviceMac_addr(plug_mac);
	     	       plug.setStatus(status);
	     	       plug.setDescription(plug_descr);
	                // Adding plug to list  
	                 plugList.add(plug);  
	            } while (cursor.moveToNext());  
	        }
	        cursor.close();
	        db.close();
	        // return plug list  
	        return plugList;  
		}
	    
	    public ArrayList<SmartPlug> getAllPlugs2()
		{
			
			SmartPlug plug = new SmartPlug();
			ArrayList<SmartPlug> plugList = new ArrayList<SmartPlug>();
			String selectQuery = "SELECT  * FROM " + Constants.TABLE_PLUG_DEV;  
			   
	        SQLiteDatabase db = this.getWritableDatabase();  
	        Cursor cursor = db.rawQuery(selectQuery, null);  
	   
	        // looping through all rows and adding to list  
	        if (cursor.moveToFirst()) {  
	            do {  
	            	
	     	        String plug_name = cursor.getString(1);
	     
	     	        String plug_curr_status = cursor.getString(3);
	     	        String plug_descr = cursor.getString(4);
	     	        boolean status = Utilities.parseStringToBool(plug_curr_status);
	     	        
	    
	     	       plug.setDeviceName(plug_name);
	     	       
	     	       plug.setStatus(status);
	     	       plug.setDescription(plug_descr);
	                // Adding plug to list  
	                 plugList.add(plug);  
	            } while (cursor.moveToNext());  
	        }
	        cursor.close();
	        db.close();
	        // return plug list  
	        return plugList;  
		}
	    
	    
	    public int getPlugsCount() {  
		        String countQuery = "SELECT  * FROM " + Constants.TABLE_PLUG_DEV;  
		        SQLiteDatabase db = this.getReadableDatabase();  
		        Cursor cursor = db.rawQuery(countQuery, null);
		        int count = cursor.getCount();
		        cursor.close();
		        db.close();
		        // return count
		        return count ;

		    }  
		
		 //------------------------------sensor --------------------------
		boolean isExistInSensorTbl(Sensor ss) {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query (Constants.TABLE_SENSOR_DEV, 
					new String[] {	Constants.SENSOR_ID, Constants.SENSOR_NAME, 
									Constants.SENSOR_MAC, Constants.SENSOR_CURR_TEMP, 
									Constants.SENSOR_CURR_HID, Constants.SENSOR_CURR_GAS,
									Constants.SENSOR_DESCR}, Constants.PLUG_MAC + "=?", 
									new String[] { String.valueOf(ss.getDeviceMac_addr()) }, null, null, null, null);
			if(cursor != null) {
				cursor.close();
				db.close();
				return true;
			} else 
			{
				return false;
			}
		}
		public boolean addSensor(Sensor sensor) {  
			boolean rs = false;
			 if(isExistInSensorTbl(sensor)) {
				 rs = false;
			 } else {
				 try {
					 	SQLiteDatabase db = this.getWritableDatabase();  				   
				        ContentValues values = new ContentValues();  			        
				        values.put(Constants.SENSOR_ID, sensor.getDeviceID()); 
				        values.put(Constants.SENSOR_NAME, sensor.getDeviceName());
				        values.put(Constants.SENSOR_MAC, sensor.getDeviceMac_addr());
				        values.put(Constants.SENSOR_CURR_TEMP,sensor.getCurrentTemperature());
				        values.put(Constants.SENSOR_CURR_HID, sensor.getCurrentHudmity());
				        values.put(Constants.SENSOR_CURR_GAS, sensor.getCurrentGas());
				        values.put(Constants.SENSOR_DESCR, sensor.getDescription());
				 
				        // Inserting Row  
				        db.insert(Constants.TABLE_SENSOR_DEV, null, values);  
				        //2nd argument is String containing nullColumnHack  
				        db.close(); // Closing database connection  
				        rs = true;
				} catch (Exception e) {
					rs = false;
					Log.e("DB ERROR", e.toString());
				}
		    }
			 return rs;
		}
		public boolean updateSensorInfor(Sensor ss) {
			 boolean rs = false;
			 if(isExistInSensorTbl(ss)) {
				 rs = false;
			 } else {
				 try {
					 SQLiteDatabase db = this.getWritableDatabase();  		   
					 ContentValues values = new ContentValues();  			        
				     values.put(Constants.SENSOR_ID, ss.getDeviceID()); 
				     values.put(Constants.SENSOR_NAME, ss.getDeviceName());
				     values.put(Constants.SENSOR_MAC, ss.getDeviceMac_addr());
				     values.put(Constants.SENSOR_CURR_TEMP,ss.getCurrentTemperature());
				     values.put(Constants.SENSOR_CURR_HID, ss.getCurrentHudmity());
				     values.put(Constants.SENSOR_CURR_GAS, ss.getCurrentGas());
				     values.put(Constants.SENSOR_DESCR, ss.getDescription());
					 int row = db.update(Constants.TABLE_SENSOR_DEV, values, Constants.SENSOR_MAC + " = ?",  
				                new String[] { String.valueOf(ss.getDeviceMac_addr()) }); 
					 db.close();
					 if(row != 0) {
						 rs = true;
					 } else {
						 rs = false;
					 }
				} catch (Exception e) {
					rs = false;
					Log.e("DB ERROR", e.toString());
				}			
			 }
			 return rs;
		 } 
		public boolean deleteASensor(Sensor ss) {
			 boolean rs = false;
			 if(isExistInSensorTbl(ss)){
				 rs = false;
			 } else {
				 try {
					 SQLiteDatabase db = this.getWritableDatabase();  
				     int row = db.delete(Constants.TABLE_SENSOR_DEV, Constants.SENSOR_MAC + " = ?",  
				                new String[] { String.valueOf(ss.getDeviceMac_addr()) });  
				     db.close();
				     if(row != 0) {
						 rs = true;
					 } else {
						 rs = false;
					 }
				} catch (Exception e) {
					rs = false;
					Log.e("DB ERROR", e.toString());
				}						 
			 }
			 return rs;     
		 }
		public Sensor getSensorBasedOnMAC_addr(String mac_address) {
			Sensor sensor = new Sensor();
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query (Constants.TABLE_SENSOR_DEV, 
											new String[] {	Constants.SENSOR_ID, Constants.SENSOR_NAME, 
															Constants.SENSOR_MAC, Constants.SENSOR_CURR_TEMP, 
															Constants.SENSOR_CURR_HID, Constants.SENSOR_CURR_GAS,
															Constants.SENSOR_DESCR}, Constants.PLUG_MAC + "=?", 
															new String[] { String.valueOf(mac_address) }, null, null, null, null);  
		    if (cursor != null) {
		         cursor.moveToFirst();
		    } else {
		    	return null;
		    }
		    String sensor_id = cursor.getString(0);
		    String sensor_name = cursor.getString(1);
		    String sensor_mac = cursor.getString(2);
		    double sensor_curr_temp = cursor.getDouble(3);
		    double sensor_curr_hid = cursor.getDouble(4);
		    double sensor_curr_gas = cursor.getDouble(5);
		    String sensor_descr = cursor.getString(6);
		    
		    cursor.close();
		    db.close();
		    
		    sensor.setDeviceID(sensor_id);
		    sensor.setDeviceName(sensor_name);
		    sensor.setDeviceMac_addr(sensor_mac);;
		    sensor.setCurrentTemperature(sensor_curr_temp);
		    sensor.setCurrentHudmity(sensor_curr_hid);
		    sensor.setCurrentGas(sensor_curr_gas);
		    sensor.setDescription(sensor_descr);
		    return sensor;  
				
		}
		public ArrayList<Sensor> getAllSensors()
			{
				
				Sensor sensor = new Sensor();
				ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
				String selectQuery = "SELECT  * FROM " + Constants.TABLE_SENSOR_DEV;  
				   
		        SQLiteDatabase db = this.getWritableDatabase();  
		        Cursor cursor = db.rawQuery(selectQuery, null);  
		   
		        // looping through all rows and adding to list  
		        if (cursor.moveToFirst()) {  
		            do {  
		            	String sensor_id = cursor.getString(0);
		    		    String sensor_name = cursor.getString(1);
		    		    String sensor_mac = cursor.getString(2);
		    		    double sensor_curr_temp = cursor.getDouble(3);
		    		    double sensor_curr_hid = cursor.getDouble(4);
		    		    double sensor_curr_gas = cursor.getDouble(5);
		    		    String sensor_descr = cursor.getString(6);
		    		        
		    		    sensor.setDeviceID(sensor_id);
		    		    sensor.setDeviceName(sensor_name);
		    		    sensor.setDeviceMac_addr(sensor_mac);;
		    		    sensor.setCurrentTemperature(sensor_curr_temp);
		    		    sensor.setCurrentHudmity(sensor_curr_hid);
		    		    sensor.setCurrentGas(sensor_curr_gas);
		    		    sensor.setDescription(sensor_descr);
		                // Adding sensor to list  
		                 sensorList.add(sensor);  
		            } while (cursor.moveToNext());  
		        }  
		   
		        // return sensor list 
		        cursor.close();
		        db.close();
		        return sensorList;  
			}
		
		public ArrayList<Sensor> getAllSensors2()
		{
			
			Sensor sensor = new Sensor();
			ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
			String selectQuery = "SELECT  * FROM " + Constants.TABLE_SENSOR_DEV;  
			   
	        SQLiteDatabase db = this.getWritableDatabase();  
	        Cursor cursor = db.rawQuery(selectQuery, null);  
	   
	        // looping through all rows and adding to list  
	        if (cursor.moveToFirst()) {  
	            do {  
	            	
	    		    String sensor_name = cursor.getString(1);
	    		  
	    		    double sensor_curr_temp = cursor.getDouble(3);
	    		    double sensor_curr_hid = cursor.getDouble(4);
	    		    double sensor_curr_gas = cursor.getDouble(5);
	    		    String sensor_descr = cursor.getString(6);
	    		        
	    		    
	    		    sensor.setDeviceName(sensor_name);
	    		   
	    		    sensor.setCurrentTemperature(sensor_curr_temp);
	    		    sensor.setCurrentHudmity(sensor_curr_hid);
	    		    sensor.setCurrentGas(sensor_curr_gas);
	    		    sensor.setDescription(sensor_descr);
	                // Adding sensor to list  
	                 sensorList.add(sensor);  
	            } while (cursor.moveToNext());  
	        }  
	   
	        // return sensor list 
	        cursor.close();
	        db.close();
	        return sensorList;  
		}
		public int getSensorsCount() {  
		        String countQuery = "SELECT  * FROM " + Constants.TABLE_SENSOR_DEV;  
		        SQLiteDatabase db = this.getReadableDatabase();  
		        Cursor cursor = db.rawQuery(countQuery, null);
		        int count = cursor.getCount();
		        cursor.close();
		        db.close();
		        // return count
		        return count ;

		 }  
}
