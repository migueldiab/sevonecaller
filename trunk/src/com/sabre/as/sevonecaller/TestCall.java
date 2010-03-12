/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sabre.as.sevonecaller;

import com.sabre.avaya.MyEngineConfigurationFactory;
import com.sabre.avaya.AppIntfWS_PortType;
import com.sabre.avaya.AppIntfWS_ServiceLocator;
import com.sabre.avaya.LaunchVXMLRequest;
import com.sabre.avaya.LaunchVXMLResponse;
import java.net.URL;
import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.Stub;

/**
 *
 * @author SG0894180
 */
public class TestCall {

    /**
     * @param args the command line arguments
     */
    public void callMe(String message) {
        try { // Call Web Service Operation
          System.out.println("Creating Avaya Environment");
          System.setProperty("javax.net.ssl.trustStore","C:\\Program Files\\Java\\jre6\\lib\\security\\AvayaCerts");

          //
          // com.avaya.vp.services.AppIntfWS_Service service = new com.avaya.vp.services.AppIntfWS_Service();
          //
          // com.avaya.vp.services.AppIntfWS port = service.getAppIntfWS();
          //
          // ((BindingProvider)port).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "coffee");
          // ((BindingProvider)port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "P1zza2g0");
          // https://10.135.94.80/axis/services/AppIntfWS?wsdl


          MyEngineConfigurationFactory configFactory =
          new MyEngineConfigurationFactory(MyEngineConfigurationFactory.AUTHENTICATION_TYPE.DIGEST);

          EngineConfiguration config = configFactory.getClientEngineConfig();

          /* locate the service */
          System.out.println("Locating the service");
          AppIntfWS_ServiceLocator locator = new AppIntfWS_ServiceLocator(config);
          URL avayaURL = new URL("https://10.135.94.80/axis/services/AppIntfWS");
          AppIntfWS_PortType AvayaPort = locator.getAppIntfWS(avayaURL);

          /* set parameters */
          System.out.println("Setting Parameters");
          org.apache.axis.client.Stub stub = (Stub) AvayaPort;
          stub.setTimeout(45000);// in milliseconds
          stub.setUsername("coffee");
          stub.setPassword("P1zza2g0");

          System.out.println("Launching VXML Request");
          LaunchVXMLRequest callRequest = new LaunchVXMLRequest();
          callRequest.setFromURI("");
          callRequest.setToURI("tel:+6342");
          callRequest.setApplicationName("asnotification");
          callRequest.setParameters("enable_call_classification=true;asmessage=Severity One Notification. "+message+". I repeat again. "+message);
          callRequest.setConnectTimeoutSecs(120);
          callRequest.setApplicationURL("");
          callRequest.setUuiInfo("");

          System.out.println("Getting Response");
          LaunchVXMLResponse callResponse = AvayaPort.launchVXML(callRequest);

          System.out.println("ID = "+callResponse.getSessionID_returned());
          System.out.println("Resources = "+callResponse.getTotalRes_returned());
          System.out.println("Free SIP = "+callResponse.getUnusedSIP_returned());
          System.out.println("Free H323 = "+callResponse.getUnusedH323_returned());
          System.out.println("Done callig");
          } catch (Exception ex) {
          System.out.println(ex.toString());
          }

    }

}
