package com.cathy.cathyblog.common.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IdUtil {
    private static final Lock ID_GEN_LOCK = new ReentrantLock();
    private static final long ID_GEN_SLEEP_MILLIS = 50L;

    private IdUtil() {
    }

    public static synchronized String genTimeMillisId() {
        ID_GEN_LOCK.lock();

        String ret;
        try {
            ret = String.valueOf(System.currentTimeMillis());

            try {
                Thread.sleep(50L);
            } catch (InterruptedException var5) {
                throw new RuntimeException("Generates time millis id fail");
            }
        } finally {
            ID_GEN_LOCK.unlock();
        }

        return ret;
    }
}
