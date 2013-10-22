package com.alpha.setting.alarm.musicbrowsing;

import java.util.List;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.UDAServiceType;
import org.teleal.cling.support.contentdirectory.DIDLParser;
import org.teleal.cling.support.contentdirectory.callback.Browse;
import org.teleal.cling.support.model.BrowseFlag;
import org.teleal.cling.support.model.DIDLContent;
import org.teleal.cling.support.model.SortCriterion;
import org.teleal.cling.support.model.container.Container;
import org.teleal.cling.support.model.item.Item;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.alpha.fragments.settings.AlarmSettingAddEditFragement;
import com.alpha.fragments.settings.ags.AGSAlarmSettingAddEditFragement;
import com.alpha.setting.alarm.musicbrowsing.AlarmSettingMusicBrowsingListViewPadAdapter.ViewHandler;
import com.alpha.upnp.parser.TrackDO;
import com.alpha.upnp.value.AGSHandlerMessages;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;

// FSAl_Frequency_VIEW_LISTNER
public class AlarmSettingMusicBrowsingViewListener {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "AlarmSettingMusicBrowsingViewListener";
	private int sizeDeviceScreen = 0;
	
	public AlarmSettingMusicBrowsingViewListener(Context context, int device_size) {
		this.context = context;
		this.mlog.switchLog = true;
		this.sizeDeviceScreen = device_size;
	}	
	
