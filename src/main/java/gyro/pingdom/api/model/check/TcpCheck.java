package gyro.pingdom.api.model.check;

import gyro.core.diff.Diffable;

public class TcpCheck extends Diffable {

    private Integer port;
    private String stringToSend;
    private String stringToExpect;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getStringToSend() {
        return stringToSend;
    }

    public void setStringToSend(String stringToSend) {
        this.stringToSend = stringToSend;
    }

    public String getStringToExpect() {
        return stringToExpect;
    }

    public void setStringToExpect(String stringToExpect) {
        this.stringToExpect = stringToExpect;
    }

    @Override
    public String primaryKey() {
        return null;
    }

    @Override
    public String toDisplayString() {
        return "tcp check on port " + getPort();
    }
}
