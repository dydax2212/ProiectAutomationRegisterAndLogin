package Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.io.*;

public class LoggerUtility
{
    private static final String suiteLogsPath = "target/logs/suite/";
    private static final String regressionSuiteLogPath = "target/logs/";
    private static final Logger logger = LogManager.getLogger();

    public static synchronized void startTestCase(String testName)
    {
        ThreadContext.put("threadName", testName);
        logger.info("----------Execution started " + testName + "----------");
    }

    public static synchronized void endTestCase(String testName)
    {
        logger.info("----------Execution ended " + testName + "----------");
    }

    public static synchronized void infoTest(String stepName)  // stepName! stepName! I'm stuck in the washer machine!
    {
        logger.info(Thread.currentThread().getName() + ": " +getCallInfo());
    }

    public static synchronized void errorLog(String errorName)
    {
        logger.info(Thread.currentThread().getName() + ": " + getCallInfo() + errorName);
    }

    public static synchronized String getCallInfo()
    {
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        return className + " " + methodName;
    }

    public static void mergeFilesIntoOne()
    {
        File dir = new File(suiteLogsPath);
        String[] fileNames = dir.list();
        PrintWriter pW = null;
        try {
            pW = new PrintWriter(regressionSuiteLogPath + "regressionLogs.log");
            for (String fileName : fileNames) {
                File f = new File(dir, fileName);
                BufferedReader br = new BufferedReader(new FileReader(f));
                pW.println("Content of file " + fileName);
                String line = br.readLine();
                while (line != null) {
                    pW.println(line);
                    line = br.readLine();
                }
                pW.flush();
            }
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
