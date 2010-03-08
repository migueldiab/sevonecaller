/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sevonecaller;
import java.util.Collection;
import java.util.Iterator;
import mailClient.*;
import java.util.LinkedList;
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
        while(!sevOneQueue.isEmpty()){
            // use WS to create a PhoneCall with these msgs
        System.out.print(sevOneQueue.remove() + "\t");
            // deberiamos logear los msgs a un archivo por si falla la llamada.
      }
      
    }

}
