package com.test.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * @author harrypotter
 */
public class CuratorDemo {
    public static void main(String[] args) {
        CuratorFramework curatorFramework=CuratorFrameworkFactory.builder().build();
        InterProcessMutex interProcessMutex=new InterProcessMutex(curatorFramework,"/locks");
        try {
            interProcessMutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
