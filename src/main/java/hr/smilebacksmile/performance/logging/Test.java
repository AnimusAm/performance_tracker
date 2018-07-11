package hr.smilebacksmile.performance.logging;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

    private final static Logger LOGGER = LoggerFactory.getLogger(Test.class);

    public static void main(String [] args) {
        LOGGER.trace("test");
    }
}
