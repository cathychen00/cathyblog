package com.cathy.cathyblog;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTests {
    @Test
    public void test1(){
         Logger logger = LoggerFactory.getLogger(LogTests.class);
         logger.info("info 111");
         logger.debug("debug 111");
         logger.warn("warn test");
         logger.error("error test");
    }
}
