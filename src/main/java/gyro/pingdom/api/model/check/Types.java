package gyro.pingdom.api.model.check;

public class Types {

    private HttpCheck http;

    public HttpCheck getHttp() {
        return http;
    }

    public void setHttp(HttpCheck http) {
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
