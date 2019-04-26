package gyro.pingdom.api.model.check;

public class TcpCheck extends Check {

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
}
