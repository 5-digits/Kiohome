package com.helper.dataAdapter;

import java.util.ArrayList;

import com.demo.R;
import com.devices.Sensor;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class PairingSensorAdapter extends ArrayAdapter<Sensor> {

	Activity context = null;
	ArrayList<Sensor>sensorList= new ArrayList<Sensor>();
	
	//------------- interface area----------------
	customButtonListener customListener;
	public interface customButtonListener {  
        public void onButtonClickListner(int position, Sensor dev);  
    }
	public void setCustomButtonListner(customButtonListener listener) {  
        this.customListener = listener;  
    }
	//------------- interface area----------------

	public PairingSensorAdapter(Context context, int resource, Activity context2, ArrayList<Sensor> sensorList) {
		super(context, resource);
		context = context2;
		this.sensorList = sensorList;
	}
	
	public PairingSensorAdapter(Activity context, ArrayList<Sensor> plugList) 
	{
		super(context, R.layout.custom_sensor_display, plugList); 
		this.context=context;
		this.sensorList=plugList;
	}

	public View getView(final int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);  
            convertView = inflater.inflate(R.layout.custom_sensor_display, null);  
            viewHolder = new ViewHolder();			
            viewHolder.txtSensorID = (TextView) convertView.findViewById(R.id.txtSensorID);
            viewHolder.txtSensorName = (TextView) convertView.findViewById(R.id.txtSensorName);
            viewHolder.txtSensorDescription = (TextView) convertView.findViewById(R.id.txtSensorDescription);
            viewHolder.txtSensorTemperature = (TextView) convertView.findViewById(R.id.txtTemperature);
            viewHolder.txtSensorHudmity = (TextView) convertView.findViewById(R.id.txtHudmity);
//            viewHolder.txtSensorGas = (TextView) convertView.findViewById(R.id.txtGas);
            viewHolder.btnUnPair = (Button) convertView.findViewById(R.id.btnUnPair);
            convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// do data
		if(sensorList.size() > 0) {
			final Sensor sensor = sensorList.get(position);
			if(sensor != null) {
				viewHolder.txtSensorID.setText(sensor.getDeviceID());
				viewHolder.txtSensorName.setText(sensor.getDeviceName());
				viewHolder.txtSensorDescription.setText(sensor.getDescription());
				viewHolder.txtSensorTemperature.setText(sensor.getCurrentTemperature()+"");
				viewHolder.txtSensorTemperature.setText(sensor.getCurrentHudmity()+"");
//				viewHolder.txtSensorGas.setText(sensor.getCurrentGas()+"");
				
				viewHolder.btnUnPair.setOnClickListener(new OnClickListener() {
					
					@Override 
					public void onClick(View v) {
						if (customListener != null) {  
						customListener.onButtonClickListner(position,sensor);  
						}  
					
					}
				});
			}
		}
		// xong do data
		return convertView;
	}

    public class ViewHolder {  
        TextView txtSensorID;  
        TextView txtSensorName;
        TextView txtSensorDescription;   
        TextView txtSensorTemperature;
        TextView txtSensorHudmity;
//        TextView txtSensorGas;
        Button btnUnPair;  
    }  

}
