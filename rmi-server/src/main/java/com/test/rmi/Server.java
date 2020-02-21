package com.test.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author harrypotter
 */
public class Server {

    public static void main(String[] args) {
        try {
            //已经发布了一个远程对象
            IHelloService helloService=new HelloServiceImpl();

            LocateRegistry.createRegistry(1099);

            //注册中心 key - value
            Naming.rebind("rmi://127.0.0.1/Hello",helloService);
            System.out.println("服务启动成功");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
