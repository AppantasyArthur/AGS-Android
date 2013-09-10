package com.FSAL_Music.SETTING;

import java.util.List;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.UDAServiceType;
import org.teleal.cling.support.contentdirectory.callback.Browse;
import org.teleal.cling.support.model.BrowseFlag;
import org.teleal.cling.support.model.DIDLContent;
import org.teleal.cling.support.model.SortCriterion;
import org.teleal.cling.support.model.container.Container;
import org.teleal.cling.support.model.item.Item;
import com.FSAL_Music.SETTING.FSAl_Music_ListView_BaseAdapter_PAD.ViewHandler;
import com.alpha.fragments.Fragment_SAlarm_EditAdd;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.appantasy.androidapptemplate.event.lastchange.TrackDO;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FSAl_Music_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSAl_Frequency_VIEW_LISTNER";
	private int device_size = 0;
	public FSAl_Music_VIEW_LISTNER(Context context, int device_size) {
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}	
	
	public void Back_Button_Listner(Button Back_Button,final FragmentManager fragmentManager){
		if(device_size==6){
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					Tool.FragmentActivity_MainRemoveFragment(fragmentManager, "Fragment_SAlarm_Music", R.animator.alpha_in, R.animator.alpha_out);
				}
			});	
		}else{
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					Tool.FragmentActivity_MainRemoveFragment(fragmentManager, "Fragment_SAlarm_Music", R.animator.alpha_in, R.animator.alpha_out);
				}
			});	
		}		
	}
	public void SET_Music_ListView_Listner(ListView Music_ListView, final Button MusicBack_Button,final FragmentManager fragmentManager){
		if(device_size==6){
			//***************************PHONE*********************************	
			Music_ListView.setOnItemClickListener(new OnItemClickListener(){				
				@Override
				public void onItemClick(final AdapterView<?> adapterView, final View view, int arg2, long arg3) {
					AndroidUpnpService upnpServer = ((MainFragmentActivity)context).GETUPnPService();
					
					com.FSAL_Music.SETTING.FSAl_Music_ListView_BaseAdapter_Phone.ViewHandler viewHandler = (com.FSAL_Music.SETTING.FSAl_Music_ListView_BaseAdapter_Phone.ViewHandler)view.getTag();
					
					int kind = viewHandler.kindOfItme;					
					SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+dc:title")};
					BrowseFlag browseFlag = BrowseFlag.DIRECT_CHILDREN;					
					
					if(kind== 0 ){			
//						//Category
						((FSAl_Music_ListView_BaseAdapter_Phone)adapterView.getAdapter()).ShowLevelOne(MusicBack_Button,(Integer)viewHandler.object);
					}else if(kind == 1){
						//Device
						//取得Device
						Device device = (Device)((com.FSAL_Music.SETTING.FSAl_Music_ListView_BaseAdapter_Phone.ViewHandler)view.getTag()).object;
						//設定選取Device
						((FSAl_Music_ListView_BaseAdapter_Phone)adapterView.getAdapter()).setChooseDevice(device);
						mlog.info(TAG, "device = "+device.getDetails().getFriendlyName());
						Service service = device.findService(new UDAServiceType("ContentDirectory"));
						Browse browse = new Browse(service, "0", browseFlag, "*", 0, 0l, sortCriterion){
							@Override
							public void received(ActionInvocation arg0,	DIDLContent arg1) {
								for(int i =0;i<arg0.getOutput().length;i++){
									mlog.info(TAG, "OT = "+arg0.getOutputMap().toString());	
								}	
								//取得Container List
								List<Container> listC = arg1.getContainers();
								//取得 Item List
								List<Item> listI = arg1.getItems();
								//更新FM_Music_ListView_BaseAdapter
								((FSAl_Music_ListView_BaseAdapter_Phone)adapterView.getAdapter()).ShowFile(MusicBack_Button,"0", listC,listI);
							}
							@Override
							public void updateStatus(Status arg0) {}
							@Override
							public void failure(ActionInvocation arg0, UpnpResponse arg1, String arg2) {}
						};	
						upnpServer.getControlPoint().execute(browse);
					}else if(kind == 2){
						//Container 
						
						Container container = (Container)viewHandler.object;					
						
						final String ParentID = container.getParentID();
						String ObjectID = container.getId();
						Device device = ((FSAl_Music_ListView_BaseAdapter_Phone)adapterView.getAdapter()).getChooseDevice();
						if(device==null){
							return ;
						}
						Browse browse = new Browse(device.findService(new UDAServiceType("ContentDirectory")), ObjectID, BrowseFlag.DIRECT_CHILDREN, "*", 0, 0l, sortCriterion){
							@Override
							public void received(ActionInvocation arg0,	DIDLContent arg1) {	
								for(int i =0;i<arg0.getOutput().length;i++){
									mlog.info(TAG, "OT = "+arg0.getOutput()[i].toString());	
								}	
								//取得Container List
								List<Container> listC = arg1.getContainers();
								//取得 Item List
								List<Item> listI = arg1.getItems();
								mlog.info(TAG, "C Size = "+listC.size()+"&& I Size = "+ listI.size());								
								//更新FM_Music_ListView_BaseAdapter
								((FSAl_Music_ListView_BaseAdapter_Phone)adapterView.getAdapter()).ShowFile(MusicBack_Button,ParentID, listC,listI);								
							}
							@Override
							public void updateStatus(Status arg0) {	}
							@Override
							public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {		
								mlog.info(TAG, "Container failure = "+arg1);
							}							
						};											
						upnpServer.getControlPoint().execute(browse);
					}else if(kind == 3){
						//Item						
						Item item = (Item)viewHandler.object;	
						//設定資料
						Fragment_SAlarm_EditAdd fragment_SAlarm_EditAdd = (Fragment_SAlarm_EditAdd)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
						if(fragment_SAlarm_EditAdd!=null){
							fragment_SAlarm_EditAdd.SetMusic(item, 3);
						}
						Tool.FragmentActivity_MainRemoveFragment(fragmentManager, "Fragment_SAlarm_Music", R.animator.alpha_in, R.animator.alpha_out);
					}else if(kind == 4){
						//List
						String Name = (String)viewHandler.object;
						mlog.info(TAG,"Name = "+Name);
						((FSAl_Music_ListView_BaseAdapter_Phone)adapterView.getAdapter()).ShowLocalFile(MusicBack_Button,Name);
						
					}else if(kind ==5){
						//Track
						TrackDO trackDO = (TrackDO)viewHandler.object;
						//設定資料
						Fragment_SAlarm_EditAdd fragment_SAlarm_EditAdd = (Fragment_SAlarm_EditAdd)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
						if(fragment_SAlarm_EditAdd!=null){
							fragment_SAlarm_EditAdd.SetMusic(trackDO, 5);
						}
						Tool.FragmentActivity_MainRemoveFragment(fragmentManager, "Fragment_SAlarm_Music", R.animator.alpha_in, R.animator.alpha_out);
					}
				}
			});
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************	
			Music_ListView.setOnItemClickListener(new OnItemClickListener(){				
				@Override
				public void onItemClick(final AdapterView<?> adapterView, final View view, int arg2, long arg3) {
					AndroidUpnpService upnpServer = ((MainFragmentActivity)context).GETUPnPService();
					ViewHandler viewHandler = (ViewHandler)view.getTag();
					int kind = ((ViewHandler)view.getTag()).kindOfItme;					
					SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+dc:title")};
					BrowseFlag browseFlag = BrowseFlag.DIRECT_CHILDREN;					
					if(kind== 0 ){			
//						//Category
						((FSAl_Music_ListView_BaseAdapter_PAD)adapterView.getAdapter()).ShowLevelOne(MusicBack_Button,(Integer)viewHandler.object);
					}else if(kind == 1){
						//Device
						//取得Device
						Device device = (Device)((com.FSAL_Music.SETTING.FSAl_Music_ListView_BaseAdapter_PAD.ViewHandler)view.getTag()).object;
						//設定選取Device
						((FSAl_Music_ListView_BaseAdapter_PAD)adapterView.getAdapter()).setChooseDevice(device);
						mlog.info(TAG, "device = "+device.getDetails().getFriendlyName());
						Service service = device.findService(new UDAServiceType("ContentDirectory"));
						Browse browse = new Browse(service, "0", browseFlag, "*", 0, 0l, sortCriterion){
							@Override
							public void received(ActionInvocation arg0,	DIDLContent arg1) {
								for(int i =0;i<arg0.getOutput().length;i++){
									mlog.info(TAG, "OT = "+arg0.getOutputMap().toString());	
								}	
								//取得Container List
								List<Container> listC = arg1.getContainers();
								//取得 Item List
								List<Item> listI = arg1.getItems();
								//更新FM_Music_ListView_BaseAdapter
								((FSAl_Music_ListView_BaseAdapter_PAD)adapterView.getAdapter()).ShowFile(MusicBack_Button,"0", listC,listI);
							}
							@Override
							public void updateStatus(Status arg0) {}
							@Override
							public void failure(ActionInvocation arg0, UpnpResponse arg1, String arg2) {}
						};	
						upnpServer.getControlPoint().execute(browse);
					}else if(kind == 2){
						//Container 
						
						Container container = (Container)viewHandler.object;					
						
						final String ParentID = container.getParentID();
						String ObjectID = container.getId();
						Device device = ((FSAl_Music_ListView_BaseAdapter_PAD)adapterView.getAdapter()).getChooseDevice();
						if(device==null){
							return ;
						}
						Browse browse = new Browse(device.findService(new UDAServiceType("ContentDirectory")), ObjectID, BrowseFlag.DIRECT_CHILDREN, "*", 0, 0l, sortCriterion){
							@Override
							public void received(ActionInvocation arg0,	DIDLContent arg1) {	
								for(int i =0;i<arg0.getOutput().length;i++){
									mlog.info(TAG, "OT = "+arg0.getOutput()[i].toString());	
								}	
								//取得Container List
								List<Container> listC = arg1.getContainers();
								//取得 Item List
								List<Item> listI = arg1.getItems();
								mlog.info(TAG, "C Size = "+listC.size()+"&& I Size = "+ listI.size());								
								//更新FM_Music_ListView_BaseAdapter
								((FSAl_Music_ListView_BaseAdapter_PAD)adapterView.getAdapter()).ShowFile(MusicBack_Button,ParentID, listC,listI);								
							}
							@Override
							public void updateStatus(Status arg0) {	}
							@Override
							public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {		
								mlog.info(TAG, "Container failure = "+arg1);
							}							
						};											
						upnpServer.getControlPoint().execute(browse);
					}else if(kind == 3){
						//Item
						
						Item item = (Item)viewHandler.object;		
						//設定資料
						Fragment_SAlarm_EditAdd fragment_SAlarm_EditAdd = (Fragment_SAlarm_EditAdd)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
						if(fragment_SAlarm_EditAdd!=null){
							fragment_SAlarm_EditAdd.SetMusic(item, 3);
						}
						Tool.FragmentActivity_MainRemoveFragment(fragmentManager, "Fragment_SAlarm_Music", R.animator.alpha_in, R.animator.alpha_out);
					}else if(kind == 4){
						//List
						String Name = (String)viewHandler.object;
						mlog.info(TAG,"Name = "+Name);
						((FSAl_Music_ListView_BaseAdapter_PAD)adapterView.getAdapter()).ShowLocalFile(MusicBack_Button,Name);
						
					}else if(kind ==5){
						//Track
						TrackDO trackDO = (TrackDO)viewHandler.object;
						//設定資料
						Fragment_SAlarm_EditAdd fragment_SAlarm_EditAdd = (Fragment_SAlarm_EditAdd)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
						if(fragment_SAlarm_EditAdd!=null){
							fragment_SAlarm_EditAdd.SetMusic(trackDO, 5);
						}
						Tool.FragmentActivity_MainRemoveFragment(fragmentManager, "Fragment_SAlarm_Music", R.animator.alpha_in, R.animator.alpha_out);
					}
				}
			});			
			//***************************PAD*********************************	
		}		
	}
	public void SET_MusicBack_Button_Listner(Button MusicBack,final ListView Music_ListView) {
		if(device_size==6){
			//***************************PHONE*********************************	
			MusicBack.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					((FSAl_Music_ListView_BaseAdapter_Phone)Music_ListView.getAdapter()).ShowPrivous((Button)v);
				}
			});			
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			MusicBack.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(final View v) {
					((FSAl_Music_ListView_BaseAdapter_PAD)Music_ListView.getAdapter()).ShowPrivous((Button)v);
				}
			});			
			//***************************PAD*********************************	
		}
	}
	public void SET_MusicTop_Button_Listner(final Button MusicBack, Button MusicTop,final ListView Music_ListView){
		if(device_size==6){
			//***************************PHONE*********************************	
			MusicTop.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					((FSAl_Music_ListView_BaseAdapter_Phone)Music_ListView.getAdapter()).ShowTopDevice(MusicBack);
				}
			});
			
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			MusicTop.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(final View v) {
					((FSAl_Music_ListView_BaseAdapter_PAD)Music_ListView.getAdapter()).ShowTopDevice(MusicBack);
				}
			});
			
			//***************************PAD*********************************	
		}
	}
}
