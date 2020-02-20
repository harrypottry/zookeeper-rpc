package com.test.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author harrypotter
 */
public class HelloServiceImpl extends UnicastRemoteObject implements IHelloService{

    protected HelloServiceImpl() throws RemoteException {
       // super();
    }

    @Override
    public String sayHello(String msg) throws RemoteException{
        return "Hello,"+msg;
    }
}
