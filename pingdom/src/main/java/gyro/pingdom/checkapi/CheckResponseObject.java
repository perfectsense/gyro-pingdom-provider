package gyro.pingdom.checkapi;

import gyro.core.diff.Diffable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckResponseObject extends Diffable {

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
    public List<Integer> teamids;
    public List<Integer> userids;
    public Types type;

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

    public List<Integer> getIntegrationids() {
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Integer> getTeamids() {
        return teamids;
    }

    public void setTeamids(List<Integer> teamids) {
        this.teamids = teamids;
    }

    public List<Integer> getUserids() {
        return userids;
    }

    public void setUserids(List<Integer> userids) {
        this.userids = userids;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public List<String> tags() {
        List<String> tag = new ArrayList<>();
        getTags().forEach(r -> tag.add(r.getName()));
        return tag;
    }

    @Override
    public String primaryKey() {
        return getName();
    }

    @Override
    public String toDisplayString() {
        return "check " + getName();
    }

    @Override
    public String toString() {
        return "CheckObject{" +
                "type=" + type +
                ", created='" + created + '\'' +
                ", hostname='" + hostname + '\'' +
                ", integrationids=" + integrationids +
                ", id=" + id +
                ", ipv6=" + ipv6 +
                ", name='" + name + '\'' +
                ", notifyagainevery=" + notifyagainevery +
                ", notifywhenbackup=" + notifywhenbackup +
                ", paused=" + paused +
                ", probeFilters=" + probeFilters +
                ", resolution=" + resolution +
                ", responsetime_threshold=" + responsetime_threshold +
                ", sendnotificationwhendown=" + sendnotificationwhendown +
                ", tags=" + tags +
                ", teamids=" + teamids +
                ", userids=" + userids +
                '}';
    }
}
