package com.test.rmi.rpc;

import com.test.rmi.rpc.anno.RpcAnnotation;

/**
 * @author harrypotter
 *
 */
@RpcAnnotation(IGpHello.class)
public class GpHelloImpl implements IGpHello{
    @Override
    public String sayHello(String msg) {
        return "I'm 8080 Node , "+msg;
    }
}
