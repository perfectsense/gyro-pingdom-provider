package gyro.pingdom.api.model.check;

import java.util.ArrayList;
import java.util.List;

public class HttpCheckResponse extends Check {

    private String url;
    private Boolean encryption;
    private Integer port;
    private String auth;
    private String shouldcontain;
    private String shouldnotcontain;
    private String postdata;
    private RequestHeader requestheaders;
    private String typeName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getEncryption() {
        return encryption;
    }

    public void setEncryption(Boolean encryption) {
        this.encryption = encryption;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getShouldContain() {
        return shouldcontain;
    }

    public void setShouldContain(String shouldcontain) {
        this.shouldcontain = shouldcontain;
    }

    public String getShouldNotContain() {
        return shouldnotcontain;
    }

    public void setShouldNotContain(String shouldnotcontain) {
        this.shouldnotcontain = shouldnotcontain;
    }

    public String getPostdata() {
        return postdata;
    }

    public void setPostdata(String postdata) {
        this.postdata = postdata;
    }

    public RequestHeader getRequestheaders() {
        return requestheaders;
    }

    public void setRequestheaders(RequestHeader requestheaders) {
        this.requestheaders = requestheaders;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<String> headers() {
        List<String> header;
        if (getRequestheaders().getRequests() != null) {
            header = new ArrayList<>(getRequestheaders().getRequests());
        }
        return null;
    }

    @Override
    public String toString() {
        return "HttpCheck{" +
                "url='" + url + '\'' +
                ", encryption='" + encryption + '\'' +
                ", port='" + port + '\'' +
                ", auth='" + auth + '\'' +
                ", shouldcontain='" + shouldcontain + '\'' +
                ", shouldnotcontain='" + shouldnotcontain + '\'' +
                ", postdata='" + postdata + '\'' +
                ", requestheaders='" + requestheaders + '\'' +
                '}';
    }
}
