package gyro.pingdom.check;

import com.psddev.dari.util.ObjectUtils;
import gyro.core.GyroException;
import gyro.core.resource.Resource;
import gyro.core.resource.ResourceDiffProperty;
import gyro.core.resource.ResourceName;
import gyro.pingdom.api.model.check.Check;
import gyro.pingdom.api.model.check.CheckResponse;
import gyro.pingdom.api.model.check.CheckService;
import gyro.pingdom.api.model.check.HttpCheck;
import gyro.pingdom.api.model.common.Message;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@ResourceName("http-check")
public class HttpCheckResource extends CheckResource {

    private String url;
    private Boolean encryption;
    private Integer port;
    private String auth;
    private String shouldContain;
    private String shouldNotContain;
    private String postData;
    private Map<String, String> requestHeaders;

    @ResourceDiffProperty(updatable = true)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ResourceDiffProperty(updatable = true)
    public Boolean getEncryption() {
        return encryption;
    }

    public void setEncryption(Boolean encryption) {
        this.encryption = encryption;
    }

    @ResourceDiffProperty(updatable = true)
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @ResourceDiffProperty(updatable = true)
    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    @ResourceDiffProperty(updatable = true)
    public String getShouldContain() {
        return shouldContain;
    }

    public void setShouldContain(String shouldContain) {
        if (!ObjectUtils.isBlank(shouldNotContain)) {
            this.shouldContain = shouldContain;
        }
    }

    @ResourceDiffProperty(updatable = true)
    public String getShouldNotContain() {
        return shouldNotContain;
    }

    public void setShouldNotContain(String shouldNotContain) {
        if (!ObjectUtils.isBlank(shouldNotContain)) {
            this.shouldNotContain = shouldNotContain;
        }
    }

    @ResourceDiffProperty(updatable = true)
    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    @Override
    public void doRefresh(Check check) {
        HttpCheck http = check.getType().getHttp();

        setUrl(http.getUrl());
        setEncryption(http.getEncryption());
        setPort(http.getPort());
        setAuth(http.getAuth());
        setShouldContain(http.getShouldContain());
        setShouldNotContain(http.getShouldNotContain());
        setPostData(http.getPostData());
    }

    @Override
    public void create() {
        CheckService service = createClient(CheckService.class);

        try {
            Call<CheckResponse> call = service.createHttpCheck(
                getName(), getHostname(), "http", getPaused(), getResolution(), usersToString(), getSendNotificationWhenDown(),
                getNotifyAgainEvery(), getNotifyWhenBackUp(), tagsToString(), probeFiltersToString(), getIpv6(),
                getResponseTimeThreshold(), getIntegrationIds(), getTeamIds(),
                getUrl(),
                getEncryption(),
                getPort(),
                getAuth(),
                getShouldContain(),
                getShouldNotContain(),
                getPostData());

            Response<CheckResponse> response = call.execute();
            if (!response.isSuccessful()) {
                throw new GyroException(response.errorBody().string());
            }

            CheckResponse check = response.body();

            setId(check.getCheck().getId());
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public void update(Resource current, Set<String> changedProperties) {
        CheckService service = createClient(CheckService.class);

        try {
            Call<Message> call = service.modifyHttpCheck(
                getId(), getName(), getHostname(), getPaused(), getResolution(), usersToString(), getSendNotificationWhenDown(),
                getNotifyAgainEvery(), getNotifyWhenBackUp(), tagsToString(), probeFiltersToString(), getIpv6(),
                getResponseTimeThreshold(), getIntegrationIds(), getTeamIds(),
                getUrl(),
                getEncryption(),
                getPort(),
                getAuth(),
                getShouldContain(),
                getShouldNotContain(),
                getPostData());

            Response<Message> response = call.execute();
            if (!response.isSuccessful()) {
                throw new GyroException(response.errorBody().string());
            }
        } catch(IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public String toDisplayString() {
        return "http check " + getUrl();
    }
}
