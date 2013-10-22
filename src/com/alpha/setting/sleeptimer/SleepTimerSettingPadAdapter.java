package com.alpha.setting.sleeptimer;

import java.util.ArrayList;
import java.util.List;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.model.action.ActionArgumentValue;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.ServiceType;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alpha.fragments.settings.SleepTimerSettingFragement;
import com.alpha.upnp.AGSActionCallback;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.value.SystemServiceValues;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

// FSS_SleepTimer_ListView_BaseAdapter_PAD
public class SleepTimerSettingPadAdapter extends BaseAdapter {
	
	//private static Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "SleepTimerSettingPadAdapter";
		
	private List<String> listData = new ArrayList<String>();
	private int chooseItem = -1;
	
	private DeviceDisplay chooseDeviceDisplay;
	public SleepTimerSettingPadAdapter(Handler handlerMessager, DeviceDisplay chooseDeviceDisplay){
		
		this.chooseDeviceDisplay = chooseDeviceDisplay;
		this.handlerToast = handlerMessager;		
		this.mlog.switchLog = true;	
		
		createOptionList();
		
		updateSelection();
		
	}
	
	//public static final int SHOW_MESSAGE = 0;
	private static Handler handlerToast; /* = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			if(msg.what == AGSHandlerMessages.SHOW_MESSAGE){
				Toast t = Toast.makeText(context, (String)msg.obj, Toast.LENGTH_SHORT);
				t.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
				t.show();
			}
			
			super.handleMessage(msg);
		}
		
	};*/
	
//	private void showMessage(String text){
//		
//		Message msg = handlerToast.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
//		msg.obj = text;
//		handlerToast.sendMessage(msg);
//		
//	}
	
	@SuppressWarnings("rawtypes")
	private void updateSelection() {
		
		Device mediaRender = chooseDeviceDisplay.getDevice();
		if(mediaRender != null){
			
			ServiceType typeSystemService = new ServiceType(SystemServiceValues.DEFAULT_NAMESPACE, SystemServiceValues.SERVICE_NAME);
			Service serviceSystem = mediaRender.findService(typeSystemService);
			if(serviceSystem != null){
				
				Action actionIdentifySpeaker = serviceSystem.getAction(SystemServiceValues.ACTION_SLEEP_TIMER_GET);
				if(actionIdentifySpeaker != null){
					
					@SuppressWarnings("unchecked")
					ActionInvocation invocationIdentifySpeaker = new ActionInvocation(actionIdentifySpeaker , null);
					AGSActionCallback callbackIdentifySpeaker = new AGSActionCallback(invocationIdentifySpeaker, tag, handlerToast){

						@Override
						public void success(ActionInvocation ai) {
							
							ActionArgumentValue output = ai.getOutput(SystemServiceValues.DEFAULT_OUTPUT_RESULT);
							String result = (String)output.getValue();
							Log.d(tag, result);
														
							setChooseItem(SystemServiceValues.getSleepTimerOptions(result));
							
						}
						
					};
					
					AndroidUpnpService upnpServer = MainFragmentActivity.getServiceAndroidUPnP(); // ((MainFragmentActivity)context).getUPnPService();
					upnpServer.getControlPoint().execute(callbackIdentifySpeaker);
					
				}else{
					Log.d(tag, "getsleeptimer action is null");
				}
				
			}else{
				Log.d(tag, "system service is null");
			}
			
		}else{
			Log.d(tag, "mediarender device is null");
		}
	
	}
	
