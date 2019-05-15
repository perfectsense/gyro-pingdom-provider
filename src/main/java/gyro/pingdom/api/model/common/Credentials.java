package gyro.pingdom.api.model.common;

import java.util.Properties;

public class Credentials {
    private String appKey;
    private String email;
    private String password;

    public Credentials(Properties properties) {
        this.appKey = (String) properties.get("app-key");
        this.email = (String) properties.get("email");
        this.password = (String) properties.get("password");
    }

    public String getAppKey() {
        return appKey;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
