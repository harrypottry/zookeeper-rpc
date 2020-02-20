package com.test.rmi.rpc;

import com.test.rmi.rpc.zk.IServiceDiscovery;
import com.test.rmi.rpc.zk.ServiceDiscoveryImpl;
import com.test.rmi.rpc.zk.ZkConfig;

/**
 * @author harrypotter
 */
public class ClientDemo {

    public static void main(String[] args) throws InterruptedException {
        IServiceDiscovery serviceDiscovery=new
                ServiceDiscoveryImpl(ZkConfig.CONNNECTION_STR);

        RpcClientProxy rpcClientProxy=new RpcClientProxy(serviceDiscovery);

        for(int i=0;i<10;i++) {
            IGpHello hello = rpcClientProxy.clientProxy(IGpHello.class, null);
            System.out.println(hello.sayHello("mic"));
            Thread.sleep(1000);
        }
    }
}
