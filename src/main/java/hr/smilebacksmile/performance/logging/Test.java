package hr.smilebacksmile.performance.logging;


import hr.smilebacksmile.performance.logging.annotations.LoggablePerformance;

public class Test {


    public static void main(String [] args) {

        printBlaBla();
    }

    @LoggablePerformance(logger = "MethodPerformanceLogger")
    public static void printBlaBla() {
        System.out.println("blabla");
    }
}
