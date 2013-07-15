package com.FSA.SETTING;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;

public class FSA_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSM_VIEW_SETTING";
	private int device_size = 0;
	
	public FSA_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			switch(view.getId()){
			case R.id.pFSA_RLayout:
				pFSA_RLayout(view);
				break;
			case R.id.pFSA_RLayout_TITLE_RLayout:
				pFSA_RLayout_TITLE_RLayout(view);
				break;
			case R.id.pFSA_RLayout_BODY_RLayout:
				pFSA_RLayout_BODY_RLayout(view);
				break;
			}	
		}else{
			switch(view.getId()){
			case R.id.FSA_RLayout_TITLE_RLayout:
				PAD_FSA_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FSA_RLayout_BODY_RLayout:
				PAD_FSA_RLayout_BODY_RLayout(view);
				break;
			}	
		}
	}


//***************************PHONE*********************************
	private void pFSA_RLayout(View view) {
		new ThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSA_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(37, view);
		new ThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Done Button
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFSA_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSA_RLayout_RLayout_Back_Button).getLayoutParams().height = Tool.getWidth(26);
		Tool.fitsViewRightMargin(7, view.findViewById(R.id.pFSA_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFSA_RLayout_RLayout_Back_Button));
		new ThreadReadStateListInAssets(context, "phone/setting/done_f.png", "phone/setting/done_n.png", view.findViewById(R.id.pFSA_RLayout_RLayout_Back_Button), 4);
		//Title TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFSA_RLayout_RLayout_Title_TextView));
	}
	private void pFSA_RLayout_BODY_RLayout(View view) {
		//About_RLayout
		Tool.fitsViewHeight(30, view.findViewById(R.id.pFSA_RLayout_RLayout_About_RLayout));
		Tool.fitsViewWidth(297, view.findViewById(R.id.pFSA_RLayout_RLayout_About_RLayout));
		Tool.fitsViewTopMargin(74, view.findViewById(R.id.pFSA_RLayout_RLayout_About_RLayout));
		Tool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSA_RLayout_RLayout_About_RLayout));
		new ThreadReadBitMapInAssets(context, "phone/setting/setting_firmware_about_bar.PNG", view.findViewById(R.id.pFSA_RLayout_RLayout_About_RLayout), 3);
		//LAbout TextView		
		Tool.fitsViewWidth(140, view.findViewById(R.id.pFSA_RLayout_RLayout_RLayout_LAbout_TextView));
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSA_RLayout_RLayout_RLayout_LAbout_TextView));
		Tool.fitsViewLeftMargin(9, view.findViewById(R.id.pFSA_RLayout_RLayout_RLayout_LAbout_TextView));
		//RAbout TextView		
		Tool.fitsViewWidth(140, view.findViewById(R.id.pFSA_RLayout_RLayout_RLayout_RAbout_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFSA_RLayout_RLayout_RLayout_RAbout_TextView));
		Tool.fitsViewRightMargin(9, view.findViewById(R.id.pFSA_RLayout_RLayout_RLayout_RAbout_TextView));
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSA_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(50, view);
		
		//Title TextView
		Tool.fitsViewHeight(33, view.findViewById(R.id.FSA_RLayout_RLayout_Title_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSA_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSA_RLayout_BODY_RLayout(View view) {
		//About_RLayout
		Tool.fitsViewHeight(62, view.findViewById(R.id.FSA_RLayout_RLayout_About_RLayout));
		Tool.fitsViewWidth(665, view.findViewById(R.id.FSA_RLayout_RLayout_About_RLayout));
		Tool.fitsViewTopMargin(37, view.findViewById(R.id.FSA_RLayout_RLayout_About_RLayout));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSA_RLayout_RLayout_About_RLayout));
		//LAbout TextView		
		Tool.fitsViewWidth(400, view.findViewById(R.id.FSA_RLayout_RLayout_RLayout_LAbout_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.FSA_RLayout_RLayout_RLayout_LAbout_TextView));
		Tool.fitsViewLeftMargin(10, view.findViewById(R.id.FSA_RLayout_RLayout_RLayout_LAbout_TextView));
		//RAbout TextView		
		Tool.fitsViewWidth(400, view.findViewById(R.id.FSA_RLayout_RLayout_RLayout_RAbout_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSA_RLayout_RLayout_RLayout_RAbout_TextView));
		Tool.fitsViewRightMargin(10, view.findViewById(R.id.FSA_RLayout_RLayout_RLayout_RAbout_TextView));
	}
//***************************PAD*********************************
}
