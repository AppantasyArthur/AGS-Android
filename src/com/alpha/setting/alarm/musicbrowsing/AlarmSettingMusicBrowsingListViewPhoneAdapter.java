package com.alpha.setting.alarm.musicbrowsing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.types.UDAServiceType;
import org.teleal.cling.support.contentdirectory.callback.Browse;
import org.teleal.cling.support.model.BrowseFlag;
import org.teleal.cling.support.model.DIDLContent;
import org.teleal.cling.support.model.SortCriterion;
import org.teleal.cling.support.model.container.Container;
import org.teleal.cling.support.model.item.Item;

import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.parser.TrackDO;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

// FSAl_Music_ListView_BaseAdapter_Phone
public class AlarmSettingMusicBrowsingListViewPhoneAdapter extends BaseAdapter {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "AlarmSettingMusicBrowsingListViewPhoneAdapter";	
	//==============類別清單================
	private List<String> CategoryList;
	//====================================
	//===========MediaServer==============
	private List<DeviceDisplay> DeviceList;
	private List<Container> ContainerList;
	private List<Item> MusicTrackList;
	//====================================
	//===========AGSPlayList==============
	private List<JSONObject> LocalNameList;
	private List<TrackDO> LocalMusicTrackList;
	//====================================
	private AlarmSettingMusicBrowsingAdapterListener FSALMLBListner;	
	private List<String> ParentID = new ArrayList<String>();
	private Device chooseDevice;
	private Handler handler = new Handler(){
		public void handleMessage (Message msg) {
			switch(msg.what){
			case 0:
				if(DeviceList!=null){
					DeviceList.add((DeviceDisplay)msg.obj);
					if(ParentID.size()>=1&&ParentID.get(0).equals("0")){						
						AlarmSettingMusicBrowsingListViewPhoneAdapter.this.notifyDataSetChanged();
					}					
				}
				break;
			case 1:
				if(DeviceList!=null){
					DeviceList.remove((DeviceDisplay)msg.obj);
					if(ParentID.size()>=1&&ParentID.get(0).equals("0")){
						AlarmSettingMusicBrowsingListViewPhoneAdapter.this.notifyDataSetChanged();
					}	
				}
				break;	
			case 2:
				if(ParentID.size()==1&&ParentID.get(0).equals("1")){
					AlarmSettingMusicBrowsingListViewPhoneAdapter.this.notifyDataSetChanged();
				}
				break;
			case 10:
				
				AlarmSettingMusicBrowsingListViewPhoneAdapter.this.notifyDataSetChanged();
				break;
			case 11:
				FileContent fileContent = (FileContent)msg.obj;
				if(fileContent.ParentID!=null){
					AlarmSettingMusicBrowsingListViewPhoneAdapter.this.ParentID.add(fileContent.ParentID);
				}		
				AlarmSettingMusicBrowsingListViewPhoneAdapter.this.ContainerList.clear();
				AlarmSettingMusicBrowsingListViewPhoneAdapter.this.MusicTrackList.clear();
				if(fileContent.ContainerList!=null){
					for(int i=0;i<fileContent.ContainerList.size();i++){
						AlarmSettingMusicBrowsingListViewPhoneAdapter.this.ContainerList.add(fileContent.ContainerList.get(i));
					}
				}
				if(fileContent.Itemlist!=null){
					for(int i=0;i<fileContent.Itemlist.size();i++){
						Item item = fileContent.Itemlist.get(i);
						if(item.getClass().getName().equals("org.teleal.cling.support.model.item.MusicTrack")||item.getClass().getName().equals("org.teleal.cling.support.model.item.AudioItem")){
							AlarmSettingMusicBrowsingListViewPhoneAdapter.this.MusicTrackList.add(item);
						}
					}
				}	
				AlarmSettingMusicBrowsingListViewPhoneAdapter.this.notifyDataSetChanged();
				break;
			case 13:
				View view = (View)msg.obj;				
				if(view!=null){					
					view.setVisibility(View.VISIBLE);
				}
				break;
			case 14:
				View view1 = (View)msg.obj;
				if(view1!=null){
					view1.setVisibility(View.GONE);
				}
				break;
			}
		}
	};
	public AlarmSettingMusicBrowsingListViewPhoneAdapter(Context context){
		this.context = context;		
		this.mlog.switchLog = true;
		this.DeviceList = new ArrayList<DeviceDisplay>();
		this.ContainerList =  new ArrayList<Container>();
		this.MusicTrackList = new ArrayList<Item>();
		SetList();
		SetListner();		
	}
	private void SetList(){
		//?��??��? ?��??�差
		CategoryList = new ArrayList<String>();		
		CategoryList.add("Media Server");
		CategoryList.add("AGS PlayList");
		
		//?��?MRList
		List<DeviceDisplay> MSList = ((MainFragmentActivity)context).getDeviceDisplayList().getMediaServerList();
		if(MSList!=null){
			for(int i =0;i<MSList.size();i++){
				DeviceList.add(MSList.get(i));
			}			
		}
		GetLocalNameList();
	}
	private void SetListner(){
		FSALMLBListner = new AlarmSettingMusicBrowsingAdapterListener(){
			@Override
			public void addMediaServer(DeviceDisplay deviceDisplay) {
				handler.obtainMessage(0, deviceDisplay).sendToTarget();
				mlog.info(TAG, "AddMediaServer");
			}

			@Override
			public void removeMediaServer(DeviceDisplay deviceDisplay) {
				handler.obtainMessage(1, deviceDisplay).sendToTarget();
				mlog.info(TAG, "RemoveMediaServer");
			}			
		};
		((MainFragmentActivity)context).getDeviceDisplayList().setFSAl_Music_ListView_BaseAdapter_Listner(FSALMLBListner);
	}
	@Override
	public int getCount() {
		if(ParentID.size()>1){
			int categoryNumber = Integer.valueOf(ParentID.get(0));
			switch(categoryNumber){
			case 0:
				//Media Server
				int size = ContainerList.size()+MusicTrackList.size();
				return size; 			
			case 1:
				//AGS PlayList				
				return this.LocalMusicTrackList.size();
			}
		}else if(ParentID.size()==1){	
			
			int categoryNumber = Integer.valueOf(ParentID.get(0));
			switch(categoryNumber){
			case 0:
				//Media Server
				return this.DeviceList.size();					
			case 1:
				//AGS PlayList
				return LocalNameList.size();					
			}			
		}else if(ParentID.size()==0){
			return CategoryList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHandler viewHandler = null;
		if(convertView==null){			
			convertView = LayoutInflater.from(context).inflate(R.layout.fsal_music_listview_cell_pad, null);
			convertView.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			viewHandler = new ViewHandler(convertView);
			basicSetView(viewHandler);
			convertView.setTag(viewHandler);
		}else{
			viewHandler = (ViewHandler)convertView.getTag();
		}
		
		
		if(ParentID.size()>1){
			int categoryNumber = Integer.valueOf(ParentID.get(0));
			switch(categoryNumber){
			case 0:
				//Media Server
				if(position<ContainerList.size()){
					//顯示File
					viewHandler.cell_RLayout_Name_TextView.setText(ContainerList.get(position).getTitle());
					viewHandler.kindOfItme = 2;
					viewHandler.object = ContainerList.get(position);
					viewHandler.cell_RLayout_Image_ImageView.setVisibility(View.VISIBLE);
					viewHandler.cell_RLayout_Tick_ImageView.setVisibility(View.INVISIBLE);
				}else{
					int musicPosition = position-ContainerList.size();
					//顯示Leaf
					viewHandler.cell_RLayout_Name_TextView.setText(MusicTrackList.get(musicPosition).getTitle());
					viewHandler.kindOfItme = 3;
					viewHandler.object = MusicTrackList.get(musicPosition);
					viewHandler.cell_RLayout_Image_ImageView.setVisibility(View.GONE);
					//?�否顯示Tick
					if(true){
						viewHandler.cell_RLayout_Tick_ImageView.setVisibility(View.VISIBLE);
					}else{
						viewHandler.cell_RLayout_Tick_ImageView.setVisibility(View.INVISIBLE);
					}
				}		
				break;			
			case 1:
				//AGS PlayList
				viewHandler.cell_RLayout_Name_TextView.setText(this.LocalMusicTrackList.get(position).getTitle());
				viewHandler.kindOfItme = 5;
				viewHandler.object = this.LocalMusicTrackList.get(position);
				viewHandler.cell_RLayout_Image_ImageView.setVisibility(View.GONE);
				//?�否顯示Tick
				if(true){
					viewHandler.cell_RLayout_Tick_ImageView.setVisibility(View.VISIBLE);
				}else{
					viewHandler.cell_RLayout_Tick_ImageView.setVisibility(View.INVISIBLE);
				}
				break;		
			}	
		}else if(ParentID.size()==1){
			//第�?層顯�?			
			int categoryNumber = Integer.valueOf(ParentID.get(0));
			switch(categoryNumber){
			case 0:
				//Media Server
				viewHandler.cell_RLayout_Name_TextView.setText(this.DeviceList.get(position).getDevice().getDetails().getFriendlyName());
				viewHandler.kindOfItme = 1;	
				viewHandler.object = this.DeviceList.get(position).getDevice();
				viewHandler.cell_RLayout_Image_ImageView.setVisibility(View.VISIBLE);
				viewHandler.cell_RLayout_Tick_ImageView.setVisibility(View.INVISIBLE);
				break;			
			case 1:
				//AGS PlayList
				viewHandler.cell_RLayout_Name_TextView.setText(this.LocalNameList.get(position).optString("Name"));
				viewHandler.kindOfItme = 4;	
				viewHandler.object = this.LocalNameList.get(position);
				viewHandler.cell_RLayout_Image_ImageView.setVisibility(View.VISIBLE);
				viewHandler.cell_RLayout_Tick_ImageView.setVisibility(View.INVISIBLE);
				break;					
			}					
		}else{
			//�?0 層顯�?			
			viewHandler.cell_RLayout_Name_TextView.setText(CategoryList.get(position));
			viewHandler.kindOfItme = 0;	
			viewHandler.object = position;
			viewHandler.cell_RLayout_Image_ImageView.setVisibility(View.VISIBLE);
			viewHandler.cell_RLayout_Tick_ImageView.setVisibility(View.INVISIBLE);
		}
		
		if(position==0&&!(position==(this.getCount()-1))){
			//第�???			
			TKBTool.fitsViewHeight(30, viewHandler.cell_RLayout);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_01_f.png","pad/Settings/alarm_box_01_n.png", viewHandler.cell_RLayout, 3);
			
		}else if(position==0&&(position==(this.getCount()-1))){
			//?��?�??
			TKBTool.fitsViewHeight(34, viewHandler.cell_RLayout);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_02_f.png","pad/Settings/alarm_box_02_n.png", viewHandler.cell_RLayout, 3);
		}else if((position==(this.getCount()-1))){
			//???�??
			TKBTool.fitsViewHeight(33, viewHandler.cell_RLayout);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_03_f.png","pad/Settings/alarm_box_03_n.png", viewHandler.cell_RLayout, 3);
		}else{
			//?��?
			TKBTool.fitsViewHeight(31, viewHandler.cell_RLayout);
			new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_02_f.png","pad/Settings/alarm_box_02_n.png", viewHandler.cell_RLayout, 3);
		}
		mlog.info(TAG, "position = "+position);
		return convertView;
	}
	
	public class ViewHandler{
		public int kindOfItme = 0;//0= Category 1=Device 2=File 3=item 4 = NameList 5 = TrackList
		public Object object;		
		private RelativeLayout cellBG_RLayout;
		private RelativeLayout cell_RLayout;
		private TextView cell_RLayout_Name_TextView;
		private ImageView cell_RLayout_Tick_ImageView;
		private ImageView cell_RLayout_Image_ImageView;
		
		
		public ViewHandler(View view){
			this.cellBG_RLayout = (RelativeLayout)view.findViewById(R.id.FSAl_Music_ListView_CellBG_RLayout);
			this.cell_RLayout = (RelativeLayout)view.findViewById(R.id.FSAl_Music_ListView_Cell_RLayout);
			this.cell_RLayout_Name_TextView = (TextView)view.findViewById(R.id.FSAl_Music_ListView_Cell_RLayout_Name_TextView);
			this.cell_RLayout_Tick_ImageView = (ImageView)view.findViewById(R.id.FSAl_Music_ListView_Cell_RLayout_Tick_ImageView);
			this.cell_RLayout_Image_ImageView = (ImageView)view.findViewById(R.id.FSAl_Music_ListView_Cell_RLayout_Image_ImageView);
		}
	}
	private void basicSetView(ViewHandler viewHandler) {
		//cell_RLayout_Name_TextView			
		TKBTool.fitsViewLeftMargin(19, viewHandler.cell_RLayout_Name_TextView);
		TKBTool.fitsViewTextSize(10, viewHandler.cell_RLayout_Name_TextView);
		//cell_RLayout_Tick_ImageView
		TKBTool.fitsViewHeight(18, viewHandler.cell_RLayout_Tick_ImageView);
		viewHandler.cell_RLayout_Tick_ImageView.getLayoutParams().width = TKBTool.getHeight(32);
		TKBTool.fitsViewRightMargin(35, viewHandler.cell_RLayout_Tick_ImageView);
		new TKBThreadReadStateListInAssets(context, "pad/Settings/pick_f.png","pad/Settings/pick_n.png", viewHandler.cell_RLayout_Tick_ImageView, 1);
		//cell_RLayout_Image_ImageView
		TKBTool.fitsViewHeight(9, viewHandler.cell_RLayout_Image_ImageView);
		viewHandler.cell_RLayout_Image_ImageView.getLayoutParams().width = TKBTool.getHeight(16);
		TKBTool.fitsViewRightMargin(10, viewHandler.cell_RLayout_Image_ImageView);
		new TKBThreadReadStateListInAssets(context, "phone/playlist/down_f.png", "phone/playlist/down_n.png", viewHandler.cell_RLayout_Image_ImageView, 1);
			
	}
	//?�容?��?
	//類別?��?
	public void ShowLevelOne(Button MusicBack_Button ,int ParentID){
		this.ParentID.add(""+ParentID);
		handler.obtainMessage(10).sendToTarget();
		handler.obtainMessage(13, MusicBack_Button).sendToTarget();
	}
	//Media Server => kind=1?�kind=2
	public void ShowFile(final Button MusicBack_Button,String ParentID,List<Container> ContainerList,List<Item> Itemlist){
		if(ContainerList!=null){
			for(int i =0;i<ContainerList.size();i++){
				Container container = ContainerList.get(i);
				mlog.info(TAG, "PID="+container.getParentID());
				mlog.info(TAG, "ID="+container.getId());
				mlog.info(TAG, "Title="+container.getTitle());
				mlog.info(TAG, "CC"+container.getChildCount());
				mlog.info(TAG, "S="+container.toString());
			}		
		}
		if(Itemlist!=null){
			for(int i =0;i<Itemlist.size();i++){
				Item item = Itemlist.get(i);
				mlog.info(TAG, "Item PID="+item.getParentID());
				mlog.info(TAG, "Item ID="+item.getId());
				mlog.info(TAG, "Item Title="+item.getTitle());
				mlog.info(TAG, "RFID"+item.getRefID());
				mlog.info(TAG, "Item S="+item.toString());
			}
		}		
		handler.obtainMessage(11,new FileContent(ParentID,ContainerList,Itemlist)).sendToTarget();
		handler.obtainMessage(13, MusicBack_Button).sendToTarget();
	}
	//AGS PlayList
	public void GetLocalNameList(){	
		if(this.LocalNameList!=null){
			LocalNameList.clear();
		}
		this.LocalNameList = new ArrayList<JSONObject>();
		SharedPreferences sharedPreferences = context.getSharedPreferences("LocalMusicList", Context.MODE_PRIVATE);
		String strLocalMusicListName = sharedPreferences.getString("LocalMusicList", "[]");
		try {
			
			JSONArray MusicList = new JSONArray(strLocalMusicListName);
			for(int i = 0;i < MusicList.length();i++){
								
				JSONObject o = MusicList.optJSONObject(i); // MusicList.get(i);
				mlog.debug(TAG, o.toString());
				LocalNameList.add(o);
				
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
//		JSONObject MusicList = TKBTool.StringToJSONObject(strLocalMusicListName);
//		Iterator Names = MusicList.keys();
//		while(Names.hasNext()){
//			String name = (String)Names.next();
//			this.LocalNameList.add(name);
//		}
	}
	public void ShowLocalFile(Button MusicBack_Button,String Name){
		this.ParentID.add(""+Name);
		this.LocalMusicTrackList = null;//歸零
		this.LocalMusicTrackList = GetLocalMusicTrackList(Name);
		handler.obtainMessage(10).sendToTarget();
		handler.obtainMessage(13, MusicBack_Button).sendToTarget();
	}
	public List<TrackDO> GetLocalMusicTrackList(String Name){
		List<TrackDO> list = new ArrayList<TrackDO>();
		SharedPreferences sharedPreferences = context.getSharedPreferences("LocalMusicList", Context.MODE_PRIVATE);
		String strLocalMusicListName = sharedPreferences.getString("LocalMusicList", "{}");
		JSONObject MusicList = TKBTool.StringToJSONObject(strLocalMusicListName);		
		JSONArray trackArray = TKBTool.AnalysisJSONArray(MusicList, Name);
		
		if(trackArray!=null){
			for(int i=0;i<trackArray.length();i++){
				JSONObject track = TKBTool.GetJSONObjectFromJSONArray(trackArray, i);
				if(track!=null){
					TrackDO trackDO = new TrackDO();
					trackDO.setId(TKBTool.AnalysisJSONObjectToString(track, "Track_Id"));
					trackDO.setTitle(TKBTool.AnalysisJSONObjectToString(track, "Track_Title"));
					trackDO.setMetaData(TKBTool.AnalysisJSONObjectToString(track, "Track_MetaData"));
					list.add(trackDO);
				}
			}
		}
		return list;
	}
	private class FileContent{
		private String ParentID;
		private List<Container> ContainerList;
		private List<Item> Itemlist;
		public FileContent(String ParentID,List<Container> ContainerList,List<Item> Itemlist){
			this.ParentID = ParentID;
			this.ContainerList = ContainerList;
			this.Itemlist = Itemlist;
		}
	}
	
	//?��?上層
	public void ShowTopDevice(Button MusicBack_Button){
		this.ParentID.clear();
		handler.obtainMessage(10).sendToTarget();
		handler.obtainMessage(14, MusicBack_Button).sendToTarget();
	}
	//?��?�?��	
	public void ShowPrivous(Button MusicBack_Button){		
		if(ParentID.size()>1){
			int categoryNumber = Integer.valueOf(ParentID.get(0));
			switch(categoryNumber){
			case 0:
				//Media Server
				ShowPrivousFile(MusicBack_Button);
				break;			
			case 1:
				//AGS PlayList
				ShowLocalPrivousFile(MusicBack_Button);
				break;
			}
		}else if(ParentID.size()==1){			
			ShowTopDevice(MusicBack_Button);
		}
	}
	//Media Server ?��?�??
	private void ShowPrivousFile(final Button MusicBack_Button){
		AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
		SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+cd:title")};
		Browse browse = new Browse(chooseDevice.findService(new UDAServiceType("ContentDirectory")), this.ParentID.get(ParentID.size()-1), BrowseFlag.DIRECT_CHILDREN, "", 0, 0l, null){
			@Override
			public void received(ActionInvocation arg0,	DIDLContent arg1) {
				AlarmSettingMusicBrowsingListViewPhoneAdapter.this.ParentID.remove(AlarmSettingMusicBrowsingListViewPhoneAdapter.this.ParentID.size()-1);
				List<Container> listC = arg1.getContainers();
				List<Item> listI = arg1.getItems();
				AlarmSettingMusicBrowsingListViewPhoneAdapter.this.ShowFile(MusicBack_Button,null, listC,listI);
				for(int i =0;i<listC.size();i++){
					Container container = listC.get(i);
					mlog.info(TAG, "PID="+container.getParentID());
					mlog.info(TAG, "ID="+container.getId());
					mlog.info(TAG, "Title="+container.getTitle());
					mlog.info(TAG, "CC"+container.getChildCount());
					mlog.info(TAG, "S="+container.toString());
				}								
				for(int i =0;i<listI.size();i++){
					Item item = listI.get(i);
					mlog.info(TAG, "Item PID="+item.getParentID());
					mlog.info(TAG, "Item ID="+item.getId());
					mlog.info(TAG, "Item Title="+item.getTitle());
					mlog.info(TAG, "RFID"+item.getRefID());
					mlog.info(TAG, "Item S="+item.toString());
				}
			}
			@Override
			public void updateStatus(Status arg0) {								
			}
			@Override
			public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {		
				Log.i(TAG, "Container failure = "+arg2);
			}							
		};			
		upnpServer.getControlPoint().execute(browse);
	}
	//AGS PlayList ?��?�??
	private void ShowLocalPrivousFile(Button MusicBack_Button){
		this.ParentID.remove(this.ParentID.size()-1);
		handler.obtainMessage(10).sendToTarget();
		handler.obtainMessage(13, MusicBack_Button).sendToTarget();
	}
	
	
	public void setChooseDevice(Device chooseDevice){
		this.chooseDevice = chooseDevice;
	}
	public Device getChooseDevice(){
		return this.chooseDevice;
	}
}
