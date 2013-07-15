package com.FM.SETTING;

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
import com.FM.SETTING.FM_Music_ListView_BaseAdapter_PAD.ViewHandler;
import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.FragmentActivity_Setting;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FM_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FM_VIEW_SETTING";
	private int device_size = 0;
	public FM_VIEW_LISTNER(Context context, int device_size) {
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void Speaker_Button_LISTNER(Button Speaker_Button){
		Speaker_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((FragmentActivity_Main)context).ShowViewContent_ViewFlipperDisplay(1,R.animator.alpha_in,R.animator.translate_right_out);
			}
		});
	}
	public void NowPlaying_Button_LISTNER(Button NowPlaying_Button){
		NowPlaying_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((FragmentActivity_Main)context).ShowViewContent_ViewFlipperDisplay(0,R.animator.translate_right_in,R.animator.alpha_out);
			}
		});
	}
	
	public void SET_Music_ListView_Listner(FM_ListView Music_ListView, final Button MusicBack_Button){
		if(device_size==6){
			//***************************PHONE*********************************	
			Music_ListView.setOnItemClickListener(new OnItemClickListener(){
				private FM_PopupWindow fm_PopupWindow = new FM_PopupWindow(context);
				@Override
				public void onItemClick(final AdapterView<?> adapterView, final View view, int arg2, long arg3) {
					AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
					int kind = ((com.FM.SETTING.FM_Music_ListView_BaseAdapter_Phone.ViewHandler)view.getTag()).kindOfItme;					
					SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+dc:title")};
					BrowseFlag browseFlag = BrowseFlag.DIRECT_CHILDREN;					
					if(kind == 0){
						//取得Device
						Device device = (Device)((com.FM.SETTING.FM_Music_ListView_BaseAdapter_Phone.ViewHandler)view.getTag()).object;
						//設定選取Device
						((FM_Music_ListView_BaseAdapter_Phone)adapterView.getAdapter()).setChooseDevice((Device)((com.FM.SETTING.FM_Music_ListView_BaseAdapter_Phone.ViewHandler)view.getTag()).object);
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
								((FM_Music_ListView_BaseAdapter_Phone)adapterView.getAdapter()).ShowFile(MusicBack_Button,"0", listC,listI);
							}
							@Override
							public void updateStatus(Status arg0) {}

							@Override
							public void failure(ActionInvocation arg0,	UpnpResponse arg1, String arg2) {
								mlog.info(TAG, "device failure = "+arg2);
							}							
						};									
						upnpServer.getControlPoint().execute(browse);
					}else if(kind == 1){		
						Container container = (Container)((com.FM.SETTING.FM_Music_ListView_BaseAdapter_Phone.ViewHandler)view.getTag()).object;					
						final String ParentID = container.getParentID();
						String ObjectID = container.getId();
						Device device = ((FM_Music_ListView_BaseAdapter_Phone)adapterView.getAdapter()).getChooseDevice();
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
								((FM_Music_ListView_BaseAdapter_Phone)adapterView.getAdapter()).ShowFile(MusicBack_Button,ParentID, listC,listI);								
							}
							@Override
							public void updateStatus(Status arg0) {	}
							@Override
							public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {		
								mlog.info(TAG, "Container failure = "+arg1);
							}							
						};											
						upnpServer.getControlPoint().execute(browse);
					}else if(kind ==2){							
						
						//取得 item
						Item item = (Item)((com.FM.SETTING.FM_Music_ListView_BaseAdapter_Phone.ViewHandler)view.getTag()).object;
						//取得MS Device
						Device device = ((FM_Music_ListView_BaseAdapter_Phone)adapterView.getAdapter()).getChooseDevice();		
//						for(int i = 0;i<MR_Device.getDevice().findServices().length;i++){
//							Service service = MR_Device.getDevice().findServices()[i];							
//							mlog.info(TAG, ""+service.toString());	
//							for(int j=0;j<service.getActions().length;j++){
//								Action action = service.getActions()[j];
//								mlog.info(TAG, ""+action.toString());	
//							}
//						}
						View rootView = adapterView.getRootView();
						fm_PopupWindow.SetItemChooseDevice(item, device);
						fm_PopupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0 );
					}
				}
			});
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************	
			Music_ListView.setOnItemClickListener(new OnItemClickListener(){
				private FM_PopupWindow fm_PopupWindow = new FM_PopupWindow(context);
				@Override
				public void onItemClick(final AdapterView<?> adapterView, final View view, int arg2, long arg3) {
					AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
					int kind = ((ViewHandler)view.getTag()).kindOfItme;					
					SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+dc:title")};
					BrowseFlag browseFlag = BrowseFlag.DIRECT_CHILDREN;					
					if(kind == 0){
						//取得Device
						Device device = (Device)((ViewHandler)view.getTag()).object;
						//設定選取Device
						((FM_Music_ListView_BaseAdapter_PAD)adapterView.getAdapter()).setChooseDevice((Device)((ViewHandler)view.getTag()).object);
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
								((FM_Music_ListView_BaseAdapter_PAD)adapterView.getAdapter()).ShowFile(MusicBack_Button,"0", listC,listI);
							}
							@Override
							public void updateStatus(Status arg0) {}

							@Override
							public void failure(ActionInvocation arg0,	UpnpResponse arg1, String arg2) {
								mlog.info(TAG, "device failure = "+arg2);
							}							
						};									
						upnpServer.getControlPoint().execute(browse);
					}else if(kind == 1){		
						Container container = (Container)((ViewHandler)view.getTag()).object;					
						final String ParentID = container.getParentID();
						String ObjectID = container.getId();
						Device device = ((FM_Music_ListView_BaseAdapter_PAD)adapterView.getAdapter()).getChooseDevice();
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
								((FM_Music_ListView_BaseAdapter_PAD)adapterView.getAdapter()).ShowFile(MusicBack_Button,ParentID, listC,listI);
							}
							@Override
							public void updateStatus(Status arg0) {	}
							@Override
							public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {		
								mlog.info(TAG, "Container failure = "+arg1);
							}							
						};											
						upnpServer.getControlPoint().execute(browse);
					}else if(kind ==2){							
						
						//取得 item
						Item item = (Item)((ViewHandler)view.getTag()).object;
						//取得MS Device
						Device device = ((FM_Music_ListView_BaseAdapter_PAD)adapterView.getAdapter()).getChooseDevice();		
//						for(int i = 0;i<MR_Device.getDevice().findServices().length;i++){
//							Service service = MR_Device.getDevice().findServices()[i];							
//							mlog.info(TAG, ""+service.toString());	
//							for(int j=0;j<service.getActions().length;j++){
//								Action action = service.getActions()[j];
//								mlog.info(TAG, ""+action.toString());	
//							}
//						}
						View rootView = adapterView.getRootView();
						fm_PopupWindow.SetItemChooseDevice(item, device);
						fm_PopupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0 );
					}
				}
			});
			Music_ListView.setOnItemLongClickListener(new OnItemLongClickListener(){
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
					int kind = ((ViewHandler)arg1.getTag()).kindOfItme;		
					if(kind==2){
						((FM_ListView)arg0).setItemLongClickState(true);
						return true;
					}else{
						return false;
					}
					
				}
			});
			
			//***************************PAD*********************************	
		}		
	}
