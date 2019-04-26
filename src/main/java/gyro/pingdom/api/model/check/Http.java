package gyro.pingdom.api.model.check;

public class Http extends Types {

    private HttpCheckResponse http;

    @Override
    public HttpCheckResponse getHttp() {
        return http;
    }

    @Override
    public void setHttp(HttpCheckResponse http) {
        this.http = http;
    }
}
