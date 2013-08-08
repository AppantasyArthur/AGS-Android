package com.alpha.UPNP;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.model.meta.Device;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;

public class UpnpServiceConnection implements ServiceConnection{
	private Context context;
	private AndroidUpnpService upnpService;
	private BrowseRegistryListener browseRegistryListener;
	public UpnpServiceConnection(Context context,BrowseRegistryListener browseRegistryListener){
		this.context = context;
		this.browseRegistryListener = browseRegistryListener;
	}
	public AndroidUpnpService getUPnPService(){
		return this.upnpService;
	}
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {	
		upnpService = (AndroidUpnpService)service;
		//���y�@��
		for (Device device : upnpService.getRegistry().getDevices()) {
			browseRegistryListener.deviceAdded(device); //�[�JDeviceDisplayList
        }
		
		upnpService.getRegistry().addListener(browseRegistryListener);
		// Search asynchronously for all devices
        upnpService.getControlPoint().search(); // �I�s Service �̪� upnpservice �}�l search
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		if(upnpService!=null){		
			upnpService.getRegistry().removeListener(browseRegistryListener);
		}
		upnpService = null;
	}
}
