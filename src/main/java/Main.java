import Graphic.SignInFrame;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;


public class Main {

    static Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        DOMConfigurator.configure("src/main/resources/log4j2.xml");

        log.info("Program started");

        SignInFrame frame = new SignInFrame();
    }
}
