package com.alpha.musicinfo;

import java.util.ArrayList;
import java.util.List;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.model.action.ActionArgumentValue;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.ActionArgument;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.ServiceId;
import org.teleal.cling.model.types.UDAServiceId;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.parser.TrackDO;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBTool;

// FM_Queue_ListView_BaseAdapter
public class MusicInfoListPhoneViewAdapter extends BaseAdapter {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "MusicInfoListPhoneViewAdapter";
		
	private boolean isEdit = false;
	private int startDragPosition = -1;
	private int holdDragPosition = -1;
	private int InsertPosition = -1;
	private int SelectedPosition = -1;
	private List<TrackDO> dataList = new ArrayList<TrackDO>();
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case 0:
				if(MusicInfoListPhoneViewAdapter.this.dataList!=null){
					MusicInfoListPhoneViewAdapter.this.dataList.clear();
					MusicInfoListPhoneViewAdapter.this.notifyDataSetChanged();
				}				
				break;
			case 1:
				if(MusicInfoListPhoneViewAdapter.this.dataList!=null){
					MusicInfoListPhoneViewAdapter.this.dataList.clear();
				}else{
					MusicInfoListPhoneViewAdapter.this.dataList = new ArrayList<TrackDO>();
				}
				List<TrackDO> queueList = (List<TrackDO>)msg.obj;
				for(int i = 0;i< queueList.size();i++){
					mlog.info(TAG, "==========EVEN STAR==========");
					mlog.info(TAG, "Track ID valeu= "+queueList.get(i).getId());
					mlog.info(TAG, "Track Title valeu= "+queueList.get(i).getTitle());
					mlog.info(TAG, "==========EVEN END==========");
					MusicInfoListPhoneViewAdapter.this.dataList.add(queueList.get(i));
				}
				MusicInfoListPhoneViewAdapter.this.notifyDataSetChanged();	
				break;
			}
		}
	};
	public MusicInfoListPhoneViewAdapter(Context context){
		this.context = context;		
		this.mlog.switchLog = true;
		
		MusicInfoQueueListViewAdapterListener queqe_listner = new MusicInfoQueueListViewAdapterListener(){
			@Override
			public void cleanQueueList() {				
				handler.obtainMessage(0).sendToTarget();
			}
			@Override
			public void setQueueList(List<TrackDO> trackList) {
				handler.obtainMessage(1,trackList).sendToTarget();
				mlog.info(TAG, "AddQueqeList");	
			}			
		};
		((MainFragmentActivity)context).getDeviceDisplayList().setQueqe_Listner(queqe_listner);
		dataList.clear();	
	}
	@Override
	public int getCount() {
		if(InsertPosition>=0){
			return dataList.size()+1;		
		}else{
			return dataList.size();		
		}
		
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHandler viewHandler = null;
		if(convertView==null){			
			convertView = LayoutInflater.from(context).inflate(R.layout.fi_queue_listview_cell_pad, null);
			convertView.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT, TKBTool.getHeight(50)));
			viewHandler = new ViewHandler(convertView);
			basicSetView(viewHandler);
			convertView.setTag(viewHandler);
		}else{
			viewHandler = (ViewHandler)convertView.getTag();
		}	
		viewHandler.position = position;
		
		if(isEdit){
			if(viewHandler.Delete_ImageView.getVisibility()==View.GONE||viewHandler.Drag_ImageView.getVisibility()==View.GONE){
				viewHandler.Delete_ImageView.setVisibility(View.VISIBLE);
				viewHandler.Drag_ImageView.setVisibility(View.VISIBLE);				
			}
		}else{
			if(viewHandler.Delete_ImageView.getVisibility()==View.VISIBLE||viewHandler.Drag_ImageView.getVisibility()==View.VISIBLE){
				viewHandler.Delete_ImageView.setVisibility(View.GONE);
				viewHandler.Drag_ImageView.setVisibility(View.GONE);				
			}		
		}
		//?��?設�?
		if(position==startDragPosition&&startDragPosition==holdDragPosition){
			viewHandler.cell_RLayout.setVisibility(View.INVISIBLE);
			new TKBThreadReadBitMapInAssets(context, "phone/queue/edit__select_bg.PNG", viewHandler.cellBG_RLayout, 3);
		}else if(position == holdDragPosition){			
			viewHandler.cell_RLayout.setVisibility(View.INVISIBLE);
			new TKBThreadReadBitMapInAssets(context, "phone/queue/edit__select_bg.PNG", viewHandler.cellBG_RLayout, 3);
		}else{
			viewHandler.cell_RLayout.setVisibility(View.VISIBLE);
			new TKBThreadReadBitMapInAssets(context, "phone/queue/playlist_btn_n.png", viewHandler.cellBG_RLayout, 3);
		}		
		
		if(startDragPosition>holdDragPosition&&position>holdDragPosition&&position<=startDragPosition){
			viewHandler.NameUP_TextView.setText("position = "+dataList.get(position-1).getTitle());
		}else if(startDragPosition<holdDragPosition&&position<holdDragPosition&&position>=startDragPosition){
			Log.i(TAG, "startDragPosition = "+startDragPosition);
			Log.i(TAG, "holdDragPosition = "+holdDragPosition);
			Log.i(TAG, "position = "+position);
			if((position+1)<dataList.size()){
				viewHandler.NameUP_TextView.setText(""+dataList.get(position+1).getTitle());
			}			
		}else{
			if((position)<dataList.size()){
				viewHandler.NameUP_TextView.setText(""+dataList.get(position).getTitle());
			}
		}
		//Insert設�?
		if(InsertPosition!=-1){
			if(position ==InsertPosition){
				viewHandler.cell_RLayout.setVisibility(View.INVISIBLE);
				new TKBThreadReadBitMapInAssets(context, "pad/Queqe/drag_bar.png", viewHandler.cellBG_RLayout, 3);
			}else if(position>InsertPosition){
				viewHandler.NameUP_TextView.setText("position = "+dataList.get(position-1).getTitle());			
				viewHandler.cell_RLayout.setVisibility(View.VISIBLE);
				new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_btn_n.png", viewHandler.cellBG_RLayout, 3);
			}else{
				viewHandler.NameUP_TextView.setText("position = "+dataList.get(position).getTitle());
				new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_btn_n.png", viewHandler.cellBG_RLayout, 3);
			}
		}
		
		return convertView;
	}
	
	private class ViewHandler{
		private int position;
		private RelativeLayout cellBG_RLayout;
		private RelativeLayout cell_RLayout;
		private ImageView Delete_ImageView;
		private RelativeLayout Content_RLayout;
		private ImageView Image_ImageView;
		private TextView NameUP_TextView;
		private TextView NameDown_TextView;
		private ImageView Drag_ImageView;
		
		public ViewHandler(View view){
			this.cellBG_RLayout = (RelativeLayout)view.findViewById(R.id.FI_QUEUE_ListView_CellBG_RLayout);
			this.cell_RLayout = (RelativeLayout)view.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout);
			this.Delete_ImageView = (ImageView)view.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout_Delete_ImageView);
			this.Content_RLayout = (RelativeLayout)view.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout_Content_RLayout);
			this.Image_ImageView = (ImageView)view.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout_RLayout_Image_ImageView);
			this.NameUP_TextView = (TextView)view.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout_RLayout_NameUP_TextView);
			this.NameDown_TextView = (TextView)view.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout_RLayout_NameDown_TextView);
			this.Drag_ImageView = (ImageView)view.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout_Drag_ImageView);
			SetDelete_ImageView_LISTNER();
		}
		private void SetDelete_ImageView_LISTNER(){
			this.Delete_ImageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.i(TAG, "delete = "+position);
					//?��?upnpServer
					AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
					//?��?MR Device
					DeviceDisplay MR_Device = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
					
					ServiceId serviceId = new UDAServiceId("AVTransport");
					Service AVTransportService = null;
					//檢查Device �?res
					if(MR_Device!=null){
						//?��?device ??"AVTransport" service
						AVTransportService = MR_Device.getDevice().findService(serviceId);
					}else{
						return;
					}
					ActionArgumentValue[] values = new ActionArgumentValue[2];
					Action action = AVTransportService.getAction("RemoveTrackInQueue");
					ActionArgument InstanceID = action.getInputArgument("InstanceID");
					ActionArgument TrackID = action.getInputArgument("TrackID");
					if(InstanceID!=null&&TrackID!=null){
						values[0] =new ActionArgumentValue(InstanceID, "0");
						values[1] =new ActionArgumentValue(TrackID, dataList.get(position).getId());
						
						ActionInvocation ai = new ActionInvocation(action,values);
						
						ActionCallback RemoveTrackInQueueActionCallBack = new ActionCallback(ai){
							@Override
							public void failure(ActionInvocation arg0, UpnpResponse arg1, String arg2) {
								mlog.info(TAG, "RemoveTrackInQueueActionCallBack failure = "+arg2);
							}
							@Override
							public void success(ActionInvocation arg0) {									
								mlog.info(TAG, "RemoveTrackInQueueActionCallBack success");
							}											
						};
						upnpServer.getControlPoint().execute(RemoveTrackInQueueActionCallBack);
					}				
				}
			});
		}
	}
	private void basicSetView(ViewHandler viewHandler) {		
		//Delete ImageView
		TKBTool.fitsViewLeftMargin(7, viewHandler.Delete_ImageView);
		viewHandler.Delete_ImageView.getLayoutParams().height = TKBTool.getWidth(27);
		TKBTool.fitsViewWidth(53, viewHandler.Delete_ImageView);
		new TKBThreadReadBitMapInAssets(context, "phone/queue/delete.png", viewHandler.Delete_ImageView, 1);
		//Content RLayout
		TKBTool.fitsViewLeftMargin(7, viewHandler.Content_RLayout);
		TKBTool.fitsViewRightMargin(7, viewHandler.Content_RLayout);
		TKBTool.fitsViewHeight(30, viewHandler.Content_RLayout);
		//Image ImageView
		viewHandler.Image_ImageView.getLayoutParams().height = TKBTool.getWidth(36);
		TKBTool.fitsViewWidth(36, viewHandler.Image_ImageView);
		new TKBThreadReadBitMapInAssets(context, "phone/queue/music_nophoto.png", viewHandler.Image_ImageView, 1);
		//NameUP TextView
		TKBTool.fitsViewHeight(18, viewHandler.NameUP_TextView);
		TKBTool.fitsViewTextSize(10, viewHandler.NameUP_TextView);
		TKBTool.fitsViewLeftMargin(5, viewHandler.NameUP_TextView);
		//NameDown TextView
		TKBTool.fitsViewHeight(12, viewHandler.NameDown_TextView);
		TKBTool.fitsViewTextSize(8, viewHandler.NameDown_TextView);
		TKBTool.fitsViewLeftMargin(5, viewHandler.NameDown_TextView);
		//Drag_ImageView
		viewHandler.Drag_ImageView.getLayoutParams().height = TKBTool.getWidth(20);
		TKBTool.fitsViewWidth(17, viewHandler.Drag_ImageView);
		TKBTool.fitsViewRightMargin(15, viewHandler.Drag_ImageView);
		new TKBThreadReadBitMapInAssets(context, "phone/queue/music_icon.png", viewHandler.Drag_ImageView, 1);
	}
	public void SET_Edite(boolean isEdit){
		if(this.isEdit == isEdit ){
			return;
		}else{
			this.isEdit = isEdit;
			this.notifyDataSetChanged();
		}		
	}
	public boolean GET_Edite(){
		return this.isEdit;
	}
	public void SET_DRAG_START_POSITION(int startDragPosition){
		this.startDragPosition = startDragPosition;
		this.holdDragPosition = startDragPosition;
		this.SelectedPosition = -1;
		this.notifyDataSetChanged();
	}
	public void setDragNHoldPosition(int holdDragPosition){
		this.holdDragPosition = holdDragPosition;
		this.notifyDataSetChanged();
	}
	public void SET_SORT(int holdDragPosition){
		this.holdDragPosition = holdDragPosition;	
		
		TrackDO aaa = dataList.get(startDragPosition);
		dataList.remove(startDragPosition);
		dataList.add(holdDragPosition, aaa);
		mlog.info(TAG, "holdDragPosition = "+holdDragPosition);
		mlog.info(TAG, "startDragPosition = "+startDragPosition);
	}
	public void SET_INSERT_POSITION(int InsertPosition){
		mlog.info(TAG, "InsertPosition = "+InsertPosition);
		if(this.InsertPosition == InsertPosition ){
			return;
		}else{
			this.SelectedPosition = -1;
			this.InsertPosition = InsertPosition;			
			this.notifyDataSetChanged();
		}		
	}
	public void SET_SELECTED_POSITION(int SelectedPosition){
		this.SelectedPosition = SelectedPosition;
		this.notifyDataSetChanged();
	}
	public List<TrackDO> GetQueue(){
		return this.dataList;
	}
}
