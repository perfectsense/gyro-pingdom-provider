package gyro.pingdom;

import gyro.core.Credentials;
import gyro.core.GyroException;
import gyro.core.resource.ResourceType;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@ResourceType("credentials")
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
        String file = getCredentialFilePath();

        try (InputStreamReader input = new InputStreamReader(openInput(file), StandardCharsets.UTF_8)) {
            Properties props = new Properties();

            props.load(input);

            return props;

        } catch (IOException error) {
            throw new GyroException(
                String.format("Can't load [%s] properties file!", file),
                error);
        }
    }

}
