package com.alpha.setting.functionmenu;

import com.alpha.fragments.SettingMenuFragment;
import com.alpha.fragments.Fragment_SRenderers;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.Fragment_SETTING;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class SettingsMenuListener {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FSM_VIEW_LISTNER";
	private int deviceScreenSize = 0;
	
	private SettingMenuFragment fragmentSettingMenu;
	public SettingsMenuListener(Context context, int device_size,SettingMenuFragment fragment_SMenu) {
		this.context = context;
		this.mlog.switchLog = true;
		this.deviceScreenSize = device_size;
		this.fragmentSettingMenu = fragment_SMenu;
	}
	
	// Done_Button_Listner
	public void setDoneButtonListener(Button buttonDone){		
		
		if(deviceScreenSize==6){
			buttonDone.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {			
					TKBTool.doPopBackFragment(((MainFragmentActivity)context).getSupportFragmentManager());
				}
			});			
		}else{
			buttonDone.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {					
					TKBTool.doPopBackFragment(((MainFragmentActivity)context).getSupportFragmentManager());
				}
			});
		}		
	}
	
	// Menu_ListView_LISTNER
	public void setSettingFunctionMenuListener(ListView listSettingFunctionMenu,final FragmentManager fragmentManager){
		
		if(deviceScreenSize==6){
			listSettingFunctionMenu.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					SettingsMenuPhoneAdapter baseAdapter_PAD = (SettingsMenuPhoneAdapter)parent.getAdapter();
					baseAdapter_PAD.setChoosedMenu(position);
					showRenderers(fragmentManager);
				}
			});
		}else{
			listSettingFunctionMenu.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					SettingsMenuPadAdapter adapterPad = (SettingsMenuPadAdapter)parent.getAdapter();
					adapterPad.setFunctionChoosed(position);
					showRenderers(fragmentManager);
				}
			});
		}		
	}
	private void showRenderers(FragmentManager fragmentManager){
		
		if(deviceScreenSize==6){
			if(fragmentManager.findFragmentByTag("Fragment_SRenderers")==null){
				TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.alpha_in, R.animator.alpha_out);
			}
			
			Fragment_SETTING fragment_SETTING = (Fragment_SETTING)((MainFragmentActivity)context).getSupportFragmentManager().findFragmentByTag("Fragment_SETTING");
			if(fragment_SETTING!=null){
				fragment_SETTING.ShowViewContent_ViewFlipperDisplay(1, R.animator.translate_bottom_in,R.animator.translate_top_out);
			}			
		}else{ // Pad
			if(fragmentManager.findFragmentByTag("Fragment_SRenderers")==null){
				TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
			}
		}
	}
	
}
