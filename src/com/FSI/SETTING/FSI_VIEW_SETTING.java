package com.FSI.SETTING;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;

public class FSI_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSI_VIEW_SETTING";
	private int device_size = 0;
	
	public FSI_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			
		}else{
			switch(view.getId()){
			case R.id.FSI_RLayout_TITLE_RLayout:
				PAD_FSA_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FSI_RLayout_BODY_RLayout:
				PAD_FSA_RLayout_BODY_RLayout(view);
				break;
			}	
		}
	}

//***************************PHONE*********************************
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSA_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(50, view);
		
		//Title TextView
		Tool.fitsViewHeight(33, view.findViewById(R.id.FSI_RLayout_RLayout_Title_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSI_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSA_RLayout_BODY_RLayout(View view) {
		Tool.fitsViewWidth(662, view.findViewById(R.id.FSI_RLayout_RLayout_IdSpeaker_ListView));
		Tool.fitsViewTopMargin(62, view.findViewById(R.id.FSI_RLayout_RLayout_IdSpeaker_ListView));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSI_RLayout_RLayout_IdSpeaker_ListView));
		FSI_IdSpeaker_ListView_BaseAdapter baseAdapter = new FSI_IdSpeaker_ListView_BaseAdapter(context);
		((ListView)view.findViewById(R.id.FSI_RLayout_RLayout_IdSpeaker_ListView)).setAdapter(baseAdapter);
	}
//***************************PAD*********************************
}
