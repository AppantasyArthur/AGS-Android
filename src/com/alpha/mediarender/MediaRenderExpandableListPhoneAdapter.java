package com.alpha.mediarender;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alpha.fragments.MediaRendererListFragement;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.parser.GroupVO;
import com.alpha.upnp.parser.GroupVO.Group;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBTool;

// FS_SPEAKER_ExpandableListAdapter_Phone
public class MediaRenderExpandableListPhoneAdapter extends BaseExpandableListAdapter {
	
	private Context context;
	private ExpandableListView EListView;
	private Bitmap arrow_f;
	private Bitmap arrow_n;
	private ImageSpan play_Span;
	private ImageSpan stop_Span;
	private ImageSpan pause_Span;
	private Drawable menu1;
	private Drawable menu2;
	private Drawable menu3;
	
//	private FS_PopupWindow popupWindow;
	
	private MediaRenderExpandableListAdapterListner FSELAListner;
	
	private List<DeviceDisplay> GroupList;
	private static String tag = "MediaRenderExpandableListPhoneAdapter";
	private TKBLog mlog = new TKBLog();
	
	private Handler handler = new Handler(){
		public void handleMessage (Message msg) {
			switch(msg.what){			
			case 0:
				MediaRenderExpandableListPhoneAdapter.this.GroupList.clear();
				MediaRenderExpandableListPhoneAdapter.this.GroupList = GetGroupList();
				MediaRenderExpandableListPhoneAdapter.this.notifyDataSetChanged();
				break;			
			}
		}
	};
	
	public MediaRenderExpandableListPhoneAdapter(Context context,ExpandableListView EListView){
		this.mlog.switchLog = true;
		this.context = context;
		this.EListView = EListView;
		this.GroupList = GetGroupList();
//		this.popupWindow = new FS_PopupWindow(this.context);
		LoadBitmap();
		SetList();
		SetListner();
	}
	private List<DeviceDisplay> GetGroupList(){
		List<DeviceDisplay> list = new ArrayList<DeviceDisplay>();
		for(DeviceDisplay deviceDisplay:((MainFragmentActivity)context).getDeviceDisplayList().getGroupList()){
			list.add(deviceDisplay);
		}
		return list;		
	}
	private void SetList(){
		//分類MRList
		List<DeviceDisplay> MRList = ((MainFragmentActivity)context).getDeviceDisplayList().getMediaRendererList();
		if(MRList==null||MRList.size()==0){
			return;
		}
	}
	private void SetListner(){
		FSELAListner = new MediaRenderExpandableListAdapterListner(){
			@Override
			public void SetPositionChange() {
				handler.obtainMessage(0).sendToTarget();
				mlog.info(tag, "SetPositionChange");
			}
		};
		((MainFragmentActivity)context).getDeviceDisplayList().setSpeakerListner(FSELAListner);
	}
	private void LoadBitmap(){
		this.arrow_f = TKBTool.readBitMapInAssets(context, "phone/speaker/arrow_close.PNG");
		this.arrow_n = TKBTool.readBitMapInAssets(context, "phone/speaker/arrow_open.png");
		this.play_Span = new ImageSpan(context, Bitmap.createScaledBitmap(TKBTool.readBitMapInAssets(context, "phone/speaker/play_icon.png"), TKBTool.getWidth(9), TKBTool.getWidth(14), false),ImageSpan.ALIGN_BASELINE);
		this.stop_Span = new ImageSpan(context, Bitmap.createScaledBitmap(TKBTool.readBitMapInAssets(context, "phone/speaker/stop_icon.png"), TKBTool.getWidth(9), TKBTool.getWidth(14), false),ImageSpan.ALIGN_BASELINE);
		this.pause_Span = new ImageSpan(context, Bitmap.createScaledBitmap(TKBTool.readBitMapInAssets(context, "phone/speaker/pause_icon.png"), TKBTool.getWidth(9), TKBTool.getWidth(14), false),ImageSpan.ALIGN_BASELINE);
		
		this.menu1 = new BitmapDrawable(context.getResources(),TKBTool.readBitMapInAssets(context, "phone/speaker/menu_top.PNG"));
		this.menu2 = new BitmapDrawable(context.getResources(),TKBTool.readBitMapInAssets(context, "phone/speaker/menu_center.PNG"));
		this.menu3 = new BitmapDrawable(context.getResources(),TKBTool.readBitMapInAssets(context, "phone/speaker/menu_botton.PNG"));
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
			convertView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			viewHandler = new CViewHandler(convertView);
			basicSetChildView(viewHandler);	
			convertView.setTag(viewHandler);
		}else{
			viewHandler = (CViewHandler)convertView.getTag();
		}
		viewHandler.SET_Position(groupPosition, childPosition);
		
