package com.FM.SETTING;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FM_Music_ListView_BaseAdapter extends BaseAdapter {
	
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FM_Music_ListView_BaseAdapter";
		
	public FM_Music_ListView_BaseAdapter(Context context){
		this.context = context;		
		this.mlog.LogSwitch = true;
	
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 100;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.fm_music_listview_cell_pad, null);
			convertView.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT, Tool.getHeight(43)));
			viewHandler = new ViewHandler(convertView);
			basicSetView(viewHandler);
			convertView.setTag(viewHandler);
		}else{
			viewHandler = (ViewHandler)convertView.getTag();
		}		
				
		return convertView;
	}
	
	private class ViewHandler{
		private RelativeLayout cellBG_RLayout;
		private RelativeLayout cell_RLayout;
		private TextView cell_RLayout_Name_TextView;
		private ImageView cell_RLayout_Image_ImageView;
		
		public ViewHandler(View view){
			this.cellBG_RLayout = (RelativeLayout)view.findViewById(R.id.FM_Music_ListView_CellBG_RLayout);
			this.cell_RLayout = (RelativeLayout)view.findViewById(R.id.FM_Music_ListView_Cell_RLayout);
			this.cell_RLayout_Name_TextView = (TextView)view.findViewById(R.id.FM_Music_ListView_Cell_RLayout_Name_TextView);
			this.cell_RLayout_Image_ImageView = (ImageView)view.findViewById(R.id.FM_Music_ListView_Cell_RLayout_Image_ImageView);
		}
	}
	private void basicSetView(ViewHandler viewHandler) {
		//cellBG RLayout
		new ThreadReadStateListInAssets(context, "pad/Playlist/playlist_btn.png", "pad/Playlist/playlist_btn_n.png", viewHandler.cell_RLayout, 3);
		//cell_RLayout_Name_TextView
		Tool.fitsViewTopMargin(5, viewHandler.cell_RLayout_Name_TextView);		
		Tool.fitsViewHeight(30, viewHandler.cell_RLayout_Name_TextView);
		Tool.fitsViewLeftMargin(30, viewHandler.cell_RLayout_Name_TextView);
		Tool.fitsViewTextSize(8, viewHandler.cell_RLayout_Name_TextView);
		//cell_RLayout_Image_ImageView
		Tool.fitsViewHeight(13, viewHandler.cell_RLayout_Image_ImageView);
		viewHandler.cell_RLayout_Image_ImageView.getLayoutParams().width = Tool.getHeight(7);
		Tool.fitsViewTopMargin(13, viewHandler.cell_RLayout_Image_ImageView);
		Tool.fitsViewRightMargin(10, viewHandler.cell_RLayout_Image_ImageView);
		new ThreadReadStateListInAssets(context, "pad/Playlist/playlist_arrow_f.png", "pad/Playlist/playlist_arrow_n.png", viewHandler.cell_RLayout_Image_ImageView, 1);
	}
}
