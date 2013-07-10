package com.FS.SETTING;

import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;


public class FS_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FS_VIEW_LISTNER";
	private int device_size = 0;
	public FS_VIEW_LISTNER(Context context, int device_size) {
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void ShowTITLE4_IButton_LISTNER(ImageButton ShowTITLE4_IButton,final RelativeLayout TITLE4_RLayout) {
		ShowTITLE4_IButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(TITLE4_RLayout.getVisibility()==View.GONE){
					new ThreadReadBitMapInAssets(context, "pad/PlayBack/playback_arrow_f.png", v, 2);
					TITLE4_RLayout.setVisibility(View.VISIBLE);
				}else{
					new ThreadReadBitMapInAssets(context, "pad/PlayBack/playback_arrow_n.png", v, 2);
					TITLE4_RLayout.setVisibility(View.GONE);
				}
			}
		});
	}
	
	public void SET_SPEAKER_EListView_Listner(final FS_SPEAKER_ExpandableListView fS_SPEAKER_EListView) {
		if(device_size==6){
			//***************************PHONE*********************************	
			fS_SPEAKER_EListView.setOnGroupClickListener(new OnGroupClickListener(){
				@Override
				public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
					//設定GSELECTED					
					((FS_SPEAKER_ExpandableListAdapter_Phone)parent.getExpandableListAdapter()).SET_GView_SELECTED(groupPosition);
					return true;
				}			
			});			
			fS_SPEAKER_EListView.setOnChildClickListener(new OnChildClickListener(){
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,	int groupPosition, int childPosition, long id) {
					//設定CSELECTED
					((FS_SPEAKER_ExpandableListAdapter_Phone)parent.getExpandableListAdapter()).SET_CVIEW_SELECTED(groupPosition, childPosition);
					return true;
				}					
			});
			fS_SPEAKER_EListView.setOnItemLongClickListener(new OnItemLongClickListener(){
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
					if (ExpandableListView.getPackedPositionType(arg3) == ExpandableListView.PACKED_POSITION_TYPE_CHILD){
						//長按 Child Item
						long packedPos = ((ExpandableListView) arg0).getExpandableListPosition(arg2);
						int groupPosition = ExpandableListView.getPackedPositionGroup(packedPos);
						int childPosition = ExpandableListView.getPackedPositionChild(packedPos);												
					}else if(ExpandableListView.getPackedPositionType(arg3) == ExpandableListView.PACKED_POSITION_TYPE_GROUP){
						//長按 Group Item
						long packedPos = ((ExpandableListView) arg0).getExpandableListPosition(arg2);
						int groupPosition = ExpandableListView.getPackedPositionGroup(packedPos);			
					}
					
					return true;
				}				
			});
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			fS_SPEAKER_EListView.setOnGroupClickListener(new OnGroupClickListener(){
				@Override
				public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
					//設定GSELECTED					
					((FS_SPEAKER_ExpandableListAdapter_Pad)parent.getExpandableListAdapter()).SET_GView_SELECTED(groupPosition);
					return true;
				}			
			});			
			fS_SPEAKER_EListView.setOnChildClickListener(new OnChildClickListener(){
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,	int groupPosition, int childPosition, long id) {
					//設定CSELECTED
					((FS_SPEAKER_ExpandableListAdapter_Pad)parent.getExpandableListAdapter()).SET_CVIEW_SELECTED(groupPosition, childPosition);
					return true;
				}					
			});
			fS_SPEAKER_EListView.setOnItemLongClickListener(new OnItemLongClickListener(){
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
					if (ExpandableListView.getPackedPositionType(arg3) == ExpandableListView.PACKED_POSITION_TYPE_CHILD){
						//長按 Child Item
						long packedPos = ((ExpandableListView) arg0).getExpandableListPosition(arg2);
						int groupPosition = ExpandableListView.getPackedPositionGroup(packedPos);
						int childPosition = ExpandableListView.getPackedPositionChild(packedPos);												
					}else if(ExpandableListView.getPackedPositionType(arg3) == ExpandableListView.PACKED_POSITION_TYPE_GROUP){
						//長按 Group Item
						long packedPos = ((ExpandableListView) arg0).getExpandableListPosition(arg2);
						int groupPosition = ExpandableListView.getPackedPositionGroup(packedPos);			
					}
					
					return true;
				}				
			});
			//***************************PAD*********************************	
		}		
	}
}
