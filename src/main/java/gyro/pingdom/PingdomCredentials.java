package gyro.pingdom;

import gyro.core.Credentials;
import gyro.core.GyroException;
import gyro.core.resource.ResourceType;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@ResourceType("credentials")
public class PingdomCredentials extends Credentials<gyro.pingdom.api.model.common.Credentials> {

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
    public gyro.pingdom.api.model.common.Credentials findCredentials(boolean refresh) {
        return findCredentials(refresh, true);
    }

    @Override
    public gyro.pingdom.api.model.common.Credentials findCredentials(boolean refresh, boolean extended) {
        return new gyro.pingdom.api.model.common.Credentials(loadProperties());
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
