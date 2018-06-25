package com.hy.storm;

import backtype.storm.topology.TopologyBuilder;

/**
 * Created with IDEA by User1071324110@qq.com
 *
 * @author 10713
 * @date 2018/6/25 11:10
 */
public class WordCountTopologyMain {
    public static void main(String[] args) {
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("mySpout", new MySpout(), 2);

    }
}
