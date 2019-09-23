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

package gyro.pingdom.user;

import gyro.core.GyroException;
import gyro.core.GyroUI;
import gyro.core.resource.Resource;

import gyro.core.resource.Updatable;
import gyro.core.resource.Output;

import gyro.core.scope.State;
import gyro.pingdom.PingdomResource;
import gyro.pingdom.api.model.common.Message;
import gyro.pingdom.api.model.user.ContactTarget;
import gyro.pingdom.api.model.user.NewContactTargetResponse;
import gyro.pingdom.api.model.user.SmsTarget;
import gyro.pingdom.api.model.user.UserService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Set;

public class SmsTargetResource extends PingdomResource {

    private Integer id;
    private String countryCode;
    private String number;
    private String provider;
    private String severity;

    public SmsTargetResource() {

    }

    public SmsTargetResource(SmsTarget smsTarget) {
        setCountryCode(smsTarget.getCountryCode());
        setId(smsTarget.getId());
        setNumber(smsTarget.getNumber());
        setProvider(smsTarget.getProvider());
        setSeverity(smsTarget.getSeverity());
    }

    /**
     * The id of the target
     */
    @Output
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * The country code. (Optional)
     */
    @Updatable
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * The phone number of the target. (Required)
     */
    @Updatable
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * The provider for the target. (Optional)
     */
    @Updatable
    public String getProvider() {
        if (provider == null) {
            return "nexmo";
        }

        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * The severity of the target. (Optional)
     */
    @Updatable
    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Integer getUserId() {
        UserResource parent = (UserResource) parentResource();
        return parent.getId();
    }

    @Override
    public boolean refresh() {
        return false;
    }

    @Override
    public void create(GyroUI ui, State state) {
        UserService service = createClient(UserService.class);

        try {
            Call<NewContactTargetResponse> call = service.addSmsTarget(
                getUserId(), getCountryCode(), getNumber(), getProvider(), getSeverity());
            Response<NewContactTargetResponse> response = call.execute();

            if (!response.isSuccessful()) {
                throw new GyroException(response.errorBody().string());
            }

            NewContactTargetResponse contactTargetResponse = response.body();
            ContactTarget contactTarget = contactTargetResponse.getContactTarget();

            setId(contactTarget.getId());
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public void update(GyroUI ui, State state, Resource current, Set<String> changedFieldNames) {
        UserService service = createClient(UserService.class);

        try {
            Call<Message> call = service.modifySmsTarget(
                getUserId(), getId(), getCountryCode(), getNumber(), getProvider(), getSeverity());
            Response<Message> response = call.execute();

            if (!response.isSuccessful()) {
                throw new GyroException(response.errorBody().string());
            }
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public void delete(GyroUI ui, State state) {
        UserService service = createClient(UserService.class);

        try {
            service.deleteTarget(getUserId(), getId()).execute().body();
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public String primaryKey() {
        return null;
    }

}
