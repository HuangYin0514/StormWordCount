package com.hy.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

/**
 * Created with IDEA by User1071324110@qq.com
 *
 * @author 10713
 * @date 2018/6/25 11:10
 */
public class WordCountTopologyMain {
    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
        //1、准备一个TopologyBuilder
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("mySpout", new MySpout(), 2);
        topologyBuilder.setBolt("myBolt1", new MySplitBolt(), 2).shuffleGrouping("mySpout");
        topologyBuilder.setBolt("myBolt2", new MyCountBolt(), 4).fieldsGrouping("myBolt1", new Fields("word"));
//        topologyBuilder.setBolt("myBolt2", new MyCountBolt(), 4).shuffleGrouping("myBolt1");
        //2、创建一个configuration，用来指定当前topology 需要的worker的数量
        Config config = new Config();
        config.setNumWorkers(2);
        //3、提交任务  -----两种模式 本地模式和集群模式
        StormSubmitter.submitTopology("mywordcount",config,topologyBuilder.createTopology());
//        LocalCluster localCluster = new LocalCluster();
//        localCluster.submitTopology("mywordcount", config, topologyBuilder.createTopology());
    }
}
