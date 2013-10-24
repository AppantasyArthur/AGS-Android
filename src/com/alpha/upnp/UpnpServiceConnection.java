package com.alpha.upnp;

import org.teleal.cling.android.AndroidUpnpService;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.alpha.upnp.device.AGSMediaServer;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.util.DeviceProperty;
import com.tkb.UpnpOverride.ControlPointProcessBar;


public class UpnpServiceConnection implements ServiceConnection{
	
	private Context context;
	private AndroidUpnpService upnpService;
	private ControlPointProcessBar controlPointProcessBar;
	private BrowseRegistryListener browseRegistryListener;
	
	private int device_size = 0;
	
	private AGSMediaServer localMediaServer;
	
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
		
		MainFragmentActivity.setServiceAndroidUPnP(upnpService);
		
		// Init Control Point Process Bar
		controlPointProcessBar = ((com.tkb.UpnpOverride.AndroidUpnpServiceImpl.Binder)service).getControlPointProcessBar();
		if(DeviceProperty.isPhone()){
			
		}else{			
			controlPointProcessBar.SetProcessBarListner(((MainFragmentActivity)context).VIEW_LISTNER.getProcessBarListner());
		}
		
		//掃描一次
//		for (Device device : upnpService.getRegistry().getDevices()) {
//			browseRegistryListener.deviceAdded(device); //加入DeviceDisplayList
//        }
		
		upnpService.getRegistry().addListener(browseRegistryListener);
		
		// Search asynchronously for all devices
		upnpService.getRegistry().removeAllRemoteDevices();
        upnpService.getControlPoint().search(); // 呼叫 Service 裡的 upnpservice 開始 search
        
        //  
        localMediaServer = new AGSMediaServer();
		upnpService.getRegistry().addDevice(localMediaServer.getDevice());
        
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		if(upnpService!=null){		
			upnpService.getRegistry().removeListener(browseRegistryListener);
		}
		upnpService = null;
	}
}
