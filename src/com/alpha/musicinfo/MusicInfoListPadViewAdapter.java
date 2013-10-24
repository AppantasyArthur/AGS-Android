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

import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.parser.TrackDO;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBTool;
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

// FM_Queue_ListView_BaseAdapter
public class MusicInfoListPadViewAdapter extends BaseAdapter {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "MusicInfoListPadViewAdapter";
		
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
				if(MusicInfoListPadViewAdapter.this.dataList!=null){
					MusicInfoListPadViewAdapter.this.dataList.clear();
					MusicInfoListPadViewAdapter.this.notifyDataSetChanged();
				}				
				break;
			case 1:
				if(MusicInfoListPadViewAdapter.this.dataList!=null){
					MusicInfoListPadViewAdapter.this.dataList.clear();
				}else{
					MusicInfoListPadViewAdapter.this.dataList = new ArrayList<TrackDO>();
				}
				List<TrackDO> queueList = (List<TrackDO>)msg.obj;
				for(int i = 0;i< queueList.size();i++){
//					mlog.info(tag, "==========EVEN STAR==========");
//					mlog.info(tag, "Track ID valeu= "+queueList.get(i).getId());
//					mlog.info(tag, "Track Title valeu= "+queueList.get(i).getTitle());
//					mlog.info(tag, "==========EVEN END==========");
					MusicInfoListPadViewAdapter.this.dataList.add(queueList.get(i));
				}
				MusicInfoListPadViewAdapter.this.notifyDataSetChanged();	
				break;
			}
		}
	};
	
	public MusicInfoListPadViewAdapter(Context context){
		
		this.context = context;		
		this.mlog.switchLog = true;
		
		MusicInfoQueueListViewBaseAdapterListener queqe_listner = new MusicInfoQueueListViewBaseAdapterListener(){
			@Override
			public void cleanQueueList() {				
				handler.obtainMessage(0).sendToTarget();
			}
			@Override
			public void setQueueList(List<TrackDO> trackList) {
				handler.obtainMessage(1, trackList).sendToTarget();
//				mlog.info(tag, "AddQueqeList");	
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
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHandler viewHandler = null;
		if(convertView==null){			
			convertView = LayoutInflater.from(context).inflate(R.layout.fi_queue_listview_cell_pad, null);
			convertView.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT, TKBTool.getHeight(58)));
			viewHandler = new ViewHandler(convertView);
			basicSetView(viewHandler);
			convertView.setTag(viewHandler);
		}else{
			viewHandler = (ViewHandler)convertView.getTag();
		}	
		viewHandler.position = position;
		
		if(isEdit){
			if(viewHandler.viewDeleteImage.getVisibility()==View.GONE||viewHandler.viewDragImage.getVisibility()==View.GONE){
				viewHandler.viewDeleteImage.setVisibility(View.VISIBLE);
				viewHandler.viewDragImage.setVisibility(View.VISIBLE);				
			}
		}else{
			if(viewHandler.viewDeleteImage.getVisibility()==View.VISIBLE||viewHandler.viewDragImage.getVisibility()==View.VISIBLE){
				viewHandler.viewDeleteImage.setVisibility(View.GONE);
				viewHandler.viewDragImage.setVisibility(View.GONE);				
			}		
		}
		//?��?設�?
		if(position==startDragPosition&&startDragPosition==holdDragPosition){
			viewHandler.layoutCell.setVisibility(View.INVISIBLE);
			new TKBThreadReadBitMapInAssets(context, "pad/Queqe/drag_bar.png", viewHandler.layoutCellBackground, 3);
		}else if(position == holdDragPosition){			
			viewHandler.layoutCell.setVisibility(View.INVISIBLE);
			new TKBThreadReadBitMapInAssets(context, "pad/Queqe/drag_bar.png", viewHandler.layoutCellBackground, 3);
		}else{
			viewHandler.layoutCell.setVisibility(View.VISIBLE);
			new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_btn_n.png", viewHandler.layoutCellBackground, 3);
		}		
		
		if(startDragPosition>holdDragPosition&&position>holdDragPosition&&position<=startDragPosition){
			viewHandler.viewTopNameText.setText("position = "+dataList.get(position-1).getTitle());
		}else if(startDragPosition<holdDragPosition&&position<holdDragPosition&&position>=startDragPosition){
			Log.i(tag, "startDragPosition = "+startDragPosition);
			Log.i(tag, "holdDragPosition = "+holdDragPosition);
			Log.i(tag, "position = "+position);
			if((position+1)<dataList.size()){
				viewHandler.viewTopNameText.setText(""+dataList.get(position+1).getTitle());
			}			
		}else{
			if((position)<dataList.size()){
				viewHandler.viewTopNameText.setText(""+dataList.get(position).getTitle());
			}
		}
		//Insert設�?
		if(InsertPosition!=-1){
			if(position ==InsertPosition){
				viewHandler.layoutCell.setVisibility(View.INVISIBLE);
				new TKBThreadReadBitMapInAssets(context, "pad/Queqe/drag_bar.png", viewHandler.layoutCellBackground, 3);
			}else if(position>InsertPosition){
				viewHandler.viewTopNameText.setText("position = "+dataList.get(position-1).getTitle());			
				viewHandler.layoutCell.setVisibility(View.VISIBLE);
				new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_btn_n.png", viewHandler.layoutCellBackground, 3);
			}else{
				viewHandler.viewTopNameText.setText("position = "+dataList.get(position).getTitle());
				new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_btn_n.png", viewHandler.layoutCellBackground, 3);
			}
		}
		mlog.info(tag, "position = "+position);
		return convertView;
	}
	
	private class ViewHandler{
		
		private int position;
		private RelativeLayout layoutCellBackground;
		private RelativeLayout layoutCell;
		private ImageView viewDeleteImage;
		private RelativeLayout layoutContent;
		private ImageView Image_ImageView;
		private TextView viewTopNameText;
		private TextView viewButtonNameText;
		private ImageView viewDragImage;
		
		public ViewHandler(View view){
			
			this.layoutCellBackground = (RelativeLayout)view.findViewById(R.id.FI_QUEUE_ListView_CellBG_RLayout);
			this.layoutCell = (RelativeLayout)view.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout);
			this.viewDeleteImage = (ImageView)view.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout_Delete_ImageView);
			this.layoutContent = (RelativeLayout)view.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout_Content_RLayout);
			this.Image_ImageView = (ImageView)view.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout_RLayout_Image_ImageView);
			this.viewTopNameText = (TextView)view.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout_RLayout_NameUP_TextView);
			this.viewButtonNameText = (TextView)view.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout_RLayout_NameDown_TextView);
			this.viewDragImage = (ImageView)view.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout_Drag_ImageView);
			
			setDeleteImageViewListener();
			
		}
		
		private void setDeleteImageViewListener(){
			
			this.viewDeleteImage.setOnClickListener(new View.OnClickListener() {
				
				@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
				@Override
				public void onClick(View v) {
					Log.i(tag, "delete = "+position);
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
								mlog.info(tag, "RemoveTrackInQueueActionCallBack failure = "+arg2);
							}
							@Override
							public void success(ActionInvocation arg0) {									
								mlog.info(tag, "RemoveTrackInQueueActionCallBack success");
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
		TKBTool.fitsViewLeftMargin(10, viewHandler.viewDeleteImage);
		viewHandler.viewDeleteImage.getLayoutParams().width = TKBTool.getHeight(58);
		TKBTool.fitsViewHeight(36, viewHandler.viewDeleteImage);
		new TKBThreadReadBitMapInAssets(context, "pad/Queqe/delete.png", viewHandler.viewDeleteImage, 1);
		//Content RLayout
		TKBTool.fitsViewLeftMargin(10, viewHandler.layoutContent);
		TKBTool.fitsViewRightMargin(10, viewHandler.layoutContent);
		//Image ImageView
		viewHandler.Image_ImageView.getLayoutParams().width = TKBTool.getHeight(40);
		TKBTool.fitsViewHeight(40, viewHandler.Image_ImageView);
		new TKBThreadReadBitMapInAssets(context, "pad/Queqe/queqe_nocover.png", viewHandler.Image_ImageView, 1);
		//NameUP TextView
		TKBTool.fitsViewHeight(34, viewHandler.viewTopNameText);
		TKBTool.fitsViewTextSize(8, viewHandler.viewTopNameText);
		TKBTool.fitsViewLeftMargin(5, viewHandler.viewTopNameText);
		//NameDown TextView
		TKBTool.fitsViewHeight(24, viewHandler.viewButtonNameText);
		TKBTool.fitsViewTextSize(6, viewHandler.viewButtonNameText);
		TKBTool.fitsViewLeftMargin(5, viewHandler.viewButtonNameText);
		//Drag_ImageView
		viewHandler.viewDragImage.getLayoutParams().width = TKBTool.getHeight(22);
		TKBTool.fitsViewHeight(18, viewHandler.viewDragImage);
		TKBTool.fitsViewRightMargin(15, viewHandler.viewDragImage);
		new TKBThreadReadBitMapInAssets(context, "pad/Queqe/queqe_exchange.png", viewHandler.viewDragImage, 1);
	}
	public void setIsEditting(boolean isEdit){
		if(this.isEdit == isEdit ){
			return;
		}else{
			this.isEdit = isEdit;
			this.notifyDataSetChanged();
		}		
	}
	public boolean isEditting(){
		return this.isEdit;
	}
	public void setDragStartPosition(int startDragPosition){
		this.startDragPosition = startDragPosition;
		this.holdDragPosition = startDragPosition;
		this.SelectedPosition = -1;
		this.notifyDataSetChanged();
	}
	public void setDragStopPosition(int holdDragPosition){
		this.holdDragPosition = holdDragPosition;
		this.notifyDataSetChanged();
	}
	public void setSort(int holdDragPosition){
		
		this.holdDragPosition = holdDragPosition;	
		
		TrackDO aaa = dataList.get(startDragPosition);
		dataList.remove(startDragPosition);
		dataList.add(holdDragPosition, aaa);
		
		mlog.info(tag, "holdDragPosition = "+holdDragPosition);
		mlog.info(tag, "startDragPosition = "+startDragPosition);
		
	}
	public void setInsertPosition(int InsertPosition){
		mlog.info(tag, "InsertPosition = "+InsertPosition);
		if(this.InsertPosition == InsertPosition ){
			return;
		}else{
			this.SelectedPosition = -1;
			this.InsertPosition = InsertPosition;			
			this.notifyDataSetChanged();
		}		
	}
	public void setSelectedPosition(int SelectedPosition){
		this.SelectedPosition = SelectedPosition;
		this.notifyDataSetChanged();
	}
	public List<TrackDO> getQueue(){
		return this.dataList;
	}
	
}
