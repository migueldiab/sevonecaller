/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sabre.mailclient;
import java.lang.*;
import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.LinkedList;
import java.util.Queue;
import javax.mail.Flags.Flag;


/**
 *
 * @author SG0203705
 */
public class MailClient {

  public MailClient() {
    Properties mailProp = new Properties();
    try {
      mailProp.load(new FileInputStream("C:\\sevOneCaller.properties"));
      this.setMailserver(mailProp.getProperty("mailserver"));
      this.setUser(mailProp.getProperty("user"));
      this.setPassword(mailProp.getProperty("password"));
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }

  private String mailserver = "";
  private String user = "";
  private String password = "";

  public void setMailserver(String mailserver) {
      this.mailserver = mailserver;
  }

  public void setPassword(String password) {
      this.password = password;
  }

  public void setUser(String user) {
      this.user = user;
  }

  public String getMailserver() {
      return mailserver;
  }

  public String getPassword() {
      return password;
  }

  public String getUser() {
      return user;
  }

  public Queue receive() {
    Store store=null;
    Folder folder=null;
    Queue msgQueue = new LinkedList();
    try
    {
      System.out.println("Getting Config");
      Properties props = System.getProperties();
      Session session = Session.getDefaultInstance(props, null);
      store = session.getStore("pop3");
      store.connect(this.mailserver, this.user, this.password);
      folder = store.getDefaultFolder();
      System.out.println("Looking into Inbox");
      if (folder == null) throw new Exception("No default folder");
      // -- ...and its INBOX --
      folder = folder.getFolder("INBOX");
      if (folder == null) throw new Exception("No POP3 INBOX");
      // Open msgs folder on read/write to be able to delete the msgs  after retreive them
      folder.open(Folder.READ_WRITE);
      // -- Get the message wrappers and process them --
      Message[] emailMsgs = folder.getMessages();
      System.out.println("Getting Messages");
      for (int msgNum = 0; msgNum < emailMsgs.length; msgNum++)
      {
        System.out.println("Message "+msgNum);
        msgQueue.add(emailMsgs[msgNum].getSubject());
        emailMsgs[msgNum].setFlag(Flags.Flag.DELETED, true);
      }
      System.out.println("Done Mail");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if (folder!=null) folder.close(true);
        if (store!=null) store.close();
      }
      catch (Exception ex2) {ex2.printStackTrace();}
    }

    // retrun Msg queue.
    return msgQueue;
  }
}
