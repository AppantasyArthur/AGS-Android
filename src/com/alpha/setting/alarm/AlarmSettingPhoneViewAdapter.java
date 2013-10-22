package com.alpha.setting.alarm;

import java.util.ArrayList;
import java.util.List;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.model.action.ActionArgumentValue;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.ServiceType;

import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.parser.AlarmListParser;
import com.alpha.upnp.value.AlarmServiceValues;
import com.alpha.upnp.value.ServiceValues;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
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

// FSI_IdSpeaker_ListView_BaseAdapter_PAD
public class AlarmSettingPhoneViewAdapter extends BaseAdapter {
	
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "AlarmSettingPhoneViewAdapter";
		
	private TextView textNoAlarmSign;
	private List<AlarmItemContent> dataList = new ArrayList<AlarmItemContent>();
	
	private boolean isEdit = false;
	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		
		if(this.isEdit != isEdit){
			notifyDataSetChanged();
		}
		
		this.isEdit = isEdit;
		if(isEdit){
			
			// AlarmSettingListViewPadView
			//ListView list = (ListView)fragementMainView.findViewById(R.id.AlarmSettingListViewPadView);
			//list.
			
		}
		
	}
	
	private DeviceDisplay chooseDeviceDisplay;
	public AlarmSettingPhoneViewAdapter(Context context, TextView alarm_TextView, DeviceDisplay chooseDeviceDisplay){
		
		this.context = context;		
		this.mlog.switchLog = true;	
		this.textNoAlarmSign = alarm_TextView;
		
		//CreateDataList();
		
		this.chooseDeviceDisplay = chooseDeviceDisplay;
		
		updateAlarmProfile();
		
	}
	
	@SuppressWarnings("rawtypes")
	private void updateAlarmProfile() {
		
		AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
		DeviceDisplay mediaRenderDisplay = chooseDeviceDisplay;
		
		Device mediaRenderer = mediaRenderDisplay.getDevice();
		//mediaRenderer.
		
		String alarmserviceNamespace = ServiceValues.DEFAULT_NAMESPACE;
		String alarmserviceType = AlarmServiceValues.SERVICE_NAME;
		ServiceType typeAlarmService = new ServiceType(alarmserviceNamespace, alarmserviceType);
		
		Service serviceAlarm = mediaRenderer.findService(typeAlarmService);
		if(serviceAlarm != null){
			
			Action actionGetAlarmProfileList = serviceAlarm.getAction(AlarmServiceValues.ACTION_GET_ALARM_PROFILE_LIST);
			if(actionGetAlarmProfileList != null){
			
				@SuppressWarnings("unchecked")
				ActionInvocation invokeGetAlarmProfileList = new ActionInvocation(actionGetAlarmProfileList , null);
				ActionCallback callbackGetAlarmProfileList = new ActionCallback(invokeGetAlarmProfileList){

					@Override
					public void failure(ActionInvocation ai, UpnpResponse ur, String str) {
						// error log
						// show message
						Log.d(tag, "callbackSystemInfo failure - ");
						Log.d(tag, "UPnPResponse : " + ur + ", other messgae : " + str);
					}

					@Override
					public void success(ActionInvocation ai) {
						
						// update done
						ActionArgumentValue output = ai.getOutput(AlarmServiceValues.ACTION_GET_ALARM_PROFILE_LIST_OUTPUT);
						String listProfile = (String) output.getValue();
						Log.d(tag, "AlarmProfileList : " + listProfile);
						
						// AlarmItemContent
						// dataList
						AlarmListParser parser = new AlarmListParser();
						final ArrayList<AlarmItemContent> items = parser.parse(listProfile);
						Thread thread = new Thread(){

							@Override
							public void run() {
								
								Message msg = handlerUpdateListView.obtainMessage(UPDATE_LIST_VIEW, items);
								handlerUpdateListView.sendMessage(msg);
								super.run();
								
							}
							
						};
						thread.start();
						
						//Iterator<AlarmItemContent> itr = items.iterator();
						//while(itr.hasNext()){
							
						//	AlarmItemContent item = itr.next();
						//	dataList.add(item);
						//	Log.d(tag, item.toString());
							
						//}
						
						//setDataList(items);
						
//						SystemInfoParser parser = new SystemInfoParser();
//						SystemInfoVO vo = parser.parse(infoSystem);
//						
//						if(vo != null){
//							
//							dataList.clear();
//							//dataList.add(new AboutItemContent(SystemInfoVO.FIRMWARE_VERSION, vo.getFirmwareVersion()));
//							//dataList.add(new AboutItemContent(SystemInfoVO.SERIAL_NUMBER, vo.getSerialNumber()));
//							
//						}else{
//							Log.d(tag, "parsing error : SystemInfoVO is null");
//						}
						
					}
					
				};
				upnpServer.getControlPoint().execute(callbackGetAlarmProfileList);
				
			}else{
				// error log
				// show message
			}
			
		}else{
			// error log
			// show message
		}
		
	}
	
	public static final int UPDATE_LIST_VIEW = 0;
	public Handler handlerUpdateListView = new Handler(){

		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {
			
			if(msg.what == UPDATE_LIST_VIEW){
				
				ArrayList<AlarmItemContent> items = (ArrayList<AlarmItemContent>)msg.obj;
				setDataList(items);
				
			}
			
			super.handleMessage(msg);
			
		}
		
	};
	
	private void CreateDataList(){
		//dataList.add(new AlarmItemContent("aaaa", "bbbb","cccc","0","dddd"));
		//dataList.add(new AlarmItemContent("aaaa", "bbbb","cccc","0","dddd"));
		//dataList.add(new AlarmItemContent("aaaa", "bbbb","cccc","0","dddd"));
		//dataList.add(new AlarmItemContent("aaaa", "bbbb","cccc","0","dddd"));
	}
	
	public void setDataList(List<AlarmItemContent> dataList){
		this.dataList = dataList;
		this.notifyDataSetChanged();
	}
	
