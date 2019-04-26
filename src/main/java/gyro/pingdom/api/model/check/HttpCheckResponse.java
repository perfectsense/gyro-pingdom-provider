package gyro.pingdom.api.model.check;

import java.util.ArrayList;
import java.util.List;

public class HttpCheckResponse extends Check {

    private String url;
    private Boolean encryption;
    private Integer port;
    private String auth;
    private String shouldContain;
    private String shouldNotContain;
    private String postData;
    private RequestHeader requestHeaders;
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
        return shouldContain;
    }

    public void setShouldContain(String shouldContain) {
        this.shouldContain = shouldContain;
    }

    public String getShouldNotContain() {
        return shouldNotContain;
    }

    public void setShouldNotContain(String shouldNotContain) {
        this.shouldNotContain = shouldNotContain;
    }

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData;
    }

    public RequestHeader getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(RequestHeader requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<String> headers() {
        List<String> header;
        if (getRequestHeaders().getRequests() != null) {
            header = new ArrayList<>(getRequestHeaders().getRequests());
        }
        return null;
    }

}
