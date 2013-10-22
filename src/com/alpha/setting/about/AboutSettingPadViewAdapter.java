package com.alpha.setting.about;

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

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.parser.SystemInfoParser;
import com.alpha.upnp.parser.SystemInfoVO;
import com.alpha.upnp.value.ServiceValues;
import com.alpha.upnp.value.SystemServiceValues;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;

public class AboutSettingPadViewAdapter extends BaseAdapter {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = AboutSettingPadViewAdapter.class.toString();
		
	private List<AboutItemContent> dataList = new ArrayList<AboutItemContent>();
	
	private Drawable menu1;
	private Drawable menu2;
	private Drawable menu3;
	private Drawable menu4;
	
	private DeviceDisplay chooseDeviceDisplay;
	
	public AboutSettingPadViewAdapter(Context context, DeviceDisplay chooseDeviceDisplay){
		
		this.context = context;		
		this.mlog.switchLog = true;
		LoadBitmap();
		CreateDataList();
		
		this.chooseDeviceDisplay = chooseDeviceDisplay;
		updateAboutInfo();
		
	}
	
	@SuppressWarnings("rawtypes")
	private void updateAboutInfo() {
		
		AndroidUpnpService upnpServer = MainFragmentActivity.getServiceAndroidUPnP(); //((MainFragmentActivity)context).getUPnPService();
		DeviceDisplay mediaRenderDisplay = chooseDeviceDisplay;
		
		Device mediaRenderer = mediaRenderDisplay.getDevice();
		//mediaRenderer.
		
		String systemserviceNamespace = ServiceValues.DEFAULT_NAMESPACE;
		String systemserviceType = SystemServiceValues.SERVICE_NAME;
		ServiceType typeSystemService = new ServiceType(systemserviceNamespace, systemserviceType);
		
		Service serviceSystem = mediaRenderer.findService(typeSystemService);
		if(serviceSystem != null){
			
			Action actionSystemInfo = serviceSystem.getAction(SystemServiceValues.ACTION_SYSTEM_INFO);
			if(actionSystemInfo != null){
			
				@SuppressWarnings("unchecked")
				ActionInvocation invocationSystemInfo = new ActionInvocation(actionSystemInfo , null);
				ActionCallback callbackSystemInfo = new ActionCallback(invocationSystemInfo){

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
						ActionArgumentValue outputSystemInfo = ai.getOutput(SystemServiceValues.ACTION_SYSTEM_INFO_OUTPUT);
						String infoSystem = (String) outputSystemInfo.getValue();
						Log.d(tag, "SystemInfo" + infoSystem);
						
						SystemInfoParser parser = new SystemInfoParser();
						SystemInfoVO vo = parser.parse(infoSystem);
						
						if(vo != null){
							
							dataList.clear();
							dataList.add(new AboutItemContent(SystemInfoVO.FIRMWARE_VERSION, vo.getFirmwareVersion()));
							dataList.add(new AboutItemContent(SystemInfoVO.SERIAL_NUMBER, vo.getSerialNumber()));
							
						}else{
							Log.d(tag, "parsing error : SystemInfoVO is null");
						}
						
					}
					
				};
				
//				AboutSettingSystemInfo updateerSystemInfo = new AboutSettingSystemInfo(){
//
//					@Override
//					public void onSystmeInfoChanged(String xmlSystemInfo) {
//						
//						Log.d(tag, "onSystmeInfoChanged : " + xmlSystemInfo);
//						
//						// parsing data
//						
//						// assign data
//						
//					}
//					
//				};
//				((MainFragmentActivity)context).getDeviceDisplayList().setSystemInfoChangedListener(updateerSystemInfo);
				upnpServer.getControlPoint().execute(callbackSystemInfo);
				
			}else{
				// error log
				// show message
			}
			
		}else{
			// error log
			// show message
		}
		
	}
	
	private void CreateDataList(){
		//dataList.add(new AboutItemContent("aaaa", "bbbb"));
		//dataList.add(new AboutItemContent("aaaa", "bbbb"));
		//dataList.add(new AboutItemContent("aaaa", "bbbb"));
		//dataList.add(new AboutItemContent("aaaa", "bbbb"));
	}
	private void LoadBitmap(){		
		this.menu1 = new BitmapDrawable(context.getResources(),TKBTool.readBitMapInAssets(context, "pad/Settings/identify_01_box.png"));
		this.menu2 = new BitmapDrawable(context.getResources(),TKBTool.readBitMapInAssets(context, "pad/Settings/identify_02_box.png"));
		this.menu3 = new BitmapDrawable(context.getResources(),TKBTool.readBitMapInAssets(context, "pad/Settings/identify_03_box.png"));
		this.menu4 = new BitmapDrawable(context.getResources(),TKBTool.readBitMapInAssets(context, "pad/Settings/Settings_box.png"));
	}
	public void SetDataList(List<AboutItemContent> dataList){
		this.dataList = dataList;
		this.notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		return dataList.size();
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
			convertView = LayoutInflater.from(context).inflate(R.layout.fsa_about_listview_cell_pad, null);
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
			TKBTool.fitsViewHeight(70, viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu1);
		}else if(position==0&&(position==(this.getCount()-1))){
			//只有一個
			TKBTool.fitsViewHeight(62, viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu4);
		}else if((position==(this.getCount()-1))){
			//最後一個
			TKBTool.fitsViewHeight(73, viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu3);
		}else{
			//其他
			TKBTool.fitsViewHeight(71, viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu2);
		}
		
		viewHandler.LeftText_TextView.setText(dataList.get(position).getLeftString());
		viewHandler.RightText_TextView.setText(dataList.get(position).getRightString());
		
		mlog.info(tag, "position = " + position);
		return convertView;
	}
	private class ViewHandler{
		private int position;
		
		private RelativeLayout CCell_RLayout;			
		private TextView LeftText_TextView;
		private TextView RightText_TextView;
		
		public ViewHandler(View view){
			this.CCell_RLayout = (RelativeLayout)view.findViewById(R.id.FSA_About_ListView_Cell_RLayout);
			this.LeftText_TextView = (TextView)view.findViewById(R.id.FSA_About_ListView_Cell_RLayout_LeftText_TextView);
			this.RightText_TextView = (TextView)view.findViewById(R.id.FSA_About_ListView_Cell_RLayout_RightText_TextView);
		}
		public void SET_Position(int position){
			this.position = position;			
		}
	}
	private void basicSetChildView(ViewHandler viewHandler){
		//RightText_TextView		
		TKBTool.fitsViewTextSize(8, viewHandler.LeftText_TextView);
		TKBTool.fitsViewLeftMargin(10, viewHandler.LeftText_TextView);
		//RightText_TextView		
		TKBTool.fitsViewTextSize(8, viewHandler.RightText_TextView);
		TKBTool.fitsViewRightMargin(10, viewHandler.RightText_TextView);
	}

}
