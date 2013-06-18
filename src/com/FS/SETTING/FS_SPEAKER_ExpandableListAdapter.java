package com.FS.SETTING;

import com.alpha.upnpui.R;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.Tool;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FS_SPEAKER_ExpandableListAdapter extends BaseExpandableListAdapter {
	
	private Context context;
	private FS_SPEAKER_ExpandableListView EListView;
	private int GView_SELECTED = -1;
	private int CView_SELECTED = -1;
	private Bitmap arrow_f;
	private Bitmap arrow_n;
	private ImageSpan play_Span;
	private ImageSpan stop_Span;
	private ImageSpan pause_Span;
	
	public FS_SPEAKER_ExpandableListAdapter(Context context,FS_SPEAKER_ExpandableListView EListView){
		this.context = context;
		this.EListView = EListView;
		LoadBitmap();
	}
	private void LoadBitmap(){
		this.arrow_f = Tool.readBitMapInAssets(context, "pad/Speakermanagement/arrow_f.png");
		this.arrow_n = Tool.readBitMapInAssets(context, "pad/Speakermanagement/arrow_n.png");
		this.play_Span = new ImageSpan(context, Bitmap.createScaledBitmap(Tool.readBitMapInAssets(context, "pad/Speakermanagement/status_play.png"), Tool.getHeight(15), Tool.getHeight(18), false));
		this.stop_Span = new ImageSpan(context, Tool.readBitMapInAssets(context, "pad/Speakermanagement/status_stop.png"));
		this.pause_Span = new ImageSpan(context, Tool.readBitMapInAssets(context, "pad/Speakermanagement/status_pause.png"));
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,boolean isLastChild, View convertView, ViewGroup parent) {
		CViewHandler viewHandler =null;
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.fs_speaker_elistview_ccell_pad, null);
			convertView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,Tool.getHeight(38)));
			viewHandler = new CViewHandler(convertView);
			basicSetChildView(viewHandler);	
			convertView.setTag(viewHandler);
		}else{
			viewHandler = (CViewHandler)convertView.getTag();
		}
		viewHandler.SET_Position(groupPosition, childPosition);
		
		if(childPosition==0&&!isLastChild){
			//第一個
			new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/menu_01.png", viewHandler.CCell_RLayout, 3);
		}else if(isLastChild){
			//最後一個
			new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/menu_03.png", viewHandler.CCell_RLayout, 3);
		}else{
			//其他
			new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/menu_02.png", viewHandler.CCell_RLayout, 3);
		}
		return convertView;
	}
	private void basicSetChildView(CViewHandler viewHandler){
		//EListView_GCell_RLayout
		Tool.fitsViewHeight(38,viewHandler.CCell_RLayout);
		Tool.fitsViewWidth(228, viewHandler.CCell_RLayout);
		//Name_TextView
		Tool.fitsViewTextSize(8, viewHandler.Name_TextView);
	}
	private class CViewHandler{
		private int Gposition;
		private int Cposition;
		
		private RelativeLayout CCell_RLayout;			
		private TextView Name_TextView;
		
		public CViewHandler(View view){
			this.CCell_RLayout = (RelativeLayout)view.findViewById(R.id.FS_SPEAKER_EListView_CCell_RLayout);
			this.Name_TextView = (TextView)view.findViewById(R.id.FS_SPEAKER_EListView_CCell_RLayout_Name_TextView);
		}
		public void SET_Position(int Gposition,int Cposition){
			this.Gposition = Gposition;
			this.Cposition = Cposition;
		}
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		if(groupPosition%2==0){
			return 3;
		}else{
			return 0;
		}		
	}
	
	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,	View convertView, ViewGroup parent) {
		GViewHandler viewHandler = null;
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.fs_speaker_elistview_gcell_pad, null);
			convertView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			viewHandler = new GViewHandler(convertView);
			basicSetGroupView(viewHandler);	
			convertView.setTag(viewHandler);
			new Indicator_ClickListener(viewHandler);				
		}else{
			viewHandler = (GViewHandler)convertView.getTag();
		}
		//設定position
		viewHandler.SET_Position(groupPosition);
		
		int ChildrenCount = this.getChildrenCount(groupPosition);
		//group or single
		if(ChildrenCount>0){
			//Group
			convertView.getLayoutParams().height = Tool.getHeight(118);
			Tool.fitsViewHeight(95, viewHandler.GCell_RLayout);
			if(viewHandler.Indicator_ImageView.getVisibility()!=View.VISIBLE){
				viewHandler.Indicator_ImageView.setVisibility(View.VISIBLE);
			}
		}else{
			//Single
			convertView.getLayoutParams().height = Tool.getHeight(97);
			Tool.fitsViewHeight(74, viewHandler.GCell_RLayout);
			if(viewHandler.Indicator_ImageView.getVisibility()!=View.GONE){
				viewHandler.Indicator_ImageView.setVisibility(View.GONE);
			}
		}
		//設定selected
		if(GView_SELECTED == groupPosition){
			if(ChildrenCount>0){
				new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/group_f.png", viewHandler.GCell_RLayout, 3);
			}else{
				new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/single_f.png", viewHandler.GCell_RLayout, 3);
			}
			
		}else{
			if(ChildrenCount>0){
				new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/group_n.png", viewHandler.GCell_RLayout, 3);
			}else{
				new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/single_n.png", viewHandler.GCell_RLayout, 3);
			}				
		}
		
		//設定Indicator 圖片
		if(isExpanded){
			viewHandler.Indicator_ImageView.setImageBitmap(arrow_n);
		}else{
			viewHandler.Indicator_ImageView.setImageBitmap(arrow_f);
		}
		
		//設定跑馬燈內容
		setRunState_TextView_Content(0,"123456789987613000000000000000000000000000000000000000000000000000054321",viewHandler.RunState_TextView);
		viewHandler.RunState_TextView.setSelected(true);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {		
		return true;
	}
	private void basicSetGroupView(GViewHandler viewHandler){
		//EListView_GCell_RLayout
		Tool.fitsViewWidth(231, viewHandler.GCell_RLayout);
		//Name_TextView
		Tool.fitsViewTextSize(10, viewHandler.Name_TextView);
		Tool.fitsViewHeight(32, viewHandler.Name_TextView);
		Tool.fitsViewLeftMargin(10, viewHandler.Name_TextView);
		Tool.fitsViewTopMargin(15,viewHandler.Name_TextView);
		//AddChildItem ImageView
		viewHandler.AddChildItem_ImageView.getLayoutParams().width = Tool.getHeight(33);
		Tool.fitsViewHeight(32, viewHandler.AddChildItem_ImageView);
		Tool.fitsViewRightMargin(20, viewHandler.AddChildItem_ImageView);
		Tool.fitsViewTopMargin(15, viewHandler.AddChildItem_ImageView);
		new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/add_btn_n.png", viewHandler.AddChildItem_ImageView, 1);
		//Indicator TextView
		viewHandler.Indicator_ImageView.getLayoutParams().width = Tool.getHeight(15);
		Tool.fitsViewHeight(14, viewHandler.Indicator_ImageView);
		Tool.fitsViewRightMargin(5, viewHandler.Indicator_ImageView);
		//RunState TextView
		Tool.fitsViewHeight(23, viewHandler.RunState_TextView);
		Tool.fitsViewWidth(220, viewHandler.RunState_TextView);
		Tool.fitsViewTextSize(6, viewHandler.RunState_TextView);	
	}
	private class GViewHandler{
		private int position;
		private RelativeLayout GCell_RLayout;			
		private TextView Name_TextView;
		private ImageView AddChildItem_ImageView;
		private ImageView Indicator_ImageView;	
		private TextView RunState_TextView;
		public GViewHandler(View view){
			this.GCell_RLayout = (RelativeLayout)view.findViewById(R.id.FS_SPEAKER_EListView_GCell_RLayout);
			this.Name_TextView = (TextView)view.findViewById(R.id.FS_SPEAKER_EListView_GCell_RLayout_Name_TextView);
			this.AddChildItem_ImageView = (ImageView)view.findViewById(R.id.FS_SPEAKER_EListView_GCell_RLayout_AddChildItem_ImageView);
			this.Indicator_ImageView = (ImageView)view.findViewById(R.id.FS_SPEAKER_EListView_GCell_RLayout_Indicator_ImageView);
			this.RunState_TextView = (TextView)view.findViewById(R.id.FS_SPEAKER_EListView_GCell_RLayout_RunState_TextView);
		}
		public void SET_Position(int position){
			this.position = position;
		}
	}
	private class Indicator_ClickListener {
		private GViewHandler viewHandler;
		public Indicator_ClickListener(GViewHandler viewHandler){
			this.viewHandler = viewHandler;
			viewHandler.Indicator_ImageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(Indicator_ClickListener.this.viewHandler.position>=0&&!EListView.isGroupExpanded(Indicator_ClickListener.this.viewHandler.position)){
						Indicator_ClickListener.this.viewHandler.Indicator_ImageView.setImageBitmap(arrow_n);
						EListView.expandGroup(Indicator_ClickListener.this.viewHandler.position,true);
					}else{						
						EListView.collapseGroup(Indicator_ClickListener.this.viewHandler.position);						
						Indicator_ClickListener.this.viewHandler.Indicator_ImageView.setImageBitmap(arrow_f);
					}										
				}
			});
			viewHandler.AddChildItem_ImageView.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch(event.getAction()){
					case MotionEvent.ACTION_DOWN:
						new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/add_btn_f.png", v, 1);
						break;
					case MotionEvent.ACTION_UP:
						new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/add_btn_n.png", v, 1);
						break;
					case MotionEvent.ACTION_CANCEL:
						new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/add_btn_n.png", v, 1);
						break;
					}
					return true;
				}
			});
		}
	}
	private void setRunState_TextView_Content(int State,String TextContent,TextView RunState_TextView){
		SpannableStringBuilder spannalbeStringBuilder = new SpannableStringBuilder();
		SpannableString spannableString  = new SpannableString(TextContent);
		switch(State){
		case 0:
			spannableString.setSpan(play_Span, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			break;
		case 1:
			spannableString.setSpan(play_Span, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			break;
		case 2:
			spannableString.setSpan(play_Span, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			break;
		}
		spannalbeStringBuilder.append(spannableString);
		RunState_TextView.setText(spannalbeStringBuilder);
	}
	public void SET_GView_SELECTED(int position){
		this.GView_SELECTED = position;
		this.notifyDataSetChanged();
	}
	public void SET_CVIEW_SELECTED(int Gposition, int Cposition){
		this.GView_SELECTED = Gposition;
		this.CView_SELECTED = Cposition;
		this.notifyDataSetChanged();
	}
}
