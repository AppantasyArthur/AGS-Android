/*
 * Copyright (C) 2010 Teleal GmbH, Switzerland
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.tkb.UpnpOverride;

import org.teleal.cling.UpnpServiceConfiguration;
import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.controlpoint.ControlPoint;
import org.teleal.cling.controlpoint.SubscriptionCallback;
import org.teleal.cling.model.message.header.MXHeader;
import org.teleal.cling.model.message.header.STAllHeader;
import org.teleal.cling.registry.Registry;
import org.teleal.cling.protocol.ProtocolFactory;
import org.teleal.cling.model.message.header.UpnpHeader;
import org.teleal.cling.model.types.DeviceType;

import android.util.Log;

import java.util.logging.Logger;

/**
 * Default implementation.
 * <p>
 * This implementation uses the executor returned by
 * {@link org.teleal.cling.UpnpServiceConfiguration#getSyncProtocolExecutor()}.
 * </p>
 *
 * @author Christian Bauer
 */
//TKB// implements ControlPointProcessBar
public class ControlPointImpl implements ControlPoint,ControlPointProcessBar{

    private static Logger log = Logger.getLogger(ControlPointImpl.class.getName());

    protected final UpnpServiceConfiguration configuration;
    protected final ProtocolFactory protocolFactory;
    protected final Registry registry;
    protected ProcessBarListner processBarListner;

    public ControlPointImpl(UpnpServiceConfiguration configuration, ProtocolFactory protocolFactory, Registry registry) {
        log.fine("Creating ControlPoint: " + getClass().getName());
        
        this.configuration = configuration;
        this.protocolFactory = protocolFactory;
        this.registry = registry;
    }

    public UpnpServiceConfiguration getConfiguration() {
        return configuration;
    }

    public ProtocolFactory getProtocolFactory() {
        return protocolFactory;
    }

    public Registry getRegistry() {
        return registry;
    }

    public void search() {
        search(new STAllHeader(), MXHeader.DEFAULT_VALUE);
    }

    public void search(UpnpHeader searchType) {
        search(searchType, MXHeader.DEFAULT_VALUE);
    }

    public void search(int mxSeconds) {
        search(new STAllHeader(), mxSeconds);
    }

    public void search(UpnpHeader searchType, int mxSeconds) {
        log.fine("Sending asynchronous search for: " + searchType.getString());
        getConfiguration().getAsyncProtocolExecutor().execute(
                getProtocolFactory().createSendingSearch(searchType, mxSeconds)
        );
    }

    public void execute(ActionCallback callback) {
        log.fine("Invoking action in background: " + callback);
        callback.setControlPoint(this);
        getConfiguration().getSyncProtocolExecutor().execute(callback);
        
        DeviceType deviceType = callback.getActionInvocation().getAction().getService().getDevice().getType();
        Log.i("aaaaaaa", "deviceType = "+deviceType.getType());
        if(deviceType.getType().toString().equals("MediaRenderer")&&processBarListner!=null){
        	processBarListner.SetProcessBarDisplay();
        }
    }

    public void execute(SubscriptionCallback callback) {
        log.fine("Invoking subscription in background: " + callback);
        callback.setControlPoint(this);
        getConfiguration().getSyncProtocolExecutor().execute(callback);
    }
    //TKB//SetProcessBarListner
	@Override
	public void SetProcessBarListner(ProcessBarListner processBarListner) {
		this.processBarListner = processBarListner;
	}    
}
