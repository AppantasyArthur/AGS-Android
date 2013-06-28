package com.alpha.UPNP;

import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.LocalDevice;
import org.teleal.cling.model.meta.RemoteDevice;
import org.teleal.cling.registry.DefaultRegistryListener;
import org.teleal.cling.registry.Registry;

public class BrowseRegistryListener extends DefaultRegistryListener {
	private DeviceDisplayList deviceDisplayList;
	public BrowseRegistryListener(DeviceDisplayList deviceDisplayList){
		this.deviceDisplayList = deviceDisplayList;
	}
	@Override
    public void remoteDeviceDiscoveryStarted(Registry registry, RemoteDevice device) {
        //deviceAdded(device);
    }
    @Override
    public void remoteDeviceDiscoveryFailed(Registry registry, final RemoteDevice device, final Exception ex) {
        deviceRemoved(device);
    }

    @Override
    public void remoteDeviceAdded(Registry registry, RemoteDevice device) {
        deviceAdded(device);
        System.out.println("remote found : " + device.getType().getDisplayString());
    }

    @Override
    public void remoteDeviceRemoved(Registry registry, RemoteDevice device) {
        deviceRemoved(device);
    }

    @Override
    public void localDeviceAdded(Registry registry, LocalDevice device) {
        //deviceAdded(device);
    	System.out.println("local found : " + device.getType().getDisplayString());
    }

    @Override
    public void localDeviceRemoved(Registry registry, LocalDevice device) {
        deviceRemoved(device);
    }

    public void deviceAdded(final Device device) {
    	DeviceDisplay dd = new DeviceDisplay(device);
    	deviceDisplayList.addDeviceDisplay(dd);
    }

    public void deviceRemoved(final Device device) {
    	DeviceDisplay dd = new DeviceDisplay(device);
    	deviceDisplayList.removeDeviceDisplay(dd);
    }	
}
