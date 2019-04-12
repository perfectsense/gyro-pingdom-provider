package gyro.pingdom.users;

import gyro.core.BeamException;
import gyro.core.diff.ResourceName;
import gyro.lang.Resource;

import gyro.pingdom.PingdomResource;
import gyro.pingdom.api.ContactTargetId;
import gyro.pingdom.api.SmsTarget;
import gyro.pingdom.api.SmsTargetService;

import java.io.IOException;
import java.util.Set;

@ResourceName(parent = "user", value = "sms-target")
public class SmsTargetResource extends PingdomResource {

    public String country_code;
    public Integer id;
    public String number;
    public String provider;
    public String severity;

    public SmsTargetResource(SmsTarget smsTarget) {
        setCountryCode(smsTarget.getCountryCode());
        setId(smsTarget.getId());
        setNumber(smsTarget.getNumber());
        setProvider(smsTarget.getProvider());
        setSeverity(smsTarget.getSeverity());
    }

    public String getCountryCode() {
        return country_code;
    }

    public void setCountryCode(String country_code) {
        this.country_code = country_code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

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
        SmsTargetService service = (SmsTargetService) createClient(SmsTargetService.class);

        try {
            ContactTargetId contactTarget = service.addSmsTargetToUser(getUserId(),
                    getCountryCode(), getNumber(),
                    getProvider(), getSeverity()).execute().body();

            //setId(contactTarget.getContactTarget().getId());
        } catch (IOException ex) {
            throw new BeamException(ex.getMessage());
        }
    }

    @Override
    public void update(Resource current, Set<String> changedProperties) {
        SmsTargetService service = (SmsTargetService) createClient(SmsTargetService.class);

        try {
            service.modifySmsTarget(getUserId(),
                    getId(), getCountryCode(), getNumber(),
                    getProvider(), getSeverity()).execute().body();
        } catch (IOException ex) {
            throw new BeamException(ex.getMessage());
        }
    }

    @Override
    public void delete() {
        SmsTargetService service = (SmsTargetService) createClient(SmsTargetService.class);

        service.deleteSmsTarget(getUserId(), getId());
    }

    @Override
    public String toDisplayString() {return "sms target " + getNumber();}


}
