package com.alpha.setting.alarm.frequency;

import java.util.ArrayList;
import java.util.List;

import com.alpha.fragments.settings.AlarmSettingAddEditFragement;
import com.alpha.fragments.settings.ags.AGSAlarmSettingAddEditFragement;
import com.alpha.setting.alarm.AlarmItemContent;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

// FSAl_Frequency_ListView_BaseAdapter_PAD
public class AlarmSettingFrequencyOptionPadViewAdapter extends BaseAdapter {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private FragmentManager fragmentManager;
	private static final String TAG = "AlarmSettingFrequencyOptionPadViewAdapter";
		
	private List<String> dataList = new ArrayList<String>();
	private int chooseItem = -1;
	
	public AlarmSettingFrequencyOptionPadViewAdapter(Context context,FragmentManager fragmentManager){
		this.context = context;			
		this.mlog.switchLog = true;
		this.fragmentManager = fragmentManager;
		SetChooseItem();
		CreateDataList();
	}
	private void CreateDataList(){			
		dataList.add("Once");
		dataList.add("Every Monday");
		dataList.add("Every Tuesday");
		dataList.add("Every Wedesday");
		dataList.add("Every Thursday");
		dataList.add("Every Friday");
		dataList.add("Every Saturday");
		dataList.add("Every Sunday");
	}
	
	private void SetChooseItem(){		
		
//		AlarmSettingAddEditFragement fragment_SAlarm_EditAdd = (AlarmSettingAddEditFragement)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
//		if(fragment_SAlarm_EditAdd!=null){
//			this.chooseItem = fragment_SAlarm_EditAdd.GetFrequency();
//		}
		
		String freq = AGSAlarmSettingAddEditFragement.getFrequencyText();
		chooseItem = AlarmItemContent.getFrequencyOptionPosition(freq);
		
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
		mlog.info(TAG, "position = "+position);
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
		public void setPosition(int position){
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
		new TKBThreadReadStateListInAssets(context, "pad/Settings/pick_f.png","pad/Settings/pick_n.png", viewHandler.Tick_ImageView, 1);
	}

}
