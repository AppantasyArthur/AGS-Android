package com.alpha.setting.alarm;

import java.util.ArrayList;
import java.util.Iterator;
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
import org.teleal.cling.model.types.ServiceType;

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

// FSI_IdSpeaker_ListView_BaseAdapter
public class AlarmSettingPadViewAdapter extends BaseAdapter {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "AlarmSettingPadViewAdapter";
	
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
	
	private LayoutInflater mInflater;
	private View fragementMainView;
	private TextView textNoAlarmSign;
	private List<AlarmItemContent> dataList = new ArrayList<AlarmItemContent>();	
	private DeviceDisplay chooseDeviceDisplay;
	public AlarmSettingPadViewAdapter(Context context, View fragementMainView, DeviceDisplay chooseDeviceDisplay){
		
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;		
		this.mlog.switchLog = true;
		this.fragementMainView = fragementMainView;
		this.textNoAlarmSign = (TextView)fragementMainView.findViewById(R.id.AlarmSettingNoAlarmSign);
		this.chooseDeviceDisplay = chooseDeviceDisplay;
		
		updateAlarmProfile();
		
		//fakeDataList();
		
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

	private void fakeDataList(){
		dataList.add(new AlarmItemContent("aaaa", "bbbb", "cccc", "0", "dddd"));
		dataList.add(new AlarmItemContent("aaaa", "bbbb", "cccc", "0", "dddd"));
		dataList.add(new AlarmItemContent("aaaa", "bbbb", "cccc", "0", "dddd"));
		dataList.add(new AlarmItemContent("aaaa", "bbbb", "cccc", "0", "dddd"));
	}
	
	public void setDataList(List<AlarmItemContent> dataList){
		
		this.dataList.clear();
		this.dataList = dataList;
		this.notifyDataSetChanged();
		
	}

	@Override
	public int getCount() {
		
		int count = dataList.size();
		if(count==0){
			if(textNoAlarmSign.getVisibility() == View.INVISIBLE){
				textNoAlarmSign.setVisibility(View.VISIBLE);
			}			
		}else{
			if(textNoAlarmSign.getVisibility() == View.VISIBLE){
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
	
//	@Override
//	public int getViewTypeCount() {
//		return dataList.size();
//	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {	// by position
		
		//convertView.getH
		//System.out.println("getView " + position + " " + convertView);
		//if()
		ViewHandler viewHandler = null; // get from convertView
		
		if(convertView == null){
			
			convertView = LayoutInflater.from(context).inflate(R.layout.fsal_alarm_listview_cell_pad, null);
			convertView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			//convertView = mInflater.inflate(R.layout.fsal_alarm_listview_cell_pad, null);
			
			viewHandler = new ViewHandler(convertView);
			setChildView(viewHandler);	
			convertView.setTag(viewHandler);
			
		}else{
			viewHandler = (ViewHandler)convertView.getTag();
		}
		
		//Log.d(tag, "view height : " + convertView.getHeight());
		
		viewHandler.setPosition(position);
		
		//底圖
		if(position == 0 
		&&!(position == (this.getCount() - 1))){
			//第一個
			TKBTool.fitsViewHeight(70, viewHandler.layoutCells);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_01_f.png","pad/Settings/alarm_box_01_n.png", viewHandler.layoutCells, 3);
		}else if(	position == 0
				&&	(position == (this.getCount() - 1))){
			//只有一個
			TKBTool.fitsViewHeight(62, viewHandler.layoutCells);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_02_f.png","pad/Settings/alarm_box_02_n.png", viewHandler.layoutCells, 3);
		}else if((position==(this.getCount()-1))){
			//最後一個
			TKBTool.fitsViewHeight(73, viewHandler.layoutCells);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_03_f.png","pad/Settings/alarm_box_03_n.png", viewHandler.layoutCells, 3);
		}else{
			//其他
			TKBTool.fitsViewHeight(71, viewHandler.layoutCells);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_02_f.png","pad/Settings/alarm_box_02_n.png", viewHandler.layoutCells, 3);
		}
		
		if(this.isEdit){
			if(viewHandler.imageDelete.getVisibility() != View.VISIBLE){
				viewHandler.imageDelete.setVisibility(View.VISIBLE);
			}			
		}else{
			if(viewHandler.imageDelete.getVisibility() == View.VISIBLE){
				viewHandler.imageDelete.setVisibility(View.GONE);
			}	
		}
		
		viewHandler.textTime.setText(dataList.get(position).getHour()
								+	":" + dataList.get(position).getMinute()
								+	" " + dataList.get(position).getAMPM());
		
		viewHandler.textFrequency.setText("" 
									+	dataList.get(position).getFreaquency());
		
		viewHandler.textAlarmName.setText(dataList.get(position).getNameAlarm());
		
		//Log.i(tag, "Not position = " + position);
		
		return convertView; // return convertview
		
	}
	
	// control a record of List, Arthur
	private class ViewHandler{
		
		private int position;
		
		private RelativeLayout layoutCells;	
		//private ListView viewAlarmProfileList;
		
		private ImageView imageDelete;
		private TextView textTime;
		private TextView textFrequency;
		private TextView textAlarmName;	
		private ImageView imageArrow;
		
		public ViewHandler(View view){
			
			this.layoutCells 	= (RelativeLayout)view.findViewById(R.id.FSAl_Alarm_ListView_Cell_RLayout);
			//this.viewAlarmProfileList = (ListView)view.findViewById(R.id.AlarmSettingListViewPadView);
			this.textTime 		= (TextView)view.findViewById(R.id.FSAl_Alarm_ListView_Cell_RLayout_Time_TextView);
			this.textFrequency 	= (TextView)view.findViewById(R.id.FSAl_Alarm_ListView_Cell_RLayout_Frequency_TextView);
			
			this.imageDelete 	= (ImageView)view.findViewById(R.id.FSAl_Alarm_ListView_Cell_RLayout_Delete_ImageView);
			setDeleteImageListener();
			
			this.textAlarmName 	= (TextView)view.findViewById(R.id.FSAl_Alarm_ListView_Cell_RLayout_AlarmName_TextView);
			this.imageArrow 	= (ImageView)view.findViewById(R.id.FSAl_Alarm_ListView_Cell_RLayout_Arrow_ImageView);
			
		}
		
		private void setDeleteImageListener(){
			
			this.imageDelete.setOnClickListener(new View.OnClickListener() {
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@Override
				public void onClick(View v) {		
					
					mlog.info(tag, "Delete position = " + position);
					AlarmItemContent item = dataList.get(position);
					
					AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
					DeviceDisplay mediaRenderDisplay = chooseDeviceDisplay;
					
					Device mediaRenderer = mediaRenderDisplay.getDevice();
					//mediaRenderer.
					
					String alarmserviceNamespace = ServiceValues.DEFAULT_NAMESPACE;
					String alarmserviceType = AlarmServiceValues.SERVICE_NAME;
					ServiceType typeAlarmService = new ServiceType(alarmserviceNamespace, alarmserviceType);
					
					Service serviceAlarm = mediaRenderer.findService(typeAlarmService);
					if(serviceAlarm != null){
						
						Action actionDeleteAlarmProfile = serviceAlarm.getAction(AlarmServiceValues.ACTION_DELETE_ALARM_PROFILE);
						if(actionDeleteAlarmProfile != null){
							
							ArrayList<ActionArgumentValue> values = new ArrayList<ActionArgumentValue>();
							
							ActionArgument argPosition = actionDeleteAlarmProfile.getInputArgument(AlarmServiceValues.ACTION_DELETE_ALARM_PROFILE_INPUT_POSITION);
							ActionArgumentValue valPosition = new ActionArgumentValue(argPosition, item.getPosition());
							values.add(valPosition);
						
							ActionInvocation invokeDeleteAlarmProfile = new ActionInvocation(actionDeleteAlarmProfile , values.toArray(new ActionArgumentValue[values.size()]));
							ActionCallback callbackDeleteAlarmProfile = new ActionCallback(invokeDeleteAlarmProfile){

								@Override
								public void failure(ActionInvocation ai, UpnpResponse ur, String str) {
									// error log
									// show message
									Log.d(tag, "callbackDeleteAlarmProfile failure - ");
									Log.d(tag, "UPnPResponse : " + ur + ", other messgae : " + str);
								}

								@Override
								public void success(ActionInvocation ai) {
									
									Log.d(tag, "callbackDeleteAlarmProfile success - ");
									Log.d(tag, "ActionInvocation : " + ai);
									
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
									
									// update done
//									ActionArgumentValue output = ai.getOutput(AlarmServiceValues.ACTION_GET_ALARM_PROFILE_LIST_OUTPUT);
//									String infoSystem = (String) output.getValue();
//									Log.d(tag, "AlarmProfileList" + infoSystem);
									
									// update list and toast
									
								}
								
							};
							upnpServer.getControlPoint().execute(callbackDeleteAlarmProfile);
							
						}else{
							// error log
							// show message
						}
						
					}else{
						// error log
						// show message
					}
					
				}
			});
			
		}
		
		public void setPosition(int position){
			this.position = position;			
		}
		
	}
	
	private void setChildView(ViewHandler viewHandler){
		
		//Delete_ImageView
		TKBTool.fitsViewHeight(40, viewHandler.imageDelete);
		TKBTool.fitsViewWidth(50, viewHandler.imageDelete);
		TKBTool.fitsViewLeftMargin(10, viewHandler.imageDelete);
		new TKBThreadReadBitMapInAssets(context, "pad/Queqe/delete.png", viewHandler.imageDelete, 1);
		
		//Time_TextView
		TKBTool.fitsViewHeight(35, viewHandler.textTime);
		TKBTool.fitsViewTextSize(8, viewHandler.textTime);
		TKBTool.fitsViewLeftMargin(10, viewHandler.textTime);
		
		//Frequency_TextView
		TKBTool.fitsViewHeight(25, viewHandler.textFrequency);
		TKBTool.fitsViewTextSize(6, viewHandler.textFrequency);
		TKBTool.fitsViewLeftMargin(10, viewHandler.textFrequency);
		
		//AlarmName_TextView
		TKBTool.fitsViewHeight(35, viewHandler.textAlarmName);
		TKBTool.fitsViewTextSize(8, viewHandler.textAlarmName);
		TKBTool.fitsViewRightMargin(10, viewHandler.textAlarmName);
		
		//Arrow_ImageView
		TKBTool.fitsViewHeight(13, viewHandler.imageArrow);
		viewHandler.imageArrow.getLayoutParams().width = TKBTool.getHeight(7);
		TKBTool.fitsViewRightMargin(10, viewHandler.imageArrow);
		new TKBThreadReadStateListInAssets(context, "pad/Playlist/playlist_arrow_f.png", "pad/Playlist/playlist_arrow_n.png", viewHandler.imageArrow, 1);
		
	}

}