	public void setMusicBrowsingListViewBackButtonListener(Button Back_Button,final FragmentManager fragmentManager){
		if(sizeDeviceScreen==6){
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					//TKBTool.animationRemoveFragment(fragmentManager, "Fragment_SAlarm_Music", R.animator.alpha_in, R.animator.alpha_out);
					TKBTool.doPopBackFragment(fragmentManager);
				}
			});	
		}else{
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					//TKBTool.animationRemoveFragment(fragmentManager, "Fragment_SAlarm_Music", R.animator.alpha_in, R.animator.alpha_out);
					TKBTool.doPopBackFragment(fragmentManager);
				}
			});	
		}		
	}
	public void setMusicBrowsingListViewListener(ListView listMusicBrowsing, final Button buttonBack,final FragmentManager fragmentManager){
		
		if(sizeDeviceScreen == 6){
			
			//***************************PHONE*********************************	
			listMusicBrowsing.setOnItemClickListener(new OnItemClickListener(){				
				@SuppressWarnings("rawtypes")
				@Override
				public void onItemClick(final AdapterView<?> parent, final View view, int position, long id) {
					
					AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
					AlarmSettingMusicBrowsingListViewPhoneAdapter.ViewHandler viewHandler = (AlarmSettingMusicBrowsingListViewPhoneAdapter.ViewHandler)view.getTag();
					
					int kind = viewHandler.kindOfItme;					
					SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+dc:title")};
					BrowseFlag browseFlag = BrowseFlag.DIRECT_CHILDREN;					
					
					if(kind== 0 ){			
//						//Category
						((AlarmSettingMusicBrowsingListViewPhoneAdapter)parent.getAdapter()).ShowLevelOne(buttonBack,(Integer)viewHandler.object);
					}else if(kind == 1){
						//Device
						//取得Device
						Device device = (Device)((AlarmSettingMusicBrowsingListViewPhoneAdapter.ViewHandler)view.getTag()).object;
						//設定選取Device
						((AlarmSettingMusicBrowsingListViewPhoneAdapter)parent.getAdapter()).setChooseDevice(device);
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
								((AlarmSettingMusicBrowsingListViewPhoneAdapter)parent.getAdapter()).ShowFile(buttonBack,"0", listC,listI);
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
						Device device = ((AlarmSettingMusicBrowsingListViewPhoneAdapter)parent.getAdapter()).getChooseDevice();
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
								((AlarmSettingMusicBrowsingListViewPhoneAdapter)parent.getAdapter()).ShowFile(buttonBack,ParentID, listC,listI);								
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
						
						Device device = ((AlarmSettingMusicBrowsingListViewPhoneAdapter)parent.getAdapter()).getChooseDevice();
						if(device == null){
							Log.d(tag, "doesn't select device");
							return ;
						}
						
						//Item
						final Item item = (Item)viewHandler.object;
						Browse browse = new Browse(device.findService(new UDAServiceType("ContentDirectory")), item.getId(), BrowseFlag.METADATA, "*", 0, 1l, sortCriterion){

							@Override
							public void received(ActionInvocation ai, DIDLContent contentDIDL) {
								
								final String music = item.getTitle();
								final String uri = item.getFirstResource().getValue().toString();
								final String dataMeta = ai.getOutput()[0].toString();
								
								//設定資料
//								AlarmSettingAddEditFragement fragementAddEditAlarmSetting = (AlarmSettingAddEditFragement)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
//								if(fragementAddEditAlarmSetting != null){
//									fragementAddEditAlarmSetting.setAlarmSettingProfileMusic(item, 3);
//									fragementAddEditAlarmSetting.setAlarmSettingProfileMusicURI(uri);
//									fragementAddEditAlarmSetting.setAlarmSettingProfileMusicMetaData(dataMeta);
//								}
								
								Log.d(tag, music);
								Log.d(tag, uri);
								Log.d(tag, dataMeta);
								
								Thread t = new Thread(){

									@Override
									public void run() {
										Looper.prepare();
										Handler handlerMessage = AGSAlarmSettingAddEditFragement.getMessageHandler();
										handlerMessage.sendMessage(handlerMessage.obtainMessage(AGSHandlerMessages.SET_MUSIC, music));
										handlerMessage.sendMessage(handlerMessage.obtainMessage(AGSHandlerMessages.SET_URI, uri));
										handlerMessage.sendMessage(handlerMessage.obtainMessage(AGSHandlerMessages.SET_METADATA, dataMeta));
										Looper.loop();
									}
									
								};
								t.start();
								
								//TKBTool.animationRemoveFragment(fragmentManager, "Fragment_SAlarm_Music", R.animator.alpha_in, R.animator.alpha_out);
								TKBTool.doPopBackFragment(fragmentManager);
								
							}

							@Override
							public void updateStatus(Status status) {
								Log.d(tag, "AlarmSettingMusicBrowsing Media Server Browsing MetaData updateStatus - ");
								Log.d(tag, "Status : " + status);
							}

							@Override
							public void failure(ActionInvocation ai,
									UpnpResponse ur, String message) {
								Log.d(tag, "AlarmSettingMusicBrowsing Media Server Browsing MetaData failure - ");
								Log.d(tag, "UpnpResponse : " + ur );
								Log.d(tag, "other message : " + message);
							}
							
						};
						upnpServer.getControlPoint().execute(browse);
						
						
						
					}else if(kind == 4){
						//List
						String Name = (String)viewHandler.object;
						mlog.info(tag,"Name = "+Name);
						((AlarmSettingMusicBrowsingListViewPhoneAdapter)parent.getAdapter()).ShowLocalFile(buttonBack,Name);
						
					}else if(kind ==5){
						//Track
						TrackDO trackDO = (TrackDO)viewHandler.object;
						//設定資料
						AlarmSettingAddEditFragement fragment_SAlarm_EditAdd = (AlarmSettingAddEditFragement)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
						if(fragment_SAlarm_EditAdd!=null){
							fragment_SAlarm_EditAdd.setAlarmSettingProfileMusic(trackDO, 5);
						}
						TKBTool.animationRemoveFragment(fragmentManager, "Fragment_SAlarm_Music", R.animator.alpha_in, R.animator.alpha_out);
					}
				}
			});
			//***************************PHONE*********************************	
			
		}else{
			
			final SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+dc:title")};
			
			//***************************PAD*********************************	
			listMusicBrowsing.setOnItemClickListener(new OnItemClickListener(){				
				@SuppressWarnings("rawtypes")
				@Override
				public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
					
					AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
					
					ViewHandler viewHandler = (ViewHandler)view.getTag();
					int kind = viewHandler.kindOfItme;
						
					if(kind == 0 ){
						
						//Category
						((AlarmSettingMusicBrowsingListViewPadAdapter)parent.getAdapter()).ShowLevelOne(buttonBack,(Integer)viewHandler.object);
						
					}else if(kind == 1){ // first 
						
						SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+dc:title")};
						BrowseFlag browseFlag = BrowseFlag.DIRECT_CHILDREN;	
			
						//取得Device
						Device device = (Device)((AlarmSettingMusicBrowsingListViewPadAdapter.ViewHandler)view.getTag()).object;
						
						//設定選取Device
						((AlarmSettingMusicBrowsingListViewPadAdapter)parent.getAdapter()).setChooseDevice(device);
						mlog.info(tag, "browsing device = " + device.getDetails().getFriendlyName());
						
						Service service = device.findService(new UDAServiceType("ContentDirectory"));
						Browse browse = new Browse(service, "0", browseFlag, "*", 0, 0l, sortCriterion){
							@Override
							public void received(ActionInvocation ai, DIDLContent contentDIDL) {
								
								for(int i = 0;i < ai.getOutput().length;i++){
									mlog.info(tag, "AlarmSettingMusicBrowsing ouput : " + ai.getOutputMap().toString());	
								}
								
								//取得Container List
								List<Container> listC = contentDIDL.getContainers();
								
								//取得 Item List
								List<Item> listI = contentDIDL.getItems();
								
								//更新FM_Music_ListView_BaseAdapter
								((AlarmSettingMusicBrowsingListViewPadAdapter)parent.getAdapter()).showBorwsingResult(buttonBack, "0", listC, listI);
								
							}
							
							@Override
							public void updateStatus(Status status) {
								Log.d(tag, "AlarmSettingMusicBrowsing Media Server First Browsing updateStatus - ");
								Log.d(tag, "Status : " + status);
							}
							
							@Override
							public void failure(ActionInvocation ai, UpnpResponse ur, String msg) {
								Log.d(tag, "AlarmSettingMusicBrowsing Media Server First Browsing failure - ");
								Log.d(tag, "UpnpResponse : " + ur );
								Log.d(tag, "other message : " + msg);
							}
							
						};	
						upnpServer.getControlPoint().execute(browse);
						
					}else if(kind == 2){
						
						//Container 
						Container container = (Container)viewHandler.object;					
						
						final String ParentID = container.getParentID();
						String ObjectID = container.getId();
						Device device = ((AlarmSettingMusicBrowsingListViewPadAdapter)parent.getAdapter()).getChooseDevice();
						if(device == null){
							Log.d(tag, "doesn't select device");
							return ;
						}
						
						Browse browse = new Browse(device.findService(new UDAServiceType("ContentDirectory")), ObjectID, BrowseFlag.DIRECT_CHILDREN, "*", 0, 0l, sortCriterion){
							@Override
							public void received(ActionInvocation ai, DIDLContent contentDIDL) {
								
								for(int i = 0;i < ai.getOutput().length;i++){
									mlog.info(tag, "AlarmSettingMusicBrowsing ouput : " + ai.getOutput()[i].toString());	
								}
								
								//取得Container List
								List<Container> listC = contentDIDL.getContainers();
								
								//取得 Item List
								List<Item> listI = contentDIDL.getItems();
								
								//mlog.info(tag, "C Size = "+listC.size()+"&& I Size = "+ listI.size());								
								
								//更新FM_Music_ListView_BaseAdapter
								((AlarmSettingMusicBrowsingListViewPadAdapter)parent.getAdapter()).showBorwsingResult(buttonBack, ParentID, listC, listI);
								
							}
							
							@Override
							public void updateStatus(Status status) {
								Log.d(tag, "AlarmSettingMusicBrowsing Media Server Browsing updateStatus - ");
								Log.d(tag, "Status : " + status);
							}
							
							@Override
							public void failure(ActionInvocation ai, UpnpResponse ur, String msg) {
								Log.d(tag, "AlarmSettingMusicBrowsing Media Server Browsing failure - ");
								Log.d(tag, "UpnpResponse : " + ur );
								Log.d(tag, "other message : " + msg);
							}
							
						};											
						upnpServer.getControlPoint().execute(browse);
						
					}else if(kind == 3){
						
						Device device = ((AlarmSettingMusicBrowsingListViewPadAdapter)parent.getAdapter()).getChooseDevice();
						if(device == null){
							Log.d(tag, "doesn't select device");
							return ;
						}
						
						//Item
						final Item item = (Item)viewHandler.object;
						Browse browse = new Browse(device.findService(new UDAServiceType("ContentDirectory")), item.getId(), BrowseFlag.METADATA, "*", 0, 1l, sortCriterion){

							@Override
							public void received(ActionInvocation ai, DIDLContent contentDIDL) {
								
								final String music = item.getTitle();
								final String uri = item.getFirstResource().getValue().toString();
								final String dataMeta = ai.getOutput()[0].toString();
								DIDLParser p = new DIDLParser();
								try {
									String xml = p.generate(contentDIDL);
									if(dataMeta.equalsIgnoreCase(xml)){
										Log.d(tag, "兩者相等");
									}else{
										Log.d(tag, "兩者不相等");
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								
								//設定資料
//								AlarmSettingAddEditFragement fragementAddEditAlarmSetting = (AlarmSettingAddEditFragement)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
//								if(fragementAddEditAlarmSetting != null){
//									fragementAddEditAlarmSetting.setAlarmSettingProfileMusic(item, 3);
//									fragementAddEditAlarmSetting.setAlarmSettingProfileMusicURI(uri);
//									fragementAddEditAlarmSetting.setAlarmSettingProfileMusicMetaData(dataMeta);
//								}
								
								Log.d(tag, music);
								Log.d(tag, uri);
								Log.d(tag, dataMeta);
								
								Thread t = new Thread(){

									@Override
									public void run() {
										Looper.prepare();
										Handler handlerMessage = AGSAlarmSettingAddEditFragement.getMessageHandler();
										handlerMessage.sendMessage(handlerMessage.obtainMessage(AGSHandlerMessages.SET_MUSIC, music));
										handlerMessage.sendMessage(handlerMessage.obtainMessage(AGSHandlerMessages.SET_URI, uri));
										handlerMessage.sendMessage(handlerMessage.obtainMessage(AGSHandlerMessages.SET_METADATA, dataMeta));
										Looper.loop();
									}
									
								};
								t.start();
								
								//TKBTool.animationRemoveFragment(fragmentManager, "Fragment_SAlarm_Music", R.animator.alpha_in, R.animator.alpha_out);
								TKBTool.doPopBackFragment(fragmentManager);
								
							}

							@Override
							public void updateStatus(Status status) {
								Log.d(tag, "AlarmSettingMusicBrowsing Media Server Browsing MetaData updateStatus - ");
								Log.d(tag, "Status : " + status);
							}

							@Override
							public void failure(ActionInvocation ai,
									UpnpResponse ur, String message) {
								Log.d(tag, "AlarmSettingMusicBrowsing Media Server Browsing MetaData failure - ");
								Log.d(tag, "UpnpResponse : " + ur );
								Log.d(tag, "other message : " + message);
							}
							
						};
						upnpServer.getControlPoint().execute(browse);
						
						
						
					}else if(kind == 4){ // Local Play-List Container
						
						//List
						String Name = (String)viewHandler.object;
						mlog.info(tag,"Name = " + Name);
						((AlarmSettingMusicBrowsingListViewPadAdapter)parent.getAdapter()).ShowLocalFile(buttonBack,Name);
						
					}else if(kind == 5){ // Local Play-List Item
						
						//Track
						TrackDO trackDO = (TrackDO)viewHandler.object;
						
						//設定資料
						AlarmSettingAddEditFragement fragementAddEditAlarmSetting = (AlarmSettingAddEditFragement)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
						if(fragementAddEditAlarmSetting != null){
							fragementAddEditAlarmSetting.setAlarmSettingProfileMusic(trackDO, 5);
						}
						
						TKBTool.animationRemoveFragment(fragmentManager, "Fragment_SAlarm_Music", R.animator.alpha_in, R.animator.alpha_out);
						
					}
					
				}
				
			});			
			//***************************PAD*********************************
			
		}	
		
	}
	
	public void setMusicBrowsingBackButtonListener(Button buttonBack,final ListView listMusicBrowsing) {
		
		if(sizeDeviceScreen == 6){
			
			//***************************PHONE*********************************	
			buttonBack.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					((AlarmSettingMusicBrowsingListViewPhoneAdapter)listMusicBrowsing.getAdapter()).ShowPrivous((Button)v);
				}
			});			
			//***************************PHONE*********************************
			
		}else{
			
			//***************************PAD*********************************
			buttonBack.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(final View v) {
					((AlarmSettingMusicBrowsingListViewPadAdapter)listMusicBrowsing.getAdapter()).ShowPrivous((Button)v);
				}
			});			
			//***************************PAD*********************************
			
		}
		
	}
	
	public void setMusicBrowsingGoTopButtonListener(final Button MusicBack, Button MusicTop,final ListView Music_ListView){
		
		if(sizeDeviceScreen == 6){
			
			//***************************PHONE*********************************	
			MusicTop.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					((AlarmSettingMusicBrowsingListViewPhoneAdapter)Music_ListView.getAdapter()).ShowTopDevice(MusicBack);
				}
			});
			//***************************PHONE*********************************
			
		}else{
			
			//***************************PAD*********************************
			MusicTop.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(final View v) {
					((AlarmSettingMusicBrowsingListViewPadAdapter)Music_ListView.getAdapter()).ShowTopDevice(MusicBack);
				}
			});
			//***************************PAD*********************************
			
		}
	}
}
