package com.alpha.musicsource;

import com.alpha.upnp.DeviceDisplay;

public interface MusicSourceAdapterListener {
	public void AddMediaServer(DeviceDisplay deviceDisplay);
	public void RemoveMediaServer(DeviceDisplay deviceDisplay);
	public void LocalNameListChange();
}
