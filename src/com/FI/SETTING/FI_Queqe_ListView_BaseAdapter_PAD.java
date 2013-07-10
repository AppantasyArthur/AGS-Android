package com.FI.SETTING;

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
import org.teleal.cling.support.model.item.Item;

import com.alpha.UPNP.DeviceDisplay;
import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.R;
import com.appantasy.androidapptemplate.event.lastchange.TrackDO;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.Tool;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FI_Queqe_ListView_BaseAdapter_PAD extends BaseAdapter {
	
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FM_Queue_ListView_BaseAdapter";
		
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
				if(FI_Queqe_ListView_BaseAdapter_PAD.this.dataList!=null){
					FI_Queqe_ListView_BaseAdapter_PAD.this.dataList.clear();
					FI_Queqe_ListView_BaseAdapter_PAD.this.notifyDataSetChanged();
				}				
				break;
			case 1:
				if(FI_Queqe_ListView_BaseAdapter_PAD.this.dataList!=null){
					FI_Queqe_ListView_BaseAdapter_PAD.this.dataList.clear();
				}else{
					FI_Queqe_ListView_BaseAdapter_PAD.this.dataList = new ArrayList<TrackDO>();
				}
				List<TrackDO> queueList = (List<TrackDO>)msg.obj;
				for(int i = 0;i< queueList.size();i++){
					mlog.info(TAG, "==========EVEN STAR==========");
					mlog.info(TAG, "Track ID valeu= "+queueList.get(i).getId());
					mlog.info(TAG, "Track Title valeu= "+queueList.get(i).getTitle());
					mlog.info(TAG, "==========EVEN END==========");
					FI_Queqe_ListView_BaseAdapter_PAD.this.dataList.add(queueList.get(i));
				}
				FI_Queqe_ListView_BaseAdapter_PAD.this.notifyDataSetChanged();	
				break;
			}
		}
	};
	public FI_Queqe_ListView_BaseAdapter_PAD(Context context){
		this.context = context;		
		this.mlog.LogSwitch = true;
		
		FI_Queqe_ListView_BaseAdapter_Queqe_Listner queqe_listner = new FI_Queqe_ListView_BaseAdapter_Queqe_Listner(){
			@Override
			public void ClearQueqeList() {				
				handler.obtainMessage(0).sendToTarget();
			}
			@Override
			public void AddQueqeList(List<TrackDO> trackList) {
				handler.obtainMessage(1,trackList).sendToTarget();
				mlog.info(TAG, "AddQueqeList");	
			}			
		};
		((FragmentActivity_Main)context).GETDeviceDisplayList().setQueqe_Listner(queqe_listner);
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
		return null;
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
			convertView.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT, Tool.getHeight(58)));
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
		//排序設定
		if(position==startDragPosition&&startDragPosition==holdDragPosition){
			viewHandler.cell_RLayout.setVisibility(View.INVISIBLE);
			new ThreadReadBitMapInAssets(context, "pad/Queqe/drag_bar.png", viewHandler.cellBG_RLayout, 3);
		}else if(position == holdDragPosition){			
			viewHandler.cell_RLayout.setVisibility(View.INVISIBLE);
			new ThreadReadBitMapInAssets(context, "pad/Queqe/drag_bar.png", viewHandler.cellBG_RLayout, 3);
		}else{
			viewHandler.cell_RLayout.setVisibility(View.VISIBLE);
			new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_btn_n.png", viewHandler.cellBG_RLayout, 3);
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
		//Insert設定
		if(InsertPosition!=-1){
			if(position ==InsertPosition){
				viewHandler.cell_RLayout.setVisibility(View.INVISIBLE);
				new ThreadReadBitMapInAssets(context, "pad/Queqe/drag_bar.png", viewHandler.cellBG_RLayout, 3);
			}else if(position>InsertPosition){
				viewHandler.NameUP_TextView.setText("position = "+dataList.get(position-1).getTitle());			
				viewHandler.cell_RLayout.setVisibility(View.VISIBLE);
				new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_btn_n.png", viewHandler.cellBG_RLayout, 3);
			}else{
				viewHandler.NameUP_TextView.setText("position = "+dataList.get(position).getTitle());
				new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_btn_n.png", viewHandler.cellBG_RLayout, 3);
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
					//取得upnpServer
					AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
					//取得MR Device
					DeviceDisplay MR_Device = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
					
					ServiceId serviceId = new UDAServiceId("AVTransport");
					Service AVTransportService = null;
					//檢查Device 跟 res
					if(MR_Device!=null){
						//取得device 的 "AVTransport" service
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
		Tool.fitsViewLeftMargin(10, viewHandler.Delete_ImageView);
		viewHandler.Delete_ImageView.getLayoutParams().width = Tool.getHeight(19);
		Tool.fitsViewHeight(19, viewHandler.Delete_ImageView);
		new ThreadReadBitMapInAssets(context, "pad/Queqe/queqe_delete.png", viewHandler.Delete_ImageView, 1);
		//Content RLayout
		Tool.fitsViewLeftMargin(10, viewHandler.Content_RLayout);
		Tool.fitsViewRightMargin(10, viewHandler.Content_RLayout);
		//Image ImageView
		viewHandler.Image_ImageView.getLayoutParams().width = Tool.getHeight(40);
		Tool.fitsViewHeight(40, viewHandler.Image_ImageView);
		new ThreadReadBitMapInAssets(context, "pad/Queqe/queqe_nocover.png", viewHandler.Image_ImageView, 1);
		//NameUP TextView
		Tool.fitsViewHeight(34, viewHandler.NameUP_TextView);
		Tool.fitsViewTextSize(8, viewHandler.NameUP_TextView);
		Tool.fitsViewLeftMargin(5, viewHandler.NameUP_TextView);
		//NameDown TextView
		Tool.fitsViewHeight(24, viewHandler.NameDown_TextView);
		Tool.fitsViewTextSize(6, viewHandler.NameDown_TextView);
		Tool.fitsViewLeftMargin(5, viewHandler.NameDown_TextView);
		//Drag_ImageView
		viewHandler.Drag_ImageView.getLayoutParams().width = Tool.getHeight(22);
		Tool.fitsViewHeight(18, viewHandler.Drag_ImageView);
		Tool.fitsViewRightMargin(15, viewHandler.Drag_ImageView);
		new ThreadReadBitMapInAssets(context, "pad/Queqe/queqe_exchange.png", viewHandler.Drag_ImageView, 1);
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
	public void SET_DRAG_HOLD_POSITION(int holdDragPosition){
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
	
}
