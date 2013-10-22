package com.alpha.setting.wirelesssetup;

import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.StateSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

// FSI_IdSpeaker_ListView_BaseAdapter
public class WirelessSettingPhoneViewAdapter extends BaseAdapter {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "WirelessSettingPhoneViewAdapter";
	
	public WirelessSettingPhoneViewAdapter(Context context){
		this.context = context;		
		this.mlog.switchLog = true;		
	}
	
	private StateListDrawable CreateStateListDrawable(Bitmap bitmapA,Bitmap bitmapB){
		StateListDrawable states = null;		
		states = new StateListDrawable();		
		states.addState(new int[] {android.R.attr.state_pressed}, new BitmapDrawable(bitmapA));
		states.addState(StateSet.WILD_CARD,new BitmapDrawable(bitmapB));
		return states;
	}
	@Override
	public int getCount() {
		return 1;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.fsw_wifiap_listview_cell_pad, null);
			convertView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			viewHandler = new ViewHandler(convertView);
			basicSetChildView(viewHandler);	
			convertView.setTag(viewHandler);
		}else{
			viewHandler = (ViewHandler)convertView.getTag();
		}
		viewHandler.setPosition(position);
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
		
		if(position==this.getCount()-1){
			//最後一個 other network
			if(viewHandler.Tick_ImageView.getVisibility()==View.VISIBLE){
				viewHandler.Tick_ImageView.setVisibility(View.INVISIBLE);
			}
			if(viewHandler.Lock_ImageView.getVisibility()==View.VISIBLE){
				viewHandler.Lock_ImageView.setVisibility(View.INVISIBLE);
			}
			viewHandler.Name_TextView.setText("Other");
			new TKBThreadReadBitMapInAssets(context, "phone/setting/icon_arrow.png", viewHandler.WaveArrow_ImageView, 1);
		}else{
			//判斷是否要顯示Tick
			if(true){
				if(viewHandler.Tick_ImageView.getVisibility()!=View.VISIBLE){
					viewHandler.Tick_ImageView.setVisibility(View.VISIBLE);
				}
			}
			//判斷是否要顯示Lock
			if(true){
				if(viewHandler.Lock_ImageView.getVisibility()!=View.VISIBLE){
					viewHandler.Lock_ImageView.setVisibility(View.VISIBLE);
				}
			}
			//viewHandler.Name_TextView.setText("name");
			
			switch(1){
			case 1:
				new TKBThreadReadStateListInAssets(context, "pad/Settings/wifi_01_f.png","pad/Settings/wifi_01_n.png", viewHandler.WaveArrow_ImageView, 1);
				break;
			case 2:
				new TKBThreadReadStateListInAssets(context, "pad/Settings/wifi_02_f.png","pad/Settings/wifi_02_n.png", viewHandler.WaveArrow_ImageView, 1);
				break;
			case 3:
				new TKBThreadReadStateListInAssets(context, "pad/Settings/wifi_03_f.png","pad/Settings/wifi_03_n.png", viewHandler.WaveArrow_ImageView, 1);
				break;
			case 4:
				new TKBThreadReadStateListInAssets(context, "pad/Settings/wifi_04_f.png","pad/Settings/wifi_04_n.png", viewHandler.WaveArrow_ImageView, 1);
				break;
			}
		}
		mlog.info(tag, "position = "+position);
		return convertView;
	}
	private class ViewHandler{
		private int position;
		
		private ImageView Tick_ImageView;
		private RelativeLayout CCell_RLayout;			
		private TextView Name_TextView;
		private ImageView Lock_ImageView;
		private ImageView WaveArrow_ImageView;
		
		public ViewHandler(View view){
			this.CCell_RLayout = (RelativeLayout)view.findViewById(R.id.FSW_WIFIAP_ListView_Cell_RLayout);
			this.Tick_ImageView = (ImageView)view.findViewById(R.id.FSW_WIFIAP_ListView_RLayout_Tick_ImageView);
			this.Name_TextView = (TextView)view.findViewById(R.id.FSW_WIFIAP_ListView_RLayout_Name_TextView);
			this.Lock_ImageView = (ImageView)view.findViewById(R.id.FSW_WIFIAP_ListView_RLayout_Lock_ImageView);
			this.WaveArrow_ImageView = (ImageView)view.findViewById(R.id.FSW_WIFIAP_ListView_RLayout_WaveArrow_ImageView);
		}
		public void setPosition(int position){
			this.position = position;			
		}
	}
	private void basicSetChildView(ViewHandler viewHandler){
		//Tick_ImageView
		TKBTool.fitsViewHeight(18, viewHandler.Tick_ImageView);
		viewHandler.Tick_ImageView.getLayoutParams().width = TKBTool.getHeight(14);
		TKBTool.fitsViewLeftMargin(5, viewHandler.Tick_ImageView);
		new TKBThreadReadStateListInAssets(context, "pad/Settings/pick_f.png","pad/Settings/pick_n.png", viewHandler.Tick_ImageView, 1);
		//Name TextView
		TKBTool.fitsViewLeftMargin(5, viewHandler.Name_TextView);
		TKBTool.fitsViewTextSize(12, viewHandler.Name_TextView);
		//Lock_ImageView
		TKBTool.fitsViewHeight(18, viewHandler.Lock_ImageView);
		viewHandler.Lock_ImageView.getLayoutParams().width = TKBTool.getHeight(14);
		TKBTool.fitsViewRightMargin(10, viewHandler.Lock_ImageView);
		new TKBThreadReadStateListInAssets(context,"pad/Settings/Lock_n.png", "pad/Settings/Lock_n.png", viewHandler.Lock_ImageView, 1);
		//WaveArrow_ImageView
		TKBTool.fitsViewHeight(15, viewHandler.WaveArrow_ImageView);
		viewHandler.WaveArrow_ImageView.getLayoutParams().width = TKBTool.getHeight(11);
		TKBTool.fitsViewRightMargin(10, viewHandler.WaveArrow_ImageView);
		
	}
}
