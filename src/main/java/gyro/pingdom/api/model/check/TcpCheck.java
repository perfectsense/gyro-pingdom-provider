package gyro.pingdom.api.model.check;

import gyro.pingdom.api.model.check.Check;

public class TcpCheck extends Check {

    private Integer port;
    private String stringtosend;
    private String stringtoexpect;
}
