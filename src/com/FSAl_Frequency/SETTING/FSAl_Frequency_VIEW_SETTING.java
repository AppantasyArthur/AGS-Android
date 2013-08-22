package com.FSAl_Frequency.SETTING;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;

public class FSAl_Frequency_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSAl_Frequency_VIEW_SETTING";
	private int device_size = 0;
	
	public FSAl_Frequency_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			switch(view.getId()){
			case R.id.pFSAl_Frequency_RLayout:
				pFSAl_Frequency_RLayout(view);
				break;
			case R.id.pFSAl_Frequency_RLayout_TITLE_RLayout:
				pFSAl_Frequency_RLayout_TITLE_RLayout(view);
				break;
			case R.id.pFSAl_Frequency_RLayout_BODY_RLayout:
				pFSAl_Frequency_RLayout_BODY_RLayout(view);
				break;
			}	
		}else{
			switch(view.getId()){
			case R.id.FSAl_Frequency_RLayout_TITLE_RLayout:
				PAD_FSAl_Frequency_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FSAl_Frequency_RLayout_BODY_RLayout:
				PAD_FSAl_Frequency_RLayout_BODY_RLayout(view);
				break;
			}	
		}
	}


//***************************PHONE*********************************
	private void pFSAl_Frequency_RLayout(View view) {
		new ThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSAl_Frequency_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(37, view);
		new ThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Back Button
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Back_Button).getLayoutParams().height = Tool.getWidth(26);
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Back_Button));
		new ThreadReadStateListInAssets(context, "phone/setting/done_f.png", "phone/setting/done_n.png", view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Back_Button), 4);
		//Title TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Title_TextView));
	}
	private void pFSAl_Frequency_RLayout_BODY_RLayout(View view) {	
		//Frequency_RLayout
		Tool.fitsViewWidth(297, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
		Tool.fitsViewHeight(350, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
		Tool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
		Tool.fitsViewTopMargin(10, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSAl_Frequency_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(55, view);	
		//Back Button
		Tool.fitsViewWidth(55, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Back_Button));
		Tool.fitsViewHeight(32, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Back_Button));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Back_Button));
		new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		Tool.fitsViewHeight(50, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Title_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSAl_Frequency_RLayout_BODY_RLayout(View view) {		
		//Frequency_RLayout
		Tool.fitsViewWidth(667, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
		Tool.fitsViewHeight(600, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
		Tool.fitsViewTopMargin(30, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
	}
//***************************PAD*********************************
}
