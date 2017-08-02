package org.after90.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhaogj on 07/07/2017.
 */
@Slf4j
public class FileUtils {
    //遍历目录及子目录，得到所有文件
    public static List<File> getFileList(File filePath) {
        List<File> listOutput = new LinkedList<File>();

        if (filePath.exists()) {
            if (!filePath.getName().startsWith(".")) {
                if (filePath.isFile()) {
                    if (!filePath.getName().startsWith(".")) {
                        listOutput.add(filePath);
                        log.info("find file:{}", filePath.getName());
                    }
                } else if (filePath.isDirectory()) {
                    File[] afile = filePath.listFiles();
                    for (File file : afile) {
                        listOutput.addAll(getFileList(file));
                    }
                }
            }
        } else {
            log.info("文件不存在:{}", filePath.getPath());
        }
        return listOutput;
    }
}
