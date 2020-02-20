package com.test.rmi.rpc;

import com.test.rmi.rpc.anno.RpcAnnotation;

/**
 * @author harrypotter
 *
 */
@RpcAnnotation(value = IGpHello.class)
public class GpHelloImpl2 implements IGpHello{
    @Override
    public String sayHello(String msg) {
        return "I'm 8081 node ："+msg;
    }
}