	private void createOptionList(){
		listData.add("Off");
		listData.add("15 Minutes");
		listData.add("30 Minutes");
		listData.add("45 Minutes");
		listData.add("1 Hour");
		listData.add("2 Hour");
		listData.add("3 Hour");	
	}
	public void setChooseItem(int chooseItem){
		this.chooseItem = chooseItem;
		this.notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {	
		ViewHandler viewHandler =null;
		if(convertView==null){
			convertView = LayoutInflater.from(SleepTimerSettingFragement.context).inflate(R.layout.fss_sleeptimer_listview_cell_pad, null);
			convertView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			viewHandler = new ViewHandler(convertView);
			basicSetChildView(viewHandler);	
			convertView.setTag(viewHandler);
		}else{
			viewHandler = (ViewHandler)convertView.getTag();
		}
		viewHandler.SET_Position(position);
		//底圖
		if(position==0&&!(position==(this.getCount()-1))){
			//第一個
			TKBTool.fitsViewHeight(70, viewHandler.CCell_RLayout);
			new TKBThreadReadStateListInAssets(SleepTimerSettingFragement.context, "pad/Settings/alarm_box_01_f.png","pad/Settings/alarm_box_01_n.png", viewHandler.CCell_RLayout, 3);
		}else if(position==0&&(position==(this.getCount()-1))){
			//只有一個
			TKBTool.fitsViewHeight(62, viewHandler.CCell_RLayout);
			new TKBThreadReadStateListInAssets(SleepTimerSettingFragement.context, "pad/Settings/alarm_box_02_f.png","pad/Settings/alarm_box_02_n.png", viewHandler.CCell_RLayout, 3);
		}else if((position==(this.getCount()-1))){
			//最後一個
			TKBTool.fitsViewHeight(73, viewHandler.CCell_RLayout);
			new TKBThreadReadStateListInAssets(SleepTimerSettingFragement.context, "pad/Settings/alarm_box_03_f.png","pad/Settings/alarm_box_03_n.png", viewHandler.CCell_RLayout, 3);
		}else{
			//其他
			TKBTool.fitsViewHeight(71, viewHandler.CCell_RLayout);
			new TKBThreadReadStateListInAssets(SleepTimerSettingFragement.context, "pad/Settings/alarm_box_02_f.png","pad/Settings/alarm_box_02_n.png", viewHandler.CCell_RLayout, 3);
		}
		
		if(position==chooseItem){
			if(viewHandler.Tick_ImageView.getVisibility()!=View.VISIBLE){
				viewHandler.Tick_ImageView.setVisibility(View.VISIBLE);
			}
		}else{
			if(viewHandler.Tick_ImageView.getVisibility()==View.VISIBLE){
				viewHandler.Tick_ImageView.setVisibility(View.INVISIBLE);
			}
		}		
		
		viewHandler.Time_TextView.setText(listData.get(position));
		mlog.info(tag, "position = "+position);
		return convertView;
	}
	
	private class ViewHandler{
		private int position;
		
		private RelativeLayout CCell_RLayout;			
		private TextView Time_TextView;
		private ImageView Tick_ImageView;
		
		public ViewHandler(View view){
			this.CCell_RLayout = (RelativeLayout)view.findViewById(R.id.FSS_SleepTimer_ListView_Cell_RLayout);
			this.Time_TextView = (TextView)view.findViewById(R.id.FSS_SleepTimer_ListView_Cell_RLayout_Time_TextView);
			this.Tick_ImageView = (ImageView)view.findViewById(R.id.FSS_SleepTimer_ListView_Cell_RLayout_Tick_ImageView);
		}
		public void SET_Position(int position){
			this.position = position;			
		}
	}
	private void basicSetChildView(ViewHandler viewHandler){
		//Time_TextView		
		TKBTool.fitsViewTextSize(8, viewHandler.Time_TextView);
		TKBTool.fitsViewLeftMargin(10, viewHandler.Time_TextView);
		//Tick_ImageView		
		TKBTool.fitsViewHeight(50, viewHandler.Tick_ImageView);
		TKBTool.fitsViewWidth(50, viewHandler.Tick_ImageView);
		TKBTool.fitsViewRightMargin(10, viewHandler.Tick_ImageView);
		new TKBThreadReadStateListInAssets(SleepTimerSettingFragement.context, "pad/Settings/pick_f.png","pad/Settings/pick_n.png", viewHandler.Tick_ImageView, 1);
	}

}
