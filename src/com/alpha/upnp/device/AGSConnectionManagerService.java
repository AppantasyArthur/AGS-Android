package com.alpha.upnp.device;

import org.teleal.cling.model.ServiceReference;
import org.teleal.cling.model.action.ActionException;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.support.connectionmanager.AbstractPeeringConnectionManagerService;
import org.teleal.cling.support.model.ConnectionInfo;
import org.teleal.cling.support.model.ConnectionInfo.Direction;
import org.teleal.cling.support.model.ProtocolInfo;

public class AGSConnectionManagerService extends
		AbstractPeeringConnectionManagerService {

	@Override
	protected void closeConnection(ConnectionInfo ci) {
		

	}

	@Override
	protected ConnectionInfo createConnection(int arg0, int arg1,
			ServiceReference arg2, Direction arg3, ProtocolInfo arg4)
			throws ActionException {
		
		return null;
	}

	@Override
	protected void peerFailure(ActionInvocation arg0, UpnpResponse arg1,
			String arg2) {
		

	}

}
