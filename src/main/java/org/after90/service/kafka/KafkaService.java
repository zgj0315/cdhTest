package org.after90.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.after90.repository.KafkaRepository;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class KafkaService {
    @Autowired
    private KafkaRepository kafka;

    public void readKafka() {
        log.info("begin read kafka");
        kafka.consumer.subscribe(Arrays.asList("topic_test"));
        int nCount = 0;
        while (true) {
            ConsumerRecords<String, String> records = kafka.consumer.poll(1000);
            if (records.count() == 0) {
                nCount++;
                if (nCount > 10) {
                    break;
                }
            } else {
                nCount = 0;
            }
            for (ConsumerRecord<String, String> record : records) {
                //if (record.offset() % 100 == 0) {
                log.info("offset = {}, key = {}, value = {}", record.offset(), record.key(), record.value());
                //}
            }
            kafka.consumer.commitSync();
            //log.info("finish commitSync, records.count:{}",records.count());
        }
    }

    public void writeKafka() {
        for (int i = 0; i < 1000; i++) {
            String strKey = "key " + i + " ";
            String strValue = "value " + i + " ";
            long lNow = System.currentTimeMillis();
            kafka.producer.send(new ProducerRecord<String, String>("topic_test", strKey + lNow, strValue + lNow));
            log.info("data to kafka, key:{}", strKey);
        }
    }

    public void writeKafkaAuto() {
        while (true) {
            long lNow = System.currentTimeMillis();
            String strKey = "this is key " + lNow;
            String strValue = "this is value " + lNow;
            try {
                kafka.producer.send(new ProducerRecord<String, String>("topic_test", strKey, strValue));
                log.info("input message:{}", lNow);
                Thread.sleep(1000 * 3);
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }
}
