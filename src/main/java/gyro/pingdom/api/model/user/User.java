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

package gyro.pingdom.api.model.user;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Integer id;
    private String name;
    private String paused;
    private String primary;
    private List<SmsTarget> sms;
    private List<EmailTarget> email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaused() {
        return paused;
    }

    public void setPaused(String paused) {
        this.paused = paused;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public List<SmsTarget> getSms() {
        if (sms == null) {
            sms = new ArrayList<>();
        }

        return sms;
    }

    public void setSms(List<SmsTarget> sms) {
        this.sms = sms;
    }

    public List<EmailTarget> getEmail() {
        if (email == null) {
            email = new ArrayList<>();
        }

        return email;
    }

    public void setEmail(List<EmailTarget> email) {
        this.email = email;
    }

}
