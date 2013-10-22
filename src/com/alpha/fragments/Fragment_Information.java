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
import com.FI.SETTING.FI_ListView;
import com.FI.SETTING.FI_PointLiLayout;
import com.FI.SETTING.FI_Queqe_ListView_BaseAdapter_PAD;
import com.FI.SETTING.FI_Queqe_ListView_BaseAdapter_Phone;
import com.FI.SETTING.FI_VIEW_LISTNER;
import com.FI.SETTING.FI_VIEW_SETTING;
import com.FI.SETTING.FI_ViewFlipper;
import com.alpha.upnp.parser.TrackDO;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;

public class Fragment_Information extends Fragment {
	
	//VIEWS
	private View Fragment_MainView;	
	//Queuev清單
	private FI_ListView fi_Queue_ListView;
	//InfoViewFlipper
	private FI_ViewFlipper fi_Info_ViewFlipper;
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	//SETTING
	private FI_VIEW_SETTING VIEW_SETTING;
	private FI_VIEW_LISTNER VIEW_LISTNER;
	private static String TAG = "Fragment_Information";
	private TKBLog mlog = new TKBLog();
	private Context context;
	private int device_size = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CreateProcess();		
		Log.v(TAG, "onCreate");
	}	
	private void CreateProcess() {
		this.context = this.getActivity();
		this.mlog.switchLog = true;		
		device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		fragmentManager = ((MainFragmentActivity)context).getSupportFragmentManager();
		//介面設定取得
        this.VIEW_SETTING = new FI_VIEW_SETTING(this.context,this.device_size);
        //動作設定取得
        this.VIEW_LISTNER = new FI_VIEW_LISTNER(this.context,this.device_size,this.fragmentManager);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(DeviceProperty.isPhone()){
			//手機
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_information_phone, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();//介面設定
			Phone_findViewListner();//動作設定
		}else{
			//平板
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_information_pad, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();//介面設定
			PAD_findViewListner();//動作設定
		}		
		return Fragment_MainView;
	}

	
	private void Phone_findView() {
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFI_RLayout));		
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFI_RLayout_TITLE2_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFI_RLayout_TITLE2_1_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFI_RLayout_TITLE3_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFI_RLayout_TITLE4_RLayout));
		//==========UP Info================
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFI_RLayout_UP_RLayout));
		fi_Info_ViewFlipper = (FI_ViewFlipper)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_INFOR_ViewFlipper);
		fi_Info_ViewFlipper.setFI_PointLiLayout((FI_PointLiLayout)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Point_LLayout));
		//==========UP================
		//==========down Queue================
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFI_RLayout_DOWN_RLayout));
		fi_Queue_ListView = (FI_ListView)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_QUEUE_ListView);
		//==========down================
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFI_RLayout_Bottom_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFI_RLayout_Bottom2_RLayout));
	}
	private void Phone_findViewListner() {
		//切換Speaker 按鈕
		this.VIEW_LISTNER.Speaker_Button_LISTNER((Button)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Speaker_Button));
		//切換Music 按鈕
		this.VIEW_LISTNER.Music_Button_LISTNER((Button)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Music_Button));
		//聲音按鈕
		this.VIEW_LISTNER.Sound_IButton_LISTNER((ImageButton)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Sound_IButton));
		//播放器按鈕
		this.VIEW_LISTNER.Previous_IButton_LISTNER((ImageButton)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Previous_IButton));
		this.VIEW_LISTNER.Next_IButton_LISTNER((ImageButton)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Next_IButton));
		this.VIEW_LISTNER.Play_IButton_LISTNER((ImageButton)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Play_IButton));
		//TimeSeek
		this.VIEW_LISTNER.SetTimeSeekLISTNER((TextView)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Current_TextView),
												(SeekBar)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Music_SeekBar),
												(TextView)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Total_TextView));
		//PlayMode按鈕
		this.VIEW_LISTNER.CycleRandom_IButton_LISTNER((ImageButton)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Cycle_IButton),
														(ImageButton)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Random_IButton));
		//顯示Queue按鈕
		this.VIEW_LISTNER.SET_QUEUE_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Queue_Button),
													(RelativeLayout)Fragment_MainView.findViewById(R.id.pFI_RLayout_TITLE2_1_RLayout),
													(RelativeLayout)Fragment_MainView.findViewById(R.id.pFI_RLayout_Bottom2_RLayout),
													(ViewFlipper)Fragment_MainView.findViewById(R.id.pFI_RLayout_ViewContent_ViewFlipper));
		//關閉Queue按鈕
		this.VIEW_LISTNER.SET_Close_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Close_Button),
													(RelativeLayout)Fragment_MainView.findViewById(R.id.pFI_RLayout_TITLE2_1_RLayout),
													(RelativeLayout)Fragment_MainView.findViewById(R.id.pFI_RLayout_Bottom2_RLayout),
													(ViewFlipper)Fragment_MainView.findViewById(R.id.pFI_RLayout_ViewContent_ViewFlipper));
		//PlayMode、TimeSeek 控制bar 開關
		this.VIEW_LISTNER.ShowTITLE4_IButton_LISTNER((ImageButton)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_ShowTITLE4_IButton),
														(RelativeLayout)Fragment_MainView.findViewById(R.id.pFI_RLayout_TITLE4_RLayout));
		this.VIEW_LISTNER.Sound_SeekBarLISTNER((SeekBar)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Sound_SeekBar),
												(ImageButton)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Sound_IButton));
		//Queue
		this.VIEW_LISTNER.SET_QUEUE_ListView_Listner(fi_Queue_ListView);	
		//清除按鈕
		this.VIEW_LISTNER.Clear_Button_LISTNER((Button)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Clear_Button),
												(ImageView)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_ButtonsBG_ImageView));
		//儲存按鈕
		this.VIEW_LISTNER.Save_Button_LISTNER((Button)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Save_Button),
												(ImageView)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_ButtonsBG_ImageView));
		//完成按鈕
		this.VIEW_LISTNER.Done_Button_LISTNER((Button)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Done_Button),
												(Button)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Clear_Button),
												(Button)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Save_Button),
												this);
		//設定按鈕
		this.VIEW_LISTNER.Setting_IButton_LISTNER((ImageButton)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Setting_IButton));
		this.VIEW_LISTNER.Setting_IButton_LISTNER((ImageButton)Fragment_MainView.findViewById(R.id.pFI_RLayout_RLayout_Setting2_IButton));
	}
	private void PAD_findView() {		
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FI_RLayout_UP_RLayout));
		//==========UP Info================
		fi_Info_ViewFlipper = (FI_ViewFlipper)Fragment_MainView.findViewById(R.id.FI_RLayout_RLayout_INFOR_ViewFlipper);
		fi_Info_ViewFlipper.setFI_PointLiLayout((FI_PointLiLayout)Fragment_MainView.findViewById(R.id.FI_RLayout_RLayout_Point_LLayout));
		//==========UP================
		//==========down Queue================
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FI_RLayout_DOWN_RLayout));
		fi_Queue_ListView = (FI_ListView)Fragment_MainView.findViewById(R.id.FI_RLayout_RLayout_QUEUE_ListView);
		//==========down================
		mlog.info(TAG, "findView OK");
	}	
	private void PAD_findViewListner() {
		VIEW_LISTNER.SET_QUEUE_ListView_Listner(fi_Queue_ListView);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.v(TAG, "onActivityCreated");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.v(TAG, "onStart");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.v(TAG, "onResume");
		
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.v(TAG, "onPause");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.v(TAG, "onStop");
	}
	
	@Override
	public void onDestroyView() {		
		super.onDestroyView();		
		Log.v(TAG, "onDestroyView");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();	
		
		Log.v(TAG, "onDestroy");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		////////解決 Google SDK ViewFlipper API 問題////////////////
		View ViewContent_ViewFlipper = this.Fragment_MainView.findViewById(R.id.pFI_RLayout_ViewContent_ViewFlipper);
		if(ViewContent_ViewFlipper!=null){
			((FAM_ViewFlipper)ViewContent_ViewFlipper).onDetachedFromWindow_P();
		}
		if(fi_Info_ViewFlipper!=null){
			fi_Info_ViewFlipper.onDetachedFromWindow_P();
		}
		//////////////////////////////////////////////////////////
		Log.v(TAG, "onDetach");
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	//PAD 判斷拖移
	public void GET_TRANSFROM_FROM_FM(MotionEvent FM_Event){	
		
		if(fi_Queue_ListView!=null&&fi_Info_ViewFlipper!=null){	
			int[] Queue_location = new int[2];
			fi_Queue_ListView.getLocationOnScreen(Queue_location);
			int[] Info_location = new int[2];
			fi_Info_ViewFlipper.getLocationOnScreen(Info_location);
			switch(FM_Event.getAction()){			
			case MotionEvent.ACTION_MOVE:
				if(FM_Event.getRawX()>=Queue_location[0]&&FM_Event.getRawX()<=Queue_location[0]+fi_Queue_ListView.getWidth()&&FM_Event.getRawY()>=Queue_location[1]&&FM_Event.getRawY()<=Queue_location[1]+fi_Queue_ListView.getHeight()){
					int position = fi_Queue_ListView.pointToPosition((int)(FM_Event.getRawX()-Queue_location[0]), (int)(FM_Event.getRawY()-Queue_location[1]));
					if(position!=AdapterView.INVALID_POSITION){
						((FI_Queqe_ListView_BaseAdapter_PAD)fi_Queue_ListView.getAdapter()).SET_INSERT_POSITION(position);
					}
				}else{
					((FI_Queqe_ListView_BaseAdapter_PAD)fi_Queue_ListView.getAdapter()).SET_INSERT_POSITION(-1);
				}
				break;
			case MotionEvent.ACTION_UP:
				//拖移到fi_Info_ViewFlipper
				if(FM_Event.getRawX()>=Info_location[0]&&FM_Event.getRawX()<=Info_location[0]+fi_Info_ViewFlipper.getWidth()&&FM_Event.getRawY()>=Info_location[1]&&FM_Event.getRawY()<=Info_location[1]+fi_Info_ViewFlipper.getHeight()){
					
				}
				((FI_Queqe_ListView_BaseAdapter_PAD)fi_Queue_ListView.getAdapter()).SET_INSERT_POSITION(-1);
				break;
			}
		}		
	}
	//編輯模式
	public boolean SET_FI_ListView_Edite(boolean isEdit){
		if(!isEdit){
			if(DeviceProperty.isPhone()){
				((FI_Queqe_ListView_BaseAdapter_Phone)this.fi_Queue_ListView.getAdapter()).SET_Edite(false);
			}else{
				((FI_Queqe_ListView_BaseAdapter_PAD)this.fi_Queue_ListView.getAdapter()).SET_Edite(false);
			}
		}else{
			if(DeviceProperty.isPhone()){
				((FI_Queqe_ListView_BaseAdapter_Phone)this.fi_Queue_ListView.getAdapter()).SET_Edite(true);
			}else{
				((FI_Queqe_ListView_BaseAdapter_PAD)this.fi_Queue_ListView.getAdapter()).SET_Edite(true);
			}
		}
		if(DeviceProperty.isPhone()){
			return ((FI_Queqe_ListView_BaseAdapter_Phone)this.fi_Queue_ListView.getAdapter()).GET_Edite();
		}else{
			return ((FI_Queqe_ListView_BaseAdapter_PAD)this.fi_Queue_ListView.getAdapter()).GET_Edite();
		}
		
	}
	public List<TrackDO> GetQueueItems(){
		List<TrackDO> QueueItems = new ArrayList<TrackDO>();
		List<TrackDO> list = fi_Queue_ListView.GetQueue();
		if(list!=null){
			for(TrackDO item:list){
				QueueItems.add(item);
			}
		}		
		return QueueItems;
		
	}
}
