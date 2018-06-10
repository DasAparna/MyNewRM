package com.RightMoveProperties.GenericLlib;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
/**
 * @author: Aparna
 * @Desciption : This is a Logger Class that 
 * 				 logs every step including time stamp
 * 			     and generate the report inside log folder
 *
 */
public class Log {

    protected static Logger Log = Logger.getLogger("Log");
    //It allows the configuration of Log4j from an external file
    public static void configure() {
        PropertyConfigurator.configure("Log4j.properties");
    }

    public static void startTestCase(String sTestCaseName) {
        Log.info("Started " + sTestCaseName);
    }

    public static void endTestCase(String sTestCaseName) {
        Log.info("Finished  " + sTestCaseName);
    }

    public static void info(String message) {
        Log.info(message);
    }
    public static void warn(String message) {
        Log.warn(message);
    }
    public static void error(String message) {
        Log.error(message);
    }
    public static void fatal(String message) {
        Log.fatal(message);
    }
    public static void debug(String message) {
        Log.debug(message);
    }
}