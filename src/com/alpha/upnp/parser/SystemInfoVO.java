package com.alpha.upnp.parser;

public class SystemInfoVO {

	public static String FIRMWARE_VERSION = "Firmware Version";
	public static String SERIAL_NUMBER = "Serial Number";
	
	public static String TAG_FIRMWARE_VERSION = "FirmwareVersion";
	public static String TAG_SERIAL_NUMBER = "SerialNumber";
	
	private String firmwareVersion;
	private String serialNumber;
	
	public String getFirmwareVersion() {
		return firmwareVersion;
	}
	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
}
