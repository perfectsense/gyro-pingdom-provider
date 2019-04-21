package gyro.pingdom.checkapi;

import gyro.core.diff.Diffable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Check {

    public String created;
    public String hostname;
    public List<Integer> integrationids;
    public Integer id;
    public Boolean ipv6;
    public String name;
    public Integer notifyagainevery;
    public Boolean notifywhenbackup;
    public Boolean paused;
    public Map<String, String> probeFilters;
    public Integer resolution;
    public Integer responsetime_threshold;
    public Integer sendnotificationwhendown;
    public List<Tag> tags;
    public String type;
    public List<Integer> teamids;
    public List<Integer> userids;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }



    public List<Integer> getIntegrationids() {
        if (integrationids == null) {
            integrationids = new ArrayList<>();
        }

        return integrationids;
    }

    public void setIntegrationids(List<Integer> integrationids) {
        this.integrationids = integrationids;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getNotifyagainevery() {
        return notifyagainevery;
    }

    public void setNotifyagainevery(Integer notifyagainevery) {
        this.notifyagainevery = notifyagainevery;
    }

    public Boolean getNotifywhenbackup() {
        return notifywhenbackup;
    }

    public void setNotifywhenbackup(Boolean notifywhenbackup) {
        this.notifywhenbackup = notifywhenbackup;
    }

    public Boolean getPaused() {
        return paused;
    }

    public void setPaused(Boolean paused) {
        this.paused = paused;
    }

    public Map<String, String> getProbeFilters() {
        if (probeFilters == null) {
            probeFilters = new HashMap<>();
        }

        return probeFilters;
    }

    public void setProbeFilters(Map<String, String> probeFilters) {
        this.probeFilters = probeFilters;
    }

    public Integer getResolution() {
        return resolution;
    }

    public void setResolution(Integer resolution) {
        this.resolution = resolution;
    }

    public Integer getResponsetime_threshold() {
        return responsetime_threshold;
    }

    public void setResponsetime_threshold(Integer responsetime_threshold) {
        this.responsetime_threshold = responsetime_threshold;
    }

    public Integer getSendnotificationwhendown() {
        return sendnotificationwhendown;
    }

    public void setSendnotificationwhendown(Integer sendnotificationwhendown) {
        this.sendnotificationwhendown = sendnotificationwhendown;
    }

    /*
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }*/

    public List<Integer> getTeamids() {
        if (teamids == null) {
            teamids = new ArrayList<>();
        }

        return teamids;
    }

    public void setTeamids(List<Integer> teamids) {
        this.teamids = teamids;
    }

    /*
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }*/

    public List<Integer> getUserids() {
        if (userids == null) {
            userids = new ArrayList<>();
        }
        return userids;
    }

    public void setUserids(List<Integer> userids) {
        this.userids = userids;
    }

    @Override
    public String toString() {
        return "Check{" +
                "created='" + created + '\'' +
                ", hostname='" + hostname + '\'' +
                ", integrationids=" + integrationids +
                ", id=" + id +
                ", ipv6=" + ipv6 +
                ", name='" + name + '\'' +
                ", notifyagainevery=" + notifyagainevery +
                ", notifywhenbackup=" + notifywhenbackup +
                ", paused=" + paused +
                ", resolution=" + resolution +
                ", responsetime_threshold=" + responsetime_threshold +
                ", sendnotificationwhendown=" + sendnotificationwhendown +
                ", tags=" + tags +
                ", teamids=" + teamids +
                //", type=" + type +
                ", userids=" + userids +
                '}';
    }
}
