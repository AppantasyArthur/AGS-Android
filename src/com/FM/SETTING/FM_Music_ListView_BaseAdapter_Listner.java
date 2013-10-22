package com.FM.SETTING;

import com.alpha.upnp.DeviceDisplay;

public interface FM_Music_ListView_BaseAdapter_Listner {
	public void AddMediaServer(DeviceDisplay deviceDisplay);
	public void RemoveMediaServer(DeviceDisplay deviceDisplay);
	public void LocalNameListChange();
}
