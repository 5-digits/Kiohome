package com.helper.dataAdapter;

import java.util.ArrayList;
import com.demo.R;
import com.devices.BasedDevice;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.TextView;


public class DevicesArrayAdapter extends ArrayAdapter<BasedDevice>
{
	//------------- interface area----------------
	customButtonListener customListener;
	public interface customButtonListener {  
        public void onButtonClickListner(int position, BasedDevice dev);  
    }
	public void setCustomButtonListner(customButtonListener listener) {  
        this.customListener = listener;  
    }
	//------------- interface area----------------
	
	Activity context = null;
	ArrayList<BasedDevice>deviceList= new ArrayList<BasedDevice>();
	int layoutId;
	
	public DevicesArrayAdapter(Activity context, int layoutId, ArrayList<BasedDevice> arr) 
	{
		super(context, layoutId,arr);
		this.context=context;
		this.layoutId=layoutId;
		this.deviceList=arr;
	}
	public DevicesArrayAdapter(Activity context, ArrayList<BasedDevice> arr) 
	{
		super(context, R.layout.custom_listview, arr); 
		this.context=context;
		this.deviceList=arr;
	}
	
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);  
            convertView = inflater.inflate(R.layout.custom_listview, null);  
            viewHolder = new ViewHolder();			
            viewHolder.txtDeviceID = (TextView) convertView.findViewById(R.id.txtdID);
            viewHolder.txtDeviceName = (TextView) convertView.findViewById(R.id.txtdName);
            viewHolder.txtDeviceType = (TextView) convertView.findViewById(R.id.txtdType);
            viewHolder.btnPair = (Button) convertView.findViewById(R.id.btnPair);
            convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// do data
		if(deviceList.size() > 0) {
			final BasedDevice device = deviceList.get(position);
			if(device != null) {
				viewHolder.txtDeviceID.setText(device.getDeviceID());
				viewHolder.txtDeviceName.setText(device.getDeviceName());
				viewHolder.txtDeviceType.setText(device.getDeviceType());
				
				viewHolder.btnPair.setOnClickListener(new OnClickListener() {
					
					@Override 
					public void onClick(View v) {
						if (customListener != null) {  
						customListener.onButtonClickListner(position,device);  
						}  
					
					}
				});
			}
		}
		// xong do data
		return convertView;
	}

    public class ViewHolder {  
        TextView txtDeviceID;  
        TextView txtDeviceName;
        TextView txtDeviceType;   
        Button btnPair;  
    }  
	
	
}
