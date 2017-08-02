package org.after90.service.hdfs;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HDFSService {

    public void writeHDFS() {
        log.info("begin write");
        Configuration conf = new Configuration();
        //FileSystem fs = FileSystem.get(conf);

    }
}
