//package com.helperClass;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//
//
//
//
//
//import android.app.ProgressDialog;
//import android.os.AsyncTask;
//import android.util.Log;
//
//
//import android.widget.Toast;
//
//import com.activities.ScanDeviceActivity;
//import com.devices.BasedDevice;
//
//
//public class SendPairRequestHelper extends AsyncTask<BasedDevice,BasedDevice,BasedDevice> {
//	
//	BasedDevice dev = new BasedDevice();
//	HTTPHelper jsonParser = new HTTPHelper();
//	String status = "";
//	boolean islogginOK = false;
//	private ProgressDialog pDialog;
//	
//	private static final String LOGIN_URL = "";
//	private static final String TAG_SUCCESS = "status";
//	private static final String TAG_MESSAGE ="message";
//
//
//		boolean failure = false;
//		@Override
//		protected void onPreExecute()
//		{
//			super.onPreExecute();
//			pDialog = new ProgressDialog(ma);
//			pDialog.setMessage("Attemping login...");
//			pDialog.setIndeterminate(false);
//			pDialog.setCancelable(true);
//			pDialog.show();
//		}
//		
//		@Override
//		protected BasedDevice doInBackground(BasedDevice...args) {
//			
//			String deviceID = 	dev.getDeviceID().toString();
//			String deviceName = dev.getDeviceName().toString();
//			String deviceType = dev.getDeviceType().toString();
//			try
//			{
//				//Building Parameter
//				List<NameValuePair> params = new ArrayList<NameValuePair>();
//				params.add(new BasicNameValuePair("deviceid", deviceID));
//				params.add(new BasicNameValuePair("devicename", deviceName));
//				params.add(new BasicNameValuePair("devicetype", deviceType));
//				Log.d("request!","starting");
//				//getting product details by making HTTP request
//				JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL,"POST",params);
//				Log.d("Login attempt", json.toString());
//				status = json.getString(TAG_SUCCESS);
//				if (status.equals("success"))
//				{
//					islogginOK = true;
//					Log.d("Login Successful!", json.toString());
//
//
//				}else
//				{
//					Log.d("Login Failure!", json.getString(TAG_MESSAGE));
//
//
//				}
//			}catch(JSONException e)
//			{
//				e.printStackTrace();
//
//			}
//			return null;
//		}
//		
//		protected void onPostExecute(String file_url)
//		{
//			pDialog.dismiss();
//			if(islogginOK) {
//				//move to next screen
//				
//			} else {
//				Toast.makeText(null,"Pair failed, try again", Toast.LENGTH_LONG).show();
//			}
//			
//		}
//
//		
//	
//}
