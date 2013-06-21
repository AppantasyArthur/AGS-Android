package com.FM.SETTING;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class FM_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FM_VIEW_SETTING";
	private int device_size = 0;
	public FM_VIEW_LISTNER(Context context, int device_size) {
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	
	public void SET_Music_ListView_Listner(FM_ListView Music_ListView){
		if(device_size==6){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************	
			Music_ListView.setOnItemClickListener(new OnItemClickListener(){
				private FM_PopupWindow fm_PopupWindow = new FM_PopupWindow(context);
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {					
					if(arg2>5){
						View rootView = arg0.getRootView();
						fm_PopupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0 );	
					}else{
						((FM_Music_ListView_BaseAdapter)arg0.getAdapter()).setshowNext(true);
					}
								
				}
			});
			Music_ListView.setOnItemLongClickListener(new OnItemLongClickListener(){
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
					((FM_ListView)arg0).setItemLongClickState(true);
					return true;
				}
			});
			
			//***************************PAD*********************************	
		}		
	}
	public void SET_SearchMusic_RLayout_Listner(final RelativeLayout SearchMusic_RLayout,final RelativeLayout TITLE2_RLayout,final RelativeLayout TITLE3_RLayout){
		if(device_size==6){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			EditText SearchMusic_EText = (EditText)SearchMusic_RLayout.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_EText);
			SearchMusic_EText.setOnFocusChangeListener(new OnFocusChangeListener(){
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if(hasFocus){
						TITLE2_RLayout.setVisibility(View.INVISIBLE);
						TITLE3_RLayout.setVisibility(View.VISIBLE);
					}else{
						TITLE2_RLayout.setVisibility(View.VISIBLE);
						TITLE3_RLayout.setVisibility(View.GONE);
						InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
					}
				}				
			});
			//***************************PAD*********************************	
		}		
	}

	public void SET_MusicBack_Button_Listner(Button MusicBack,final FM_ListView Music_ListView) {
		MusicBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((FM_Music_ListView_BaseAdapter)Music_ListView.getAdapter()).setshowNext(false);
			}
		});
	}
}
