package dataProviders;

import org.testng.annotations.DataProvider;
import utils.ConfigReader;

public class LoginDataProvider {
    @DataProvider(name = "loginCredentials")
    public Object[][] loginCredentials() {
        return new Object[][]{
                {ConfigReader.get("validUsername"), ConfigReader.get("validPassword"), true},
                {ConfigReader.get("invalidUsername"), ConfigReader.get("invalidPassword"), false}
        };
    }
}
