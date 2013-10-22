package com.alpha.util;

import com.alpha.upnpui.MainFragmentActivity;
import com.tkb.tool.DeviceInformation;

public class DeviceProperty {

	private static DeviceInformation di = new DeviceInformation(MainFragmentActivity.getContext());
	public static Boolean isPhone(){
		
		if(di.getDeviceScreenSize() <= 6)
			return true;
		else
			return false;
		
	}

}
