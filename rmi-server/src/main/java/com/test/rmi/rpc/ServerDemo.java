package com.test.rmi.rpc;

import com.test.rmi.rpc.zk.IRegisterCenter;
import com.test.rmi.rpc.zk.RegisterCenterImpl;

import java.io.IOException;

/**
 * @author harrypotter
 */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        IGpHello iGpHello=new GpHelloImpl();
        IGpHello iGpHello1=new GpHelloImpl2();
        IRegisterCenter registerCenter=new RegisterCenterImpl();

        RpcServer rpcServer=new RpcServer(registerCenter,"127.0.0.1:8080");
        rpcServer.bind(iGpHello,iGpHello1);
        rpcServer.publisher();
        System.in.read();
    }
}
