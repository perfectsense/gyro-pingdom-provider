package gyro.pingdom.check;

import gyro.core.GyroException;
import gyro.core.resource.ResourceDiffProperty;
import gyro.core.resource.ResourceName;
import gyro.core.resource.Resource;

import gyro.pingdom.checkapi.CheckResponse;
import gyro.pingdom.checkapi.CheckResponseObject;
import gyro.pingdom.checkapi.CheckService;
import gyro.pingdom.checkapi.HttpCheckResponse;
import gyro.pingdom.checkapi.HttpCheckService;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@ResourceName("http-check")
public class HttpCheckResource extends CheckResource {

    private String url;
    private Boolean encryption;
    private Integer port;
    private String auth;
    private String shouldContain;
    private String shouldNotContain;
    private String postdata;
    private List<String> requestHeader;

    /**
     * Path to the target on the server. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) { this.url = url; }

    /**
     * Determines if there is connection encryption. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public Boolean getEncryption() {
        return encryption;
    }

    public void setEncryption(Boolean encryption) {
        this.encryption = encryption;
    }

    /**
     * The target port. (Required)
     */
    @ResourceDiffProperty(updatable = true)
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * The username and password for HTTP authentication. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    /**
     * The target site should contain this string. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public String getShouldContain() {
        return shouldContain;
    }

    public void setShouldContain(String shouldContain) {
        this.shouldContain = shouldContain;
    }

    /**
     * The target site should not contain. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public String getShouldNotContain() {
        return shouldNotContain;
    }

    public void setShouldNotContain(String shouldNotContain) {
        this.shouldNotContain = shouldNotContain;
    }

    /**
     * Data that should be posted on the webpage. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public String getPostdata() {
        return postdata;
    }

    public void setPostdata(String postdata) {
        this.postdata = postdata;
    }

    /**
     * The request headers for the check. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public List<String> getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(List<String> requestHeader) {
        this.requestHeader = requestHeader;
    }

    @Override
    public boolean refresh() {
        CheckService service = (CheckService) createClient(CheckService.class);

        super.refresh();

        try {
            CheckResponse checkId = service.getCheck(getId()).execute().body();
            CheckResponseObject check = checkId.getCheck();

            HttpCheckResponse http = check.getType().getHttp();

            setUrl(http.getUrl());
            setEncryption(http.getEncryption());
            setPort(http.getPort());
            setAuth(http.getAuth());
            setShouldContain(http.getShouldContain());
            setShouldNotContain(http.getShouldNotContain());
            setPostdata(http.getPostdata());
            setRequestHeader(http.headers());
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }

        return true;
    }

    @Override
    public void create() {
        super.create();
        modifyCheck();
    }

    @Override
    public void update(Resource current, Set<String> changedProperties) {
        super.update(current, changedProperties);
        modifyCheck();
    }

    @Override
    public void delete() {
        super.delete();
    }

    private void modifyCheck() {
        HttpCheckService service = (HttpCheckService) createClient(HttpCheckService.class);

        try {
            service.modifyHttpCheck(super.getId(), getUrl(), getEncryption(), getPort(), getAuth(),
                getShouldContain(), getShouldNotContain(), getPostdata()).execute().body();

            if (getRequestHeader() != null) {
                for (String header : getRequestHeader()) {
                    service.modifyRequestHeader(super.getId(), header).execute().body();
                }
            }
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public String toDisplayString() {return "http check " + getName();}
}
