package com.alpha.setting.alarm.musicbrowsing;

import com.alpha.upnp.DeviceDisplay;

// FSAl_Music_ListView_BaseAdapter_Listner
public interface AlarmSettingMusicBrowsingAdapterListener {
	public void addMediaServer(DeviceDisplay deviceDisplay);
	public void removeMediaServer(DeviceDisplay deviceDisplay);
}
