package com.rajendarreddyj.basics.swing.rmiclient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Send extends Remote {
    public void sendData(String text) throws RemoteException;

    public String getData() throws RemoteException;
}