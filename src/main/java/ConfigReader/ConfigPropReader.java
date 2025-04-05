package ConfigReader;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigPropReader {
    private Properties prop;
    private FileInputStream ip;

    public Properties initLangProp(String language) {

        System.out.println("lang is : " + language);
        prop = new Properties();

        try {
            switch (language.toLowerCase()) {
                case "english":
                    ip = new FileInputStream("./src/main/resources/lang.eng.properties");
                    break;
                case "turkish":
                    ip = new FileInputStream("./src/main/resources/lang.turkish.properties");
                    break;
                default:
                    System.out.println("lang not found..." + language);
                    break;
            }
            prop.load(ip);
        } catch (Exception e) {

        }
        return prop;
    }
}
