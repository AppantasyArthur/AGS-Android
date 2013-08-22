package com.FSAl_List.SETTING;

import java.util.ArrayList;
import java.util.List;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FSAl_Alarm_ListView_BaseAdapter_PAD extends BaseAdapter {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSI_IdSpeaker_ListView_BaseAdapter";
	
	private boolean isEdit = false;
		
	private List<AlarmItemContent> dataList = new ArrayList<AlarmItemContent>();
	
	private Drawable menu1;
	private Drawable menu2;
	private Drawable menu3;
	private Drawable menu4;
	
	
	public FSAl_Alarm_ListView_BaseAdapter_PAD(Context context){
		this.context = context;		
		this.mlog.LogSwitch = true;
		LoadBitmap();
		CreateDataList();
	}
	private void CreateDataList(){
		dataList.add(new AlarmItemContent("aaaa", "bbbb","cccc",0,"dddd"));
		dataList.add(new AlarmItemContent("aaaa", "bbbb","cccc",0,"dddd"));
		dataList.add(new AlarmItemContent("aaaa", "bbbb","cccc",0,"dddd"));
		dataList.add(new AlarmItemContent("aaaa", "bbbb","cccc",0,"dddd"));
	}
	private void LoadBitmap(){		
		this.menu1 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/identify_01_box.png"));
		this.menu2 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/identify_02_box.png"));
		this.menu3 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/identify_03_box.png"));
		this.menu4 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/Settings_box.png"));
	}
	public void SetDataList(List<AlarmItemContent> dataList){
		this.dataList = dataList;
		this.notifyDataSetChanged();
	}
	public void SetEdit(boolean isEdit){
		if(this.isEdit!=isEdit){
			this.isEdit = isEdit;
			this.notifyDataSetChanged();
		}
	}
	public boolean GetEdit(){
		return this.isEdit;
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
		
		if(this.isEdit){
			if(viewHandler.Delete_ImageView.getVisibility()!=View.VISIBLE){
				viewHandler.Delete_ImageView.setVisibility(View.VISIBLE);
			}			
		}else{
			if(viewHandler.Delete_ImageView.getVisibility()==View.VISIBLE){
				viewHandler.Delete_ImageView.setVisibility(View.GONE);
			}	
		}
		
		
		viewHandler.Time_TextView.setText(dataList.get(position).getTimeH()+":"+dataList.get(position).getTimeM()+" "+dataList.get(position).getTimeAP());
		viewHandler.Frequency_TextView.setText(""+dataList.get(position).getFreaquency());
		viewHandler.AlarmName_TextView.setText(dataList.get(position).getAlarmNmae());
		
		Log.i(TAG, "Not position = "+position);
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
					
					mlog.info(TAG, "Delete position = "+position);
				}
			});
		}
		public void SET_Position(int position){
			this.position = position;			
		}
	}
	private void basicSetChildView(ViewHandler viewHandler){
		//Delete_ImageView
		Tool.fitsViewHeight(35, viewHandler.Delete_ImageView);
		Tool.fitsViewWidth(60, viewHandler.Delete_ImageView);
		Tool.fitsViewLeftMargin(10, viewHandler.Delete_ImageView);
		//Time_TextView
		Tool.fitsViewHeight(35, viewHandler.Time_TextView);
		Tool.fitsViewTextSize(8, viewHandler.Time_TextView);
		Tool.fitsViewLeftMargin(10, viewHandler.Time_TextView);
		//Frequency_TextView
		Tool.fitsViewHeight(25, viewHandler.Frequency_TextView);
		Tool.fitsViewTextSize(6, viewHandler.Frequency_TextView);
		Tool.fitsViewLeftMargin(10, viewHandler.Frequency_TextView);
		//AlarmName_TextView
		Tool.fitsViewHeight(35, viewHandler.AlarmName_TextView);
		Tool.fitsViewTextSize(8, viewHandler.AlarmName_TextView);
		Tool.fitsViewRightMargin(10, viewHandler.AlarmName_TextView);
		//Arrow_ImageView
		Tool.fitsViewHeight(35, viewHandler.Arrow_ImageView);
		Tool.fitsViewWidth(40, viewHandler.Arrow_ImageView);
		Tool.fitsViewRightMargin(10, viewHandler.Arrow_ImageView);
	}

}
