package com.FI.SETTING;

import android.content.Context;
import android.view.View;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.Tool;

public class FI_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FI_VIEW_SETTING";
	private int device_size = 0;
	
	public FI_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			
		}else{
			switch(view.getId()){
			case R.id.FI_RLayout_UP_RLayout:
				PAD_FI_RLayout_UP_RLayout(view);
				break;
			case R.id.FI_RLayout_DOWN_RLayout:
				PAD_FI_RLayout_DOWN_RLayout(view);
				break;
			}	
		}
	}
//***************************PHONE*********************************
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FI_RLayout_UP_RLayout(View view){
		Tool.fitsViewHeight(342, view);
		//DTITLE RLayout
		Tool.fitsViewHeight(38, view.findViewById(R.id.FI_RLayout_RLayout_UTITLE_RLayout));		
		//Playing TextView
		Tool.fitsViewLeftMargin(20, view.findViewById(R.id.FI_RLayout_RLayout_RLayout_PLAYING_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FI_RLayout_RLayout_RLayout_PLAYING_TextView));
		//Point LLayout
		Tool.fitsViewHeight(10, view.findViewById(R.id.FI_RLayout_RLayout_Point_LLayout));
		Tool.fitsViewBottomMargin(20, view.findViewById(R.id.FI_RLayout_RLayout_Point_LLayout));
	}
	private void PAD_FI_RLayout_DOWN_RLayout(View view){
		//DTITLE RLayout
		Tool.fitsViewHeight(30, view.findViewById(R.id.FI_RLayout_RLayout_DTITLE_RLayout));
		//QUEUE TextView
		Tool.fitsViewLeftMargin(30, view.findViewById(R.id.FI_RLayout_RLayout_RLayout_QUEUE_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FI_RLayout_RLayout_RLayout_QUEUE_TextView));
		//INFOR ListView
		FI_ListView fi_ListView = (FI_ListView)view.findViewById(R.id.FI_RLayout_RLayout_QUEUE_ListView);
		fi_ListView.setAdapter(new FI_Queue_ListView_BaseAdapter(context));
	}
//***************************PAD*********************************
	
}
