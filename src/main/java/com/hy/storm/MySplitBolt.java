package com.hy.storm;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.Map;

/**
 * Created with IDEA by User1071324110@qq.com
 *
 * @author 10713
 * @date 2018/6/25 11:35
 */
public class MySplitBolt extends BaseRichBolt {
    OutputCollector collector;

    /**
     * 初始化方法
     * @param stormConf
     * @param context
     * @param collector
     */
    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    /**
     * 被Storm框架 while(True) 循环调用，传入参数
     * @param input
     */
    @Override
    public void execute(Tuple input) {
        //这里是根据输出的位置进行获取的
        String line = input.getString(1);
        String[] arrWords = line.split(" ");
        for (String word : arrWords) {
            collector.emit(new Values(word, 1));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word", "num"));

    }
}
