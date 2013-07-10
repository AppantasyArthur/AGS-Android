package com.alpha.UPNP;

import org.teleal.cling.model.meta.Device;

import com.appantasy.androidapptemplate.event.lastchange.GroupVO;

public class DeviceDisplay {

	private Device device;
	private Device MMDevice;
	private GroupVO groupVO;
    public DeviceDisplay(Device device) {
        this.device = device;
        this.groupVO = new GroupVO();
    }

    public Device getDevice() {
        return device;
    }
    public void setMMDevice(Device MMDevice) {
        this.MMDevice = MMDevice;
    }

    public Device getMMDevice() {
        return MMDevice;
    }
    public GroupVO getGroupVO() {
		return groupVO;
	}
	public void setGroupVO(GroupVO groupVO) {
		this.groupVO = groupVO;
	}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDisplay that = (DeviceDisplay) o;
        return device.equals(that.device);
    }

    @Override
    public int hashCode() {
        return device.hashCode();
    }

    @Override
    public String toString() {
        // Display a little star while the device is being loaded
        return device.isFullyHydrated() ? device.getDisplayString() : device.getDisplayString() + " *";
    }
}
