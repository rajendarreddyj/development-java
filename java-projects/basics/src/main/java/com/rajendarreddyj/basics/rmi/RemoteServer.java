package com.rajendarreddyj.basics.rmi;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.rajendarreddyj.basics.swing.rmiclient.Send;

@SuppressWarnings("deprecation")
public class RemoteServer extends UnicastRemoteObject implements Send {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String text;

    public RemoteServer() throws RemoteException {
        super();
    }

    @Override
    public void sendData(final String gotText) {
        this.text = gotText;
    }

    @Override
    public String getData() {
        return this.text;
    }

    public static void main(final String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        String name = "//localhost/Send";
        try {
            Send remoteServer = new RemoteServer();
            Naming.rebind(name, remoteServer);
            System.out.println("RemoteServer bound");
        } catch (java.rmi.RemoteException e) {
            System.out.println("Cannot create remote server object");
        } catch (java.net.MalformedURLException e) {
            System.out.println("Cannot look up server object");
        }
    }
}
