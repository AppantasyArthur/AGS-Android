package com.alpha.upnp.value;

public class FirmwareUpdateServiceValues extends ServiceValues {

	public static final String SERVICE_NAME = "FirmwareUpgrade";
	
	public static final String ACTION_CURRENT_FIRMWARE_VERSION = "GetCurrentFirmwareVersion";
	public static final String ACTION_CURRENT_FIRMWARE_VERSION_OUTPUT = "CurrentFirmwareVersion";
	
	public static final String ACTION_FIRMWARE_UPGRADE = "DoFirmwareUpgrade";
	public static final String ACTION_FIRMWARE_UPGRADE_INPUT_TYPE = "Type";
	public static final String ACTION_FIRMWARE_UPGRADE_INPUT_PATH = "Path";
	public static final String ACTION_FIRMWARE_UPGRADE_INPUT_NEWVERSION = "NewVersion";
	public static final String ACTION_FIRMWARE_UPGRADE_EVENT_PROGRESS = "FirmwareUpgradeProgress";
	public static final String ACTION_FIRMWARE_UPGRADE_EVENT_ERROR = "FirmwareUpgradeErrorDiscription";
	
	public static final String ACTION_FIRMWARE_UPGRADE_EVENT_STATE = "FirmwareUpgradeState";
	public static final String ACTION_FIRMWARE_UPGRADE_EVENT_STATE_CHECKING = "CHECKING";
	public static final String ACTION_FIRMWARE_UPGRADE_EVENT_STATE_UPGRADING = "UPGRADING";
	public static final String ACTION_FIRMWARE_UPGRADE_EVENT_STATE_UPGRADE_SUCCESS = "UPGRADE_SUCCESS";
	public static final String ACTION_FIRMWARE_UPGRADE_EVENT_STATE_REBOOTING = "REBOOTING";
	
	
	public static final String ACTION_NEW_FIRMWARE_INFO = "GetNewFirmwareInfo";
	public static final String ACTION_NEW_FIRMWARE_INFO_OUTPUT = "NewFirmwareInfo";
	
	 
	
}
