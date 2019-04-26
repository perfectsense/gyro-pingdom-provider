package gyro.pingdom;

import gyro.core.Credentials;
import gyro.core.GyroException;
import gyro.core.resource.ResourceName;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@ResourceName("credentials")
public class PingdomCredentials extends Credentials {

    private String credentialFilePath;

    public String getCredentialFilePath() {
        return credentialFilePath;
    }

    public void setCredentialFilePath(String credentialFilePath) {
        this.credentialFilePath = credentialFilePath;
    }

    @Override
    public String getCloudName() {
        return "pingdom";
    }

    @Override
    public Map<String, String> findCredentials(boolean refresh) {
        return findCredentials(refresh, true);
    }

    @Override
    public Map<String, String> findCredentials(boolean refresh, boolean extended) {
        Properties credentialsProperties = loadProperties();

        Map<String, String> credentials = new HashMap<>();
        credentials.put("app-key", (String) credentialsProperties.get("app-key"));
        credentials.put("email", (String) credentialsProperties.get("email"));
        credentials.put("password", (String) credentialsProperties.get("password"));

        return credentials;
    }

    private Properties loadProperties() {
        try {
            File file = new File(getCredentialFilePath());
            FileInputStream fileInput = new FileInputStream(file);
            Properties props = new Properties();
            props.load(fileInput);
            fileInput.close();

            return props;
        } catch (FileNotFoundException ex) {
            throw new GyroException(ex.getMessage());
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }
}
