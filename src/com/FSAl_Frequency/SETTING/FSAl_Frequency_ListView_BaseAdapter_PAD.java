package com.FSAl_Frequency.SETTING;

import java.util.ArrayList;
import java.util.List;

import com.alpha.fragments.Fragment_SAlarm_EditAdd;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;
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

public class FSAl_Frequency_ListView_BaseAdapter_PAD extends BaseAdapter {
	private Context context;
	private MLog mlog = new MLog();
	private FragmentManager fragmentManager;
	private static final String TAG = "FSAl_Frequency_ListView_BaseAdapter_PAD";
		
	private List<String> dataList = new ArrayList<String>();
	private int chooseItem = -1;
	
	private Drawable menu1;
	private Drawable menu2;
	private Drawable menu3;
	private Drawable menu4;
	public FSAl_Frequency_ListView_BaseAdapter_PAD(Context context,FragmentManager fragmentManager){
		this.context = context;			
		this.mlog.LogSwitch = true;
		this.fragmentManager = fragmentManager;
		LoadBitmap();
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
	private void LoadBitmap(){		
		this.menu1 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/identify_01_box.png"));
		this.menu2 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/identify_02_box.png"));
		this.menu3 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/identify_03_box.png"));
		this.menu4 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/Settings_box.png"));
	}
	private void SetChooseItem(){		
		Fragment_SAlarm_EditAdd fragment_SAlarm_EditAdd = (Fragment_SAlarm_EditAdd)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
		if(fragment_SAlarm_EditAdd!=null){
			this.chooseItem = fragment_SAlarm_EditAdd.GetFrequency();
		}
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
			Tool.fitsViewHeight(70, viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu1);
		}else if(position==0&&(position==(this.getCount()-1))){
			//只有一個
			Tool.fitsViewHeight(62, viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu4);
		}else if((position==(this.getCount()-1))){
			//最後一個
			Tool.fitsViewHeight(73, viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu3);
		}else{
			//其他
			Tool.fitsViewHeight(71, viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu2);
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
		public void SET_Position(int position){
			this.position = position;			
		}
	}
	private void basicSetChildView(ViewHandler viewHandler){
		//Time_TextView		
		Tool.fitsViewTextSize(8, viewHandler.Time_TextView);
		Tool.fitsViewLeftMargin(10, viewHandler.Time_TextView);
		//Tick_ImageView		
		Tool.fitsViewHeight(50, viewHandler.Tick_ImageView);
		Tool.fitsViewWidth(50, viewHandler.Tick_ImageView);
		Tool.fitsViewRightMargin(10, viewHandler.Tick_ImageView);
	}

}
