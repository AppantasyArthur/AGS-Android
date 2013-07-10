package com.FI.SETTING;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;

public class FI_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FI_VIEW_LISTNER";
	private int device_size = 0;
	
	public FI_VIEW_LISTNER(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void SET_QUEUE_ListView_Listner(FI_ListView QUEUE_ListView){
		if(device_size==6){
			//***************************PHONE*********************************
			QUEUE_ListView.setOnItemLongClickListener(new OnItemLongClickListener(){
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
					if(!((FI_Queqe_ListView_BaseAdapter_Phone)arg0.getAdapter()).GET_Edite()){
						((FI_Queqe_ListView_BaseAdapter_Phone)arg0.getAdapter()).SET_Edite(true);
					}
					return false;
				}			
			});
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			QUEUE_ListView.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
								
				}				
			});
			QUEUE_ListView.setOnItemLongClickListener(new OnItemLongClickListener(){
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
					if(!((FI_Queqe_ListView_BaseAdapter_PAD)arg0.getAdapter()).GET_Edite()){
						((FI_Queqe_ListView_BaseAdapter_PAD)arg0.getAdapter()).SET_Edite(true);
						((FragmentActivity_Main)context).ShowDoneButton();
					}
					return false;
				}			
			});
			
			//***************************PAD*********************************
		}
	}
}
