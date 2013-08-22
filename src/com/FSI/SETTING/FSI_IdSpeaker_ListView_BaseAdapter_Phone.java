package com.FSI.SETTING;

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

public class FSI_IdSpeaker_ListView_BaseAdapter_Phone extends BaseAdapter {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSI_IdSpeaker_ListView_BaseAdapter";
		
	private List<DeviceDisplay> dataList = new ArrayList<DeviceDisplay>();
	
	private Drawable menu1;
	private Drawable menu2;
	private Drawable menu3;
	private Drawable menu4;
	
	public FSI_IdSpeaker_ListView_BaseAdapter_Phone(Context context,DeviceDisplay deviceDisplay){
		this.context = context;		
		this.mlog.LogSwitch = true;
		dataList.add(deviceDisplay);
		LoadBitmap();
	}
	private void LoadBitmap(){		
		this.menu1 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "phone/setting/setting_identify_bar_top.png"));
		this.menu2 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "phone/setting/settimg_identify_bar_center.png"));
		this.menu3 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "phone/setting/settimg_identify_bar_bottom.png"));
		this.menu4 = new BitmapDrawable(context.getResources(),Tool.readBitMapInAssets(context, "phone/setting/setting_firmware_about_bar.PNG"));
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
			convertView = LayoutInflater.from(context).inflate(R.layout.fsi_idspeaker_listview_cell_pad, null);
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
		viewHandler.Name_TextView.setText(dataList.get(position).getDevice().getDetails().getFriendlyName());
		mlog.info(TAG, "position = "+position);
		return convertView;
	}
	private class ViewHandler{
		private int position;
		
		private RelativeLayout CCell_RLayout;			
		private TextView Name_TextView;
		private ImageView Sound_ImageView;
		
		public ViewHandler(View view){
			this.CCell_RLayout = (RelativeLayout)view.findViewById(R.id.FSI_IdSpeaker_ListView_Cell_RLayout);
			this.Name_TextView = (TextView)view.findViewById(R.id.FSI_IdSpeaker_ListView_RLayout_SpeakerName_TextView);
			this.Sound_ImageView = (ImageView)view.findViewById(R.id.FSI_IdSpeaker_ListView_RLayout_Sound_ImageView);
		}
		public void SET_Position(int position){
			this.position = position;			
		}
	}
	private void basicSetChildView(ViewHandler viewHandler){
		//Name TextView
		Tool.fitsViewLeftMargin(5, viewHandler.Name_TextView);
		Tool.fitsViewTextSize(12, viewHandler.Name_TextView);
		//Sound ImageView
		Tool.fitsViewWidth(18, viewHandler.Sound_ImageView);
		viewHandler.Sound_ImageView.getLayoutParams().height = Tool.getWidth(14);
		Tool.fitsViewRightMargin(10, viewHandler.Sound_ImageView);
		new ThreadReadStateListInAssets(context, "phone/setting/setting_info_speaker_icon_f.png", "phone/setting/setting_info_speaker_icon_n.png", viewHandler.Sound_ImageView, 1);
	}

}
