package com.alpha.upnp.value;

public class ContentDirectoryServiceValues extends ServiceValues {

	public static final String SERVICE_NAME = "ContentDirectory";
	
	public static final String ACTION_BROWSE = "Browse";
	
	public static final String ACTION_BROWSE_INPUT_OBJECT_ID = "ObjectID";
	
	public static final String ACTION_BROWSE_INPUT_BROWSE_FLAG = "BrowseFlag";
	public class BrowseFlag{
		
		public static final String BROWSE_META_DATA = "BrowseMetaData";
		public static final String BROWSE_DIRECT_CHILDREN = "BrowseDirectChildren";
		
	}
	
	public static final String ACTION_BROWSE_INPUT_FILTER = "Filter";
	public static final String ACTION_BROWSE_INPUT_STARTING_INDEX = "StartingIndex";
	public static final String ACTION_BROWSE_INPUT_REQUESTED_COUNT = "RequestedCount";
	public static final String ACTION_BROWSE_INPUT_SORT_CRITERIA = "SortCriteria";
	
	public static final String ACTION_BROWSE_OUTPUT_RESULT = DEFAULT_OUTPUT_RESULT;
	public static final String ACTION_BROWSE_OUTPUT_NUMBER_RETURNED = "NumberReturned";
	public static final String ACTION_BROWSE_OUTPUT_TOTAL_MATCHES = "TotalMatches";
	public static final String ACTION_BROWSE_OUTPUT_UPDATE_ID = "UpdateID";
	
}
