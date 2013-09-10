package com.FSM.SETTING;

import com.alpha.fragments.Fragment_SMenu;
import com.alpha.fragments.Fragment_SRenderers;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.Fragment_SETTING;
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

public class FSM_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSM_VIEW_LISTNER";
	private int device_size = 0;
	private Fragment_SMenu fragment_SMenu;
	
	public FSM_VIEW_LISTNER(Context context, int device_size,Fragment_SMenu fragment_SMenu) {
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
		this.fragment_SMenu = fragment_SMenu;
	}
	
	// Done_Button_Listner
	public void setDoneButtonListener(Button buttonDone){		
		
		if(device_size==6){
			buttonDone.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {			
					Tool.FragmentActivity_MainPopFragment(((MainFragmentActivity)context).getSupportFragmentManager());
				}
			});			
		}else{
			buttonDone.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {					
					Tool.FragmentActivity_MainPopFragment(((MainFragmentActivity)context).getSupportFragmentManager());
				}
			});
		}		
	}
	
	// Menu_ListView_LISTNER
	public void setSettingFunctionMenuListener(ListView listSettingFunctionMenu,final FragmentManager fragmentManager){
		
		if(device_size==6){
			listSettingFunctionMenu.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
					FSM_MENU_ListView_BaseAdapter_Phone baseAdapter_PAD = (FSM_MENU_ListView_BaseAdapter_Phone)arg0.getAdapter();
					baseAdapter_PAD.SetChoosedMenu(arg2);
					ShowFragment_SRenderers(fragmentManager);
				}
			});
		}else{
			listSettingFunctionMenu.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
					FSM_MENU_ListView_BaseAdapter_PAD baseAdapter_PAD = (FSM_MENU_ListView_BaseAdapter_PAD)arg0.getAdapter();
					baseAdapter_PAD.SetChoosedMenu(arg2);
					ShowFragment_SRenderers(fragmentManager);
				}
			});
		}		
	}
	private void ShowFragment_SRenderers(FragmentManager fragmentManager){
		if(device_size==6){
			if(fragmentManager.findFragmentByTag("Fragment_SRenderers")==null){
				Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.alpha_in, R.animator.alpha_out);
			}
			
			Fragment_SETTING fragment_SETTING = (Fragment_SETTING)((MainFragmentActivity)context).getSupportFragmentManager().findFragmentByTag("Fragment_SETTING");
			if(fragment_SETTING!=null){
				fragment_SETTING.ShowViewContent_ViewFlipperDisplay(1, R.animator.translate_bottom_in,R.animator.translate_top_out);
			}			
		}else{
			if(fragmentManager.findFragmentByTag("Fragment_SRenderers")==null){
				Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
			}
		}
	}
	
}