		if(childPosition==0&&!isLastChild){
			//第一個
			TKBTool.fitsViewHeight(42,viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(this.menu1);
		}else if(isLastChild){
			//最後一個
			TKBTool.fitsViewHeight(42,viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(this.menu3);
		}else{
			//其他
			TKBTool.fitsViewHeight(37,viewHandler.CCell_RLayout);
			viewHandler.CCell_RLayout.setBackgroundDrawable(this.menu2);
		}
		try{
			
			boolean alive = GroupList.get(groupPosition).getGroupVO().getGroup().getMembers().get(childPosition).isAlive();
			if(alive){
				viewHandler.Name_TextView.setTextColor(Color.parseColor("white"));
			}else{
				viewHandler.Name_TextView.setTextColor(Color.parseColor("gray"));
			}
			
			viewHandler.Name_TextView.setText(GroupList.get(groupPosition).getGroupVO().getGroup().getMembers().get(childPosition).getName());
		}catch(Exception e){
			Log.e(tag, e.getMessage());
		}
		mlog.info(tag, "Gposition = "+groupPosition+"childPosition = "+childPosition);
		return convertView;
	}
	private void basicSetChildView(CViewHandler viewHandler){
		//EListView_GCell_RLayout		
		TKBTool.fitsViewWidth(277, viewHandler.CCell_RLayout);
		//Name_TextView
		TKBTool.fitsViewTextSize(8, viewHandler.Name_TextView);
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
		DeviceDisplay deviceDisplay = GroupList.get(groupPosition);		
		return deviceDisplay.getGroupVO().getGroup().getMembers().size();		
	}
	
	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getGroupPosition(DeviceDisplay deviceDisplay){		
		return GroupList.indexOf(deviceDisplay);		
	}
	
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return GroupList.size();
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
			new ClickListener(viewHandler);
			
		}else{
			viewHandler = (GViewHandler)convertView.getTag();
		}
		//設定position
		viewHandler.SET_Position(groupPosition);
		
