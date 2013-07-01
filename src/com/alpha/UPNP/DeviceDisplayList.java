package com.alpha.UPNP;

import java.util.ArrayList;
import java.util.List;

import org.teleal.cling.model.meta.DeviceDetails;
import org.teleal.cling.model.types.DeviceType;

import com.FM.SETTING.FM_Music_ListView_BaseAdapter_Listner;
import com.FS.SETTING.FS_SPEAKER_ExpandableListAdapter_Listner;
import com.tkb.tool.MLog;

public class DeviceDisplayList {
	private static String TAG = "DeviceDisplayList";
	private MLog mlog = new MLog();
	
	private DeviceDisplay ChooseMediaRenderer;
	private List<DeviceDisplay> DDList;
	private List<DeviceDisplay> MRList;//MediaRenderer List;
	private List<DeviceDisplay> MSList;//MediaServer List;
	private FS_SPEAKER_ExpandableListAdapter_Listner FSELAListner;
	private FM_Music_ListView_BaseAdapter_Listner FMLBAListner;
	
	
	public DeviceDisplayList(){
		this.mlog.LogSwitch = true;
		ChooseMediaRenderer = null;
		DDList = new ArrayList<DeviceDisplay>();
		MRList = new ArrayList<DeviceDisplay>();
		MSList = new ArrayList<DeviceDisplay>();
	}
	public void addDeviceDisplay(DeviceDisplay dd) {
		DeviceType deviceType = dd.getDevice().getType();
		if(MRList.indexOf(dd)!=-1||MSList.indexOf(dd)!=-1||DDList.indexOf(dd)!=-1){
			mlog.info(TAG, "addDeviceDisplay = return");
			return;
		}
		if(deviceType.getType().toString().equals("MediaRenderer")){
			//MediaRenderer List
			MRList.add(dd);
			if(FSELAListner!=null){
				FSELAListner.AddMediaRenderer(dd);
			}
		}else if(deviceType.getType().toString().equals("MediaServer")){
			//MediaServer List
			MSList.add(dd);
			if(FMLBAListner!=null){
				FMLBAListner.AddMediaServer(dd);
			}
		}else{
			DDList.add(dd);
		}
		DeviceDetails DeviceDetails = dd.getDevice().getDetails();
		mlog.info(TAG, "addDeviceDisplay = "+DeviceDetails.getFriendlyName());
	}
	public void removeDeviceDisplay(DeviceDisplay dd) {
		DeviceType deviceType = dd.getDevice().getType();
		if(deviceType.getType().toString().equals("MediaRenderer")){
			if(FSELAListner!=null){
				FSELAListner.RemoveMediaRenderer(dd);
			}
			mlog.info(TAG, "removeDeviceDisplay = MR");
			MRList.remove(dd);
		}else if(deviceType.getType().toString().equals("MediaServer")){
			if(FMLBAListner!=null){
				FMLBAListner.RemoveMediaServer(dd);
			}
			mlog.info(TAG, "removeDeviceDisplay = MS");
			MSList.remove(dd);
		}else{
			DDList.remove(dd);
			mlog.info(TAG, "removeDeviceDisplay = DD");
		}
	}
	public void setChooseMediaRenderer(DeviceDisplay mediaRenderer){
		this.ChooseMediaRenderer = mediaRenderer;
	}
	public DeviceDisplay getChooseMediaRenderer(){
		return this.ChooseMediaRenderer;
	}
	public List<DeviceDisplay> getDeviceDisplayList(){
		return DDList;
	}
	public List<DeviceDisplay> getMediaRendererList(){
		return MRList;
	}
	public List<DeviceDisplay> getMediaServerList(){
		return MSList;
	}
	public void setSpeakerListner(FS_SPEAKER_ExpandableListAdapter_Listner FSELAListner){
		this.FSELAListner = FSELAListner;
	}
	public void setMusicListner(FM_Music_ListView_BaseAdapter_Listner FMLBAListner){
		this.FMLBAListner = FMLBAListner;
	}
}
