package com.FSI.SETTING;

import java.util.ArrayList;
import java.util.List;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;
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

public class FSI_IdSpeaker_ListView_BaseAdapter extends BaseAdapter {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSI_IdSpeaker_ListView_BaseAdapter";
		
	private List<String> dataList = new ArrayList<String>();
	public FSI_IdSpeaker_ListView_BaseAdapter(Context context){
		this.context = context;		
		this.mlog.LogSwitch = true;
		dataList.clear();
		for(int i = 0;i<10;i++){
			dataList.add(""+i);
		}
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {	
		ViewHandler viewHandler =null;
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.fsi_idspeaker_listview_cell_pad, null);
			convertView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,Tool.getHeight(50)));
			viewHandler = new ViewHandler(convertView);
			basicSetChildView(viewHandler);	
			convertView.setTag(viewHandler);
		}else{
			viewHandler = (ViewHandler)convertView.getTag();
		}
		if(position==0&&!(position==(this.getCount()-1))){
			//第一個
			
		}else if(position==0&&(position==(this.getCount()-1))){
			//只有一個
			
		}else if((position==(this.getCount()-1))){
			//最後一個
			
		}else{
			//其他
			
		}
		viewHandler.Name_TextView.setText("render id = "+dataList.get(position));
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
		Tool.fitsViewTextSize(8, viewHandler.Name_TextView);
		//Sound ImageView
		Tool.fitsViewHeight(21, viewHandler.Sound_ImageView);
		viewHandler.Sound_ImageView.getLayoutParams().width = Tool.getHeight(30);
		Tool.fitsViewRightMargin(10, viewHandler.Sound_ImageView);
	}

}