		int ChildrenCount = this.getChildrenCount(groupPosition);
		//group or single
		if(ChildrenCount>0){
			//Group
			convertView.getLayoutParams().height = TKBTool.getHeight(110);
			TKBTool.fitsViewHeight(87, viewHandler.GCell_RLayout);
			TKBTool.fitsViewTopMargin(8,viewHandler.Name_TextView);
			TKBTool.fitsViewTopMargin(19, viewHandler.Indicator_ImageView);
			TKBTool.fitsViewTopMargin(19, viewHandler.AddChildItem_ImageButton);
			if(viewHandler.Indicator_ImageView.getVisibility()!=View.VISIBLE){
				viewHandler.Indicator_ImageView.setVisibility(View.VISIBLE);
			}
		}else{
			//Single
			convertView.getLayoutParams().height = TKBTool.getHeight(80);
			TKBTool.fitsViewHeight(65, viewHandler.GCell_RLayout);
			TKBTool.fitsViewTopMargin(1,viewHandler.Name_TextView);
			TKBTool.fitsViewTopMargin(13, viewHandler.AddChildItem_ImageButton);
			TKBTool.fitsViewTopMargin(13, viewHandler.Indicator_ImageView);
			if(viewHandler.Indicator_ImageView.getVisibility()!=View.GONE){
				viewHandler.Indicator_ImageView.setVisibility(View.GONE);
			}
		}
		//設定ChildItem_ImageButton
		if(GroupList.get(groupPosition).getMMDevice()!=null){
			//有MMDevice
			viewHandler.AddChildItem_ImageButton.setVisibility(View.VISIBLE);
		}else{
			//無MMDevice
			viewHandler.AddChildItem_ImageButton.setVisibility(View.GONE);
		}
		//設定selected
		if(((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer()!=null&&((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer().equals(GroupList.get(groupPosition))){
			if(ChildrenCount>0){
				new TKBThreadReadBitMapInAssets(context, "phone/speaker/group_f.png", viewHandler.GCell_RLayout, 3);
			}else{
				new TKBThreadReadBitMapInAssets(context, "phone/speaker/group_singel_f.png", viewHandler.GCell_RLayout, 3);
			}
		}else{
			if(ChildrenCount>0){
				new TKBThreadReadBitMapInAssets(context, "phone/speaker/group_n.png", viewHandler.GCell_RLayout, 3);
			}else{
				new TKBThreadReadBitMapInAssets(context, "phone/speaker/group_singel_n.png", viewHandler.GCell_RLayout, 3);
			}	
		}		
		//設定Indicator 圖片
		if(isExpanded){
			viewHandler.Indicator_ImageView.setImageBitmap(arrow_n);
		}else{
			viewHandler.Indicator_ImageView.setImageBitmap(arrow_f);
		}	
		
		//設定跑馬燈內容
		String playModeString = GroupList.get(groupPosition).getEventHandler().GetTransportState();
		String titleString = GroupList.get(groupPosition).getEventHandler().GetMetaDataTitle();
		
		RunStateHandler runStateHandler = new RunStateHandler(playModeString, titleString, viewHandler.RunState_TextView);
		setRunState(runStateHandler);

		viewHandler.RunState_TextView.setSelected(true);
		
		
		//viewHandler.Name_TextView.setText(GroupList.get(groupPosition).getDevice().getDetails().getFriendlyName());
		//mlog.info(TAG, "Gposition = "+groupPosition);
		
		// is group
		if(ChildrenCount > 0)
			viewHandler.Name_TextView.setText(GroupList.get(groupPosition).getGroupVO().getGroup().getName());
		else
			//viewHandler.Name_TextView.setText(GroupList.get(groupPosition).getDevice().getDetails().getFriendlyName());
			viewHandler.Name_TextView.setText(GroupList.get(groupPosition).getGroupVO().getName());
		
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
		TKBTool.fitsViewWidth(277, viewHandler.GCell_RLayout);
		//Name_TextView
		TKBTool.fitsViewTextSize(14, viewHandler.Name_TextView);
		TKBTool.fitsViewHeight(40, viewHandler.Name_TextView);
		TKBTool.fitsViewLeftMargin(10, viewHandler.Name_TextView);		
		//AddChildItem ImageView
		TKBTool.fitsViewWidth(21, viewHandler.AddChildItem_ImageButton);
		viewHandler.AddChildItem_ImageButton.getLayoutParams().height = TKBTool.getWidth(22);		
		TKBTool.fitsViewRightMargin(5, viewHandler.AddChildItem_ImageButton);	
		new TKBThreadReadBitMapInAssets(context, "phone/speaker/group_icon.PNG", viewHandler.AddChildItem_ImageButton, 2);
		//Indicator TextView
		viewHandler.Indicator_ImageView.getLayoutParams().height = TKBTool.getWidth(22);
		TKBTool.fitsViewWidth(21, viewHandler.Indicator_ImageView);
		TKBTool.fitsViewRightMargin(5, viewHandler.Indicator_ImageView);
		//RunState TextView
		TKBTool.fitsViewHeight(24, viewHandler.RunState_TextView);
		TKBTool.fitsViewWidth(270, viewHandler.RunState_TextView);
		TKBTool.fitsViewTextSize(10, viewHandler.RunState_TextView);	
	}
	private class GViewHandler{
		private int position;
		private RelativeLayout GCell_RLayout;			
		private TextView Name_TextView;
		private ImageButton AddChildItem_ImageButton;
		private ImageView Indicator_ImageView;	
		private TextView RunState_TextView;
		public GViewHandler(View view){
			this.GCell_RLayout = (RelativeLayout)view.findViewById(R.id.FS_SPEAKER_EListView_GCell_RLayout);
			this.Name_TextView = (TextView)view.findViewById(R.id.FS_SPEAKER_EListView_GCell_RLayout_Name_TextView);
			this.AddChildItem_ImageButton = (ImageButton)view.findViewById(R.id.FS_SPEAKER_EListView_GCell_RLayout_AddChildItem_ImageButton);
			this.Indicator_ImageView = (ImageView)view.findViewById(R.id.FS_SPEAKER_EListView_GCell_RLayout_Indicator_ImageView);
			this.RunState_TextView = (TextView)view.findViewById(R.id.FS_SPEAKER_EListView_GCell_RLayout_RunState_TextView);
		}
		public void SET_Position(int position){
			this.position = position;
		}
	}
	private class ClickListener {
		private GViewHandler viewHandler;
		public ClickListener(GViewHandler viewHandler){
			this.viewHandler = viewHandler;
			//Indicator 展開 收起
			viewHandler.Indicator_ImageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(ClickListener.this.viewHandler.position>=0&&!EListView.isGroupExpanded(ClickListener.this.viewHandler.position)){
						//展開
						ClickListener.this.viewHandler.Indicator_ImageView.setImageBitmap(arrow_n);
						EListView.expandGroup(ClickListener.this.viewHandler.position,true);
					}else{
						//收起
						EListView.collapseGroup(ClickListener.this.viewHandler.position);						
						ClickListener.this.viewHandler.Indicator_ImageView.setImageBitmap(arrow_f);
					}										
				}
			});
			//AddChildItem Touch 兩態
			viewHandler.AddChildItem_ImageButton.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch(event.getAction()){
					case MotionEvent.ACTION_DOWN:
						new TKBThreadReadBitMapInAssets(context, "phone/speaker/group_icon.PNG", v, 1);
						break;
					case MotionEvent.ACTION_UP:
						new TKBThreadReadBitMapInAssets(context, "phone/speaker/group_icon.PNG", v, 1);
						break;
					case MotionEvent.ACTION_CANCEL:
						new TKBThreadReadBitMapInAssets(context, "phone/speaker/group_icon.PNG", v, 1);
						break;
					}
					return false;
				}
			});
			//呼叫 AddList
			viewHandler.AddChildItem_ImageButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					MediaRendererListFragement fragment_Speaker = ((MainFragmentActivity)context).GETFragment_Speaker();
					if(fragment_Speaker!=null&&!fragment_Speaker.CheckTITLE2_1_RLayoutIsShown()){
						List<GroupVO> groupVOList = new ArrayList<GroupVO>();
						for(int i =0;i<GroupList.size();i++){
							if(i==ClickListener.this.viewHandler.position){
								Group groups = GroupList.get(i).getGroupVO().getGroup();
								for(int j=1;j<groups.getMembers().size();j++){
									groupVOList.add(groups.getMembers().get(j));
								}
							}else{
								GroupVO groupVO = GroupList.get(i).getGroupVO();
								if(groupVO.isStandalone()){
									groupVOList.add(groupVO);
								}							
							}
						}
						Log.i(tag, "groupVOList size = "+groupVOList.size());
						
						fragment_Speaker.SetOptionButtons(groupVOList);
						fragment_Speaker.SetAddDeviceDisplay(GroupList.get(ClickListener.this.viewHandler.position));
						fragment_Speaker.ShowViewContent_ViewFlipperDisplay(0, R.animator.translate_bottom_in, R.animator.translate_top_out);
					}
				}
			});
		}
	}
	
	public void setRunState(RunStateHandler runStateHandler){
		
		SpannableStringBuilder spannalbeStringBuilder = new SpannableStringBuilder();
		
		SpannableString spannableString  = new SpannableString(runStateHandler.MetaData_Title);
		switch(runStateHandler.mode){
		case 0:
			spannableString.setSpan(play_Span, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			break;
		case 1:
			spannableString.setSpan(stop_Span, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			break;
		case 2:
			spannableString.setSpan(pause_Span, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			break;
		}
		spannalbeStringBuilder.append(spannableString);
		runStateHandler.textView.setText(spannalbeStringBuilder);
		runStateHandler.textView.getParent().childDrawableStateChanged(runStateHandler.textView);
	}
	public void SET_GView_SELECTED(int position){
		if(((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer()!=this.GroupList.get(position)){
			((MainFragmentActivity)context).getDeviceDisplayList().setChooseMediaRenderer(this.GroupList.get(position));
		}		
	}
	public void SET_CVIEW_SELECTED(int Gposition, int Cposition){
		if(((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer()!=this.GroupList.get(Gposition)){
			((MainFragmentActivity)context).getDeviceDisplayList().setChooseMediaRenderer(this.GroupList.get(Gposition));
		}		
	}
}
