package gyro.pingdom.api.model.check;

public class Type {

    private HttpCheck http;
    private HttpCustomCheck httpcustom;
    private TcpCheck tcp;

    public HttpCheck getHttp() {
        return http;
    }

    public void setHttp(HttpCheck httpCheck) {
        this.http = httpCheck;
    }

    public HttpCustomCheck getHttpCustom() {
        return httpcustom;
    }

    public void setHttpCustom(HttpCustomCheck httpCustomCheck) {
        this.httpcustom = httpCustomCheck;
    }

    public TcpCheck getTcp() {
        return tcp;
    }

    public void setTcp(TcpCheck tcp) {
        this.tcp = tcp;
    }

}
