package com.helperClass;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Utilities {
	public static ProgressDialog createAlertDialog(Context ct,String msg, boolean isCancelable ){
		ProgressDialog pDialog = new ProgressDialog(ct);
		pDialog.setMessage(msg);
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(isCancelable);
		return pDialog;
	}
	public static void displayCancelableDialog(Context ct,String msg ){
		ProgressDialog pDialog = new ProgressDialog(ct);
		pDialog.setMessage(msg);
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
	}
	public static void displayUnCancelableDialog(Context ct,String msg ){
		ProgressDialog pDialog = new ProgressDialog(ct);
		pDialog.setMessage(msg);
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.show();
	}
	public static void displayToastLong(Context ctx, String message) {
		Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
	}
	
	public static void displayToastShort(Context ctx, String message) {
		Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
	}
	public static float parseStringToFloat(String str) {
		float fvalue = 0;
		try {
			fvalue = Float.parseFloat(str);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return fvalue;
	}
	public static boolean parseStringToBool(String str) {
		boolean bvalue = false;
		try {
			bvalue = Boolean.parseBoolean(str);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return bvalue;
	}
	public static JSONObject createPairedJSON() {
		  JSONObject object = new JSONObject();
		  try {
		    object.put(Constants.TAG_DEV_ID, "Plug 1");
		    object.put(Constants.TAG_DEV_NAME, "Plug number 1");
		    object.put(Constants.TAG_DEV_TYPE, "PLUG");
		    object.put(Constants.TAG_DEV_MAC_ADDR, "00:AB:09:13");
		    object.put(Constants.TAG_DEV_DATA, "true");
		    object.put(Constants.TAG_DEV_DESCRIPTION, "My smart plug is ON");
		  } catch (JSONException e) {
		    e.printStackTrace();
		  }
		  return object;
		}
	
	 public static void setDynamicHeight(ListView mListView) {
         ListAdapter mListAdapter = mListView.getAdapter();
         if (mListAdapter == null) {
             // when adapter is null
             return;
         }
         int height = 0;
         int desiredWidth = MeasureSpec.makeMeasureSpec(mListView.getWidth(), MeasureSpec.UNSPECIFIED);
         for (int i = 0; i < mListAdapter.getCount(); i++) {
             View listItem = mListAdapter.getView(i, null, mListView);
             listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
             height += listItem.getMeasuredHeight();
         }
         ViewGroup.LayoutParams params = mListView.getLayoutParams();
         params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
         mListView.setLayoutParams(params);
         mListView.requestLayout();
     }
	

}
