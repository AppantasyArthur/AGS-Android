package com.FS.SETTING;

import android.content.Context;
import android.view.View;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.Tool;

public class FS_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FS_VIEW_SETTING";
	private int device_size = 0;
	
	public FS_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			
		}else{
			switch(view.getId()){
			case R.id.FS_RLayout:
				PAD_FS_RLayout(view);
				break;
			case R.id.FS_RLayout_TITLE_RLayout:
				PAD_FS_RLayout_TITLE_RLayout(view);
				break;
//			case R.id.FS_RLayout_TITLE2_RLayout:
//				PAD_FS_RLayout_TITLE2_RLayout(view);
//				break;
			case R.id.FS_RLayout_SPEAKER_EListView:
				PAD_FS_RLayout_SPEAKER_EListView(view);
				break;
			}	
		}
	}

//***************************PHONE*********************************
//***************************PHONE*********************************
//***************************PAD*********************************	
	private void PAD_FS_RLayout(View view){
		Tool.fitsViewWidth(284, view);		
	}
	private void PAD_FS_RLayout_TITLE_RLayout(View view) {		
		Tool.fitsViewHeight(56, view);
		new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/speaker_navigation bar.png", view, 3);
		//Speaker TextView
		Tool.fitsViewLeftMargin(30, view.findViewById(R.id.FS_RLayout_RLayout_Speaker_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.FS_RLayout_RLayout_Speaker_TextView));
	}
//	private void PAD_FS_RLayout_TITLE2_RLayout(View view) {		
//		Tool.fitsViewHeight(26, view);
//		//Speaker2 TextView
//		Tool.fitsViewLeftMargin(30, view.findViewById(R.id.FS_RLayout_RLayout_Speaker2_TextView));
//		Tool.fitsViewTextSize(8, view.findViewById(R.id.FS_RLayout_RLayout_Speaker2_TextView));
//	}
	private void PAD_FS_RLayout_SPEAKER_EListView(View view) {		
		Tool.fitsViewWidth(270, view);	
		FS_SPEAKER_ExpandableListAdapter EBaseAdapter = new FS_SPEAKER_ExpandableListAdapter(context,(FS_SPEAKER_ExpandableListView)view);
		((FS_SPEAKER_ExpandableListView)view).setAdapter(EBaseAdapter);
		((FS_SPEAKER_ExpandableListView)view).setItemsCanFocus(true);
		((FS_SPEAKER_ExpandableListView)view).setGroupIndicator(null);
		
	}
//***************************PAD*********************************
	
}
