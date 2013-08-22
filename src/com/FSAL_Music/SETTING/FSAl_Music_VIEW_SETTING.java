package com.FSAL_Music.SETTING;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;

public class FSAl_Music_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSAl_Frequency_VIEW_SETTING";
	private int device_size = 0;
	
	public FSAl_Music_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			switch(view.getId()){
			case R.id.pFSAl_Music_RLayout:
				pFSAl_Music_RLayout(view);
				break;
			case R.id.pFSAl_Music_RLayout_TITLE_RLayout:
				pFSAl_Music_RLayout_TITLE_RLayout(view);
				break;
			case R.id.pFSAl_Music_RLayout_BODY_RLayout:
				pFSAl_Music_RLayout_BODY_RLayout(view);
				break;
			}	
		}else{
			switch(view.getId()){
			case R.id.FSAl_Music_RLayout_TITLE_RLayout:
				PAD_FSAl_Music_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FSAl_Music_RLayout_BODY_RLayout:
				PAD_FSAl_Music_RLayout_BODY_RLayout(view);
				break;
			}	
		}
	}


//***************************PHONE*********************************
	private void pFSAl_Music_RLayout(View view) {
		new ThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSAl_Music_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(37, view);
		new ThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Back Button
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Back_Button).getLayoutParams().height = Tool.getWidth(26);
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Back_Button));
		new ThreadReadStateListInAssets(context, "phone/setting/done_f.png", "phone/setting/done_n.png", view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Back_Button), 4);
		//Title TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Title_TextView));
	}
	private void pFSAl_Music_RLayout_BODY_RLayout(View view) {	
		//MusicState_RLayout
		Tool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_MusicState_RLayout));
		Tool.fitsViewWidth(297, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_MusicState_RLayout));
		Tool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_MusicState_RLayout));
		//MusicState_TextView
		Tool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicState_TextView));
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicState_TextView));
		//MusicControl_RLayout
		Tool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_MusicControl_RLayout));
		Tool.fitsViewWidth(297, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_MusicControl_RLayout));
		Tool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_MusicControl_RLayout));
		//MusicBack IButton
		Tool.fitsViewWidth(51, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		Tool.fitsViewHeight(23, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		Tool.fitsViewLeftMargin(10, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		Tool.fitsViewTextSize(12, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		new ThreadReadBitMapInAssets(context, "phone/playlist/back.png", view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button), 3);
		//MusicTop IButton
		Tool.fitsViewWidth(51, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		Tool.fitsViewHeight(23, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		Tool.fitsViewLeftMargin(71, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		Tool.fitsViewTextSize(12, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		new ThreadReadBitMapInAssets(context, "phone/pop/save_close_botton.png", view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button), 3);
		//Music_RLayout
		Tool.fitsViewWidth(297, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Music_RLayout));
		Tool.fitsViewHeight(300, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Music_RLayout));
		Tool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Music_RLayout));
		//Music ListView
		FSAl_Music_ListView_BaseAdapter_Phone baseAdapter = new FSAl_Music_ListView_BaseAdapter_Phone(context);
		ListView MusicListView = (ListView)view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Music_ListView);
		MusicListView.setAdapter(baseAdapter);
		
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSAl_Music_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(55, view);	
		//Back Button
		Tool.fitsViewWidth(55, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Back_Button));
		Tool.fitsViewHeight(32, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Back_Button));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Back_Button));
		new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		Tool.fitsViewHeight(50, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Title_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSAl_Music_RLayout_BODY_RLayout(View view) {	
		//MusicState_RLayout
		Tool.fitsViewHeight(61, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_MusicState_RLayout));
		Tool.fitsViewWidth(667, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_MusicState_RLayout));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_MusicState_RLayout));
		//MusicState_TextView
		Tool.fitsViewLeftMargin(20, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicState_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicState_TextView));
		//MusicControl_RLayout
		Tool.fitsViewHeight(61, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_MusicControl_RLayout));
		Tool.fitsViewWidth(667, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_MusicControl_RLayout));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_MusicControl_RLayout));
		//MusicBack IButton
		Tool.fitsViewWidth(55, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		Tool.fitsViewHeight(32, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		Tool.fitsViewLeftMargin(20, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button), 3);
		//MusicTop IButton
		Tool.fitsViewWidth(55, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		Tool.fitsViewHeight(32, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		Tool.fitsViewLeftMargin(95, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		new ThreadReadBitMapInAssets(context, "phone/pop/save_close_botton.png", view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button), 3);
		//Music_RLayout
		Tool.fitsViewWidth(667, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Music_RLayout));
		Tool.fitsViewHeight(500, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Music_RLayout));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Music_RLayout));
		//Music ListView
		FSAl_Music_ListView_BaseAdapter_PAD baseAdapter = new FSAl_Music_ListView_BaseAdapter_PAD(context);
		ListView MusicListView = (ListView)view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Music_ListView);
		MusicListView.setAdapter(baseAdapter);
	}
//***************************PAD*********************************
}
