package com.tigertext.automation.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.lang3.StringUtils;

public class TestConfig {
    private static final String PRODUCTION = "prod";
    private static final String INTEGRATION = "dev";
    private static final String STAGE = "stage";
    private static Config config;
    private static Config invalidConfig;
    private static final TestConfig testConfig = new TestConfig();

    private TestConfig()  {
        String envType = System.getProperty("environment.type");
        if(StringUtils.isEmpty(envType)) {
            envType = DriverConfig.config().getString("environment.type");
        }
        config = ConfigFactory.load("config/" + envType.toLowerCase()+ "TestConfig");
        config.checkValid(ConfigFactory.defaultReference());
        invalidConfig = ConfigFactory.load("config/invalidTestData");
        invalidConfig.checkValid(ConfigFactory.defaultReference());
    }

    public static class Environment{

        public static String getName() {

            String envType = System.getProperty("environment.type");
            if(StringUtils.isEmpty(envType)) {
                return DriverConfig.config().getString("environment.type");
            } else {
                return envType;
            }
        }

        public static String getEmail() {
            return config.getString("environment."+getName()+ ".account.email");
        }

        public static String getPassword() {
            return config.getString("environment."+getName()+ ".account.password");
        }

        public static String getUserEmail(String User) {
            return config.getString("environment."+getName()+ "." + User + ".email");
        }

        public static String getInvalidEmailState(String User) {
            return config.getString("environment."+getName()+ "." + User + ".invalidEmail");
        }

        public static String getUserPassword(String User) {
            return config.getString("environment."+getName()+ "." + User + ".password");
        }

        public static String getUserFirstName(String User) {
            return config.getString("environment."+getName()+ "." + User + ".firstName");
        }

        public static String getApiKey() {
            return config.getString("environment."+getName()+ ".apikey");
        }

        public static String getOrgToken(){
            return config.getString("environment."+getName()+".orgToken");
        }

        public static String getSecret() {
            return config.getString("environment."+getName()+ ".secret");
        }

        public static String getUserApiKey(String user) {
            String apiKey="";
            if(user.toLowerCase().contains("android"))
                apiKey = System.getProperty("key");
            //config.getString("environment." + getName() + "." + user + ".apikey");
//            else
//                apiKey = config.getString("environment." + getName() + ".apikey");
            return apiKey;
        }

        public static String getUserSecret(String user) {
            String secretKey="";
            if(user.toLowerCase().contains("android"))
                secretKey = System.getProperty("secret");
//                        config.getString("environment." + getName() + "." + user + ".secret");
//            else
//                secretKey = config.getString("environment." + getName() + ".secret");
            return secretKey;
        }

        public static String getUserToken(String user) {
            return config.getString("environment."+getName()+"."+user+".token");
        }

        public static String getDevApi() {
            return config.getString("environment."+getName()+ ".devapi");
        }

        public static String getUserLastName(String User) {
            return config.getString("environment."+getName()+ "." + User + ".lastName");
        }

        public static String getUserDisplayName(String User) {
            return config.getString("environment."+getName()+ "." + User + ".displayName");
        }

        public static String getUserSenderId(String User) {
            return config.getString("environment."+getName()+ "." + User + ".senderId");
        }

        public static String getURL() {
            return config.getString("environment."+getName()+ ".url");
        }

        public static String getInvalidEmail() { return invalidConfig.getString("environment.data.account.email"); }

        public static String getInvalidPassword() {return invalidConfig.getString("environment.data.account.password"); }

        public static String getInvalidUserPassword(String user) { return invalidConfig.getString("environment.data." + user + ".password"); }

        public static String getInvalidUserEmail(String user) { return invalidConfig.getString("environment.data." + user + ".email"); }
    }
}