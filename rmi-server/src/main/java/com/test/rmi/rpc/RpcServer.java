package com.test.rmi.rpc;

import com.test.rmi.rpc.anno.RpcAnnotation;
import com.test.rmi.rpc.zk.IRegisterCenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author harrypotter
 *
 * 用于发布一个远程服务
 */
public class RpcServer {
    //创建一个线程池
    private static final ExecutorService executorService=Executors.newCachedThreadPool();

    //注册中心
    private IRegisterCenter registerCenter;
    //服务发布地址
    private String serviceAddress;

    // 存放服务名称和服务对象之间的关系
    Map<String,Object> handlerMap=new HashMap<>();

    public RpcServer(IRegisterCenter registerCenter, String serviceAddress) {
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;
    }

    /**
     * 绑定服务名称和服务对象
     * @param services
     */
    public void bind(Object... services){
        for(Object service:services){
            RpcAnnotation annotation=service.getClass().getAnnotation(RpcAnnotation.class);
            String serviceName=annotation.value().getName();
            String version=annotation.version();
            if(version!=null&&!version.equals("")){
                serviceName=serviceName+"-"+version;
            }
            //绑定服务接口名称对应的服务
            handlerMap.put(serviceName,service);
        }
    }

    public void publisher(){
        ServerSocket serverSocket=null;
        try{
            String[] addrs=serviceAddress.split(":");
            //启动一个服务监听
            serverSocket=new ServerSocket(Integer.parseInt(addrs[1]));

            for(String interfaceName:handlerMap.keySet()){
                registerCenter.register(interfaceName,serviceAddress);
                System.out.println("注册服务成功："+interfaceName+"->"+serviceAddress);
            }

            //循环监听
            while(true){
                //监听服务
                Socket socket=serverSocket.accept();
                //通过线程池去处理请求
                executorService.execute(new ProcessorHandler(socket,handlerMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
