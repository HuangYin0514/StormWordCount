package com.hy.storm;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.MessageId;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created with IDEA by User1071324110@qq.com
 *
 * @author 10713
 * @date 2018/6/25 11:59
 */
public class MyCountBolt extends BaseRichBolt {
    OutputCollector collector;
    Map<String, Integer> map = new HashMap<String, Integer>();


    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        String word = input.getString(0);
        Integer num = input.getInteger(1);
//        System.out.println(Thread.currentThread().getId() + "   word:" + word + "   map:" + map);
        if (map.containsKey(word)) {
            Integer count = map.get(word);
            map.put(word, count + num);
            collector.emit(input,new Values(word));
        } else {
            map.put(word, num);
        }
        collector.ack(input);
//        System.out.println(map);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        //不输出
        declarer.declare(new Fields("word"));

    }
}
