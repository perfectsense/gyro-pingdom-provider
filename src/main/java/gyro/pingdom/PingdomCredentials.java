package gyro.pingdom;

import gyro.core.Credentials;
import gyro.core.GyroException;
import gyro.core.resource.ResourceName;
import gyro.core.scope.FileScope;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

@ResourceName("credentials")
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
        try (InputStream input = getRelativeCredentialsPath()) {
            Properties props = new Properties();
            props.load(input);

            return props;
        } catch (Exception ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    private InputStream getRelativeCredentialsPath() throws Exception {
        FileScope fileScope = scope().getFileScope();

        return fileScope.getRootScope().getBackend().openInput(Paths.get(fileScope.getFile()).getParent().resolve(getCredentialFilePath()).toString());
    }
}
