package com.FM.SETTING;

import java.util.ArrayList;
import java.util.List;

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
import com.alpha.UPNP.DeviceDisplay;
import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;
import android.content.Context;
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

public class FM_Music_ListView_BaseAdapter_PAD extends BaseAdapter {
	
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FM_Music_ListView_BaseAdapter";	

	private List<DeviceDisplay> DeviceList;
	private List<Container> ContainerList;
	private List<Item> MusicTrackList;
	private FM_Music_ListView_BaseAdapter_Listner FMLBAListner;	
	private List<String> ParentID = new ArrayList<String>();
	private Device chooseDevice;
	private Handler handler = new Handler(){
		public void handleMessage (Message msg) {
			switch(msg.what){
			case 0:
				if(DeviceList!=null){
					DeviceList.add((DeviceDisplay)msg.obj);
					if(ParentID.size()==0){
						FM_Music_ListView_BaseAdapter_PAD.this.notifyDataSetChanged();
					}					
				}
				break;
			case 1:
				if(DeviceList!=null){
					DeviceList.remove((DeviceDisplay)msg.obj);
					if(ParentID.size()==0){
						FM_Music_ListView_BaseAdapter_PAD.this.notifyDataSetChanged();
					}	
				}
				break;	
			case 10:
				
				FM_Music_ListView_BaseAdapter_PAD.this.notifyDataSetChanged();
				break;
			case 11:
				FileContent fileContent = (FileContent)msg.obj;
				if(fileContent.ParentID!=null){
					FM_Music_ListView_BaseAdapter_PAD.this.ParentID.add(fileContent.ParentID);
				}		
				FM_Music_ListView_BaseAdapter_PAD.this.ContainerList.clear();
				FM_Music_ListView_BaseAdapter_PAD.this.MusicTrackList.clear();
				if(fileContent.ContainerList!=null){
					for(int i=0;i<fileContent.ContainerList.size();i++){
						FM_Music_ListView_BaseAdapter_PAD.this.ContainerList.add(fileContent.ContainerList.get(i));
					}
				}
				if(fileContent.Itemlist!=null){
					for(int i=0;i<fileContent.Itemlist.size();i++){
						Item item = fileContent.Itemlist.get(i);
						if(item.getClass().getName().equals("org.teleal.cling.support.model.item.MusicTrack")||item.getClass().getName().equals("org.teleal.cling.support.model.item.AudioItem")){
							FM_Music_ListView_BaseAdapter_PAD.this.MusicTrackList.add(item);
						}
					}
				}	
				FM_Music_ListView_BaseAdapter_PAD.this.notifyDataSetChanged();
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
	public FM_Music_ListView_BaseAdapter_PAD(Context context){
		this.context = context;		
		this.mlog.LogSwitch = true;
		this.DeviceList = new ArrayList<DeviceDisplay>();
		this.ContainerList =  new ArrayList<Container>();
		this.MusicTrackList = new ArrayList<Item>();
		SetList();
		SetListner();		
	}
	private void SetList(){
		//分類MRList
		List<DeviceDisplay> MSList = ((FragmentActivity_Main)context).GETDeviceDisplayList().getMediaServerList();
		if(MSList!=null){
			for(int i =0;i<MSList.size();i++){
				DeviceList.add(MSList.get(i));
			}			
		}
	}
	private void SetListner(){
		FMLBAListner = new FM_Music_ListView_BaseAdapter_Listner(){
			@Override
			public void AddMediaServer(DeviceDisplay deviceDisplay) {
				handler.obtainMessage(0, deviceDisplay).sendToTarget();
				mlog.info(TAG, "AddMediaServer");
			}

			@Override
			public void RemoveMediaServer(DeviceDisplay deviceDisplay) {
				handler.obtainMessage(1, deviceDisplay).sendToTarget();
				mlog.info(TAG, "RemoveMediaServer");
			}
		};
		((FragmentActivity_Main)context).GETDeviceDisplayList().setMusicListner(FMLBAListner);
	}
	@Override
	public int getCount() {
		if(ParentID.size()>0){
			int size = ContainerList.size()+MusicTrackList.size();
			return size;
		}else{
			return DeviceList.size();
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
			convertView = LayoutInflater.from(context).inflate(R.layout.fm_music_listview_cell_pad, null);
			convertView.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT, Tool.getHeight(43)));
			viewHandler = new ViewHandler(convertView);
			basicSetView(viewHandler);
			convertView.setTag(viewHandler);
		}else{
			viewHandler = (ViewHandler)convertView.getTag();
		}		
		if(ParentID.size()>0){
			if(position<ContainerList.size()){
				//顯示File
				viewHandler.cell_RLayout_Name_TextView.setText(ContainerList.get(position).getTitle());
				viewHandler.kindOfItme = 1;
				viewHandler.object = ContainerList.get(position);
				viewHandler.cell_RLayout_Image_ImageView.setVisibility(View.VISIBLE);
			}else{
				int musicPosition = position-ContainerList.size();
				//顯示Leaf
				viewHandler.cell_RLayout_Name_TextView.setText(MusicTrackList.get(musicPosition).getTitle());
				viewHandler.kindOfItme = 2;
				viewHandler.object = MusicTrackList.get(musicPosition);
				viewHandler.cell_RLayout_Image_ImageView.setVisibility(View.GONE);
			}
		}else{
			//顯示Device
			viewHandler.cell_RLayout_Name_TextView.setText(DeviceList.get(position).getDevice().getDetails().getFriendlyName());
			viewHandler.kindOfItme = 0;			
			viewHandler.object = DeviceList.get(position).getDevice();
			viewHandler.cell_RLayout_Image_ImageView.setVisibility(View.VISIBLE);
		}
		return convertView;
	}
	
	public class ViewHandler{
		public int kindOfItme = 0;//0=Device 1=File 2=mp3
		public Object object;		
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
	public void ShowFile(Button Music_Button,String ParentID,List<Container> ContainerList,List<Item> Itemlist){
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
		handler.obtainMessage(13, Music_Button).sendToTarget();
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
	public void ShowPrivousFile(final Button MusicBack_Button){
		AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
		if(ParentID.size()>1){
			SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+cd:title")};
			Browse browse = new Browse(chooseDevice.findService(new UDAServiceType("ContentDirectory")), this.ParentID.get(ParentID.size()-1), BrowseFlag.DIRECT_CHILDREN, "", 0, 0l, null){
				@Override
				public void received(ActionInvocation arg0,	DIDLContent arg1) {
					FM_Music_ListView_BaseAdapter_PAD.this.ParentID.remove(FM_Music_ListView_BaseAdapter_PAD.this.ParentID.size()-1);
					List<Container> listC = arg1.getContainers();
					List<Item> listI = arg1.getItems();
					FM_Music_ListView_BaseAdapter_PAD.this.ShowFile(MusicBack_Button,null, listC,listI);
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
		}else if(ParentID.size()==1){			
			this.ParentID.clear();
			handler.obtainMessage(10).sendToTarget();
			handler.obtainMessage(14, MusicBack_Button).sendToTarget();
		}
	}
	public void setChooseDevice(Device chooseDevice){
		this.chooseDevice = chooseDevice;
	}
	public Device getChooseDevice(){
		return this.chooseDevice;
	}
}
