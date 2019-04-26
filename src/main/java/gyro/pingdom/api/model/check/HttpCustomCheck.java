package gyro.pingdom.api.model.check;

import gyro.pingdom.api.model.check.Check;

public class HttpCustomCheck extends Check {

    private String url;
    private String encryption;
    private String port;
    private String auth;
    private String additionalurls;
}
