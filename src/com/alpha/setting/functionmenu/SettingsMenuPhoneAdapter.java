package com.alpha.setting.functionmenu;

import java.util.ArrayList;
import java.util.List;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

// FSM_MENU_ListView_BaseAdapter_Phone
public class SettingsMenuPhoneAdapter extends BaseAdapter {
	
	private Context context;
	
	private TKBLog logTKB = new TKBLog();
	
	private static final String tag = "SettingFunctionMenuPhoneAdapter";
	
	private List<String> listMenu = new ArrayList<String>();
	
	private int choosedMenu = -1;
	
	public SettingsMenuPhoneAdapter(Context context){
		this.context = context;		
		this.logTKB.switchLog = true;
		
		setList();
	}
	private void setList(){
		
		if(listMenu != null){
			listMenu.clear();
		}
		listMenu.add("About");
		listMenu.add("Firmware");
		listMenu.add("Network Setup");
		listMenu.add("Identify Speaker");
		listMenu.add("Alarm");
		listMenu.add("Sleep Timer");
		
	}
	public void setChoosedMenu(int choose){
		if(this.choosedMenu!=choose){
			this.choosedMenu = choose;
			this.notifyDataSetChanged();
		}
	}
	public int getChoosedMenu(){
		return this.choosedMenu;
	}
	@Override
	public int getCount() {
		return listMenu.size();
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
			convertView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,TKBTool.getHeight(73)));
			viewHandler = new ViewHandler(convertView);
			basicSetChildView(viewHandler);	
			convertView.setTag(viewHandler);
		}else{
			viewHandler = (ViewHandler)convertView.getTag();
		}
		
		viewHandler.SET_Position(position);	
		
		viewHandler.viewName.setText(listMenu.get(position));
		
		
		logTKB.info(tag, "position = "+position);
		return convertView;
	}
	
	private class ViewHandler{
		private int position;
		
		private RelativeLayout layoutCell;			
		private TextView viewName;
		private ImageView viewImage;
		
		public ViewHandler(View view){
			this.layoutCell = (RelativeLayout)view.findViewById(R.id.FSM_Menu_ListView_Cell_RLayout);
			this.viewName = (TextView)view.findViewById(R.id.FSM_Menu_ListView_RLayout_Name_TextView);
			this.viewImage = (ImageView)view.findViewById(R.id.FSM_Menu_ListView_RLayout_Image_ImageView);
		}
		public void SET_Position(int position){
			this.position = position;			
		}
	}
	
	private void basicSetChildView(ViewHandler viewHandler){
		TKBTool.fitsViewHeight(61, viewHandler.layoutCell);
		new TKBThreadReadStateListInAssets(context, "phone/setting/settings_bar_f.PNG", "phone/setting/settings_bar_n.PNG", viewHandler.layoutCell, 3);
		//Name TextView
		TKBTool.fitsViewLeftMargin(5, viewHandler.viewName);
		TKBTool.fitsViewTextSize(12, viewHandler.viewName);
		//Image ImageView
		TKBTool.fitsViewHeight(9, viewHandler.viewImage);
		viewHandler.viewImage.getLayoutParams().width = TKBTool.getHeight(16);
		TKBTool.fitsViewRightMargin(19, viewHandler.viewImage);
		TKBTool.fitsViewTopMargin(26, viewHandler.viewImage);
		new TKBThreadReadStateListInAssets(context, "phone/playlist/down_f.png", "phone/playlist/down_n.png", viewHandler.viewImage, 1);
	}
}
