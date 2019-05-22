package gyro.pingdom.check;

import gyro.core.GyroException;
import gyro.core.resource.Resource;
import gyro.core.resource.Updatable;
import gyro.core.Type;
import gyro.pingdom.api.model.check.Check;
import gyro.pingdom.api.model.check.CheckResponse;
import gyro.pingdom.api.model.check.CheckService;
import gyro.pingdom.api.model.check.HttpCustomCheck;
import gyro.pingdom.api.model.common.Message;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Set;

@Type("custom-http-check")
public class CustomHttpCheckResource extends CheckResource {

    private String url;
    private Boolean encryption;
    private Integer port;
    private String auth;
    private String additionalUrls;

    /* URL containing Pingdom XML response. */
    @Updatable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /* Whether to connect to host using SSL. */
    @Updatable
    public Boolean getEncryption() {
        return encryption;
    }

    public void setEncryption(Boolean encryption) {
        this.encryption = encryption;
    }

    /* The target port to connect to. */
    @Updatable
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    /* Username/Password used for auth (HTTP Basic Auth). In formation "username:password". */
    @Updatable
    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    /* Up to 5 additional URLs, pointing to Pingdom XML response, to check. */
    @Updatable
    public String getAdditionalUrls() {
        return additionalUrls;
    }

    public void setAdditionalUrls(String additionalUrls) {
        this.additionalUrls = additionalUrls;
    }

    @Override
    public void doRefresh(Check check) {
        HttpCustomCheck httpCustom = check.getType().getHttpCustom();

        setUrl(httpCustom.getUrl());
        setEncryption(httpCustom.getEncryption());
        setPort(httpCustom.getPort());
        setAuth(httpCustom.getAuth());
        setAdditionalUrls(httpCustom.getAdditionalUrls());
    }

    @Override
    public void create() {
        CheckService service = createClient(CheckService.class);

        try {
            Call<CheckResponse>  call = service.createCustomHttpCheck(
                getName(), getHostname(), "httpcustom", getPaused(), getResolution(), usersToString(), getSendNotificationWhenDown(),
                getNotifyAgainEvery(), getNotifyWhenBackUp(), tagsToString(), probeFiltersToString(), getIpv6(),
                getResponseTimeThreshold(), getIntegrationIds(), getTeamIds(),
                getUrl(),
                getEncryption(),
                getPort(),
                getAuth(),
                getAdditionalUrls());

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
    public void update(Resource current, Set<String> changedFieldNames) {
        CheckService service = createClient(CheckService.class);

        try {
            Call<Message> call = service.modifyCustomHttpCheck(
                getId(), getName(), getHostname(), getPaused(), getResolution(), usersToString(), getSendNotificationWhenDown(),
                getNotifyAgainEvery(), getNotifyWhenBackUp(), tagsToString(), probeFiltersToString(), getIpv6(),
                getResponseTimeThreshold(), getIntegrationIds(), getTeamIds(),
                getUrl(),
                getEncryption(),
                getPort(),
                getAuth(),
                getAdditionalUrls());

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
        return "custom http check " + getUrl();
    }
}
