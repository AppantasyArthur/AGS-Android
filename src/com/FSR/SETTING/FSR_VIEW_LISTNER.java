package com.FSR.SETTING;

import com.alpha.UPNP.DeviceDisplay;
import com.alpha.fragments.Fragment_SAbout;
import com.alpha.fragments.Fragment_SFirmrware;
import com.alpha.fragments.Fragment_SIdenrify;
import com.alpha.fragments.Fragment_SMenu;
import com.alpha.fragments.Fragment_SSleepTimer;
import com.alpha.fragments.Fragment_SWireless;
import com.alpha.upnpui.FragmentActivity_Main;
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

public class FSR_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSR_VIEW_LISTNER";
	private int device_size = 0;
	public FSR_VIEW_LISTNER(Context context, int device_size) {
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void Back_Button_Listner(Button Back_Button){
		Back_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {			
				Fragment_SETTING fragment_SETTING = (Fragment_SETTING)((FragmentActivity_Main)context).getSupportFragmentManager().findFragmentByTag("Fragment_SETTING");
				if(fragment_SETTING!=null){
					fragment_SETTING.ShowViewContent_ViewFlipperDisplay(0, R.animator.translate_top_in, R.animator.translate_bottom_out);
				}	
			}
		});	
	}
	public void Renderers_ListView_LISTNER(ListView Renderers_ListView,final FragmentManager fragmentManager){
		if(device_size==6){
			Renderers_ListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {					
					DeviceDisplay deviceDisplay = (DeviceDisplay)arg0.getItemAtPosition(arg2);					
					Fragment_SMenu fragment_SMenu = (Fragment_SMenu)fragmentManager.findFragmentByTag("Fragment_SMenu");
					int chooseMenu = 0;
					if(fragment_SMenu!=null&&(chooseMenu=fragment_SMenu.getChooseMenu())!=0){
						switch(chooseMenu){
						case 1:
							Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SAbout(deviceDisplay), "Fragment_SAbout", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.translate_bottom_in, R.animator.translate_top_out);
							break;
						case 2:
							Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SFirmrware(deviceDisplay), "Fragment_SFirmrware", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.translate_bottom_in, R.animator.translate_top_out);
							break;
						case 3:
							Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SWireless(deviceDisplay), "Fragment_SWireless", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.translate_bottom_in, R.animator.translate_top_out);
							break;
						case 4:
							Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SIdenrify(deviceDisplay), "Fragment_SIdenrify", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.translate_bottom_in, R.animator.translate_top_out);
							break;
						case 5:
							break;
						case 6:
							Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SSleepTimer(deviceDisplay), "Fragment_SSleepTimer", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.translate_bottom_in, R.animator.translate_top_out);
							break;
						}
					}
				}
			});
		}else{
			Renderers_ListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {					
					DeviceDisplay deviceDisplay = (DeviceDisplay)arg0.getItemAtPosition(arg2);					
					Fragment_SMenu fragment_SMenu = (Fragment_SMenu)fragmentManager.findFragmentByTag("Fragment_SMenu");
					int chooseMenu = 0;
					if(fragment_SMenu!=null&&(chooseMenu=fragment_SMenu.getChooseMenu())!=0){
						switch(chooseMenu){
						case 1:
							Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SAbout(deviceDisplay), "Fragment_SAbout", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
							break;
						case 2:
							Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SFirmrware(deviceDisplay), "Fragment_SFirmrware", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
							break;
						case 3:
							Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SWireless(deviceDisplay), "Fragment_SWireless", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
							break;
						case 4:
							Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SIdenrify(deviceDisplay), "Fragment_SIdenrify", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
							break;
						case 5:
							break;
						case 6:
							Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SSleepTimer(deviceDisplay), "Fragment_SSleepTimer", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
							break;
						}
					}
				}
			});
		}		
	}
}
