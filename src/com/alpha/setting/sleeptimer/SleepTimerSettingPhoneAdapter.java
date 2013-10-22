package com.alpha.setting.sleeptimer;

import java.util.ArrayList;
import java.util.List;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;
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

// FSS_SleepTimer_ListView_BaseAdapter_Phone
public class SleepTimerSettingPhoneAdapter extends BaseAdapter {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "SleepTimerSettingPhoneAdapter";
		
	private List<String> dataList = new ArrayList<String>();
	
	private int chooseItem = -1;
	
	public SleepTimerSettingPhoneAdapter(Context context){
		this.context = context;		
		this.mlog.switchLog = true;		
		CreateDataList();
	}
	private void CreateDataList(){
		dataList.add("Off");
		dataList.add("15 Minutes");
		dataList.add("30 Minutes");
		dataList.add("45 Minutes");
		dataList.add("1 Hour");
		dataList.add("2 Hour");
		dataList.add("3 Hour");
	}
	public void setChooseItem(int chooseItem){
		this.chooseItem = chooseItem;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.fss_sleeptimer_listview_cell_pad, null);
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
		
		if(position==chooseItem){
			if(viewHandler.Tick_ImageView.getVisibility()!=View.VISIBLE){
				viewHandler.Tick_ImageView.setVisibility(View.VISIBLE);
			}
		}else{
			if(viewHandler.Tick_ImageView.getVisibility()==View.VISIBLE){
				viewHandler.Tick_ImageView.setVisibility(View.INVISIBLE);
			}
		}
		
		viewHandler.Time_TextView.setText(dataList.get(position));
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
		TKBTool.fitsViewTextSize(12, viewHandler.Time_TextView);
		TKBTool.fitsViewLeftMargin(5, viewHandler.Time_TextView);
		//Tick_ImageView		
		TKBTool.fitsViewHeight(25, viewHandler.Tick_ImageView);
		TKBTool.fitsViewWidth(25, viewHandler.Tick_ImageView);
		TKBTool.fitsViewRightMargin(10, viewHandler.Tick_ImageView);
		new TKBThreadReadStateListInAssets(context, "pad/Settings/pick_f.png","pad/Settings/pick_n.png", viewHandler.Tick_ImageView, 1);
	}

}
