package com.FSS.SETTING;

import java.util.ArrayList;
import java.util.List;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FSS_SleepTimer_ListView_BaseAdapter_Phone extends BaseAdapter {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSS_SleepTimer_ListView_BaseAdapter_Phone";
		
	private List<String> dataList = new ArrayList<String>();
	
	private int chooseItem = -1;
	private Drawable menu1;
	private Drawable menu2;
	private Drawable menu3;
	private Drawable menu4;
	
	public FSS_SleepTimer_ListView_BaseAdapter_Phone(Context context){
		this.context = context;		
		this.mlog.LogSwitch = true;		
		LoadBitmap();
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
	private void LoadBitmap(){		
		this.menu1 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "phone/setting/setting_identify_bar_top.png"));
		this.menu2 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "phone/setting/settimg_identify_bar_center.png"));
		this.menu3 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "phone/setting/settimg_identify_bar_bottom.png"));
		this.menu4 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "phone/setting/setting_firmware_about_bar.PNG"));
	}
	public void SetChooseItem(int chooseItem){
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
			Tool.fitsViewHeight(30, viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu1);
			
		}else if(position==0&&(position==(this.getCount()-1))){
			//只有一個
			Tool.fitsViewHeight(34, viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu4);
		}else if((position==(this.getCount()-1))){
			//最後一個
			Tool.fitsViewHeight(33, viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu3);
		}else{
			//其他
			Tool.fitsViewHeight(31, viewHandler.CCell_RLayout);
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
		Tool.fitsViewTextSize(12, viewHandler.Time_TextView);
		Tool.fitsViewLeftMargin(5, viewHandler.Time_TextView);
		//Tick_ImageView		
		Tool.fitsViewHeight(25, viewHandler.Tick_ImageView);
		Tool.fitsViewWidth(25, viewHandler.Tick_ImageView);
		Tool.fitsViewRightMargin(10, viewHandler.Tick_ImageView);
	}

}
