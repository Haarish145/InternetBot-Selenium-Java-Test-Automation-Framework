package dataProviders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;
import utils.ConfigReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LoginDataProvider {
    private static final String LOGIN_DATA_FILE = "login-credentials.json";

    @DataProvider(name = "loginCredentials")
    public Object[][] loginCredentials() {
        return loginCredentialRows().stream()
                .map(row -> new Object[]{
                        ConfigReader.get(row.usernameKey()),
                        ConfigReader.get(row.passwordKey()),
                        row.validCredential()
                })
                .toArray(Object[][]::new);
    }

    private List<LoginCredentialRow> loginCredentialRows() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(LOGIN_DATA_FILE)) {
            if (inputStream == null) {
                throw new IllegalStateException(LOGIN_DATA_FILE + " was not found in test resources.");
            }
            return new ObjectMapper().readValue(inputStream, new TypeReference<>() {
            });
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to read login test data.", exception);
        }
    }

    private record LoginCredentialRow(String usernameKey, String passwordKey, boolean validCredential) {
    }
}
