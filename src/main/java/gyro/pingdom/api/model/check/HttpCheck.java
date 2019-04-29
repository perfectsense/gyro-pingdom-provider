package gyro.pingdom.api.model.check;

import gyro.core.diff.Diffable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpCheck extends Diffable {

    private String url;
    private Boolean encryption;
    private Integer port;
    private String auth;
    private String shouldcontain;
    private String shouldnotcontain;
    private String postdata;
    private Map<String, String> requestheaders;
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

    public void setShouldContain(String shouldContain) {
        this.shouldcontain = shouldContain;
    }

    public String getShouldNotContain() {
        return shouldnotcontain;
    }

    public void setShouldNotContain(String shouldNotContain) {
        this.shouldnotcontain = shouldNotContain;
    }

    public String getPostData() {
        return postdata;
    }

    public void setPostData(String postData) {
        this.postdata = postData;
    }

    public Map<String, String> getRequestHeaders() {
        return requestheaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestheaders = requestHeaders;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String primaryKey() {
        return getUrl();
    }

    @Override
    public String toDisplayString() {
        return "http check " + getUrl();
    }
}
