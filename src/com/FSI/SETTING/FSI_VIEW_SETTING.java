package com.FSI.SETTING;

import android.R.raw;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
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
			switch(view.getId()){
			case R.id.pFSI_RLayout:
				pFSI_RLayout(view);
				break;
			case R.id.pFSI_RLayout_TITLE_RLayout:
				pFSI_RLayout_TITLE_RLayout(view);
				break;
			case R.id.pFSI_RLayout_BODY_RLayout:
				pFSI_RLayout_BODY_RLayout(view);
				break;
			}	
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
	private void pFSI_RLayout(View view) {
		new ThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSI_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(37, view);
		new ThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Done Button
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFSI_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSI_RLayout_RLayout_Back_Button).getLayoutParams().height = Tool.getWidth(26);
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSI_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFSI_RLayout_RLayout_Back_Button));
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/back.png", view.findViewById(R.id.pFSI_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFSI_RLayout_RLayout_Title_TextView));
	}
	private void pFSI_RLayout_BODY_RLayout(View view) {
		//IdSpeaker_RLayout
		Tool.fitsViewWidth(294, view.findViewById(R.id.pFSI_RLayout_RLayout_IdSpeaker_RLayout));
		Tool.fitsViewTopMargin(62, view.findViewById(R.id.pFSI_RLayout_RLayout_IdSpeaker_RLayout));
		Tool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSI_RLayout_RLayout_IdSpeaker_RLayout));
		
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSA_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(55, view);
		//Back Button
		Tool.fitsViewWidth(55, view.findViewById(R.id.FSI_RLayout_RLayout_Back_Button));
		Tool.fitsViewHeight(32, view.findViewById(R.id.FSI_RLayout_RLayout_Back_Button));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSI_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FSI_RLayout_RLayout_Back_Button));
		new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSI_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		Tool.fitsViewHeight(50, view.findViewById(R.id.FSI_RLayout_RLayout_Title_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSI_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSA_RLayout_BODY_RLayout(View view) {
		
		//IdSpeaker_RLayout
		Tool.fitsViewWidth(666, view.findViewById(R.id.FSI_RLayout_RLayout_IdSpeaker_RLayout));
		Tool.fitsViewHeight(550, view.findViewById(R.id.FSI_RLayout_RLayout_IdSpeaker_RLayout));
		Tool.fitsViewTopMargin(62, view.findViewById(R.id.FSI_RLayout_RLayout_IdSpeaker_RLayout));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSI_RLayout_RLayout_IdSpeaker_RLayout));
	}
//***************************PAD*********************************
}
