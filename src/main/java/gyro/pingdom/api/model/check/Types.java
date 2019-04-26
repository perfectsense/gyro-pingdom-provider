package gyro.pingdom.api.model.check;

public class Types {

    private HttpCheckResponse http;

    public HttpCheckResponse getHttp() {
        return http;
    }

    public void setHttp(HttpCheckResponse http) {
        this.http = http;
    }

    public String inUse() {
        if (getHttp() != null) {
            return "http";
        }
        return "none";
    }

    @Override
    public String toString() {
        return "Types{" +
                "http=" + http +
                '}';
    }
}
