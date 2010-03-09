/**
 * AppIntfWS_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sabre.avaya;

public interface AppIntfWS_PortType extends java.rmi.Remote {
    public GetStatusResponse getStatus(GetStatusRequest parameters) throws java.rmi.RemoteException, GetStatusFault;
    public LaunchVXMLResponse launchVXML(LaunchVXMLRequest parameters) throws java.rmi.RemoteException, LaunchVXMLFault;
    public LaunchCCXMLResponse launchCCXML(LaunchCCXMLRequest parameters) throws java.rmi.RemoteException, LaunchCCXMLFault;
    public SendCCXMLEventResponse sendCCXMLEvent(SendCCXMLEventRequest parameters) throws java.rmi.RemoteException, SendCCXMLEventFault;
    public QueryResourcesResponse queryResources(QueryResourcesRequest parameters) throws java.rmi.RemoteException, QueryResourcesFault;
}
