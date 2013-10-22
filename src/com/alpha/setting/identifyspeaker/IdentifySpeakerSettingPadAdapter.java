package com.alpha.setting.identifyspeaker;

import java.util.ArrayList;
import java.util.List;

import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;
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

// FSI_IdSpeaker_ListView_BaseAdapter_PAD
public class IdentifySpeakerSettingPadAdapter extends BaseAdapter {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "IdentifySpeakerSettingPadAdapter";
		
	private List<DeviceDisplay> listData = new ArrayList<DeviceDisplay>();
	
	private Drawable menu1;
	private Drawable menu2;
	private Drawable menu3;
	private Drawable menu4;
	public IdentifySpeakerSettingPadAdapter(Context context,DeviceDisplay deviceDisplay){
		this.context = context;		
		this.mlog.switchLog = true;
		LoadBitmap();
		listData.add(deviceDisplay);
	}
	private void LoadBitmap(){		
		this.menu1 = new BitmapDrawable(context.getResources(),TKBTool.readBitMapInAssets(context, "pad/Settings/identify_01_box.png"));
		this.menu2 = new BitmapDrawable(context.getResources(),TKBTool.readBitMapInAssets(context, "pad/Settings/identify_02_box.png"));
		this.menu3 = new BitmapDrawable(context.getResources(),TKBTool.readBitMapInAssets(context, "pad/Settings/identify_03_box.png"));
		this.menu4 = new BitmapDrawable(context.getResources(),TKBTool.readBitMapInAssets(context, "pad/Settings/Settings_box.png"));
	}
	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
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
			TKBTool.fitsViewHeight(70, viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu1);
		}else if(position==0&&(position==(this.getCount()-1))){
			//只有一個
			TKBTool.fitsViewHeight(62, viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu4);
		}else if((position==(this.getCount()-1))){
			//最後一個
			TKBTool.fitsViewHeight(73, viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu3);
		}else{
			//其他
			TKBTool.fitsViewHeight(71, viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu2);
		}
		viewHandler.Name_TextView.setText(listData.get(position).getDevice().getDetails().getFriendlyName());
		mlog.info(tag, "position = "+position);
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
		TKBTool.fitsViewLeftMargin(5, viewHandler.Name_TextView);
		TKBTool.fitsViewTextSize(8, viewHandler.Name_TextView);
		//Sound ImageView
		TKBTool.fitsViewHeight(22, viewHandler.Sound_ImageView);
		viewHandler.Sound_ImageView.getLayoutParams().width = TKBTool.getHeight(28);
		TKBTool.fitsViewRightMargin(10, viewHandler.Sound_ImageView);
		new TKBThreadReadStateListInAssets(context, "pad/Settings/Settings_speaker_f.png", "pad/Settings/Settings_speaker_n.png", viewHandler.Sound_ImageView, 1);
	}

}
