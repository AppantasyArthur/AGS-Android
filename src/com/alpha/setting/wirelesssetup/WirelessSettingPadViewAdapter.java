package com.alpha.setting.wirelesssetup;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alpha.fragments.WirelessSettingFragment;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.service.AGSActionSuccessCaller;
import com.alpha.upnp.service.AGSSytemService;
import com.alpha.upnp.value.SystemServiceValues;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

// FSI_IdSpeaker_ListView_BaseAdapter
public class WirelessSettingPadViewAdapter extends BaseAdapter {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "WirelessSettingPadViewAdapter";
	
	private List<WSSite> sites = new LinkedList<WSSite>();
	
	@SuppressWarnings("unused")
	private class WSSite{
		
		private boolean isUse = false;
		private String SSID = "N/A";
		private boolean isLocked = false;
		
		private Integer strength; // need getter/setter
		
		public boolean isUse() {
			return isUse;
		}
		public void setUse(boolean isUse) {
			this.isUse = isUse;
		}
		public String getSSID() {
			return SSID;
		}
		public void setSSID(String sSID) {
			SSID = sSID;
		}
		public boolean isLocked() {
			return isLocked;
		}
		public void setLocked(boolean isLocked) {
			this.isLocked = isLocked;
		}
		
	}
	
	private DeviceDisplay deviceDisplay;
	public WirelessSettingPadViewAdapter(Context context, DeviceDisplay deviceDisplay){
		
		this.context = context;		
		this.mlog.switchLog = true;
		
		this.deviceDisplay = deviceDisplay;
		
		AGSSytemService service = new AGSSytemService(deviceDisplay.getDevice(), WirelessSettingFragment.getMessageHandler());
		service.actGetCurrentSSID(null, new GetCurrentSSIDSuccessCaller());
		
	}
	
	// temp, need site survey, Arthur
	private class GetCurrentSSIDSuccessCaller extends AGSActionSuccessCaller<Object>{

		@Override
		public Object call() throws Exception {
			
			String result = ai.getOutput(SystemServiceValues.ACTION_GET_CURRENT_SSID_OUTPUT_RESULT).getValue().toString();
			
			if(!result.equalsIgnoreCase(SystemServiceValues.WirelessStstus.DISCONNECT)){
				
				WSSite site = new WSSite();
				site.setUse(true);
				site.setLocked(false);
				site.setSSID(result);
				sites.add(site);
				
			}
			
			WSSite siteManual = new WSSite();
			siteManual.setSSID("Other");
			sites.add(siteManual);
			
			return super.call();
			
		}
		
	}
	
//	private StateListDrawable CreateStateListDrawable(Bitmap bitmapA,Bitmap bitmapB){
//		StateListDrawable states = null;		
//		states = new StateListDrawable();		
//		states.addState(new int[] {android.R.attr.state_pressed}, new BitmapDrawable(bitmapA));
//		states.addState(StateSet.WILD_CARD,new BitmapDrawable(bitmapB));
//		return states;
//	}
	
	@Override
	public int getCount() {
//		return sites.size() + 1;
		return sites.size(); // +1 move to GetCurrentSSIDSuccessCaller::call()
	}

	@Override
	public Object getItem(int position) {
		return sites.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {	
		
		WSSite dataCurrentSite = sites.get(position);
		
		ViewHandler viewHandler = null;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.fsw_wifiap_listview_cell_pad, null);
			convertView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			viewHandler = new ViewHandler(convertView);
			basicSetChildView(viewHandler);	
			convertView.setTag(viewHandler);
		}else{
			viewHandler = (ViewHandler)convertView.getTag();
		}
		
		viewHandler.setPosition(position);
		
		//底圖
		if(position==0&&!(position==(this.getCount()-1))){
			//第一個
			TKBTool.fitsViewHeight(70, viewHandler.CCell_RLayout);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_01_f.png","pad/Settings/alarm_box_01_n.png", viewHandler.CCell_RLayout, 3);
		}else if(position==0&&(position==(this.getCount()-1))){
			//只有一個
			TKBTool.fitsViewHeight(62, viewHandler.CCell_RLayout);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_02_f.png","pad/Settings/alarm_box_02_n.png", viewHandler.CCell_RLayout, 3);
		}else if((position==(this.getCount()-1))){
			//最後一個
			TKBTool.fitsViewHeight(73, viewHandler.CCell_RLayout);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_03_f.png","pad/Settings/alarm_box_03_n.png", viewHandler.CCell_RLayout, 3);
		}else{
			//其他
			TKBTool.fitsViewHeight(71, viewHandler.CCell_RLayout);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_02_f.png","pad/Settings/alarm_box_02_n.png", viewHandler.CCell_RLayout, 3);
		}
		
