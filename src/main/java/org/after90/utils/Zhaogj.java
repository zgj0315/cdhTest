package org.after90.utils;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zhaogj on 12/07/2017.
 */
public class Zhaogj {
    public void doTest(){
        LinkedBlockingQueue<String> lbqBuffer = new LinkedBlockingQueue<String>();
        try {
            lbqBuffer.put("");
            lbqBuffer.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
