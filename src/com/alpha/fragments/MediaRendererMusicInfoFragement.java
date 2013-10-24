package com.alpha.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.FAM.SETTING.FAM_ViewFlipper;
import com.alpha.musicinfo.FI_PointLiLayout;
import com.alpha.musicinfo.MusicInfoListPadViewAdapter;
import com.alpha.musicinfo.MusicInfoListPhoneViewAdapter;
import com.alpha.musicinfo.MusicInfoViewListener;
import com.alpha.musicinfo.MusicInfoViewSetting;
import com.alpha.musicinfo.MusicInfoFlipperView;
import com.alpha.musicinfo.MusicInfoListView;
import com.alpha.upnp.parser.TrackDO;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;

// Fragment_Information
public class MediaRendererMusicInfoFragement extends AGSFragment {
	
	//VIEWS
	private View fragementMainView;
	
	//InfoViewFlipper
	private MusicInfoFlipperView fi_Info_ViewFlipper;
		
	//Queuev清單
	private MusicInfoListView viewMediaRenderQueueList;
	
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	//SETTING
	private MusicInfoViewSetting settingMusicInfoView;
	private MusicInfoViewListener listenerMusicInfoView;
	private static String tag = "MediaRendererMusicInfoFragement";
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
        this.settingMusicInfoView = new MusicInfoViewSetting(this.context,this.device_size);
        //動作設定取得
        this.listenerMusicInfoView = new MusicInfoViewListener(this.context,this.device_size,this.fragmentManager);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(DeviceProperty.isPhone()){
			//手機
			fragementMainView = (ViewGroup)inflater.inflate(R.layout.fragment_information_phone, null);
			fragementMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();//介面設定
			initPhoneViewListener();//動作設定
		}else{
			//平板
			fragementMainView = (ViewGroup)inflater.inflate(R.layout.fragment_information_pad, null);
			fragementMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();//介面設定
			PAD_findViewListner();//動作設定
		}		
		return fragementMainView;
	}

	
	private void Phone_findView() {
		this.settingMusicInfoView.VIEWSET(fragementMainView.findViewById(R.id.pFI_RLayout));		
		this.settingMusicInfoView.VIEWSET(fragementMainView.findViewById(R.id.pFI_RLayout_TITLE2_RLayout));
		this.settingMusicInfoView.VIEWSET(fragementMainView.findViewById(R.id.pFI_RLayout_TITLE2_1_RLayout));
		this.settingMusicInfoView.VIEWSET(fragementMainView.findViewById(R.id.pFI_RLayout_TITLE3_RLayout));
		this.settingMusicInfoView.VIEWSET(fragementMainView.findViewById(R.id.pFI_RLayout_TITLE4_RLayout));
		//==========UP Info================
		this.settingMusicInfoView.VIEWSET(fragementMainView.findViewById(R.id.pFI_RLayout_UP_RLayout));
		fi_Info_ViewFlipper = (MusicInfoFlipperView)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_INFOR_ViewFlipper);
		fi_Info_ViewFlipper.setFI_PointLiLayout((FI_PointLiLayout)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Point_LLayout));
		//==========UP================
		//==========down Queue================
		settingMusicInfoView.VIEWSET(fragementMainView.findViewById(R.id.pFI_RLayout_DOWN_RLayout));
		viewMediaRenderQueueList = (MusicInfoListView)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_QUEUE_ListView);
		//==========down================
		this.settingMusicInfoView.VIEWSET(fragementMainView.findViewById(R.id.pFI_RLayout_Bottom_RLayout));
		this.settingMusicInfoView.VIEWSET(fragementMainView.findViewById(R.id.pFI_RLayout_Bottom2_RLayout));
	}
	private void initPhoneViewListener() {
		//切換Speaker 按鈕
		this.listenerMusicInfoView.Speaker_Button_LISTNER((Button)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Speaker_Button));
		//切換Music 按鈕
		this.listenerMusicInfoView.Music_Button_LISTNER((Button)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Music_Button));
		//聲音按鈕
		this.listenerMusicInfoView.Sound_IButton_LISTNER((ImageButton)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Sound_IButton));
		//播放器按鈕
		this.listenerMusicInfoView.Previous_IButton_LISTNER((ImageButton)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Previous_IButton));
		this.listenerMusicInfoView.Next_IButton_LISTNER((ImageButton)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Next_IButton));
		this.listenerMusicInfoView.Play_IButton_LISTNER((ImageButton)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Play_IButton));
		//TimeSeek
		this.listenerMusicInfoView.SetTimeSeekLISTNER((TextView)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Current_TextView),
												(SeekBar)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Music_SeekBar),
												(TextView)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Total_TextView));
		//PlayMode按鈕
		this.listenerMusicInfoView.CycleRandom_IButton_LISTNER((ImageButton)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Cycle_IButton),
														(ImageButton)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Random_IButton));
		//顯示Queue按鈕
		this.listenerMusicInfoView.setShowQueueListButtonListener((Button)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Queue_Button),
													(RelativeLayout)fragementMainView.findViewById(R.id.pFI_RLayout_TITLE2_1_RLayout),
													(RelativeLayout)fragementMainView.findViewById(R.id.pFI_RLayout_Bottom2_RLayout),
													(ViewFlipper)fragementMainView.findViewById(R.id.pFI_RLayout_ViewContent_ViewFlipper));
		//關閉Queue按鈕
		this.listenerMusicInfoView.setCloseQueueListButtonListener((Button)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Close_Button),
													(RelativeLayout)fragementMainView.findViewById(R.id.pFI_RLayout_TITLE2_1_RLayout),
													(RelativeLayout)fragementMainView.findViewById(R.id.pFI_RLayout_Bottom2_RLayout),
													(ViewFlipper)fragementMainView.findViewById(R.id.pFI_RLayout_ViewContent_ViewFlipper));
		//PlayMode、TimeSeek 控制bar 開關
		this.listenerMusicInfoView.ShowTITLE4_IButton_LISTNER((ImageButton)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_ShowTITLE4_IButton),
														(RelativeLayout)fragementMainView.findViewById(R.id.pFI_RLayout_TITLE4_RLayout));
		this.listenerMusicInfoView.Sound_SeekBarLISTNER((SeekBar)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Sound_SeekBar),
												(ImageButton)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Sound_IButton));
		//Queue
		this.listenerMusicInfoView.setQueueListViewListener(viewMediaRenderQueueList);	
		//清除按鈕
		this.listenerMusicInfoView.setClearQueueButtonListener((Button)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Clear_Button),
												(ImageView)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_ButtonsBG_ImageView));
		//儲存按鈕
		this.listenerMusicInfoView.setSaveQueueButtonListener((Button)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Save_Button),
												(ImageView)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_ButtonsBG_ImageView));
		//完成按鈕
		this.listenerMusicInfoView.Done_Button_LISTNER((Button)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Done_Button),
												(Button)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Clear_Button),
												(Button)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Save_Button),
												this);
		//設定按鈕
		this.listenerMusicInfoView.Setting_IButton_LISTNER((ImageButton)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Setting_IButton));
		this.listenerMusicInfoView.Setting_IButton_LISTNER((ImageButton)fragementMainView.findViewById(R.id.pFI_RLayout_RLayout_Setting2_IButton));
	}
	private void PAD_findView() {		
		settingMusicInfoView.VIEWSET(fragementMainView.findViewById(R.id.FI_RLayout_UP_RLayout));
		//==========UP Info================
		fi_Info_ViewFlipper = (MusicInfoFlipperView)fragementMainView.findViewById(R.id.FI_RLayout_RLayout_INFOR_ViewFlipper);
		fi_Info_ViewFlipper.setFI_PointLiLayout((FI_PointLiLayout)fragementMainView.findViewById(R.id.FI_RLayout_RLayout_Point_LLayout));
		//==========UP================
		//==========down Queue================
		settingMusicInfoView.VIEWSET(fragementMainView.findViewById(R.id.FI_RLayout_DOWN_RLayout));
		viewMediaRenderQueueList = (MusicInfoListView)fragementMainView.findViewById(R.id.FI_RLayout_RLayout_QUEUE_ListView);
		//==========down================
		mlog.info(tag, "findView OK");
	}	
	private void PAD_findViewListner() {
		listenerMusicInfoView.setQueueListViewListener(viewMediaRenderQueueList);
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
		View ViewContent_ViewFlipper = this.fragementMainView.findViewById(R.id.pFI_RLayout_ViewContent_ViewFlipper);
		if(ViewContent_ViewFlipper!=null){
			((FAM_ViewFlipper)ViewContent_ViewFlipper).onDetachedFromWindow_P();
		}
		if(fi_Info_ViewFlipper!=null){
			fi_Info_ViewFlipper.onDetachedFromWindow_P();
		}
		//////////////////////////////////////////////////////////
		Log.v(tag, "onDetach");
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	//PAD 判斷拖移
	public void GET_TRANSFROM_FROM_FM(MotionEvent FM_Event){	
		
		if(viewMediaRenderQueueList!=null&&fi_Info_ViewFlipper!=null){	
			int[] Queue_location = new int[2];
			viewMediaRenderQueueList.getLocationOnScreen(Queue_location);
			int[] Info_location = new int[2];
			fi_Info_ViewFlipper.getLocationOnScreen(Info_location);
			switch(FM_Event.getAction()){			
			case MotionEvent.ACTION_MOVE:
				if(FM_Event.getRawX()>=Queue_location[0]&&FM_Event.getRawX()<=Queue_location[0]+viewMediaRenderQueueList.getWidth()&&FM_Event.getRawY()>=Queue_location[1]&&FM_Event.getRawY()<=Queue_location[1]+viewMediaRenderQueueList.getHeight()){
					int position = viewMediaRenderQueueList.pointToPosition((int)(FM_Event.getRawX()-Queue_location[0]), (int)(FM_Event.getRawY()-Queue_location[1]));
					if(position!=AdapterView.INVALID_POSITION){
						((MusicInfoListPadViewAdapter)viewMediaRenderQueueList.getAdapter()).setInsertPosition(position);
					}
				}else{
					((MusicInfoListPadViewAdapter)viewMediaRenderQueueList.getAdapter()).setInsertPosition(-1);
				}
				break;
			case MotionEvent.ACTION_UP:
				//拖移到fi_Info_ViewFlipper
				if(FM_Event.getRawX()>=Info_location[0]&&FM_Event.getRawX()<=Info_location[0]+fi_Info_ViewFlipper.getWidth()&&FM_Event.getRawY()>=Info_location[1]&&FM_Event.getRawY()<=Info_location[1]+fi_Info_ViewFlipper.getHeight()){
					
				}
				((MusicInfoListPadViewAdapter)viewMediaRenderQueueList.getAdapter()).setInsertPosition(-1);
				break;
			}
		}		
	}
	//編輯模式
	public boolean SET_FI_ListView_Edite(boolean isEdit){
		if(!isEdit){
			if(DeviceProperty.isPhone()){
				((MusicInfoListPhoneViewAdapter)this.viewMediaRenderQueueList.getAdapter()).SET_Edite(false);
			}else{
				((MusicInfoListPadViewAdapter)this.viewMediaRenderQueueList.getAdapter()).setIsEditting(false);
			}
		}else{
			if(DeviceProperty.isPhone()){
				((MusicInfoListPhoneViewAdapter)this.viewMediaRenderQueueList.getAdapter()).SET_Edite(true);
			}else{
				((MusicInfoListPadViewAdapter)this.viewMediaRenderQueueList.getAdapter()).setIsEditting(true);
			}
		}
		if(DeviceProperty.isPhone()){
			return ((MusicInfoListPhoneViewAdapter)this.viewMediaRenderQueueList.getAdapter()).GET_Edite();
		}else{
			return ((MusicInfoListPadViewAdapter)this.viewMediaRenderQueueList.getAdapter()).isEditting();
		}
		
	}
	public List<TrackDO> GetQueueItems(){
		List<TrackDO> QueueItems = new ArrayList<TrackDO>();
		List<TrackDO> list = viewMediaRenderQueueList.GetQueue();
		if(list!=null){
			for(TrackDO item:list){
				QueueItems.add(item);
			}
		}		
		return QueueItems;
		
	}
}
