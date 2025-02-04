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

import com.psddev.dari.util.ObjectUtils;
import gyro.core.GyroException;
import gyro.core.GyroUI;
import gyro.core.resource.Resource;
import gyro.core.resource.Updatable;
import gyro.core.Type;
import gyro.core.scope.State;
import gyro.pingdom.api.model.check.Check;
import gyro.pingdom.api.model.check.CheckResponse;
import gyro.pingdom.api.model.check.CheckService;
import gyro.pingdom.api.model.check.HttpCheck;
import gyro.pingdom.api.model.common.Message;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Creates a HTTP Check.
 *
 * Example
 * -------
 *
 * .. code-block:: gyro
 *
 *     pingdom::http-check http-check-example
 *         name: "http-check-example"
 *         users: [$(pingdom::user sample-user)]
 *
 *         # HTTP specific attributes
 *         url: "/"
 *         port: 443
 *         encryption: true
 *         should-contain: "hello"
 *
 *         # Common check attributes
 *         resolution: 5
 *         hostname: "s7.mydomain.com"
 *         send-notification-when-down: 7
 *         notify-again-every: 4
 *         notify-when-back-up: true
 *         tags: ["abc", "xyz"]
 *
 *         request-headers: {
 *             X-Request-Type: "api",
 *             X-Api-Key: "1234"
 *         }
 *     end
 */
@Type("http-check")
public class HttpCheckResource extends CheckResource {

    private String url;
    private Boolean encryption;
    private Integer port;
    private String auth;
    private String shouldContain;
    private String shouldNotContain;
    private String postData;
    private Map<String, String> requestHeaders;

    /**
     * URL to check on host.
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
     * String that should be in the response to consider check successful.
     */
    @Updatable
    public String getShouldContain() {
        return shouldContain;
    }

    public void setShouldContain(String shouldContain) {
        if (!ObjectUtils.isBlank(shouldNotContain)) {
            this.shouldContain = shouldContain;
        }
    }

    /**
     * String that should not be in the response to consider check successful.
     */
    @Updatable
    public String getShouldNotContain() {
        return shouldNotContain;
    }

    public void setShouldNotContain(String shouldNotContain) {
        if (!ObjectUtils.isBlank(shouldNotContain)) {
            this.shouldNotContain = shouldNotContain;
        }
    }

    /**
     * POST data to send in check request.
     */
    @Updatable
    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData;
    }

    /**
     * Request headers to send along with check request.
     */
    @Updatable
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
        setRequestHeaders(http.getRequestHeaders());

        // Remove the default Pingdom User-Agent from the headers list so it's
        // not removed since it won't be defined in the Gyro file.
        if (getRequestHeaders().containsKey("User-Agent")) {
            String userAgent = getRequestHeaders().get("User-Agent");
            if (userAgent.startsWith("Pingdom")) {
                getRequestHeaders().remove("User-Agent");
            }
        }
    }

    @Override
    public void create(GyroUI ui, State state) {
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
                getPostData(),
                requestHeadersToMap());

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
                getPostData(),
                requestHeadersToMap());

            Response<Message> response = call.execute();
            if (!response.isSuccessful()) {
                throw new GyroException(response.errorBody().string());
            }
        } catch(IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    /**
     * Convert the Gyro map to a special map needed for the API call.
     *
     * For example if the http check defines this in a Gyro file:
     *
     * request-headers: {
     *     X-Api-Key: "abc123"
     * }
     *
     * This would be converted to a map containing:
     *
     * requestheader1: "X-Api-Key:abc123"
     *
     * This would then be translated by Retrofit to a query param:
     *
     * ?requestheader1=X-Api-Key:abc123
     *
     */
    private Map<String, String> requestHeadersToMap() {
        Map<String, String> apiMap = new HashMap<>();

        int i = 0;
        for (Map.Entry<String, String> entry : getRequestHeaders().entrySet()) {
            String key = String.format("requestheader%d", i);
            String value = String.format("%s:%s", entry.getKey(), entry.getValue());

            apiMap.put(key, value);

            i++;
        }

        return apiMap;
    }
}
