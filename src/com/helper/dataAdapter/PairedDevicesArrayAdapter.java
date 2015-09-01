package com.helper.dataAdapter;

import java.util.ArrayList;

import com.demo.R;
import com.devices.BasedDevice;
import com.devices.PairedDevice;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PairedDevicesArrayAdapter extends ArrayAdapter<PairedDevice> {

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
		ArrayList<PairedDevice>deviceList= new ArrayList<PairedDevice>();
		int layoutId;
		
		public PairedDevicesArrayAdapter(Activity context, int layoutId, ArrayList<PairedDevice> arr) 
		{
			super(context, layoutId,arr);
			this.context=context;
			this.layoutId=layoutId;
			this.deviceList=arr;
		}
		
		public PairedDevicesArrayAdapter(Activity context, ArrayList<PairedDevice> arr) 
		{
			super(context, R.layout.custom_listview_paired_devices, arr); 
			this.context=context;
			this.deviceList=arr;
		}
		
		public View getView(final int position, View convertView, ViewGroup parent)
		{
			ViewHolder viewHolder;
			if (convertView == null) {
				LayoutInflater inflater = LayoutInflater.from(context);  
	            convertView = inflater.inflate(R.layout.custom_listview_paired_devices, null);  
	            viewHolder = new ViewHolder();			
	            viewHolder.txtPairedD_ID = (TextView) convertView.findViewById(R.id.txtPairedD_ID);
	            viewHolder.txtPairedD_Name = (TextView) convertView.findViewById(R.id.txtPairedD_Name);
	            viewHolder.txtPairedD_Type = (TextView) convertView.findViewById(R.id.txtPairedD_Type);
	            viewHolder.txtPairedD_Status = (TextView) convertView.findViewById(R.id.txtPairedD_Status);
	            viewHolder.txtPairedD_Desc = (TextView) convertView.findViewById(R.id.txtPairedD_Desc);
	            viewHolder.imgD_Type = (ImageView) convertView.findViewById(R.id.imgD_Type);
	            viewHolder.btnUnPair = (Button) convertView.findViewById(R.id.btnUnPair);
	            convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			// do data
			if(deviceList.size() > 0) {
				final BasedDevice device = deviceList.get(position);
				if(device != null) {
					viewHolder.txtPairedD_ID.setText(device.getDeviceID());
					viewHolder.txtPairedD_Name.setText(device.getDeviceName());
					viewHolder.txtPairedD_Type.setText(device.getDeviceType());
					if(viewHolder.txtPairedD_Type.getText().equals("Plug"))
						viewHolder.imgD_Type.setImageResource(R.drawable.troll);
					viewHolder.txtPairedD_Status.setText(device.getDeviceType());
					viewHolder.txtPairedD_Desc.setText(device.getDeviceType());
					viewHolder.btnUnPair.setOnClickListener(new OnClickListener() {
						
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
	        TextView txtPairedD_ID;  
	        TextView txtPairedD_Name;
	        TextView txtPairedD_Type;   
	        TextView txtPairedD_Status;
	        TextView txtPairedD_Desc;
	        ImageView imgD_Type;
	        Button btnUnPair;  
	    }  
		
}
