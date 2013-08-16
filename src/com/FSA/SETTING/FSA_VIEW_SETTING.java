package com.FSA.SETTING;

import android.content.Context;
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
		//Back Button
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFSA_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSA_RLayout_RLayout_Back_Button).getLayoutParams().height = Tool.getWidth(26);
		Tool.fitsViewRightMargin(7, view.findViewById(R.id.pFSA_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFSA_RLayout_RLayout_Back_Button));
		new ThreadReadStateListInAssets(context, "phone/setting/done_f.png", "phone/setting/done_n.png", view.findViewById(R.id.pFSA_RLayout_RLayout_Back_Button), 4);
		//Title TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFSA_RLayout_RLayout_Title_TextView));
	}
	private void pFSA_RLayout_BODY_RLayout(View view) {
		//Name TextView
		Tool.fitsViewTopMargin(20, view.findViewById(R.id.pFSA_RLayout_RLayout_Name_TextView));
		Tool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSA_RLayout_RLayout_Name_TextView));
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFSA_RLayout_RLayout_Name_TextView));
		//About_ListView
		Tool.fitsViewWidth(297, view.findViewById(R.id.pFSA_RLayout_RLayout_About_ListView));
		Tool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSA_RLayout_RLayout_About_ListView));
		Tool.fitsViewTopMargin(10, view.findViewById(R.id.pFSA_RLayout_RLayout_About_ListView));
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSA_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(55, view);	
		//Back Button
		Tool.fitsViewWidth(55, view.findViewById(R.id.FSA_RLayout_RLayout_Back_Button));
		Tool.fitsViewHeight(32, view.findViewById(R.id.FSA_RLayout_RLayout_Back_Button));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSA_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FSA_RLayout_RLayout_Back_Button));
		new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSA_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		Tool.fitsViewHeight(50, view.findViewById(R.id.FSA_RLayout_RLayout_Title_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSA_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSA_RLayout_BODY_RLayout(View view) {
		//Name TextView
		Tool.fitsViewTopMargin(37, view.findViewById(R.id.FSA_RLayout_RLayout_Name_TextView));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSA_RLayout_RLayout_Name_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.FSA_RLayout_RLayout_Name_TextView));
		//About_ListView
		Tool.fitsViewWidth(667, view.findViewById(R.id.FSA_RLayout_RLayout_About_ListView));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSA_RLayout_RLayout_About_ListView));
		Tool.fitsViewTopMargin(10, view.findViewById(R.id.FSA_RLayout_RLayout_About_ListView));
	}
//***************************PAD*********************************
}
