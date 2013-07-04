package com.FM.SETTING;

import java.util.List;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.ServiceId;
import org.teleal.cling.model.types.UDAServiceId;
import org.teleal.cling.model.types.UDAServiceType;
import org.teleal.cling.model.types.UnsignedIntegerFourBytes;
import org.teleal.cling.support.avtransport.callback.Play;
import org.teleal.cling.support.avtransport.callback.SetAVTransportURI;
import org.teleal.cling.support.avtransport.callback.Stop;
import org.teleal.cling.support.contentdirectory.DIDLParser;
import org.teleal.cling.support.contentdirectory.callback.Browse;
import org.teleal.cling.support.model.BrowseFlag;
import org.teleal.cling.support.model.DIDLContent;
import org.teleal.cling.support.model.DIDLObject;
import org.teleal.cling.support.model.Res;
import org.teleal.cling.support.model.SortCriterion;
import org.teleal.cling.support.model.container.Container;
import org.teleal.cling.support.model.item.Item;
import com.FM.SETTING.FM_Music_ListView_BaseAdapter.ViewHandler;
import com.alpha.UPNP.DeviceDisplay;
import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
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
	
	public void SET_Music_ListView_Listner(FM_ListView Music_ListView){
		if(device_size==6){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************	
			Music_ListView.setOnItemClickListener(new OnItemClickListener(){
				private FM_PopupWindow fm_PopupWindow = new FM_PopupWindow(context);
				@Override
				public void onItemClick(final AdapterView<?> adapterView, final View view, int arg2, long arg3) {
					AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
					int kind = ((ViewHandler)view.getTag()).kindOfItme;					
					SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+cd:title")};
					BrowseFlag browseFlag = BrowseFlag.DIRECT_CHILDREN;					
					if(kind == 0){
						//取得Device
						Device device = (Device)((ViewHandler)view.getTag()).object;
						//設定選取Device
						((FM_Music_ListView_BaseAdapter)adapterView.getAdapter()).setChooseDevice((Device)((ViewHandler)view.getTag()).object);
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
								((FM_Music_ListView_BaseAdapter)adapterView.getAdapter()).ShowFile("0", listC,listI);
								
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
						Device device = ((FM_Music_ListView_BaseAdapter)adapterView.getAdapter()).getChooseDevice();
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
								((FM_Music_ListView_BaseAdapter)adapterView.getAdapter()).ShowFile(ParentID, listC,listI);
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
						Device device = ((FM_Music_ListView_BaseAdapter)adapterView.getAdapter()).getChooseDevice();
					
						Browse browse = new Browse(device.findService(new UDAServiceType("ContentDirectory")), item.getId(), BrowseFlag.METADATA, "*", 0, 1l, sortCriterion){
							@Override
							public void received(ActionInvocation arg0,	DIDLContent arg1) {
								if(arg0.getOutput().length<=0||arg1.getItems().size()<=0){
									return;
								}
								//取得upnpServer
								AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
								//取得MR Device
								DeviceDisplay MR_Device = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
								mlog.info(TAG, "MR_Device = "+MR_Device);
								//取得MetaData								
								String MetaData = arg0.getOutput()[0].toString();	
								mlog.info(TAG, "MetaData = "+MetaData);
								//取得 item
								Item item = (Item)((ViewHandler)view.getTag()).object;
								//取得item res
								Res res = item.getFirstResource();
								//取得instanceId
								UnsignedIntegerFourBytes instanceId = new UnsignedIntegerFourBytes("0");
								//取得service
								ServiceId serviceId = new UDAServiceId("AVTransport");
								Service AVTransportService = null;
								//檢查Device 跟 res
								if(MR_Device!=null&&res!=null){
									//取得device 的 "AVTransport" service
									AVTransportService = MR_Device.getDevice().findService(serviceId);
								}else{
									return;
								}
								//res顯示內容
								try{
									mlog.info(TAG, "============Start=============");
									mlog.info(TAG, item.getId());							
									mlog.info(TAG, item.getTitle());
									mlog.info(TAG, item.getFirstResource().toString());
									mlog.info(TAG, "RES = "+item.getFirstResource().getValue());
									mlog.info(TAG, "RES = "+item.getFirstResource().getDuration());
									mlog.info(TAG, "RES = "+item.getFirstResource().getProtocolInfo());
									mlog.info(TAG, "RES = "+item.getFirstResource().getSize());
									mlog.info(TAG, "RES = "+item.getProperties().size());									
									for(int i =0;i<item.getProperties().size();i++){
										mlog.info(TAG, "Propertie = "+item.getProperties().get(i).getValue());	
									}							
									mlog.info(TAG, "============End=============");
								}catch(Exception e){
									mlog.info(TAG, e.toString());
								}						
								if(AVTransportService!=null){					
									SetAVTransportURI setAVTransportURI = new SetAVTransportURI(instanceId,AVTransportService,item.getFirstResource().getValue().toString(), MetaData){
										@Override
									    public void success(ActionInvocation invocation) {
											for(int i =0;i<invocation.getOutput().length;i++){
												mlog.info(TAG, "OT = "+invocation.getOutputMap().toString());	
											}	
											mlog.info(TAG, "setAVTransportURI success");
											PlayMusic();
										}
										@Override
										public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {
											mlog.info(TAG, "setAVTransportURI arg2"+arg2);
											mlog.info(TAG, "setAVTransportURI failure");
										}
									};						
									upnpServer.getControlPoint().execute(setAVTransportURI);
								}
							}
							@Override
							public void updateStatus(Status arg0) {	}
							@Override
							public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {		
								mlog.info(TAG, "Container failure = "+arg1);
							}							
						};											
						upnpServer.getControlPoint().execute(browse);
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
//						
//					
//						
						
//						for(int i = 0;i<MR_Device.getDevice().findServices().length;i++){
//							Service service = MR_Device.getDevice().findServices()[i];							
//							mlog.info(TAG, ""+service.toString());	
//							for(int j=0;j<service.getActions().length;j++){
//								Action action = service.getActions()[j];
//								mlog.info(TAG, ""+action.toString());	
//							}
//						}
//						View rootView = adapterView.getRootView();
//						fm_PopupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0 );
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
	private void PlayMusic(){
		//取得upnpServer
		AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
		//取得MR Device
		DeviceDisplay MR_Device = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
		//取得instanceId
		UnsignedIntegerFourBytes instanceId = new UnsignedIntegerFourBytes("0");
		//取得service
		Service StopService = null;	
		//檢查 MR_Device
		if(MR_Device!=null){
			//取得device 的 "AVTransport" service
			StopService = MR_Device.getDevice().findService( new UDAServiceId("AVTransport"));
		}else{
			return;
		}
		//檢查StopService
		if(StopService!=null){
			Play ActionCallback = new Play(instanceId,StopService){
				@Override
			    public void success(ActionInvocation invocation) {
					mlog.info(TAG, "Play success");
				}
				@Override
				public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {
					mlog.info(TAG, "Play failure");							
				}
			};
			upnpServer.getControlPoint().execute(ActionCallback);
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
		MusicBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((FM_Music_ListView_BaseAdapter)Music_ListView.getAdapter()).ShowPrivousFile();
			}
		});
	}
}
