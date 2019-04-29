package gyro.pingdom.user;

import gyro.core.GyroException;
import gyro.core.resource.Resource;
import gyro.core.resource.ResourceName;

import gyro.core.resource.ResourceDiffProperty;
import gyro.core.resource.ResourceOutput;

import gyro.pingdom.PingdomResource;
import gyro.pingdom.api.model.user.ContactTargetId;
import gyro.pingdom.api.model.user.SmsTarget;
import gyro.pingdom.api.model.user.UserService;

import java.io.IOException;
import java.util.Set;

@ResourceName(parent = "user", value = "sms-target")
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
    @ResourceOutput
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * The country code. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * The phone number of the target. (Required)
     */
    @ResourceDiffProperty(updatable = true)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * The provider for the target. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * The severity of the target. (Optional)
     */
    @ResourceDiffProperty(updatable = true)
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
    public boolean refresh() { return false; }

    @Override
    public void create() {
        UserService service = createClient(UserService.class);

        try {
            ContactTargetId contactTarget = service.addSmsTargetToUser(getUserId(),
                    getCountryCode(), getNumber(),
                    getProvider(), getSeverity()).execute().body();

            setId(contactTarget.getContactTarget().getId());

            service.modifySmsTarget(getUserId(),
                    getId(), getCountryCode(), getNumber(),
                    getProvider(), getSeverity()).execute().body();

        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public void update(Resource current, Set<String> changedProperties) {
        UserService service = createClient(UserService.class);

        SmsTargetResource oldResource = (SmsTargetResource) current;

        try {
            service.modifySmsTarget(getUserId(),
                    oldResource.getId(), getCountryCode(), getNumber(),
                    getProvider(), getSeverity()).execute().body();
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public void delete() {
        UserService service = createClient(UserService.class);

        try {
            service.deleteSmsTarget(getUserId(), getId()).execute().body();
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public String primaryKey() {
        return String.format("%s", getNumber());
    }

    @Override
    public String toDisplayString() {
        return "sms target " + getNumber();
    }

    @Override
    public String toString() {
        return "SmsTargetResource{" +
            "countryCode='" + countryCode + '\'' +
            ", id=" + id +
            ", number='" + number + '\'' +
            ", provider='" + provider + '\'' +
            ", severity='" + severity + '\'' +
            '}';
    }
}
