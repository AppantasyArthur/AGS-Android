package com.FSS.SETTING;


import com.alpha.fragments.Fragment_SRenderers;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class FSS_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSS_VIEW_LISTNER";
	private int device_size = 0;
	public FSS_VIEW_LISTNER(Context context, int device_size) {
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}	
	
	public void Back_Button_Listner(Button Back_Button,final FragmentManager fragmentManager){
		if(device_size==6){
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.translate_top_in, R.animator.translate_bottom_out);			
				}
			});	
		}else{
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
				}
			});	
		}		
	}
	public void SleepTimer_ListView_LISTNER(ListView SleepTimer_ListView){
		if(device_size==6){
			SleepTimer_ListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					FSS_SleepTimer_ListView_BaseAdapter_Phone baseAdapter = (FSS_SleepTimer_ListView_BaseAdapter_Phone)arg0.getAdapter();
					switch(arg2){
					case 0:
						//Off
						break;
					case 1:
						//15 Minutes
						break;
					case 2:
						//30 Minutes
						break;
					case 3:
						//45 Minutes
						break;
					case 4:
						//1 Hour
						break;
					case 5:
						//2 Hour
						break;
					case 6:
						//3 Hour
						break;
					}
					baseAdapter.SetChooseItem(arg2);//顯示打勾
				}
			});
		}else{
			SleepTimer_ListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					FSS_SleepTimer_ListView_BaseAdapter_PAD baseAdapter = (FSS_SleepTimer_ListView_BaseAdapter_PAD)arg0.getAdapter();
					switch(arg2){
					case 0:
						//Off
						break;
					case 1:
						//15 Minutes
						break;
					case 2:
						//30 Minutes
						break;
					case 3:
						//45 Minutes
						break;
					case 4:
						//1 Hour
						break;
					case 5:
						//2 Hour
						break;
					case 6:
						//3 Hour
						break;
					}
					baseAdapter.SetChooseItem(arg2);//顯示打勾
				}
			});
		}
		
	}
}
