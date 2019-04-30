package gyro.pingdom.api.model.check;

public class Type {

    private HttpCheck httpCheck;
    private HttpCustomCheck httpCustomCheck;
    private TcpCheck tcp;

    public HttpCheck getHttp() {
        return httpCheck;
    }

    public void setHttp(HttpCheck httpCheck) {
        this.httpCheck = httpCheck;
    }

    public HttpCustomCheck getHttpCustom() {
        return httpCustomCheck;
    }

    public void setHttpCustom(HttpCustomCheck httpCustomCheck) {
        this.httpCustomCheck = httpCustomCheck;
    }

    public TcpCheck getTcp() {
        return tcp;
    }

    public void setTcp(TcpCheck tcp) {
        this.tcp = tcp;
    }

}
