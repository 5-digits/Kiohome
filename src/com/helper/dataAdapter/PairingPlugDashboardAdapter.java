package com.helper.dataAdapter;

import java.util.ArrayList;






import com.demo.R;
import com.devices.SmartPlug;






import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

public class PairingPlugDashboardAdapter extends ArrayAdapter<SmartPlug>{
	Activity context = null;
	ArrayList<SmartPlug>plugList= new ArrayList<SmartPlug>();
	
	//------------- interface area----------------
	customButtonListener customListener;
	public interface customButtonListener {  
        public void onButtonClickListner(int position, SmartPlug dev);  
    }
	public void setCustomButtonListner(customButtonListener listener) {  
        this.customListener = listener;  
    }
	//------------- interface area----------------
	//------------- interface area----------------
	customSwitchListener customSWListener;
	public interface customSwitchListener {  
        public void onSWClickListner(int position, SmartPlug dev);  
    }
	public void setCustomSwitchListener(customSwitchListener listener) {  
        this.customSWListener = listener;  
    }
	//------------- interface area----------------

	public PairingPlugDashboardAdapter(Context context, int resource, Activity context2, ArrayList<SmartPlug> plugList) {
		super(context, resource);
		context = context2;
		this.plugList = plugList;
	}
	public PairingPlugDashboardAdapter(Activity context, ArrayList<SmartPlug> plugList) 
	{
		super(context, R.layout.custom_plug_homedashboard_display, plugList); 
		this.context=context;
		this.plugList=plugList;
	}

	public View getView(final int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);  
            convertView = inflater.inflate(R.layout.custom_plug_homedashboard_display, null);  
            viewHolder = new ViewHolder();			

            viewHolder.txtPlugName = (TextView) convertView.findViewById(R.id.txtPlugName);
            viewHolder.txtPlugDescription = (TextView) convertView.findViewById(R.id.txtPlugDescription);
            viewHolder.swPlugData = (Switch) convertView.findViewById(R.id.swPlugStatus);
            viewHolder.btnUnPair = (Button) convertView.findViewById(R.id.btnUnPair);
            convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// do data
		if(plugList.size() > 0) {
			final SmartPlug plug = plugList.get(position);
			if(plug != null) {

				viewHolder.txtPlugName.setText(plug.getDeviceName());
				viewHolder.txtPlugDescription.setText(plug.getDescription());
				viewHolder.swPlugData.setChecked(plug.getCurrentStatus());
				viewHolder.swPlugData.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(customSWListener!= null) {
							if(isChecked) {
								plug.setStatus(false);
								customSWListener.onSWClickListner(position, plug);
								
							} else {
								plug.setStatus(true);
								customSWListener.onSWClickListner(position, plug);
							}
						}
						
					}
				});
				
				viewHolder.btnUnPair.setOnClickListener(new OnClickListener() {
					
					@Override 
					public void onClick(View v) {
						if (customListener != null) {  
						customListener.onButtonClickListner(position,plug);  
						}  
					
					}
				});
			}
		}
		// xong do data
		return convertView;
	}

    public class ViewHolder {  

        TextView txtPlugName;
        TextView txtPlugDescription;   
        Switch	swPlugData;
        Button btnUnPair;  
    }  


}
