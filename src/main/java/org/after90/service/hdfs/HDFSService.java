package org.after90.service.hdfs;

import lombok.extern.slf4j.Slf4j;
import org.after90.repository.HadoopRepository;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class HDFSService {
    @Autowired
    private HadoopRepository hadoop;

    public void writeHDFS() {
        log.info("begin write");
        if (hadoop.fs != null) {
            try {
                log.info("abc is file:{}", hadoop.fs.isFile(new Path("hdfs:///user/zhaogj/abc.txt")));
                hadoop.fs.copyFromLocalFile(false, new Path("file:///home/zhaogj/cdhTest/tmp/abc.txt"), new Path("hdfs:///user/zhaogj/abc.txt"));
            } catch (Exception e) {
                log.error("", e);
            }
        } else {
            log.info("fs is null");
        }
        log.info("end write");
    }
}
