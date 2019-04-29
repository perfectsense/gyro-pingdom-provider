package gyro.pingdom.api.model.check;

import gyro.core.diff.Diffable;

public class HttpCustomCheck extends Diffable {

    private String url;
    private Boolean encryption;
    private Integer port;
    private String auth;
    private String additionalUrls;

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

    public String getAdditionalUrls() {
        return additionalUrls;
    }

    public void setAdditionalUrls(String additionalUrls) {
        this.additionalUrls = additionalUrls;
    }

    @Override
    public String primaryKey() {
        return getUrl();
    }

    @Override
    public String toDisplayString() {
        return "custom http check " + getUrl();
    }
}
