package com.alpha.upnp.value;

public class AVTransportServiceValues extends ServiceValues {

	public static final String SERVICE_NAME = "AVTransport";
	
	public static final String ACTION_ADD_TRACK_TO_QUEUE = "AddTrackToQueue";
	public static final String ACTION_ADD_TRACK_TO_QUEUE_INPUT_INSTANCE_ID = "InstanceID";
	public static final String ACTION_ADD_TRACK_TO_QUEUE_INPUT_TRACK_URI = "TrackURI";
	public static final String ACTION_ADD_TRACK_TO_QUEUE_INPUT_TRACK_URI_META_DATA = "TrackURIMetaData";
	public static final String ACTION_ADD_TRACK_TO_QUEUE_INPUT_TRACK_NUMBER = "TrackNumber";
	public static final String ACTION_ADD_TRACK_TO_QUEUE_INPUT_PLAY_NOW = "PlayNow";
	public static final String ACTION_ADD_TRACK_TO_QUEUE_OUTPUT_NR_TRACKS_ADDED = "NrTracksAdded";
	
	public static final String ACTION_DUMP_ALL_TRACKS_IN_QUEUE = "DumpAllTracksInQueue";
	public static final String ACTION_DUMP_ALL_TRACKS_IN_QUEUE_INPUT_INSTANCE_ID = "InstanceID";
	public static final String ACTION_DUMP_ALL_TRACKS_IN_QUEUE_OUTPUT_ALL_TRACKS_DIDL = "AllTracksDIDL";
	public static final String ACTION_DUMP_ALL_TRACKS_IN_QUEUE_OUTPUT_NUMBER_RETURNED = "NumberReturned";
	
}