//	public void SetEdit(boolean isEdit){
//		if(this.isEdit!=isEdit){
//			this.isEdit = isEdit;
//			this.notifyDataSetChanged();
//		}
//		
//		if(isEdit){
//			
//		}
//		
//	}
//	public boolean GetEdit(){
//		return this.isEdit;
//	}
	
	@Override
	public int getCount() {
		int count = dataList.size();
		if(count==0){
			if(textNoAlarmSign.getVisibility()!=View.VISIBLE){
				textNoAlarmSign.setVisibility(View.VISIBLE);
			}			
		}else{
			if(textNoAlarmSign.getVisibility()==View.VISIBLE){
				textNoAlarmSign.setVisibility(View.INVISIBLE);
			}			
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {	
		ViewHandler viewHandler =null;		
		if(convertView == null){	
			
			convertView = LayoutInflater.from(context).inflate(R.layout.fsal_alarm_listview_cell_pad, null);
			convertView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			
			viewHandler = new ViewHandler(convertView);
			basicSetChildView(viewHandler);	
			convertView.setTag(viewHandler);
			
		}else{
			viewHandler = (ViewHandler)convertView.getTag();
		}
		
		viewHandler.SET_Position(position);
		if(position==0&&!(position==(this.getCount()-1))){
			//第一個
			TKBTool.fitsViewHeight(30, viewHandler.CCell_RLayout);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_01_f.png","pad/Settings/alarm_box_01_n.png", viewHandler.CCell_RLayout, 3);
			
		}else if(position==0&&(position==(this.getCount()-1))){
			//只有一個
			TKBTool.fitsViewHeight(34, viewHandler.CCell_RLayout);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_02_f.png","pad/Settings/alarm_box_02_n.png", viewHandler.CCell_RLayout, 3);
		}else if((position==(this.getCount()-1))){
			//最後一個
			TKBTool.fitsViewHeight(33, viewHandler.CCell_RLayout);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_03_f.png","pad/Settings/alarm_box_03_n.png", viewHandler.CCell_RLayout, 3);
		}else{
			//其他
			TKBTool.fitsViewHeight(31, viewHandler.CCell_RLayout);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_02_f.png","pad/Settings/alarm_box_02_n.png", viewHandler.CCell_RLayout, 3);
		}
		
		if(this.isEdit){
			if(viewHandler.Delete_ImageView.getVisibility()!=View.VISIBLE){
				viewHandler.Delete_ImageView.setVisibility(View.VISIBLE);
			}			
		}else{
			if(viewHandler.Delete_ImageView.getVisibility()==View.VISIBLE){
				viewHandler.Delete_ImageView.setVisibility(View.GONE);
			}	
		}
		
		viewHandler.Time_TextView.setText(dataList.get(position).getHour()+":"+dataList.get(position).getMinute()+" "+dataList.get(position).getAMPM());
		viewHandler.Frequency_TextView.setText(""+dataList.get(position).getFreaquency());
		viewHandler.AlarmName_TextView.setText(dataList.get(position).getNameAlarm());
		
		Log.i(tag, "Not position = "+position);
		
		return convertView;
		
	}
	
	private class ViewHandler{
		private int position;
		
		private RelativeLayout CCell_RLayout;			
		private TextView Time_TextView;
		private TextView Frequency_TextView;
		private ImageView Delete_ImageView;
		private TextView AlarmName_TextView;	
		private ImageView Arrow_ImageView;
		
		public ViewHandler(View view){
			this.CCell_RLayout = (RelativeLayout)view.findViewById(R.id.FSAl_Alarm_ListView_Cell_RLayout);
			this.Time_TextView = (TextView)view.findViewById(R.id.FSAl_Alarm_ListView_Cell_RLayout_Time_TextView);
			this.Frequency_TextView = (TextView)view.findViewById(R.id.FSAl_Alarm_ListView_Cell_RLayout_Frequency_TextView);
			this.Delete_ImageView = (ImageView)view.findViewById(R.id.FSAl_Alarm_ListView_Cell_RLayout_Delete_ImageView);
			SetDelete_ImageView_Listner();
			this.AlarmName_TextView = (TextView)view.findViewById(R.id.FSAl_Alarm_ListView_Cell_RLayout_AlarmName_TextView);
			this.Arrow_ImageView = (ImageView)view.findViewById(R.id.FSAl_Alarm_ListView_Cell_RLayout_Arrow_ImageView);
		}
		private void SetDelete_ImageView_Listner(){
			this.Delete_ImageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					mlog.info(tag, "Delete position = "+position);
				}
			});
		}
		public void SET_Position(int position){
			this.position = position;			
		}
	}
	private void basicSetChildView(ViewHandler viewHandler){		
		//Delete_ImageView
		TKBTool.fitsViewHeight(20, viewHandler.Delete_ImageView);
		TKBTool.fitsViewWidth(25, viewHandler.Delete_ImageView);
		TKBTool.fitsViewLeftMargin(10, viewHandler.Delete_ImageView);
		new TKBThreadReadBitMapInAssets(context, "phone/queue/delete.png", viewHandler.Delete_ImageView, 1);
		//Time_TextView		
		TKBTool.fitsViewHeight(18, viewHandler.Time_TextView);
		TKBTool.fitsViewTextSize(10, viewHandler.Time_TextView);
		TKBTool.fitsViewLeftMargin(10, viewHandler.Time_TextView);
		//Frequency_TextView		
		TKBTool.fitsViewHeight(12, viewHandler.Frequency_TextView);
		TKBTool.fitsViewTextSize(8, viewHandler.Frequency_TextView);
		TKBTool.fitsViewLeftMargin(10, viewHandler.Frequency_TextView);
		//AlarmName_TextView		
		TKBTool.fitsViewHeight(18, viewHandler.AlarmName_TextView);
		TKBTool.fitsViewTextSize(10, viewHandler.AlarmName_TextView);
		TKBTool.fitsViewRightMargin(10, viewHandler.AlarmName_TextView);
		//Arrow_ImageView
		TKBTool.fitsViewHeight(9, viewHandler.Arrow_ImageView);
		viewHandler.Arrow_ImageView.getLayoutParams().width = TKBTool.getHeight(6);
		TKBTool.fitsViewRightMargin(10, viewHandler.Arrow_ImageView);			
		new TKBThreadReadStateListInAssets(context, "phone/playlist/down_f.png", "phone/playlist/down_n.png", viewHandler.Arrow_ImageView, 1);
	}

}
