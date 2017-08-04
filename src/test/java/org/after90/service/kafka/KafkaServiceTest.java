package test.org.after90.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.after90.Application;
import org.after90.repository.KafkaRepository;
import org.after90.service.kafka.KafkaService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class KafkaServiceTest {
    @Autowired
    private KafkaRepository kafkaR;
    @Autowired
    private KafkaService kafka;

    @Test
    public void testReadKafka() throws Exception {
        kafkaR.initConsumer();
        kafka.readKafka();
    }

    @Test
    public void testWriteKafka() throws Exception {
        kafkaR.initProducer();
        kafka.writeKafka();
    }
} 
