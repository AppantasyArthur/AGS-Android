package com.FM.SETTING;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.Tool;

public class FM_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FM_VIEW_SETTING";
	private int device_size = 0;
	
	public FM_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			
		}else{
			switch(view.getId()){
			case R.id.FM_RLayout_TITLE_RLayout:
				PAD_FM_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FM_RLayout_TITLE2_RLayout:
				PAD_FM_RLayout_TITLE2_RLayout(view);
				break;
			case R.id.FM_RLayout_TITLE3_RLayout:
				PAD_FM_RLayout_TITLE3_RLayout(view);
				break;
			case R.id.FM_RLayout_Music_ListView:
				PAD_FM_RLayout_Music_ListView(view);
				break;
			}	
		}
	}

//***************************PHONE*********************************
//***************************PHONE*********************************
//***************************PAD*********************************	
	private void PAD_FM_RLayout_TITLE_RLayout(View view) {		
		Tool.fitsViewHeight(44, view);
		//Music TextView
		Tool.fitsViewWidth(58, view.findViewById(R.id.FM_RLayout_RLayout_Music_TextView));
		Tool.fitsViewLeftMargin(30, view.findViewById(R.id.FM_RLayout_RLayout_Music_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.FM_RLayout_RLayout_Music_TextView));
		//SearchMusic LLayout		
		Tool.fitsViewWidth(162, view.findViewById(R.id.FM_RLayout_RLayout_SearchMusic_RLayout));
		Tool.fitsViewHeight(29, view.findViewById(R.id.FM_RLayout_RLayout_SearchMusic_RLayout));
		Tool.fitsViewRightMargin(10, view.findViewById(R.id.FM_RLayout_RLayout_SearchMusic_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_search_box.png", view.findViewById(R.id.FM_RLayout_RLayout_SearchMusic_RLayout), 3);
		//SearchMusic IButton
		view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_IButton).getLayoutParams().width = Tool.getHeight(18);
		Tool.fitsViewHeight(17, view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_IButton));	
		Tool.fitsViewLeftMargin(5, view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_IButton));	
		new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_search.png", view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_IButton), 2);
		//SearchMusic EText
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_EText));
		Tool.fitsViewLeftMargin(5, view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_EText));			
	}
	private void PAD_FM_RLayout_TITLE2_RLayout(View view) {		
		Tool.fitsViewHeight(44, view);
		//Music2 TextView		
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FM_RLayout_RLayout_Music2_TextView));
		//MusicBack IButton
		Tool.fitsViewWidth(55, view.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button));
		Tool.fitsViewHeight(32, view.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button));
		Tool.fitsViewLeftMargin(30, view.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button));
		new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button), 3);
	}
	private void PAD_FM_RLayout_TITLE3_RLayout(View view) {		
//		Tool.fitsViewHeight(44, view);
	}
	private void PAD_FM_RLayout_Music_ListView(View view) {
		//Music ListView
		ListView listView = (ListView)view;
		FM_Music_ListView_BaseAdapter baseAdapter = new FM_Music_ListView_BaseAdapter(context);
		listView.setAdapter(baseAdapter);
	}
//***************************PAD*********************************
	
}
