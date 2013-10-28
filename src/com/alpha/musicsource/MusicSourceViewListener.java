package com.alpha.musicsource;

import java.util.List;

import org.json.JSONObject;
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

import android.content.Context;
import android.support.v4.app.FragmentManager;
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

import com.alpha.musicsource.MusicSourcePadAdapter.ViewHandler;
import com.alpha.upnp.parser.TrackDO;
import com.alpha.upnpui.Fragment_SETTING;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBTool;

// FM_VIEW_LISTNER
public class MusicSourceViewListener {
	
	
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "MusicSourceViewListener";
//	private int device_size = 0;
	private FragmentManager fragmentManager;
	public MusicSourceViewListener(Context context, FragmentManager fragmentManager) {
		this.context = context;
		this.mlog.switchLog = true;
//		this.device_size = device_size;
		this.fragmentManager = fragmentManager;
	}
	public void Speaker_Button_LISTNER(Button Speaker_Button){
		Speaker_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((MainFragmentActivity)context).ShowViewContent_ViewFlipperDisplay(0,R.animator.alpha_in,R.animator.translate_right_out);
			}
		});
	}
	public void NowPlaying_Button_LISTNER(Button NowPlaying_Button){
		NowPlaying_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((MainFragmentActivity)context).ShowViewContent_ViewFlipperDisplay(1,R.animator.translate_right_in,R.animator.alpha_out);
			}
		});
	}
	
	private MusicSourceOptionsPopupWindow popupMusicSourceListItemOptions;
	public void setMusicSourceListViewListener(MusicSourceListView viewMusicSourceList, final Button MusicBack_Button){
		
		popupMusicSourceListItemOptions = new MusicSourceOptionsPopupWindow(context);
		if(DeviceProperty.isPhone()){
			
			//***************************PHONE*********************************	
			viewMusicSourceList.setOnItemClickListener(new OnItemClickListener(){				
				@Override
				public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
					
					AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
				
					com.alpha.musicsource.MusicSourcePhoneAdapter.ViewHandler viewHandler = (com.alpha.musicsource.MusicSourcePhoneAdapter.ViewHandler)view.getTag();
					
					int kind = viewHandler.kindOfItme;					
					SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+dc:title")};
					BrowseFlag browseFlag = BrowseFlag.DIRECT_CHILDREN;					
					
					if(kind== 0 ){			
//						//Category
						((MusicSourcePhoneAdapter)parent.getAdapter()).ShowLevelOne(MusicBack_Button,(Integer)viewHandler.object);
					}else if(kind == 1){ // Media Server Device
						
						//取得Device
						Device device = (Device)((com.alpha.musicsource.MusicSourcePhoneAdapter.ViewHandler)view.getTag()).object;
						//設定選取Device
						((MusicSourcePhoneAdapter)parent.getAdapter()).setChooseDevice(device);
						mlog.info(tag, "device = "+device.getDetails().getFriendlyName());
						Service service = device.findService(new UDAServiceType("ContentDirectory"));
						Browse browse = new Browse(service, "0", browseFlag, "*", 0, 0l, sortCriterion){
							@Override
							public void received(ActionInvocation arg0,	DIDLContent arg1) {
								for(int i =0;i<arg0.getOutput().length;i++){
									mlog.info(tag, "OT = "+arg0.getOutputMap().toString());	
								}	
								//取得Container List
								List<Container> listC = arg1.getContainers();
								//取得 Item List
								List<Item> listI = arg1.getItems();
								//更新FM_Music_ListView_BaseAdapter
								((MusicSourcePhoneAdapter)parent.getAdapter()).ShowFile(MusicBack_Button,"0", listC,listI);
							}
							@Override
							public void updateStatus(Status arg0) {}
							@Override
							public void failure(ActionInvocation arg0, UpnpResponse arg1, String arg2) {}
						};	
						upnpServer.getControlPoint().execute(browse);
					}else if(kind == 2){ // Media Server Container
						
						Container container = (Container)viewHandler.object;					
						
						final String ParentID = container.getParentID();
						String ObjectID = container.getId();
						Device device = ((MusicSourcePhoneAdapter)parent.getAdapter()).getChooseDevice();
						if(device==null){
							return ;
						}
						Browse browse = new Browse(device.findService(new UDAServiceType("ContentDirectory")), ObjectID, BrowseFlag.DIRECT_CHILDREN, "*", 0, 0l, sortCriterion){
							@Override
							public void received(ActionInvocation arg0,	DIDLContent arg1) {	
								for(int i =0;i<arg0.getOutput().length;i++){
									mlog.info(tag, "OT = "+arg0.getOutput()[i].toString());	
								}	
								//取得Container List
								List<Container> listC = arg1.getContainers();
								//取得 Item List
								List<Item> listI = arg1.getItems();
								mlog.info(tag, "C Size = "+listC.size()+"&& I Size = "+ listI.size());								
								//更新FM_Music_ListView_BaseAdapter
								((MusicSourcePhoneAdapter)parent.getAdapter()).ShowFile(MusicBack_Button,ParentID, listC,listI);								
							}
							@Override
							public void updateStatus(Status arg0) {	}
							@Override
							public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {		
								mlog.info(tag, "Container failure = "+arg1);
							}							
						};											
						upnpServer.getControlPoint().execute(browse);
					}else if(kind == 3){ // Media Server Item
						
						Item item = (Item)viewHandler.object;		
						//取得MS Device
						Device device = ((MusicSourcePhoneAdapter)parent.getAdapter()).getChooseDevice();		
						View rootView = parent.getRootView();
						popupMusicSourceListItemOptions.SetItem(item, device);
						popupMusicSourceListItemOptions.showAtLocation(rootView, Gravity.CENTER, 0, 0 );
						
					}else if(kind == 4){ //click Track List
					
						JSONObject o = (JSONObject)viewHandler.object;
//						mlog.info(tag,"Name = " + );
//						((MusicSourcePhoneAdapter)parent.getAdapter()).ShowLocalFile(MusicBack_Button,Name);
						
						View rootView = parent.getRootView();
						popupMusicSourceListItemOptions.SetTrackMetaData(o);
						popupMusicSourceListItemOptions.showAtLocation(rootView, Gravity.CENTER, 0, 0 );
						
					}else if(kind ==5){ //click Track
						
						TrackDO trackDO = (TrackDO)viewHandler.object;
						View rootView = parent.getRootView();
						popupMusicSourceListItemOptions.SetTrack(trackDO);
						popupMusicSourceListItemOptions.showAtLocation(rootView, Gravity.CENTER, 0, 0 );
						
					}
				}
			});
			viewMusicSourceList.setOnItemLongClickListener(new OnItemLongClickListener(){
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
					com.alpha.musicsource.MusicSourcePhoneAdapter.ViewHandler viewHandler = (com.alpha.musicsource.MusicSourcePhoneAdapter.ViewHandler)arg1.getTag();
					int kind = viewHandler.kindOfItme;		
					if(kind==4){
						String Name = (String)viewHandler.object;
						List<TrackDO> list = ((MusicSourcePhoneAdapter)arg0.getAdapter()).GetLocalMusicTrackList(Name);
						View rootView = arg0.getRootView();
						popupMusicSourceListItemOptions.SetTrackList(list);
						popupMusicSourceListItemOptions.showAtLocation(rootView, Gravity.CENTER, 0, 0 );
						return true;
					}else{
						return false;
					}
					
				}
			});
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************	
			viewMusicSourceList.setOnItemClickListener(new OnItemClickListener(){				
				@Override
				public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
					
					AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
					ViewHandler viewHandler = (ViewHandler)view.getTag();
					
					int kind = ((ViewHandler)view.getTag()).kindOfItme;					
					
					SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+dc:title")};
					
					BrowseFlag browseFlag = BrowseFlag.DIRECT_CHILDREN;					
					
					if(kind== 0 ){			
//						//Category
						((MusicSourcePadAdapter)parent.getAdapter()).ShowLevelOne(MusicBack_Button,(Integer)viewHandler.object);
					}else if(kind == 1){
						//Device
						//取得Device
						Device device = (Device)((com.alpha.musicsource.MusicSourcePadAdapter.ViewHandler)view.getTag()).object;
						//設定選取Device
						((MusicSourcePadAdapter)parent.getAdapter()).setChooseDevice(device);
						mlog.info(tag, "device = "+device.getDetails().getFriendlyName());
						Service service = device.findService(new UDAServiceType("ContentDirectory"));
						Browse browse = new Browse(service, "0", browseFlag, "*", 0, 0l, sortCriterion){
							@Override
							public void received(ActionInvocation arg0,	DIDLContent arg1) {
								for(int i =0;i<arg0.getOutput().length;i++){
									mlog.info(tag, "OT = "+arg0.getOutputMap().toString());	
								}	
								//取得Container List
								List<Container> listC = arg1.getContainers();
								//取得 Item List
								List<Item> listI = arg1.getItems();
								//更新FM_Music_ListView_BaseAdapter
								((MusicSourcePadAdapter)parent.getAdapter()).ShowFile(MusicBack_Button,"0", listC,listI);
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
						Device device = ((MusicSourcePadAdapter)parent.getAdapter()).getChooseDevice();
						if(device==null){
							return ;
						}
						Browse browse = new Browse(device.findService(new UDAServiceType("ContentDirectory")), ObjectID, BrowseFlag.DIRECT_CHILDREN, "*", 0, 0l, sortCriterion){
							@Override
							public void received(ActionInvocation arg0,	DIDLContent arg1) {	
								for(int i =0;i<arg0.getOutput().length;i++){
									mlog.info(tag, "OT = "+arg0.getOutput()[i].toString());	
								}	
								//取得Container List
								List<Container> listC = arg1.getContainers();
								//取得 Item List
								List<Item> listI = arg1.getItems();
								mlog.info(tag, "C Size = "+listC.size()+"&& I Size = "+ listI.size());								
								//更新FM_Music_ListView_BaseAdapter
								((MusicSourcePadAdapter)parent.getAdapter()).ShowFile(MusicBack_Button,ParentID, listC,listI);								
							}
							@Override
							public void updateStatus(Status arg0) {	}
							@Override
							public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {		
								mlog.info(tag, "Container failure = "+arg1);
							}							
						};											
						upnpServer.getControlPoint().execute(browse);
					}else if(kind == 3){
						//Item
						
						Item item = (Item)viewHandler.object;		
						//取得MS Device
						Device device = ((MusicSourcePadAdapter)parent.getAdapter()).getChooseDevice();		
						View rootView = parent.getRootView();
						popupMusicSourceListItemOptions.SetItem(item, device);
						popupMusicSourceListItemOptions.showAtLocation(rootView, Gravity.CENTER, 0, 0 );
					}else if(kind == 4){
						//List
						String Name = (String)viewHandler.object;
						mlog.info(tag,"Name = "+Name);
						((MusicSourcePadAdapter)parent.getAdapter()).ShowLocalFile(MusicBack_Button,Name);
						
					}else if(kind ==5){
						//Track
						TrackDO trackDO = (TrackDO)viewHandler.object;
						View rootView = parent.getRootView();
						popupMusicSourceListItemOptions.SetTrack(trackDO);
						popupMusicSourceListItemOptions.showAtLocation(rootView, Gravity.CENTER, 0, 0 );
					}
				}
			});
			viewMusicSourceList.setOnItemLongClickListener(new OnItemLongClickListener(){
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
					ViewHandler viewHandler = (ViewHandler)arg1.getTag();
					int kind = viewHandler.kindOfItme;		
					if(kind==3){
						((MusicSourceListView)arg0).setItemLongClickState(true);
						return true;
					}else if(kind==4){					
						String Name = (String)viewHandler.object;
						List<TrackDO> list = ((MusicSourcePadAdapter)arg0.getAdapter()).GetLocalMusicTrackList(Name);
						View rootView = arg0.getRootView();
						popupMusicSourceListItemOptions.SetTrackList(list);
						popupMusicSourceListItemOptions.showAtLocation(rootView, Gravity.CENTER, 0, 0 );
						return true;					
					}else if(kind==5){
						((MusicSourceListView)arg0).setItemLongClickState(true);
						return true;
					}else{
						return false;
					}
					
				}
			});
			
			//***************************PAD*********************************	
		}		
	}

	public void SET_SearchMusic_RLayout_Listner(final RelativeLayout SearchMusic_RLayout,final RelativeLayout TITLE2_RLayout,final RelativeLayout TITLE3_RLayout){
		if(DeviceProperty.isPhone()){
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
					if(DeviceProperty.isPhone()){
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
		
		if(DeviceProperty.isPhone()){
			//***************************PHONE*********************************	
			SerchCondition1_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)SerchConditionBG_ImageView.getTag();
					if(Tag==1){
						new TKBThreadReadBitMapInAssets(context, "phone/playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new TKBThreadReadBitMapInAssets(context, "phone/playlist/search_btn_f_01.png", SerchConditionBG_ImageView, 1);
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
						new TKBThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new TKBThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_01.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(1);
					}
				}
			});
			//***************************PAD*********************************	
		}		
	}
	public void SET_SerchCondition2_Button_Listner(Button SerchCondition2_Button,final ImageView SerchConditionBG_ImageView){
		
		if(DeviceProperty.isPhone()){
			//***************************PHONE*********************************	
			SerchCondition2_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)SerchConditionBG_ImageView.getTag();
					if(Tag==2){
						new TKBThreadReadBitMapInAssets(context, "phone/playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new TKBThreadReadBitMapInAssets(context, "phone/playlist/search_btn_f_02.png", SerchConditionBG_ImageView, 1);
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
						new TKBThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new TKBThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_02.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(2);
					}
				}
			});
			//***************************PAD*********************************	
		}		
	}
	public void SET_SerchCondition3_Button_Listner(Button SerchCondition3_Button,final ImageView SerchConditionBG_ImageView){
		
		if(DeviceProperty.isPhone()){
			//***************************PHONE*********************************	
			SerchCondition3_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)SerchConditionBG_ImageView.getTag();
					if(Tag==3){
						new TKBThreadReadBitMapInAssets(context, "phone/playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new TKBThreadReadBitMapInAssets(context, "phone/playlist/search_btn_f_03.png", SerchConditionBG_ImageView, 1);
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
						new TKBThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new TKBThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_03.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(3);
					}
				}
			});
			//***************************PAD*********************************	
		}		
	}
	public void SET_SerchCondition4_Button_Listner(Button SerchCondition4_Button,final ImageView SerchConditionBG_ImageView){
		
		if(DeviceProperty.isPhone()){
			//***************************PHONE*********************************
			SerchCondition4_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)SerchConditionBG_ImageView.getTag();
					if(Tag==4){
						new TKBThreadReadBitMapInAssets(context, "phone/playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new TKBThreadReadBitMapInAssets(context, "phone/playlist/search_btn_f_04.png", SerchConditionBG_ImageView, 1);
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
						new TKBThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_00.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(0);
					}else{
						new TKBThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_04.png", SerchConditionBG_ImageView, 1);
						SerchConditionBG_ImageView.setTag(4);
					}
				}
			});
			//***************************PAD*********************************	
		}		
	}
	

	public void SET_MusicBack_Button_Listner(Button MusicBack,final MusicSourceListView Music_ListView) {
		if(DeviceProperty.isPhone()){
			//***************************PHONE*********************************	
			MusicBack.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					((MusicSourcePhoneAdapter)Music_ListView.getAdapter()).ShowPrivous((Button)v);
				}
			});			
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			MusicBack.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(final View v) {
					((MusicSourcePadAdapter)Music_ListView.getAdapter()).ShowPrivous((Button)v);
				}
			});			
			//***************************PAD*********************************	
		}
	}
	public void SET_MusicTop_Button_Listner(final Button MusicBack, Button MusicTop,final MusicSourceListView Music_ListView){
		if(DeviceProperty.isPhone()){
			//***************************PHONE*********************************	
			MusicTop.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					((MusicSourcePhoneAdapter)Music_ListView.getAdapter()).ShowTopDevice(MusicBack);
				}
			});
			
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			MusicTop.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(final View v) {
					((MusicSourcePadAdapter)Music_ListView.getAdapter()).ShowTopDevice(MusicBack);
				}
			});
			
			//***************************PAD*********************************	
		}
	}
	public void Setting_IButton_LISTNER(ImageButton Setting_IButton) {
		Setting_IButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(fragmentManager.findFragmentByTag("Fragment_SETTING")==null){
					Fragment_SETTING fragment_SETTING = new Fragment_SETTING();
					TKBTool.animationReplaceNAdd2BackFragment(fragmentManager.beginTransaction(), fragment_SETTING, "Fragment_SETTING", R.id.pFAM_RLayout_SETTING_FLayoutt, R.animator.translate_right_in, R.animator.alpha_out,R.animator.alpha_in, R.animator.translate_right_out);
				}
			}
		});		
	}
}
