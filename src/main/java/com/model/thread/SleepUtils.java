package com.model.thread;

import java.util.concurrent.TimeUnit;

public class SleepUtils {

    public static final void second(long sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
