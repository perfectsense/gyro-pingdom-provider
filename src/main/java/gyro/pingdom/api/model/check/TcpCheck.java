package gyro.pingdom.api.model.check;

public class TcpCheck {

    private Integer port;
    private String stringtosend;
    private String stringtoexpect;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getStringToSend() {
        return stringtosend;
    }

    public void setStringToSend(String stringToSend) {
        this.stringtosend = stringToSend;
    }

    public String getStringToExpect() {
        return stringtoexpect;
    }

    public void setStringToExpect(String stringToExpect) {
        this.stringtoexpect = stringToExpect;
    }

}
