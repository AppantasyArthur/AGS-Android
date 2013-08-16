package com.FSM.SETTING;

import com.alpha.fragments.Fragment_SMenu;
import com.alpha.fragments.Fragment_SRenderers;
import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.Fragment_SETTING;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

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
	public void Done_Button_Listner(Button Done_Button){		
		if(device_size==6){
			Done_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {			
					Tool.FragmentActivity_MainPopFragment(((FragmentActivity_Main)context).getSupportFragmentManager());
				}
			});			
		}else{
			Done_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {					
					Tool.FragmentActivity_MainPopFragment(((FragmentActivity_Main)context).getSupportFragmentManager());
				}
			});
		}		
	}
	public void About_Button_Listner(Button About_Button,final FragmentManager fragmentManager){
		About_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ShowFragment_SRenderers(fragmentManager,1);
				
			}
		});
		//		if(device_size==6){
//			About_Button.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SAbout(), "Fragment_SAbout", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);	
//					Fragment_SETTING fragment_SETTING = (Fragment_SETTING)((FragmentActivity_Main)context).getSupportFragmentManager().findFragmentByTag("Fragment_SETTING");
//					if(fragment_SETTING!=null){
//						fragment_SETTING.ShowViewContent_ViewFlipperDisplay(1, R.animator.translate_bottom_in,R.animator.alpha_out);
//					}	
//				}
//			});
//		}else{
//			About_Button.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					
//					v.setSelected(true);
//					if(fragment_SMenu!=null){
//						fragment_SMenu.SetChooseMenu(1);
//					}
//				}
//			});
//		}
	}
	public void Firmrware_Button_Listner(Button Firmrware_Button,final FragmentManager fragmentManager){
		Firmrware_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ShowFragment_SRenderers(fragmentManager,2);
				
			}
		});
		//		if(device_size==6){
//			Firmrware_Button.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SFirmrware(), "Fragment_SFirmrware", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
//					Fragment_SETTING fragment_SETTING = (Fragment_SETTING)((FragmentActivity_Main)context).getSupportFragmentManager().findFragmentByTag("Fragment_SETTING");
//					if(fragment_SETTING!=null){
//						fragment_SETTING.ShowViewContent_ViewFlipperDisplay(1, R.animator.translate_bottom_in,R.animator.alpha_out);
//					}	
//				}
//			});
//		}else{
//			Firmrware_Button.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SFirmrware(), "Fragment_SFirmrware", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, 0);
//					if(fragment_SMenu!=null){
//						fragment_SMenu.SetChooseMenu(2);
//					}
//				}
//			});
//		}
	}
	public void Wireless_Button_Listner(Button Wireless_Button,final FragmentManager fragmentManager){
		Wireless_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ShowFragment_SRenderers(fragmentManager,3);
				
			}
		});
		//		if(device_size==6){
//			Wireless_Button.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SWireless(), "Fragment_SWireless", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
//					Fragment_SETTING fragment_SETTING = (Fragment_SETTING)((FragmentActivity_Main)context).getSupportFragmentManager().findFragmentByTag("Fragment_SETTING");
//					if(fragment_SETTING!=null){
//						fragment_SETTING.ShowViewContent_ViewFlipperDisplay(1, R.animator.translate_bottom_in,R.animator.alpha_out);
//					}	
//				}
//			});
//		}else{
//			Wireless_Button.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SWireless(), "Fragment_SWireless", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, 0);
//					if(fragment_SMenu!=null){
//						fragment_SMenu.SetChooseMenu(3);
//					}
//				}
//			});
//		}
	}
	public void Idenrify_Button_Listner(Button Idenrify_Button,final FragmentManager fragmentManager){
		Idenrify_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ShowFragment_SRenderers(fragmentManager,4);
				
			}
		});
		//		if(device_size==6){
//		Idenrify_Button.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
//				Fragment_SETTING fragment_SETTING = (Fragment_SETTING)((FragmentActivity_Main)context).getSupportFragmentManager().findFragmentByTag("Fragment_SETTING");
//				if(fragment_SETTING!=null){
//					fragment_SETTING.ShowViewContent_ViewFlipperDisplay(1, R.animator.translate_bottom_in,R.animator.alpha_out);
//				}	
//			}
//		});
//	}else{
//		Idenrify_Button.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.FAS_RLayout_Right_RLayout, R.animator.translate_top_in, 0);
//			}
//		});
//	}
//		if(device_size==6){
//			Idenrify_Button.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SIdenrify(), "Fragment_SIdenrify", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
//					Fragment_SETTING fragment_SETTING = (Fragment_SETTING)((FragmentActivity_Main)context).getSupportFragmentManager().findFragmentByTag("Fragment_SETTING");
//					if(fragment_SETTING!=null){
//						fragment_SETTING.ShowViewContent_ViewFlipperDisplay(1, R.animator.translate_bottom_in,R.animator.alpha_out);
//					}	
//				}
//			});
//		}else{
//			Idenrify_Button.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SIdenrify(), "Fragment_SIdenrify", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, 0);
//					if(fragment_SMenu!=null){
//						fragment_SMenu.SetChooseMenu(4);
//					}
//				}
//			});
//		}
	}
	public void Alarm_Button_Listner(Button Alarm_Button,final FragmentManager fragmentManager){
		Alarm_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ShowFragment_SRenderers(fragmentManager,5);
				
			}
		});
		//		if(device_size==6){
//			Alarm_Button.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SWireless(), "Fragment_SWireless", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
//					Fragment_SETTING fragment_SETTING = (Fragment_SETTING)((FragmentActivity_Main)context).getSupportFragmentManager().findFragmentByTag("Fragment_SETTING");
//					if(fragment_SETTING!=null){
//						fragment_SETTING.ShowViewContent_ViewFlipperDisplay(1, R.animator.translate_bottom_in,R.animator.alpha_out);
//					}	
//				}
//			});
//		}else{
//			Alarm_Button.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
////					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SWireless(), "Fragment_SWireless", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, 0);
//					if(fragment_SMenu!=null){
//						fragment_SMenu.SetChooseMenu(5);
//					}
//				}
//			});
//		}
	}
	public void SleepTimer_Button_Listner(Button SleepTimer_Button,final FragmentManager fragmentManager){

		SleepTimer_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ShowFragment_SRenderers(fragmentManager,6);
				
			}
		});
		
		//		if(device_size==6){
//			SleepTimer_Button.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SWireless(), "Fragment_SWireless", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
//					Fragment_SETTING fragment_SETTING = (Fragment_SETTING)((FragmentActivity_Main)context).getSupportFragmentManager().findFragmentByTag("Fragment_SETTING");
//					if(fragment_SETTING!=null){
//						fragment_SETTING.ShowViewContent_ViewFlipperDisplay(1, R.animator.translate_bottom_in,R.animator.alpha_out);
//					}	
//				}
//			});
//		}else{
//			SleepTimer_Button.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
////					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SWireless(), "Fragment_SWireless", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, 0);
//					if(fragment_SMenu!=null){
//						fragment_SMenu.SetChooseMenu(6);
//					}
//				}
//			});
//		}
	}
	private void ShowFragment_SRenderers(FragmentManager fragmentManager,int ChooseMenu){
		fragment_SMenu.SetChooseMenu(ChooseMenu);
		if(device_size==6){
			if(fragmentManager.findFragmentByTag("Fragment_SRenderers")==null){
				Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
			}
			
			Fragment_SETTING fragment_SETTING = (Fragment_SETTING)((FragmentActivity_Main)context).getSupportFragmentManager().findFragmentByTag("Fragment_SETTING");
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
