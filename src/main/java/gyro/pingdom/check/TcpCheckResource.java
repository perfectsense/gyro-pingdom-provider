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
import gyro.pingdom.api.model.check.TcpCheck;
import gyro.pingdom.api.model.common.Message;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Set;

/**
 * Creates a TCP Check.
 *
 * Example
 * -------
 *
 * .. code-block:: gyro
 *
 *     pingdom::tcp-check tcp-check-example
 *         name: "tcp-check-example"
 *
 *         # TCP check specific attributes
 *         port: 80
 *         string-to-send: "GET / HTTP/1.0"
 *         string-to-expect: "200 OK"
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
@Type("tcp-check")
public class TcpCheckResource extends CheckResource {

    private Integer port;
    private String stringToSend;
    private String stringToExpect;

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
     * A string to send once connected to the host.
     */
    @Updatable
    public String getStringToSend() {
        return stringToSend;
    }

    public void setStringToSend(String stringToSend) {
        this.stringToSend = stringToSend;
    }

    /**
     * String that should be in the response to consider check successful.
     */
    @Updatable
    public String getStringToExpect() {
        return stringToExpect;
    }

    public void setStringToExpect(String stringToExpect) {
        this.stringToExpect = stringToExpect;
    }

    @Override
    public void doRefresh(Check check) {
        TcpCheck tcpCheck = check.getType().getTcp();

        setPort(tcpCheck.getPort());
        setStringToExpect(tcpCheck.getStringToExpect());
        setStringToSend(tcpCheck.getStringToSend());
    }

    @Override
    public void create(GyroUI ui, State state) {
        CheckService service = createClient(CheckService.class);

        try {
            Call<CheckResponse> call = service.createTcpCheck(
                getName(), getHostname(), "tcp", getPaused(), getResolution(), usersToString(), getSendNotificationWhenDown(),
                getNotifyAgainEvery(), getNotifyWhenBackUp(), tagsToString(), probeFiltersToString(), getIpv6(),
                getResponseTimeThreshold(), getIntegrationIds(), getTeamIds(),
                getPort(),
                getStringToSend(),
                getStringToExpect());

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
            Call<Message> call = service.modifyTcpCheck(
                getId(), getName(), getHostname(), getPaused(), getResolution(), usersToString(), getSendNotificationWhenDown(),
                getNotifyAgainEvery(), getNotifyWhenBackUp(), tagsToString(), probeFiltersToString(), getIpv6(),
                getResponseTimeThreshold(), getIntegrationIds(), getTeamIds(),
                getPort(),
                getStringToSend(),
                getStringToExpect());

            Response<Message> response = call.execute();
            if (!response.isSuccessful()) {
                throw new GyroException(response.errorBody().string());
            }
        } catch(IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

}
