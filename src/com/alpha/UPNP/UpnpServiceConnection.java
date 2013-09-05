package com.alpha.UPNP;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.model.meta.Device;
import com.alpha.upnpui.FragmentActivity_Main;
import com.tkb.UpnpOverride.ControlPointProcessBar;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;


public class UpnpServiceConnection implements ServiceConnection{
	private Context context;
	private AndroidUpnpService upnpService;
	private ControlPointProcessBar controlPointProcessBar;
	private BrowseRegistryListener browseRegistryListener;
	
	private int device_size = 0;
	public UpnpServiceConnection(Context context,int device_size,BrowseRegistryListener browseRegistryListener){
		this.context = context;
		this.browseRegistryListener = browseRegistryListener;
		this.device_size = device_size;
	}
	public AndroidUpnpService getUPnPService(){
		return this.upnpService;
	}
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {	
		upnpService = (AndroidUpnpService)service;
		controlPointProcessBar = ((com.tkb.UpnpOverride.AndroidUpnpServiceImpl.Binder)service).getControlPointProcessBar();
		if(device_size==6){
			
		}else{			
			controlPointProcessBar.SetProcessBarListner(((FragmentActivity_Main)context).VIEW_LISTNER.GetProcessBarListner());
		}
		//���y�@��
		for (Device device : upnpService.getRegistry().getDevices()) {
			browseRegistryListener.deviceAdded(device); //�[�JDeviceDisplayList
        }
		
		upnpService.getRegistry().addListener(browseRegistryListener);
		
		// Search asynchronously for all devices
		upnpService.getRegistry().removeAllRemoteDevices();
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
