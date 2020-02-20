package com.test.rmi.rpc.zk.loadbalance;

import java.util.List;

/**
 * @author harrypotter
 */
public interface LoadBanalce {

    String selectHost(List<String> repos);
}