		// 最後一個
		if(position == this.getCount() - 1){
			
			//最後一個 other network
			if(viewHandler.imageTick.getVisibility()==View.VISIBLE){
				viewHandler.imageTick.setVisibility(View.INVISIBLE);
			}
			if(viewHandler.imageLocker.getVisibility()==View.VISIBLE){
				viewHandler.imageLocker.setVisibility(View.INVISIBLE);
			}
			viewHandler.textAPName.setText("Other");
			
			new TKBThreadReadBitMapInAssets(context, "phone/setting/icon_arrow.png", viewHandler.imageSignal, 1);
			
		}else{
			
			//判斷是否要顯示Tick
			if(dataCurrentSite.isUse){
				if(viewHandler.imageTick.getVisibility() != View.VISIBLE){
					viewHandler.imageTick.setVisibility(View.VISIBLE);
				}
			}
			
			//判斷是否要顯示Lock
			if(dataCurrentSite.isLocked){
				if(viewHandler.imageLocker.getVisibility() != View.VISIBLE){
					viewHandler.imageLocker.setVisibility(View.VISIBLE);
				}
			}
			
			// 顯示 Name
			viewHandler.textAPName.setText(dataCurrentSite.getSSID());
			
			// 顯示 strength
			switch(1){
			case 1:
				new TKBThreadReadStateListInAssets(context, "pad/Settings/wifi_01_f.png","pad/Settings/wifi_01_n.png", viewHandler.imageSignal, 1);
				break;
			case 2:
				new TKBThreadReadStateListInAssets(context, "pad/Settings/wifi_02_f.png","pad/Settings/wifi_02_n.png", viewHandler.imageSignal, 1);
				break;
			case 3:
				new TKBThreadReadStateListInAssets(context, "pad/Settings/wifi_03_f.png","pad/Settings/wifi_03_n.png", viewHandler.imageSignal, 1);
				break;
			case 4:
				new TKBThreadReadStateListInAssets(context, "pad/Settings/wifi_04_f.png","pad/Settings/wifi_04_n.png", viewHandler.imageSignal, 1);
				break;
			}
			
		}
		
		mlog.info(tag, "updaet data  at position = " + position);
		
		return convertView;
		
	}
	
	private class ViewHandler{
		
		private RelativeLayout CCell_RLayout;
		
		private int position; // 順序
		private ImageView imageTick; // 打勾
		private TextView textAPName; // AP Name
		private ImageView imageLocker; // Password Required
		private ImageView imageSignal; // Signal Strength
		
		public ViewHandler(View view){
			this.CCell_RLayout = (RelativeLayout)view.findViewById(R.id.FSW_WIFIAP_ListView_Cell_RLayout);
			this.imageTick = (ImageView)view.findViewById(R.id.FSW_WIFIAP_ListView_RLayout_Tick_ImageView);
			this.textAPName = (TextView)view.findViewById(R.id.FSW_WIFIAP_ListView_RLayout_Name_TextView);
			this.imageLocker = (ImageView)view.findViewById(R.id.FSW_WIFIAP_ListView_RLayout_Lock_ImageView);
			this.imageSignal = (ImageView)view.findViewById(R.id.FSW_WIFIAP_ListView_RLayout_WaveArrow_ImageView);
		}
		public void setPosition(int position){
			this.position = position;			
		}
		
	}
	private void basicSetChildView(ViewHandler viewHandler){
		//Tick_ImageView
		TKBTool.fitsViewHeight(22, viewHandler.imageTick);
		viewHandler.imageTick.getLayoutParams().width = TKBTool.getHeight(28);
		TKBTool.fitsViewLeftMargin(5, viewHandler.imageTick);
		new TKBThreadReadStateListInAssets(context, "pad/Settings/pick_f.png","pad/Settings/pick_n.png", viewHandler.imageTick, 1);
		//Name TextView
		TKBTool.fitsViewLeftMargin(5, viewHandler.textAPName);
		TKBTool.fitsViewTextSize(8, viewHandler.textAPName);
		//Lock_ImageView
		TKBTool.fitsViewHeight(30, viewHandler.imageLocker);
		viewHandler.imageLocker.getLayoutParams().width = TKBTool.getHeight(30);
		TKBTool.fitsViewRightMargin(10, viewHandler.imageLocker);
		new TKBThreadReadStateListInAssets(context,"pad/Settings/Lock_n.png", "pad/Settings/Lock_n.png", viewHandler.imageLocker, 1);
		//WaveArrow_ImageView
		TKBTool.fitsViewHeight(42, viewHandler.imageSignal);
		viewHandler.imageSignal.getLayoutParams().width = TKBTool.getHeight(36);
		TKBTool.fitsViewRightMargin(10, viewHandler.imageSignal);
	}

}
