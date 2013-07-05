package com.alpha.fragments;

import com.FI.SETTING.FI_ListView;
import com.FI.SETTING.FI_PointLiLayout;
import com.FI.SETTING.FI_Queqe_ListView_BaseAdapter;
import com.FI.SETTING.FI_VIEW_LISTNER;
import com.FI.SETTING.FI_VIEW_SETTING;
import com.FI.SETTING.FI_ViewFlipper;
import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
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

public class Fragment_Information extends Fragment {
	//VIEWS
	private View Fragment_MainView;	
	private FI_ListView fi_Queue_ListView;
	private FI_ViewFlipper fi_Info_ViewFlipper;
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	//SETTING
	private FI_VIEW_SETTING VIEW_SETTING;
	private FI_VIEW_LISTNER VIEW_LISTNER;
	private static String TAG = "Fragment_Information";
	private MLog mlog = new MLog();
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
		this.mlog.LogSwitch = true;		
		device_size = ((FragmentActivity_Main)context).getDevice_Size();
		fragmentManager = ((FragmentActivity_Main)context).getSupportFragmentManager();
		//¨ú±oView_SETTING
        this.VIEW_SETTING = new FI_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FI_VIEW_LISTNER(this.context,this.device_size);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_information_pad, null);
		Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		findView();
		findViewListner();
		return Fragment_MainView;
	}

	private void findView() {
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FI_RLayout_UP_RLayout));
		fi_Info_ViewFlipper = (FI_ViewFlipper)Fragment_MainView.findViewById(R.id.FI_RLayout_RLayout_INFOR_ViewFlipper);
		fi_Info_ViewFlipper.setFI_PointLiLayout((FI_PointLiLayout)Fragment_MainView.findViewById(R.id.FI_RLayout_RLayout_Point_LLayout));
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FI_RLayout_DOWN_RLayout));
		fi_Queue_ListView = (FI_ListView)Fragment_MainView.findViewById(R.id.FI_RLayout_RLayout_QUEUE_ListView);
		mlog.info(TAG, "findView OK");
	}	
	private void findViewListner() {
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
		Log.v(TAG, "onDetach");
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
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
						((FI_Queqe_ListView_BaseAdapter)fi_Queue_ListView.getAdapter()).SET_INSERT_POSITION(position);
					}
				}else{
					((FI_Queqe_ListView_BaseAdapter)fi_Queue_ListView.getAdapter()).SET_INSERT_POSITION(-1);
				}
				break;
			case MotionEvent.ACTION_UP:
				//©ì²¾¨ìfi_Info_ViewFlipper
				if(FM_Event.getRawX()>=Info_location[0]&&FM_Event.getRawX()<=Info_location[0]+fi_Info_ViewFlipper.getWidth()&&FM_Event.getRawY()>=Info_location[1]&&FM_Event.getRawY()<=Info_location[1]+fi_Info_ViewFlipper.getHeight()){
					
				}
				((FI_Queqe_ListView_BaseAdapter)fi_Queue_ListView.getAdapter()).SET_INSERT_POSITION(-1);
				break;
			}
		}		
	}
	public boolean SET_FI_ListView_Edite(boolean isEdit){
		if(!isEdit){
			((FI_Queqe_ListView_BaseAdapter)this.fi_Queue_ListView.getAdapter()).SET_Edite(false);
		}else{
			((FI_Queqe_ListView_BaseAdapter)this.fi_Queue_ListView.getAdapter()).SET_Edite(true);
		}
		return ((FI_Queqe_ListView_BaseAdapter)this.fi_Queue_ListView.getAdapter()).GET_Edite();
	}
}
