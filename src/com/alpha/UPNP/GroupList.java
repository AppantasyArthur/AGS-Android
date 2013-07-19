package com.alpha.UPNP;

import java.util.ArrayList;
import java.util.List;

public class GroupList {
	private List<DeviceDisplay> groupList;
	public GroupList(){
		groupList = new ArrayList<DeviceDisplay>();
	}
	public boolean AddDeviceDisplay(DeviceDisplay deviceDisplay){
		if(groupList.indexOf(deviceDisplay)==-1){
			groupList.add(deviceDisplay);
			return true;
		}else{
			return false;
		}
	}
	public boolean RemoveDeviceDisplay(DeviceDisplay deviceDisplay){
		return groupList.remove(deviceDisplay);

	}
	public boolean RemoveDeviceDisplay(int position){
		DeviceDisplay deviceDisplay = groupList.remove(position);
		if(deviceDisplay!=null){
			return true;
		}else{
			return false;
		}
	}
	public List<DeviceDisplay> GetGroupList(){
		return groupList;
	}
}
