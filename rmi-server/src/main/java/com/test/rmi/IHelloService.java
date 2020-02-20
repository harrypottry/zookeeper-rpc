package com.test.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author harrypotter
 */
public interface IHelloService extends Remote {

    String sayHello(String msg) throws RemoteException;
}
