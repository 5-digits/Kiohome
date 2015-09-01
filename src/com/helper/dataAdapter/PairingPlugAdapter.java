package com.helper.dataAdapter;

import java.util.ArrayList;
import java.util.Collections;
import com.csform.android.uiapptemplate.R;
import com.devices.SmartPlug;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.undo.UndoAdapter;
import com.nhaarman.listviewanimations.util.Swappable;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PairingPlugAdapter extends ArrayAdapter<SmartPlug> implements Swappable, UndoAdapter, OnDismissCallback{
	Activity context = null;
	ArrayList<SmartPlug>plugList= new ArrayList<SmartPlug>();
	private Context mContext;
	private LayoutInflater mInflater;
	
	
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

	
	
	public PairingPlugAdapter(Context context, int resource, Activity context2, ArrayList<SmartPlug> plugList) {
		super(context, resource);
		context = context2;
		this.plugList = plugList;
	}
	public PairingPlugAdapter(Context context, ArrayList<SmartPlug> plugList) 
	{
		super(context, R.layout.list_item_default, plugList); 
		mContext=context;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.plugList=plugList;
	}

	
	
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		final ViewHolder viewHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item_default, parent, false);
            viewHolder = new ViewHolder();			
            viewHolder.plugID = (TextView) convertView.findViewById(R.id.plugID);
            viewHolder.plugName = (TextView) convertView.findViewById(R.id.plugName);
            viewHolder.plugDecsr = (TextView) convertView.findViewById(R.id.plugDescription);
            viewHolder.icon = (TextView) convertView.findViewById(R.id.icon);
            convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// do data
		if(plugList.size() > 0) {
			final SmartPlug plug = plugList.get(position);
			if(plug != null) {
				viewHolder.plugID.setText(plug.getDeviceID());
				viewHolder.plugName.setText(plug.getDeviceName());
				viewHolder.plugDecsr.setText(plug.getDescription());
				viewHolder.icon.setText(R.string.fontello_heart_empty);
				viewHolder.icon.setOnClickListener(new OnClickListener() {
					
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

    public static class ViewHolder {  
		public /*Roboto*/TextView plugID;
		public /*Roboto*/TextView plugName;
		public /*Roboto*/TextView plugDecsr;
		public /*Roboto*/TextView plugStatus;
		public /*Fontello*/TextView icon;
    }
    
    
	@Override
	public void onDismiss(@NonNull final ViewGroup listView,
			@NonNull final int[] reverseSortedPositions) {
		for (int position : reverseSortedPositions) {
			remove(position);
		}
	}
	public void remove(int position) {
		plugList.remove(position);
	}
	
	@Override
	@NonNull
	public View getUndoClickView(@NonNull View view) {
		return view.findViewById(R.id.undo_button);
	}
	
	@Override
	@NonNull
	public View getUndoView(final int position, final View convertView,
			@NonNull final ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = LayoutInflater.from(mContext).inflate(R.layout.list_item_undo_view,
					parent, false);
		}
		return view;
	}
	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public int getCount() {
		return plugList.size();
	}

	@Override
	public SmartPlug getItem(int position) {
		return plugList.get(position);
	}
	
	@Override
	public void swapItems(int positionOne, int positionTwo) {
		Collections.swap(plugList, positionOne, positionTwo);
	}


}
