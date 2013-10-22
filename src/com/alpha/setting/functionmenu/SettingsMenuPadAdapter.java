package com.alpha.setting.functionmenu;

import java.util.ArrayList;
import java.util.List;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

// FSM_MENU_ListView_BaseAdapter_PAD
// 
public class SettingsMenuPadAdapter extends BaseAdapter {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FSM_MENU_ListView_BaseAdapter_PAD";
	
	private List<String> menuList = new ArrayList<String>();
	
	private int choosedMenu = -1;
	
	private Drawable menu1;
	private Drawable menu2;
	private Drawable menu3;
	private Drawable menu4;
	
	public SettingsMenuPadAdapter(Context context){
		this.context = context;		
		this.mlog.switchLog = true;
		
		SetList();
		LoadBitmap();
	}
	private void SetList(){
		if(menuList!=null){
			menuList.clear();
		}
		menuList.add("About");
		menuList.add("Firmrware");
		menuList.add("Network Setup");
		menuList.add("Identify Speaker");
		menuList.add("Alarm");
		menuList.add("Sleep Timer");		
	}
	private void LoadBitmap(){		
		this.menu1 = new BitmapDrawable(context.getResources(),TKBTool.readBitMapInAssets(context, "pad/Settings/Settings_item_n.png"));
		this.menu2 = new BitmapDrawable(context.getResources(),TKBTool.readBitMapInAssets(context, "pad/Settings/Settings_item_f.png"));
		this.menu3 = new BitmapDrawable(context.getResources(),TKBTool.readBitMapInAssets(context, "pad/Playlist/playlist_arrow_n.png"));
		this.menu4 = new BitmapDrawable(context.getResources(),TKBTool.readBitMapInAssets(context, "pad/Playlist/playlist_arrow_f.png"));
	}
	public void setFunctionChoosed(int choose){
		if(this.choosedMenu!=choose){
			this.choosedMenu = choose;
			this.notifyDataSetChanged();
		}
	}
	public int GetChoosedMenu(){
		return this.choosedMenu;
	}
	@Override
	public int getCount() {
		return menuList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHandler viewHandler =null;
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.fsm_menu_listview_cell_pad, null);
			convertView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,TKBTool.getHeight(100)));
			viewHandler = new ViewHandler(convertView);
			basicSetChildView(viewHandler);	
			convertView.setTag(viewHandler);
		}else{
			viewHandler = (ViewHandler)convertView.getTag();
		}
		
		if(position == choosedMenu){
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu2);
			viewHandler.Image_ImageView.setBackgroundDrawable(menu4);
		}else{
			viewHandler.CCell_RLayout.setBackgroundDrawable(menu1);
			viewHandler.Image_ImageView.setBackgroundDrawable(menu3);
		}
		viewHandler.SET_Position(position);	
		
		viewHandler.Name_TextView.setText(menuList.get(position));
		
		
		mlog.info(TAG, "position = "+position);
		return convertView;
	}
	private class ViewHandler{
		private int position;
		
		private RelativeLayout CCell_RLayout;			
		private TextView Name_TextView;
		private ImageView Image_ImageView;
		
		public ViewHandler(View view){
			this.CCell_RLayout = (RelativeLayout)view.findViewById(R.id.FSM_Menu_ListView_Cell_RLayout);
			this.Name_TextView = (TextView)view.findViewById(R.id.FSM_Menu_ListView_RLayout_Name_TextView);
			this.Image_ImageView = (ImageView)view.findViewById(R.id.FSM_Menu_ListView_RLayout_Image_ImageView);
		}
		public void SET_Position(int position){
			this.position = position;			
		}
	}
	private void basicSetChildView(ViewHandler viewHandler){
		TKBTool.fitsViewWidth(230, viewHandler.CCell_RLayout);
		TKBTool.fitsViewHeight(66, viewHandler.CCell_RLayout);
		//Name TextView
		TKBTool.fitsViewLeftMargin(10, viewHandler.Name_TextView);
		TKBTool.fitsViewTextSize(8, viewHandler.Name_TextView);
		//Image_ImageView
		TKBTool.fitsViewHeight(13, viewHandler.Image_ImageView);
		viewHandler.Image_ImageView.getLayoutParams().width = TKBTool.getHeight(7);
		TKBTool.fitsViewRightMargin(15, viewHandler.Image_ImageView);
		TKBTool.fitsViewTopMargin(28, viewHandler.Image_ImageView);
	}
}
