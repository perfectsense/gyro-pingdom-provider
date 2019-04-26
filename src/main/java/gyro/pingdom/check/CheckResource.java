package gyro.pingdom.check;

import gyro.core.GyroException;
import gyro.core.resource.ResourceDiffProperty;
import gyro.core.resource.ResourceName;
import gyro.core.resource.ResourceOutput;
import gyro.core.resource.Resource;

import gyro.pingdom.PingdomResource;
import gyro.pingdom.checkapi.CheckId;
import gyro.pingdom.checkapi.CheckResponse;
import gyro.pingdom.checkapi.CheckResponseObject;
import gyro.pingdom.checkapi.CheckService;
import gyro.pingdom.checkapi.Types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ResourceName("check")
public abstract class CheckResource extends PingdomResource {

    public String hostname;
    public List<Integer> integrationIds;
    public Integer id;
    public Boolean ipv6;
    public String name;
    public Integer notifyAgainEvery;
    public Boolean notifyWhenBackUp;
    public Boolean paused;
    public Map<String, String> probeFilters;
    public Integer resolution;
    public Integer responseTimeThreshold;
    public Integer sendNotificationWhenDown;
    public List<String> tags;
    public List<Integer> teamIds;
    public Types type;
    public List<Integer> userIds;

    /**
     * The target host of the check. (Required)
     */
    @ResourceDiffProperty(updatable = true)
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * The list of integration identifiers. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public List<Integer> getIntegrationIds() {
        if (integrationIds == null) {
            integrationIds = new ArrayList<>();
        }

        return integrationIds;
    }

    public void setIntegrationIds(List<Integer> integrationIds) {
        this.integrationIds = integrationIds;
    }

    /**
     * The returned id of the check.
     */
    @ResourceOutput
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Decides if ipv6 is used, rather than ipv4. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public Boolean getIpv6() {
        return ipv6;
    }

    public void setIpv6(Boolean ipv6) {
        this.ipv6 = ipv6;
    }

    /**
     * The name of the check. (Required)
     */
    @ResourceDiffProperty(updatable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Notify every x cycles. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public Integer getNotifyAgainEvery() {
        return notifyAgainEvery;
    }

    public void setNotifyAgainEvery(Integer notifyAgainEvery) {
        this.notifyAgainEvery = notifyAgainEvery;
    }

    /**
     * Decides if a notification is sent when the host is back up. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public Boolean getNotifyWhenBackUp() {
        return notifyWhenBackUp;
    }

    public void setNotifyWhenBackUp(Boolean notifyWhenBackUp) {
        this.notifyWhenBackUp = notifyWhenBackUp;
    }

    /**
     * Decides if check is paused. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public Boolean getPaused() {
        return paused;
    }

    public void setPaused(Boolean paused) {
        this.paused = paused;
    }

    /**
     * Decides if check is paused. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public Map<String, String> getProbeFilters() {
        if (probeFilters == null) {
            probeFilters = new HashMap<>();
        }

        return probeFilters;
    }

    public void setProbeFilters(Map<String, String> probeFilters) {
        this.probeFilters = probeFilters;
    }

    /**
     * Determines how often the host's status is checked. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public Integer getResolution() {
        return resolution;
    }

    public void setResolution(Integer resolution) {
        this.resolution = resolution;
    }

    /**
     * Triggers an alert if the response time is over this many ms. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public Integer getResponseTimeThreshold() {
        return responseTimeThreshold;
    }

    public void setResponseTimeThreshold(Integer responseTimeThreshold) {
        this.responseTimeThreshold = responseTimeThreshold;
    }

    /**
     * Determines if a notification is sent if the host goes down. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public Integer getSendNotificationWhenDown() {
        return sendNotificationWhenDown;
    }

    public void setSendNotificationWhenDown(Integer sendNotificationWhenDown) {
        this.sendNotificationWhenDown = sendNotificationWhenDown;
    }

    /**
     * The tags for the check. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public List<String> getTags() {
        if (tags == null) {
            tags = new ArrayList<>();
        }

        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Ids of the teams that will be notified is the host is down. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public List<Integer> getTeamIds() {
        if (teamIds == null) {
            teamIds = new ArrayList<>();
        }

        return teamIds;
    }

    public void setTeamIds(List<Integer> teamIds) {
        this.teamIds = teamIds;
    }

    /**
     * The type of the check. (Required)
     */
    @ResourceDiffProperty(updatable = true)
    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    /**
     * Ids of the users that will be notified is the host is down. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public List<Integer> getUserIds() {
        if (userIds == null) {
            userIds = new ArrayList<>();
        }
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    @Override
    public boolean refresh() {
        CheckService service = (CheckService) createClient(CheckService.class);

        try {
            CheckResponse checkId = service.getCheck(getId()).execute().body();

            if (checkId == null) {
                return false;
            }

            CheckResponseObject check = checkId.getCheck();

            setHostname(check.getHostname());
            setIntegrationIds(check.getIntegrationids());
            setId(check.getId());
            setIpv6(check.getIpv6());
            setName(check.getName());
            setNotifyAgainEvery(check.getNotifyagainevery());
            setNotifyWhenBackUp(check.getNotifywhenbackup());
            setPaused(check.getPaused());
            setProbeFilters(check.getProbeFilters());
            setResolution(check.getResolution());
            setResponseTimeThreshold(check.getResponsetime_threshold());
            setSendNotificationWhenDown(check.getSendnotificationwhendown());
            setTags(check.tags());
            setTeamIds(check.getTeamids());
            setType(check.getType());
            setUserIds(check.getUserids());
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }

        return true;
    }

    @Override
    public void create() {
        CheckService service = (CheckService) createClient(CheckService.class);

        try {
            CheckId check = service.createCheck(getName(), getHostname(), "http",
                    getPaused(),
                    getResolution(),
                    getSendNotificationWhenDown(),
                    getNotifyAgainEvery(), getNotifyWhenBackUp(),
                    getTags(), getUserIds()).execute().body();

            setId(check.getCheck().getId());
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public void update(Resource current, Set<String> changedProperties) {
        CheckService service = (CheckService) createClient(CheckService.class);

        try {
            service.modifyCheck(getId(), getName(), getHostname(), getType().inUse(), getPaused(),
                    getResolution(), getUserIds(), getSendNotificationWhenDown(),
                    getNotifyAgainEvery(), getNotifyWhenBackUp(),
                    getTags(), getProbeFilters(), getIpv6(),
                    getResponseTimeThreshold(), getIntegrationIds(),
                    getTeamIds()).execute().body();
        } catch(IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public void delete() {
        CheckService service = (CheckService) createClient(CheckService.class);

        try {
            service.deleteCheck(getId()).execute().body();
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public String toDisplayString() {return "check " + getName();}
}

