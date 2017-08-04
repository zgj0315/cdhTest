package org.after90.service.hdfs;

import lombok.extern.slf4j.Slf4j;
import org.after90.repository.HadoopRepository;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;


@Service
@Slf4j
public class HDFSService {
    @Autowired
    private HadoopRepository hadoop;

    public void writeHDFS() {
        log.info("begin write");
        if (hadoop.fs != null) {
            try {
                long lTime = System.currentTimeMillis();
                File file = new File("/home/zhaogj/cdhTest/tmp/abc.txt");
                //hadoop.fs.copyFromLocalFile(false, new Path("file:///home/zhaogj/cdhTest/tmp/abc.txt"), new Path("hdfs:///user/zhaogj/abc.txt"));
                //log.info("speed:{}M/s", (file.length() / 1024L / 1024L) / ((System.currentTimeMillis() - lTime) / 1000L));
                //追加
                InputStream in = new BufferedInputStream(new FileInputStream("/home/zhaogj/cdhTest/tmp/abc.txt"));
                OutputStream out = hadoop.fs.append(new Path("hdfs:///user/zhaogj/abc.txt"));
                lTime = System.currentTimeMillis();
                IOUtils.copyBytes(in, out, 4096, false);
                log.info("speed:{}M/s", (file.length() / 1024L / 1024L) / ((System.currentTimeMillis() - lTime) / 1000L));
                IOUtils.copyBytes(in, out, 4096, true);
            } catch (Exception e) {
                log.error("", e);
            }
        } else {
            log.info("fs is null");
        }
        log.info("end write");


    }
}
