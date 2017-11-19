package org.jacoco.examples.java.gradle;

/**
 *
 * @author Никита
 */
public class Metrics {
    private static long startTime;
    private static long stopTime;
    private static long usedMemoryBefore;

    public static void start() {
        startTime = System.currentTimeMillis();
        Runtime.getRuntime().gc();
        usedMemoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static void stop() {
        stopTime = System.currentTimeMillis();
    }

    public static void getExecutionTime() {
        long estimatedTime = stopTime - startTime;
        System.out.println("Execution time: " + estimatedTime +"ms");
    }

    public static void getUsedMemory() {
        Runtime.getRuntime().gc();
        long usedBytes = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() + usedMemoryBefore);
        System.out.printf("Used memory: %.2fmb\n", convertToMegabytes(usedBytes));
    }

    public static void getAllMetrics(){
        getExecutionTime();
        getUsedMemory();
    }
    
    private static double convertToMegabytes(long bytes) {
        return bytes / (1024.0 * 1024.0);
    }
}