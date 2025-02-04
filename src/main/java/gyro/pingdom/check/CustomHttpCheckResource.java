/*
 * Copyright 2019, Perfect Sense, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gyro.pingdom.check;

import gyro.core.GyroException;
import gyro.core.GyroUI;
import gyro.core.resource.Resource;
import gyro.core.resource.Updatable;
import gyro.core.Type;
import gyro.core.scope.State;
import gyro.pingdom.api.model.check.Check;
import gyro.pingdom.api.model.check.CheckResponse;
import gyro.pingdom.api.model.check.CheckService;
import gyro.pingdom.api.model.check.HttpCustomCheck;
import gyro.pingdom.api.model.common.Message;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Set;

/**
 * Creates a Custom HTTP Check.
 *
 * Example
 * -------
 *
 * .. code-block:: gyro
 *
 *     pingdom::custom-http-check custom-check-example
 *         name: "custom-check-example"
 *
 *         # Custom HTTP check specific attributes
 *         url: "/pingdom.xml"
 *         port: 443
 *         encryption: true
 *
 *         # Common check attributes
 *         resolution: 5
 *         hostname: "s7.mydomain.com"
 *         send-notification-when-down: 7
 *         notify-again-every: 4
 *         notify-when-back-up: true
 *         tags: ["abc", "xyz"]
 *     end
 */
@Type("custom-http-check")
public class CustomHttpCheckResource extends CheckResource {

    private String url;
    private Boolean encryption;
    private Integer port;
    private String auth;
    private String additionalUrls;

    /**
     * URL containing Pingdom XML response.
     */
    @Updatable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Whether to connect to host using SSL.
     */
    @Updatable
    public Boolean getEncryption() {
        return encryption;
    }

    public void setEncryption(Boolean encryption) {
        this.encryption = encryption;
    }

    /**
     * The target port to connect to.
     */
    @Updatable
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * Username/Password used for auth (HTTP Basic Auth). In formation "username:password".
     */
    @Updatable
    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    /**
     * Up to 5 additional URLs, pointing to Pingdom XML response, to check.
     */
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
    public void create(GyroUI ui, State state) {
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
    public void update(GyroUI ui, State state, Resource current, Set<String> changedFieldNames) {
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

}
