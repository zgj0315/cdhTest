package test.org.after90.service.hdfs;

import lombok.extern.slf4j.Slf4j;
import org.after90.Application;
import org.after90.repository.HadoopRepository;
import org.after90.service.hdfs.HDFSService;
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
public class HDFSServiceTest {
    @Autowired
    private HadoopRepository hadoop;
    @Autowired
    private HDFSService hdfs;

    @Test
    public void testWriteHDFS() throws Exception {
        hadoop.initFS();
        hdfs.writeHDFS();
    }


} 
