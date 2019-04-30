package gyro.pingdom.check;

import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StringUtils;
import gyro.core.GyroException;
import gyro.core.resource.ResourceDiffProperty;
import gyro.core.resource.ResourceName;
import gyro.core.resource.ResourceOutput;
import gyro.pingdom.PingdomResource;
import gyro.pingdom.api.model.check.Check;
import gyro.pingdom.api.model.check.CheckResponse;
import gyro.pingdom.api.model.check.CheckService;
import gyro.pingdom.api.model.check.Tag;
import gyro.pingdom.api.model.check.Type;
import gyro.pingdom.user.UserResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ResourceName("check")
public abstract class CheckResource extends PingdomResource {

    private Integer id;
    private String name;
    private String hostname;
    private List<Integer> integrationIds;
    private Boolean ipv6;
    private Integer notifyAgainEvery;
    private Boolean notifyWhenBackUp;
    private Boolean paused;
    private Integer resolution;
    private Integer responseTimeThreshold;
    private Integer sendNotificationWhenDown;
    private Set<String> tags;
    private List<Integer> teamIds;
    private Type type;
    private List<UserResource> users;
    private String probeRegion;

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
    public Set<String> getTags() {
        if (tags == null) {
            tags = new HashSet<>();
        }

        return tags;
    }

    public void setTags(Set<String> tags) {
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
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Users that will be notified is the host is down. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public List<UserResource> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    public void setUsers(List<UserResource> users) {
        this.users = users;
    }

    /**
     * Filter probe location. Valid values are North America (``NA``), Europe (``EU``), Asia Pacific (``APAC``), orLatin America (``LATAM``).
     */
    @ResourceDiffProperty(updatable = true)
    public String getProbeRegion() {
        return probeRegion;
    }

    public void setProbeRegion(String probeRegion) {
        this.probeRegion = probeRegion;
    }

    public abstract void doRefresh(Check check);

    @Override
    public boolean refresh() {
        CheckService service = createClient(CheckService.class);

        try {
            CheckResponse checkId = service.getCheck(getId()).execute().body();

            if (checkId == null) {
                return false;
            }

            Check check = checkId.getCheck();

            setHostname(check.getHostname());
            setIntegrationIds(check.getIntegrationIds());
            setId(check.getId());
            setIpv6(check.getIpv6());
            setName(check.getName());
            setNotifyAgainEvery(check.getNotifyAgainEvery());
            setNotifyWhenBackUp(check.getNotifyWhenBackup());
            setPaused(check.getPaused());
            setResolution(check.getResolution());
            setResponseTimeThreshold(check.getResponseTimeThreshold());
            setSendNotificationWhenDown(check.getSendNotificationWhenDown());
            setTeamIds(check.getTeamIds());

            for (Integer userId : check.getUserIds()) {
                UserResource user = findById(UserResource.class, String.valueOf(userId));
                if (user != null) {
                    getUsers().add(user);
                }
            }

            for (Tag tag : check.getTags()) {
                getTags().add(tag.getName());
            }

            if (!check.getProbeFilters().isEmpty()) {
                for (String filter : check.getProbeFilters()) {
                    if (filter.startsWith("region: ")) {
                        setProbeRegion(filter.replace("region: ", ""));
                    }
                }
            }

            doRefresh(check);
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }

        return true;
    }

    @Override
    public void delete() {
        CheckService service = createClient(CheckService.class);

        try {
            service.deleteCheck(getId()).execute().body();
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    protected String probeFiltersToString() {
        if (!ObjectUtils.isBlank(getProbeRegion())) {
            return String.format("region:%s", getProbeRegion());
        }

        return null;
    }

    protected String tagsToString() {
        return StringUtils.join(new ArrayList<>(getTags()), ",");
    }

    protected String usersToString() {
        List<String> userIds = new ArrayList<>();

        for (UserResource user : getUsers()) {
            userIds.add(String.valueOf(user.getId()));
        }

        return StringUtils.join(userIds, ",");
    }
}