//	private void PlayMusic(){
//		//取得upnpServer
//		AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
//		//取得MR Device
//		DeviceDisplay MR_Device = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
//		//取得instanceId
//		UnsignedIntegerFourBytes instanceId = new UnsignedIntegerFourBytes("0");
//		//取得service
//		Service StopService = null;	
//		//檢查 MR_Device
//		if(MR_Device!=null){
//			//取得device 的 "AVTransport" service
//			StopService = MR_Device.getDevice().findService( new UDAServiceId("AVTransport"));
//		}else{
//			return;
//		}
//		//檢查StopService
//		if(StopService!=null){
//			Play ActionCallback = new Play(instanceId,StopService){
//				@Override
//			    public void success(ActionInvocation invocation) {
//					mlog.info(TAG, "Play success");
//				}
//				@Override
//				public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {
//					mlog.info(TAG, "Play failure");							
//				}
//			};
//			upnpServer.getControlPoint().execute(ActionCallback);
//		}
//	}
	public void SET_SearchMusic_RLayout_Listner(final RelativeLayout SearchMusic_RLayout,final RelativeLayout TITLE2_RLayout,final RelativeLayout TITLE3_RLayout){
		if(device_size==6){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			EditText SearchMusic_EText = (EditText)SearchMusic_RLayout.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_EText);
			SearchMusic_EText.setOnFocusChangeListener(new OnFocusChangeListener(){
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if(hasFocus){
						TITLE2_RLayout.setVisibility(View.INVISIBLE);
						TITLE3_RLayout.setVisibility(View.VISIBLE);
					}else{
						TITLE2_RLayout.setVisibility(View.VISIBLE);
						TITLE3_RLayout.setVisibility(View.GONE);
						InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
					}
				}

				public void SET_SearchMusic_RLayout_Listner(final RelativeLayout SearchMusic_RLayout,final RelativeLayout TITLE2_RLayout,final RelativeLayout TITLE3_RLayout){
					if(device_size==6){
						//***************************PHONE*********************************	
						//***************************PHONE*********************************	
					}else{
						//***************************PAD*********************************
						EditText SearchMusic_EText = (EditText)SearchMusic_RLayout.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_EText);
						SearchMusic_EText.setOnFocusChangeListener(new OnFocusChangeListener(){
							@Override
							public void onFocusChange(View v, boolean hasFocus) {
								if(hasFocus){
									TITLE2_RLayout.setVisibility(View.INVISIBLE);
									TITLE3_RLayout.setVisibility(View.VISIBLE);
								}else{
									TITLE2_RLayout.setVisibility(View.VISIBLE);
									TITLE3_RLayout.setVisibility(View.GONE);
									InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
									imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
								}
							}				
						});
						//***************************PAD*********************************	
					}		
				}				
			});
			//***************************PAD*********************************	
		}		
	}
	public void SET_SerchCondition1_Button_Listner(Button SerchCondition1_Button,final ImageView SerchConditionBG_ImageView){
		
		if(device_size==6){
			//***************************PHONE*********************************	
			SerchCondition1_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)SerchConditionBG_ImageView.getTag();
					if(Tag==1){
						new ThreadReadBitMapInAssets(context, "phone/playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new ThreadReadBitMapInAssets(context, "phone/playlist/search_btn_f_01.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(1);
					}
				}
			});
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			SerchCondition1_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)SerchConditionBG_ImageView.getTag();
					if(Tag==1){
						new ThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new ThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_01.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(1);
					}
				}
			});
			//***************************PAD*********************************	
		}		
	}
	public void SET_SerchCondition2_Button_Listner(Button SerchCondition2_Button,final ImageView SerchConditionBG_ImageView){
		
		if(device_size==6){
			//***************************PHONE*********************************	
			SerchCondition2_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)SerchConditionBG_ImageView.getTag();
					if(Tag==2){
						new ThreadReadBitMapInAssets(context, "phone/playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new ThreadReadBitMapInAssets(context, "phone/playlist/search_btn_f_02.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(2);
					}
				}
			});
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			SerchCondition2_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)SerchConditionBG_ImageView.getTag();
					if(Tag==2){
						new ThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new ThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_02.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(2);
					}
				}
			});
			//***************************PAD*********************************	
		}		
	}
	public void SET_SerchCondition3_Button_Listner(Button SerchCondition3_Button,final ImageView SerchConditionBG_ImageView){
		
		if(device_size==6){
			//***************************PHONE*********************************	
			SerchCondition3_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)SerchConditionBG_ImageView.getTag();
					if(Tag==3){
						new ThreadReadBitMapInAssets(context, "phone/playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new ThreadReadBitMapInAssets(context, "phone/playlist/search_btn_f_03.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(3);
					}
				}
			});
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			SerchCondition3_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)SerchConditionBG_ImageView.getTag();
					if(Tag==3){
						new ThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new ThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_03.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(3);
					}
				}
			});
			//***************************PAD*********************************	
		}		
	}
	public void SET_SerchCondition4_Button_Listner(Button SerchCondition4_Button,final ImageView SerchConditionBG_ImageView){
		
		if(device_size==6){
			//***************************PHONE*********************************
			SerchCondition4_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)SerchConditionBG_ImageView.getTag();
					if(Tag==4){
						new ThreadReadBitMapInAssets(context, "phone/playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new ThreadReadBitMapInAssets(context, "phone/playlist/search_btn_f_04.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(4);
					}
				}
			});
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			SerchCondition4_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)SerchConditionBG_ImageView.getTag();
					if(Tag==4){
						new ThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new ThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_04.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(4);
					}
				}
			});
			//***************************PAD*********************************	
		}		
	}
	

	public void SET_MusicBack_Button_Listner(Button MusicBack,final FM_ListView Music_ListView) {
		if(device_size==6){
			//***************************PHONE*********************************	
			MusicBack.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					((FM_Music_ListView_BaseAdapter_Phone)Music_ListView.getAdapter()).ShowPrivousFile((Button)v);
				}
			});
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			MusicBack.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(final View v) {
					((FM_Music_ListView_BaseAdapter_PAD)Music_ListView.getAdapter()).ShowPrivousFile((Button)v);
				}
			});
			//***************************PAD*********************************	
		}
	}
	public void Setting_IButton_LISTNER(ImageButton Setting_IButton) {
		Setting_IButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intnet = new Intent();
				intnet.setClass(context, FragmentActivity_Setting.class);
				context.startActivity(intnet);
			}
		});		
	}
}
