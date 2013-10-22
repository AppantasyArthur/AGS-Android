package com.alpha.setting.rendererlist;

import java.util.ArrayList;
import java.util.List;

import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FSR_Renders_ListView_BaseAdapter_PAD extends BaseAdapter {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FSI_IdSpeaker_ListView_BaseAdapter";
		
	private List<DeviceDisplay> MRList = new ArrayList<DeviceDisplay>();
	
	public Handler handler = new Handler(){
		public void handleMessage (Message msg) {
			switch(msg.what){			
			case 0:			
				FSR_Renders_ListView_BaseAdapter_PAD.this.notifyDataSetChanged();
				break;			
			}
		}
	};
	public FSR_Renders_ListView_BaseAdapter_PAD(Context context){
		this.context = context;		
		this.mlog.switchLog = true;
		//註冊Listner
		SetListner();
		//取得目前MR
		GetMRList();
	}

	private void SetListner() {
		FSR_Renderers_ListView_BaseAdapter_Renderer_Listner FSRRRLBListner = new FSR_Renderers_ListView_BaseAdapter_Renderer_Listner(){
			@Override
			public void RenderersChange() {
				GetMRList();
				handler.obtainMessage(0).sendToTarget();
			}			
		};
		((MainFragmentActivity)context).getDeviceDisplayList().setFSR_Renderers_ListView_BaseAdapter_Renderer_Listner(FSRRRLBListner);
	}
	private void GetMRList(){
		this.MRList.clear();
		List<DeviceDisplay> list = ((MainFragmentActivity)context).getDeviceDisplayList().getMediaRendererList();
		for (DeviceDisplay deviceDisplay:list){
			MRList.add(deviceDisplay);
		}
	}
	@Override
	public int getCount() {
		return MRList.size();
	}

	@Override
	public Object getItem(int position) {
		try {
			return MRList.get(position);	
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {	
		ViewHandler viewHandler =null;
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.fsr_renderers_listview_cell_pad, null);
			convertView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,TKBTool.getHeight(100)));
			viewHandler = new ViewHandler(convertView);
			basicSetChildView(viewHandler);	
			convertView.setTag(viewHandler);
		}else{
			viewHandler = (ViewHandler)convertView.getTag();
		}
		viewHandler.SET_Position(position);		
		viewHandler.Name_TextView.setText(MRList.get(position).getDevice().getDetails().getFriendlyName());
		
		mlog.info(TAG, "position = "+position);
		return convertView;
	}
	private class ViewHandler{
		private int position;
		
		private RelativeLayout CCell_RLayout;			
		private TextView Name_TextView;
		private ImageView Image_ImageView;
		
		public ViewHandler(View view){
			this.CCell_RLayout = (RelativeLayout)view.findViewById(R.id.FSR_Renderers_ListView_Cell_RLayout);
			this.Name_TextView = (TextView)view.findViewById(R.id.FSR_Renderers_ListView_RLayout_RendererName_TextView);
			this.Image_ImageView = (ImageView)view.findViewById(R.id.FSR_Renderers_ListView_RLayout_Image_ImageView);
		}
		public void SET_Position(int position){
			this.position = position;			
		}
	}
	private void basicSetChildView(ViewHandler viewHandler){
		TKBTool.fitsViewHeight(70, viewHandler.CCell_RLayout);
		new TKBThreadReadStateListInAssets(context, "pad/Settings/Settings_item_f.png","pad/Settings/Settings_item_n.png", viewHandler.CCell_RLayout, 3);
		//Name TextView
		TKBTool.fitsViewLeftMargin(5, viewHandler.Name_TextView);
		TKBTool.fitsViewTextSize(8, viewHandler.Name_TextView);
		//Image_ImageView
		TKBTool.fitsViewHeight(13, viewHandler.Image_ImageView);
		viewHandler.Image_ImageView.getLayoutParams().width = TKBTool.getHeight(7);
		TKBTool.fitsViewRightMargin(15, viewHandler.Image_ImageView);
		TKBTool.fitsViewTopMargin(28, viewHandler.Image_ImageView);
		new TKBThreadReadStateListInAssets(context, "pad/Playlist/playlist_arrow_f.png", "pad/Playlist/playlist_arrow_n.png", viewHandler.Image_ImageView, 1);
	}

}
