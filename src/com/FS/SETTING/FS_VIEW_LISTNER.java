package com.FS.SETTING;

import com.tkb.tool.MLog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
	
	public void SET_SPEAKER_EListView_Listner(final FS_SPEAKER_ExpandableListView fS_SPEAKER_EListView) {
		if(device_size==6){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			fS_SPEAKER_EListView.setOnGroupClickListener(new OnGroupClickListener(){
				@Override
				public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
					//設定GSELECTED					
					((FS_SPEAKER_ExpandableListAdapter)parent.getExpandableListAdapter()).SET_GView_SELECTED(groupPosition);
					return true;
				}			
			});			
			fS_SPEAKER_EListView.setOnChildClickListener(new OnChildClickListener(){
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,	int groupPosition, int childPosition, long id) {
					//設定CSELECTED
					((FS_SPEAKER_ExpandableListAdapter)parent.getExpandableListAdapter()).SET_CVIEW_SELECTED(groupPosition, childPosition);
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
