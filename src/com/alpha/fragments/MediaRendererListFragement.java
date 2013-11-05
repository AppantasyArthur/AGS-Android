
package com.alpha.fragments;

import java.util.ArrayList;
import java.util.List;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.model.action.ActionArgumentValue;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.ActionArgument;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.UDAServiceId;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.alpha.mainfragment.FAM_ViewFlipper;
import com.alpha.mediarender.MediaRenderListViewListener;
import com.alpha.mediarender.MediaRenderListViewSetting;
import com.alpha.mediarender.OptionButton;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.parser.GroupVO;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;

// Fragment_Speaker
public class MediaRendererListFragement extends Fragment {
	//VIEWS
	private View fragementMainView;	
	private ExpandableListView FS_SPEAKER_EListView;
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	//SETTING
	private MediaRenderListViewSetting settingView;
	private MediaRenderListViewListener listenerView;	

	private static String tag = "MediaRendererListFragement";
	private TKBLog mlog = new TKBLog();
	private Context context;
	private int device_size = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CreateProcess();		
		Log.v(tag, "onCreate");
	}	
	private void CreateProcess() {
		this.context = this.getActivity();
		this.mlog.switchLog = true;		
		device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		fragmentManager = ((MainFragmentActivity)context).getSupportFragmentManager();
		
		//介面設定取得
        this.settingView = new MediaRenderListViewSetting(this.context, this.device_size);
      //動作設定取得
        this.listenerView = new MediaRenderListViewListener(this.context, this.device_size, this.fragmentManager);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(DeviceProperty.isPhone()){
			//手機
			fragementMainView = (ViewGroup)inflater.inflate(R.layout.fragment_speaker_phone, null);
			fragementMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			initPhoneView();//介面設定
			initPhoneViewListener();//動作設定
		}else{	
			//平板
			fragementMainView = (ViewGroup)inflater.inflate(R.layout.fragment_speaker_pad, null);
			fragementMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			initPadView();//介面設定
			initPadViewListener();//動作設定
		}
		return fragementMainView;
	}
	private void initPhoneView() {
		this.settingView.setView(fragementMainView.findViewById(R.id.pFS_RLayout));		
		this.settingView.setView(fragementMainView.findViewById(R.id.pFS_RLayout_TITLE2_RLayout));
		this.settingView.setView(fragementMainView.findViewById(R.id.pFS_RLayout_TITLE2_1_RLayout));
		this.settingView.setView(fragementMainView.findViewById(R.id.pFS_RLayout_TITLE3_RLayout));
		this.settingView.setView(fragementMainView.findViewById(R.id.pFS_RLayout_TITLE4_RLayout));
		//===========Speaker List===========
		FS_SPEAKER_EListView = (ExpandableListView)fragementMainView.findViewById(R.id.pFS_RLayout_SPEAKER_EListView);
		this.settingView.setView(FS_SPEAKER_EListView);	
		//===========Speaker===========		
		this.settingView.setView(fragementMainView.findViewById(R.id.pFS_RLayout_Bottom_RLayout));
		this.settingView.setView(fragementMainView.findViewById(R.id.pFS_RLayout_Bottom2_RLayout));
		mlog.info(tag, "findView OK");
	}
	private void initPhoneViewListener() {
		
		//切換NowPlaying 按鈕
		this.listenerView.setFlip2NowPlayingFragementButtonListener4Phone((Button)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Nowplaying_Button));
		//切換Music 按鈕
		this.listenerView.setFlip2MusicFragementButtonListener4Phone((Button)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Music_Button));
		//Close
		this.listenerView.Close_Button_LISTNER((Button)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Close_Button),
												this);
		//Done
		this.listenerView.Done_Button_LISTNER((Button)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Done_Button),
				this);
		//聲音按鈕
		this.listenerView.Sound_IButton_LISTNER((ImageButton)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Sound_IButton));
		//播放器按鈕
		this.listenerView.Previous_IButton_LISTNER((ImageButton)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Previous_IButton));
		this.listenerView.Next_IButton_LISTNER((ImageButton)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Next_IButton));
		this.listenerView.setPlaybackButtonListener((ImageButton)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Play_IButton));
		this.listenerView.setTimeProgressListener((TextView)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Current_TextView),
												(SeekBar)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Music_SeekBar),
												(TextView)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Total_TextView));
		//PlayMode按鈕
		this.listenerView.CycleRandom_IButton_LISTNER((ImageButton)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Cycle_IButton),
														(ImageButton)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Random_IButton));
		//PlayMode、TimeSeek 控制bar 開關
		this.listenerView.ShowTITLE4_IButton_LISTNER((ImageButton)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_ShowTITLE4_IButton),
														(RelativeLayout)fragementMainView.findViewById(R.id.pFS_RLayout_TITLE4_RLayout));
		this.listenerView.Sound_SeekBarLISTNER((SeekBar)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Sound_SeekBar),
												(ImageButton)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Sound_IButton));
		//===========Speaker List===========
		this.listenerView.SET_SPEAKER_EListView_Listner(FS_SPEAKER_EListView);
		//===========Speaker===========
		//設定按鈕
		this.listenerView.Setting_IButton_LISTNER((ImageButton)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Setting_IButton));
		this.listenerView.Setting_IButton_LISTNER((ImageButton)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_Setting2_IButton));
		//Select 按鈕
		this.listenerView.SELECT_Button_LISTNER((Button)fragementMainView.findViewById(R.id.pFS_RLayout_RLayout_SELECT_Button),
													this);
	}
	
	private void initPadView() {
		settingView.setView(fragementMainView.findViewById(R.id.FS_RLayout));
		settingView.setView(fragementMainView.findViewById(R.id.FS_RLayout_TITLE_RLayout));
//		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FS_RLayout_TITLE2_RLayout));
		//===========Speaker List===========
		FS_SPEAKER_EListView = (ExpandableListView)fragementMainView.findViewById(R.id.FS_RLayout_SPEAKER_EListView);
		settingView.setView(fragementMainView.findViewById(R.id.FS_RLayout_SPEAKER_RLayout));
		//===========Speaker===========
		
		mlog.info(tag, "findView OK");
	}	
	private void initPadViewListener() {
		//===========Speaker List===========
		listenerView.SET_SPEAKER_EListView_Listner(FS_SPEAKER_EListView);
		//===========Speaker===========
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.v(tag, "onActivityCreated");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.v(tag, "onStart");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.v(tag, "onResume");
		
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.v(tag, "onPause");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.v(tag, "onStop");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();		
		Log.v(tag, "onDestroyView");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();		
		Log.v(tag, "onDestroy");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		////////解決 Google SDK ViewFlipper API 問題////////////////
		View ViewContent_ViewFlipper = this.fragementMainView.findViewById(R.id.pFS_RLayout_ViewContent_ViewFlipper);
		if(ViewContent_ViewFlipper!=null){
			((FAM_ViewFlipper)ViewContent_ViewFlipper).onDetachedFromWindow_P();
		}		
		//////////////////////////////////////////////////////////
		Log.v(tag, "onDetach");
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	private List<OptionButton> OptionButtonsList;
	private DeviceDisplay addDeviceDisplay;
	//Phone Close Done Bar 顯示
	public boolean CheckTITLE2_1_RLayoutIsShown(){
		View TITLE2_1_RLayout = fragementMainView.findViewById(R.id.pFS_RLayout_TITLE2_1_RLayout);
		if(TITLE2_1_RLayout.isShown()){
			return true;
		}else{
			return false;
		}
	}
	//Phone Create AddList Item
	public void SetOptionButtons(List<GroupVO> groupVOList){
		LinearLayout ChooseScroll_LLayout = (LinearLayout)fragementMainView.findViewById(R.id.pFS_RLayout_ViewFlipper_ScrollView_ChooseScroll_LLayout);
		ChooseScroll_LLayout.removeAllViews();
		if(OptionButtonsList!=null){
			OptionButtonsList.clear();
		}else{
			OptionButtonsList = new ArrayList<OptionButton>();
		}
		
		for(int i =0 ;i<groupVOList.size();i++){
			OptionButton optionButton = new OptionButton(context,i, groupVOList.get(i));//new OptionButton(i,groupVOList.get(i));
			ChooseScroll_LLayout.addView(optionButton.cellView);
			this.OptionButtonsList.add(optionButton);
		}
	}
	//Phone 設定 要Add 的Renderer
	public void SetAddDeviceDisplay(DeviceDisplay addDeviceDisplay){
		this.addDeviceDisplay = addDeviceDisplay;
	}
	//Phone RendererList AddList 切換
	public void ShowViewContent_ViewFlipperDisplay(int Page,int InAnimation,int OutAnimation){
		View ViewContent_ViewFlipper = fragementMainView.findViewById(R.id.pFS_RLayout_ViewContent_ViewFlipper);
		if(ViewContent_ViewFlipper==null){
			return;
		}
		if(InAnimation!=0){
			((ViewFlipper)ViewContent_ViewFlipper).setInAnimation(context, InAnimation);
		}else{
			((ViewFlipper)ViewContent_ViewFlipper).setInAnimation(null);
		}
		if(InAnimation!=0){
			((ViewFlipper)ViewContent_ViewFlipper).setOutAnimation(context, OutAnimation);
		}else{
			((ViewFlipper)ViewContent_ViewFlipper).setOutAnimation(null);
		}
		
		View TITLE2_1_RLayout = fragementMainView.findViewById(R.id.pFS_RLayout_TITLE2_1_RLayout);
		View Bottom2_RLayout = fragementMainView.findViewById(R.id.pFS_RLayout_Bottom2_RLayout);
		switch(Page){
		case 0:				
			//顯示AddView
			TITLE2_1_RLayout.setVisibility(View.VISIBLE);
			Bottom2_RLayout.setVisibility(View.VISIBLE);
			((ViewFlipper)ViewContent_ViewFlipper).setDisplayedChild(1);
			
			break;
		case 1:
			//顯示Speaker 加入Group
			for(int i=0;i<OptionButtonsList.size();i++){
				if(OptionButtonsList.get(i).isSelected){
					SetRelationWithMaster(OptionButtonsList.get(i).groupVO.getUdn(),true);
				}else{
					SetRelationWithMaster(OptionButtonsList.get(i).groupVO.getUdn(),false);
				}					
			}			
			TITLE2_1_RLayout.setVisibility(View.GONE);
			Bottom2_RLayout.setVisibility(View.GONE);
			((ViewFlipper)ViewContent_ViewFlipper).setDisplayedChild(0);
			break;
		case 2:
			//顯示Speaker 取消Group			
			TITLE2_1_RLayout.setVisibility(View.GONE);
			Bottom2_RLayout.setVisibility(View.GONE);
			((ViewFlipper)ViewContent_ViewFlipper).setDisplayedChild(0);
			break;
		}
	}
	//Phone Renderer 全選
	public void SetALLOptionButtonsSelect(){
		if(OptionButtonsList!=null){
			for(OptionButton optionButton:OptionButtonsList){
				optionButton.isSelected = true;
				new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/select_icon.PNG", optionButton.Radio_ImageButton, 2);
			}
		}
	}	
	//Phone Master Slave 設定
	private void SetRelationWithMaster(String SUDN,boolean isAdd){
		//取得upnpServer
		AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
		Device mMMDevice = addDeviceDisplay.getMMDevice();
		String MUDN = mMMDevice.getIdentity().getUdn().toString();
		Device MMDevice = ((MainFragmentActivity)context).getDeviceDisplayList().GetMMDevice(SUDN);
		if(MMDevice==null){
			return;
		}
		Service GroupService = MMDevice.findService(new UDAServiceId("Group"));
		if(GroupService!=null){
			Action SetRelationWithMasterAction = GroupService.getAction("SetRelationWithMaster");
			if(SetRelationWithMasterAction!=null){				
				ActionArgumentValue[] values = new ActionArgumentValue[2];
				ActionArgument DeviceUDN = SetRelationWithMasterAction.getInputArgument("DeviceUDN");
				ActionArgument RelationAction = SetRelationWithMasterAction.getInputArgument("RelationAction");
				if(DeviceUDN!=null&&RelationAction!=null){
					values[0] =new ActionArgumentValue(DeviceUDN, MUDN);
					if(isAdd){
						values[1] =new ActionArgumentValue(RelationAction, "Add");
					}else{
						values[1] =new ActionArgumentValue(RelationAction, "Remove");
					}
					mlog.info(tag, "DeviceUDN  = "+values[0].toString());
					mlog.info(tag, "RelationAction = "+values[1].toString());
					ActionInvocation ai = new ActionInvocation(SetRelationWithMasterAction,values);
					ActionCallback SetRelationWithMasterActionCallBack = new ActionCallback(ai){
						@Override
						public void failure(ActionInvocation arg0, UpnpResponse arg1, String arg2) {
							mlog.info(tag, "SetRelationWithMasterActionCallBack failure = "+arg2);
						}
						@Override
						public void success(ActionInvocation arg0) {									
							mlog.info(tag, "SetRelationWithMasterActionCallBack success");
						}											
					};
					upnpServer.getControlPoint().execute(SetRelationWithMasterActionCallBack);	
				}
			}			
		}		
	}	
}
