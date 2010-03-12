/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sabre.as.sevonecaller;
import com.sabre.mailclient.MailClient;
import java.util.Queue;

/**
 *
 * @author SG0203705
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
      MailClient client = new MailClient();
      Queue sevOneQueue = client.receive();

      
      while(!sevOneQueue.isEmpty()) {
        // use WS to create a PhoneCall with these msgs
        TestCall unaLlamada = new TestCall();
        unaLlamada.callMe((String) sevOneQueue.remove());
        // deberiamos logear los msgs a un archivo por si falla la llamada.
      }      
    }

}
