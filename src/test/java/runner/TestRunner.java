package runner;

import listeners.TestListeners;
import org.testng.TestNG;
import org.testng.log4testng.Logger;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;



    public class TestRunner {


        private static void configurateSuite() {
            TestNG tng = new TestNG();
            XmlSuite suite = new XmlSuite();
            suite.setName("Test suite");
            List<String> files = new ArrayList<>();
            files.addAll(new ArrayList<String>() {
                             {
                                 add("src/test/java/resources/TestngRegression.xml");
                             }


                         }
            );

            suite.setSuiteFiles(files);
            suite.setParallel(XmlSuite.ParallelMode.METHODS);
            suite.setThreadCount(4);
            List<XmlSuite> suites = new ArrayList<XmlSuite>();
            suites.add(suite);
            tng.setXmlSuites(suites);
            tng.run();
        }

        public static void main(String[] args) {
            configurateSuite();
            Logger logger = Logger.getLogger(TestListeners.class);


        }
    }


