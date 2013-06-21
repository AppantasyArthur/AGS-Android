package com.FI.SETTING;

import java.util.ArrayList;
import java.util.List;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.Tool;
import android.content.Context;
import android.graphics.Color;
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

public class FI_Queue_ListView_BaseAdapter extends BaseAdapter {
	
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FM_Queue_ListView_BaseAdapter";
		
	private boolean isEdit = false;
	private int startDragPosition = -1;
	private int holdDragPosition = -1;
	private int InsertPosition = -1;
	private int SelectedPosition = -1;
	private List<String> dataList = new ArrayList<String>();
	public FI_Queue_ListView_BaseAdapter(Context context){
		this.context = context;		
		this.mlog.LogSwitch = true;
		dataList.clear();
		for(int i = 0;i<2;i++){
			dataList.add(""+i);
		}
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
		}else if(position == holdDragPosition){			
			viewHandler.cell_RLayout.setVisibility(View.INVISIBLE);			
		}else{
			viewHandler.cell_RLayout.setVisibility(View.VISIBLE);
		}		
		
		if(startDragPosition>holdDragPosition&&position>holdDragPosition&&position<=startDragPosition){
			viewHandler.NameUP_TextView.setText("position = "+dataList.get(position-1));
		}else if(startDragPosition<holdDragPosition&&position<holdDragPosition&&position>=startDragPosition){
			Log.i(TAG, "startDragPosition = "+startDragPosition);
			Log.i(TAG, "holdDragPosition = "+holdDragPosition);
			Log.i(TAG, "position = "+position);
			if((position+1)<dataList.size()){
				viewHandler.NameUP_TextView.setText("position = "+dataList.get(position+1));
			}			
		}else{
			if((position)<dataList.size()){
				viewHandler.NameUP_TextView.setText("position = "+dataList.get(position));
			}
		}
		//Insert設定
		if(InsertPosition!=-1){
			if(position ==InsertPosition){
				viewHandler.cell_RLayout.setVisibility(View.INVISIBLE);
			}else if(position>InsertPosition){
				viewHandler.NameUP_TextView.setText("position = "+dataList.get(position-1));			
				viewHandler.cell_RLayout.setVisibility(View.VISIBLE);
			}else{
				viewHandler.NameUP_TextView.setText("position = "+dataList.get(position));
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
		}
	}
	private void basicSetView(ViewHandler viewHandler) {		
		new ThreadReadBitMapInAssets(context, "pad/Queqe/drag_bar.png", viewHandler.cellBG_RLayout, 3);
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
		
		String aaa = dataList.get(startDragPosition);
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
