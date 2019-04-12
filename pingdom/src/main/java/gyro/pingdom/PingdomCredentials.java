package gyro.pingdom;

import com.google.common.collect.ImmutableMap;
import gyro.core.diff.ResourceName;
import gyro.lang.Credentials;

import java.util.Map;

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
        ImmutableMap.Builder<String, String> mapBuilder = new ImmutableMap.Builder<>();
        return mapBuilder.build();
    }
}
