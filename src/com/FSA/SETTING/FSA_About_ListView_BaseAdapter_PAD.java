package com.FSA.SETTING;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FSA_About_ListView_BaseAdapter_PAD extends BaseAdapter {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSA_About_ListView_BaseAdapter_PAD";
		
	private List<AboutItemContent> dataList = new ArrayList<AboutItemContent>();
	
	private Drawable menu1;
	private Drawable menu2;
	private Drawable menu3;
	private Drawable menu4;
	public FSA_About_ListView_BaseAdapter_PAD(Context context){
		this.context = context;		
		this.mlog.LogSwitch = true;
		LoadBitmap();
		CreateDataList();
	}
	private void CreateDataList(){
		dataList.add(new AboutItemContent("aaaa", "bbbb"));
		dataList.add(new AboutItemContent("aaaa", "bbbb"));
		dataList.add(new AboutItemContent("aaaa", "bbbb"));
		dataList.add(new AboutItemContent("aaaa", "bbbb"));
	}
	private void LoadBitmap(){		
		this.menu1 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/identify_01_box.png"));
		this.menu2 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/identify_02_box.png"));
		this.menu3 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/identify_03_box.png"));
		this.menu4 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/Settings_box.png"));
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
		
		viewHandler.LeftText_TextView.setText(dataList.get(position).getLeftString());
		viewHandler.RightText_TextView.setText(dataList.get(position).getRightString());
		
		mlog.info(TAG, "position = "+position);
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
		Tool.fitsViewTextSize(8, viewHandler.LeftText_TextView);
		Tool.fitsViewLeftMargin(10, viewHandler.LeftText_TextView);
		//RightText_TextView		
		Tool.fitsViewTextSize(8, viewHandler.RightText_TextView);
		Tool.fitsViewRightMargin(10, viewHandler.RightText_TextView);
	}

}
