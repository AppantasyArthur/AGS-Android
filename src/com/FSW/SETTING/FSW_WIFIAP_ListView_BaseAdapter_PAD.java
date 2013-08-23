package com.FSW.SETTING;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

public class FSW_WIFIAP_ListView_BaseAdapter_PAD extends BaseAdapter {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSI_IdSpeaker_ListView_BaseAdapter";
	
	public FSW_WIFIAP_ListView_BaseAdapter_PAD(Context context){
		this.context = context;		
		this.mlog.LogSwitch = true;		
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
			new ThreadReadStateListInAssets(context, "pad/Settings/alarm_box_01_f.png","pad/Settings/alarm_box_01_n.png", viewHandler.CCell_RLayout, 3);
		}else if(position==0&&(position==(this.getCount()-1))){
			//只有一個
			Tool.fitsViewHeight(62, viewHandler.CCell_RLayout);
			new ThreadReadStateListInAssets(context, "pad/Settings/alarm_box_02_f.png","pad/Settings/alarm_box_02_n.png", viewHandler.CCell_RLayout, 3);
		}else if((position==(this.getCount()-1))){
			//最後一個
			Tool.fitsViewHeight(73, viewHandler.CCell_RLayout);
			new ThreadReadStateListInAssets(context, "pad/Settings/alarm_box_03_f.png","pad/Settings/alarm_box_03_n.png", viewHandler.CCell_RLayout, 3);
		}else{
			//其他
			Tool.fitsViewHeight(71, viewHandler.CCell_RLayout);
			new ThreadReadStateListInAssets(context, "pad/Settings/alarm_box_02_f.png","pad/Settings/alarm_box_02_n.png", viewHandler.CCell_RLayout, 3);
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
			
			new ThreadReadStateListInAssets(context, "pad/Playlist/playlist_arrow_f.png","pad/Playlist/playlist_arrow_n.png", viewHandler.WaveArrow_ImageView, 1);
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
				new ThreadReadStateListInAssets(context, "pad/Settings/wifi_01_f.png","pad/Settings/wifi_01_n.png", viewHandler.WaveArrow_ImageView, 1);
				break;
			case 2:
				new ThreadReadStateListInAssets(context, "pad/Settings/wifi_02_f.png","pad/Settings/wifi_02_n.png", viewHandler.WaveArrow_ImageView, 1);
				break;
			case 3:
				new ThreadReadStateListInAssets(context, "pad/Settings/wifi_03_f.png","pad/Settings/wifi_03_n.png", viewHandler.WaveArrow_ImageView, 1);
				break;
			case 4:
				new ThreadReadStateListInAssets(context, "pad/Settings/wifi_04_f.png","pad/Settings/wifi_04_n.png", viewHandler.WaveArrow_ImageView, 1);
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
		new ThreadReadStateListInAssets(context, "pad/Settings/pick_f.png","pad/Settings/pick_n.png", viewHandler.Tick_ImageView, 1);
		//Name TextView
		Tool.fitsViewLeftMargin(5, viewHandler.Name_TextView);
		Tool.fitsViewTextSize(8, viewHandler.Name_TextView);
		//Lock_ImageView
		Tool.fitsViewHeight(30, viewHandler.Lock_ImageView);
		viewHandler.Lock_ImageView.getLayoutParams().width = Tool.getHeight(33);
		Tool.fitsViewRightMargin(10, viewHandler.Lock_ImageView);
		new ThreadReadStateListInAssets(context,"pad/Settings/Lock_n.png", "pad/Settings/Lock_n.png", viewHandler.Lock_ImageView, 1);
		//WaveArrow_ImageView
		Tool.fitsViewHeight(30, viewHandler.WaveArrow_ImageView);
		viewHandler.WaveArrow_ImageView.getLayoutParams().width = Tool.getHeight(33);
		Tool.fitsViewRightMargin(10, viewHandler.WaveArrow_ImageView);
	}

}
