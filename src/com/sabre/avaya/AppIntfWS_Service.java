/**
 * AppIntfWS_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sabre.avaya;

public interface AppIntfWS_Service extends javax.xml.rpc.Service {
    public java.lang.String getAppIntfWSAddress();

    public AppIntfWS_PortType getAppIntfWS() throws javax.xml.rpc.ServiceException;

    public AppIntfWS_PortType getAppIntfWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
