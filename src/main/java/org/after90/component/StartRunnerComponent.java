package org.after90.component;

import lombok.extern.slf4j.Slf4j;
import org.after90.repository.HadoopRepository;
import org.after90.service.hdfs.HDFSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by zhaogj on 16/02/2017.
 */
@Component
@Slf4j
@Order(value = 1)
public class StartRunnerComponent implements CommandLineRunner {

    @Value("${JUnit_Testing}")
    private int nJUnitTesting;

    @Autowired
    private HadoopRepository hadoop;
    @Autowired
    private HDFSService hdfs;

    @Override
    public void run(String... args) throws Exception {
        log.info("StartRunnerComponent is run");
        log.info("totalMemory:{}M", Runtime.getRuntime().totalMemory() / 1024 / 1024);
        if (nJUnitTesting == 0) {
            log.info("This is not test.");
            hadoop.initFS();
            hdfs.writeHDFS();
        } else {
            log.info("This is test.");
        }
    }
}
