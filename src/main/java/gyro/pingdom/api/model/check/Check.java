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

package gyro.pingdom.api.model.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Check {

    private Integer id;
    private String created;
    private String hostname;
    private List<Integer> integrationids;
    private Boolean ipv6;
    private String name;
    private Integer notifyagainevery;
    private Boolean notifywhenbackup;
    private Boolean paused;
    private List<String> probe_filters;
    private Integer resolution;
    private Integer responsetime_threshold;
    private Integer sendnotificationwhendown;
    private List<Tag> tags;
    private List<Integer> teamIds;
    private List<Integer> userids;
    private Type type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public List<Integer> getIntegrationIds() {
        return integrationids;
    }

    public void setIntegrationIds(List<Integer> integrationIds) {
        this.integrationids = integrationIds;
    }

    public Boolean getIpv6() {
        return ipv6;
    }

    public void setIpv6(Boolean ipv6) {
        this.ipv6 = ipv6;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNotifyAgainEvery() {
        return notifyagainevery;
    }

    public void setNotifyAgainEvery(Integer notifyAgainEvery) {
        this.notifyagainevery = notifyAgainEvery;
    }

    public Boolean getNotifyWhenBackup() {
        return notifywhenbackup;
    }

    public void setNotifyWhenBackup(Boolean notifyWhenBackup) {
        this.notifywhenbackup = notifyWhenBackup;
    }

    public Boolean getPaused() {
        return paused;
    }

    public void setPaused(Boolean paused) {
        this.paused = paused;
    }

    public List<String> getProbeFilters() {
        if (probe_filters == null) {
            probe_filters = new ArrayList<>();
        }

        return probe_filters;
    }

    public void setProbeFilters(List<String> probeFilters) {
        this.probe_filters = probeFilters;
    }

    public Integer getResolution() {
        return resolution;
    }

    public void setResolution(Integer resolution) {
        this.resolution = resolution;
    }

    public Integer getResponseTimeThreshold() {
        return responsetime_threshold;
    }

    public void setResponseTimeThreshold(Integer responseTimeThreshold) {
        this.responsetime_threshold = responseTimeThreshold;
    }

    public Integer getSendNotificationWhenDown() {
        return sendnotificationwhendown;
    }

    public void setSendNotificationWhenDown(Integer sendNotificationWhenDown) {
        this.sendnotificationwhendown = sendNotificationWhenDown;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Integer> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<Integer> teamIds) {
        this.teamIds = teamIds;
    }

    public List<Integer> getUserIds() {
        if (userids == null) {
            userids = new ArrayList<>();
        }

        return userids;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userids = userIds;
    }

}
