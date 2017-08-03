package org.after90.repository;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class HadoopRepository {
    public FileSystem fs = null;
    public String strMark = null;

    @Value("${strHadoopConfPath}")
    private String strHadoopConfPath;

    public void initFS() {
        strMark = "has value";
        log.info("strHadoopConfPath:{}", strHadoopConfPath);
        Configuration conf = new Configuration();
        conf.addResource(new Path(strHadoopConfPath + "core-site.xml"));
        conf.addResource(new Path(strHadoopConfPath + "hdfs-site.xml"));
        try {
            fs = FileSystem.get(conf);
            log.info("FileSystem init success.");
            //log.info("abc is file:{}", fs.isFile(new Path("hdfs:///user/zhaogj/abc.txt")));
        } catch (Exception e) {
            log.error("FileSystem init error", e);
        }
    }
}
