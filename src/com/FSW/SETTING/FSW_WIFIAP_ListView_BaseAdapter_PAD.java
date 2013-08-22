package com.FSW.SETTING;

import java.util.ArrayList;
import java.util.List;

import com.alpha.UPNP.DeviceDisplay;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadStateListInAssets;
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

public class FSW_WIFIAP_ListView_BaseAdapter_PAD extends BaseAdapter {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSI_IdSpeaker_ListView_BaseAdapter";
	
	private Drawable Wave1;
	private Drawable Wave2;
	private Drawable Wave3;
	private Drawable Arrow;
	private Drawable menu1;
	private Drawable menu2;
	private Drawable menu3;
	private Drawable menu4;
	
	public FSW_WIFIAP_ListView_BaseAdapter_PAD(Context context){
		this.context = context;		
		this.mlog.LogSwitch = true;
		LoadBitmap();
	}
	private void LoadBitmap(){		
		this.menu1 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/identify_01_box.png"));
		this.menu2 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/identify_02_box.png"));
		this.menu3 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/identify_03_box.png"));
		this.menu4 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "pad/Settings/Settings_box.png"));
	}
	@Override
	public int getCount() {
		return 4+1;
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
		viewHandler.SET_Position(position);
		//底圖
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
		
		if(position==this.getCount()-1){
			//最後一個 other network
			if(viewHandler.Tick_ImageView.getVisibility()==View.VISIBLE){
				viewHandler.Tick_ImageView.setVisibility(View.INVISIBLE);
			}
			if(viewHandler.Lock_ImageView.getVisibility()==View.VISIBLE){
				viewHandler.Lock_ImageView.setVisibility(View.INVISIBLE);
			}
			viewHandler.Name_TextView.setText("Other");
			
//			viewHandler.WaveArrow_ImageView.setImageDrawable(Arrow);
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
			viewHandler.Name_TextView.setText("name");
			
			switch(1){
			case 1:
//				viewHandler.WaveArrow_ImageView.setImageDrawable(Wave1);
				break;
			case 2:
//				viewHandler.WaveArrow_ImageView.setImageDrawable(Wave2);
				break;
			case 3:
//				viewHandler.WaveArrow_ImageView.setImageDrawable(Wave3);
				break;
			}
		}
		mlog.info(TAG, "position = "+position);
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
		public void SET_Position(int position){
			this.position = position;			
		}
	}
	private void basicSetChildView(ViewHandler viewHandler){
		//Tick_ImageView
		Tool.fitsViewHeight(22, viewHandler.Tick_ImageView);
		viewHandler.Tick_ImageView.getLayoutParams().width = Tool.getHeight(28);
		Tool.fitsViewLeftMargin(5, viewHandler.Tick_ImageView);
		//Name TextView
		Tool.fitsViewLeftMargin(5, viewHandler.Name_TextView);
		Tool.fitsViewTextSize(8, viewHandler.Name_TextView);
		//Lock_ImageView
		Tool.fitsViewHeight(22, viewHandler.Lock_ImageView);
		viewHandler.Lock_ImageView.getLayoutParams().width = Tool.getHeight(28);
		Tool.fitsViewRightMargin(10, viewHandler.Lock_ImageView);		
		//WaveArrow_ImageView
		Tool.fitsViewHeight(22, viewHandler.WaveArrow_ImageView);
		viewHandler.WaveArrow_ImageView.getLayoutParams().width = Tool.getHeight(28);
		Tool.fitsViewRightMargin(10, viewHandler.WaveArrow_ImageView);
	}

}
