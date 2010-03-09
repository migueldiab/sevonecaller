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
    public static void main(String[] args) {
        try { // Call Web Service Operation
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
          AppIntfWS_ServiceLocator locator = new AppIntfWS_ServiceLocator(config);
          URL avayaURL = new URL("https://10.135.94.80/axis/services/AppIntfWS");
          AppIntfWS_PortType AvayaPort = locator.getAppIntfWS(avayaURL);

          /* set parameters */
          org.apache.axis.client.Stub stub = (Stub) AvayaPort;
          stub.setTimeout(45000);// in milliseconds
          stub.setUsername("coffee");
          stub.setPassword("P1zza2g0");

          LaunchVXMLRequest callRequest = new LaunchVXMLRequest();
          callRequest.setFromURI("");
          callRequest.setToURI("tel:+6862");
          callRequest.setApplicationName("asnotification");
          callRequest.setParameters("enable_call_classification=true;asmessage=Alvaro, please stop having sex with Orcas, killer whales, and start working! The ebony giant will milk you all over... Thank you for listening...");
          callRequest.setConnectTimeoutSecs(120);
          callRequest.setApplicationURL("");
          callRequest.setUuiInfo("");

          LaunchVXMLResponse callResponse = AvayaPort.launchVXML(callRequest);

          System.out.println("ID = "+callResponse.getSessionID_returned());
          System.out.println("Resources = "+callResponse.getTotalRes_returned());
          System.out.println("Free SIP = "+callResponse.getUnusedSIP_returned());
          System.out.println("Free H323 = "+callResponse.getUnusedH323_returned());

          } catch (Exception ex) {
          System.out.println(ex.toString());
          }

    }

}
