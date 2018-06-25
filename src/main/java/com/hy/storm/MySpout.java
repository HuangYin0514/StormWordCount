package com.hy.storm;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.Map;

/**
 * Created with IDEA by User1071324110@qq.com
 *
 * @author 10713
 * @date 2018/6/25 11:19
 */
public class MySpout extends BaseRichSpout {
    SpoutOutputCollector collector;

    /**
     * 初始化方法
     *
     * @param conf
     * @param context
     * @param collector
     */
    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }

    /**
     * storm 框架在  while(true) 调用nextTuple方法
     */
    @Override
    public void nextTuple() {
        collector.emit(new Values("i am lilei love hanmeimei","aa is bb cc ed fg"));

    }

    /**
     * 标记
     * @param declarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word","a"));
    }
}
