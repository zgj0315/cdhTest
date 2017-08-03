package org.after90.service.hdfs;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class HDFSService {

    public void writeHDFS() {
        log.info("begin write");
        String strHadoopConfPath = "/etc/hadoop/conf/";
        Configuration conf = new Configuration();
        conf.addResource(new Path(strHadoopConfPath + "core-site.xml"));
        conf.addResource(new Path(strHadoopConfPath + "hdfs-site.xml"));
        log.info("conf is ok");
        FileSystem fs = null;
        try {
            fs = FileSystem.get(conf);
            log.info("fs is ok");

            if (fs != null) {
                fs.close();
                log.info("fs is close");
            }
        } catch (Exception e) {
            log.error("", e);
        }
        log.info("end write");
    }
}
