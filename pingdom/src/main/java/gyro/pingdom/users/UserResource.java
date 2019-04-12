package gyro.pingdom.users;

import gyro.core.BeamException;
import gyro.core.diff.ResourceName;
import gyro.lang.Resource;

import gyro.pingdom.PingdomResource;
import gyro.pingdom.api.ContactTargetsList;
import gyro.pingdom.api.EmailTarget;
import gyro.pingdom.api.SmsTarget;
import gyro.pingdom.api.UserId;
import gyro.pingdom.api.UserService;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ResourceName("user")
public class UserResource extends PingdomResource {

    private List<EmailTargetResource> email;
    private Integer id;
    private String name;
    private String paused;
    private String primaryContact;
    private List<SmsTargetResource> sms;

    public List<EmailTargetResource> getEmail() {
        if (email == null) {
            email = new ArrayList<>();
        }

        return email;
    }

    public void setEmail(List<EmailTargetResource> email) {
        this.email = email;
    }

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

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public List<SmsTargetResource> getSms() {
        if (sms == null) {
            sms = new ArrayList<>();
        }

        return sms;
    }

    public void setSms(List<SmsTargetResource> sms) {
        this.sms = sms;
    }

    @Override
    public boolean refresh() {
        UserService service = (UserService) createClient(UserService.class);
        try {
            ContactTargetsList body = service.getUser(getId()).execute().body();

            if (body == null) {
                return false;
            }

            getEmail().clear();
            for (EmailTarget emailTarget : body.getContactTargets().getEmail()) {
                getEmail().add(new EmailTargetResource(emailTarget));
            }
            getSms().clear();
            for (SmsTarget smsTarget : body.getContactTargets().getSms()) {
                getSms().add(new SmsTargetResource(smsTarget));
            }

        } catch (IOException ex) {
            throw new BeamException(ex.getMessage());
        }

        return true;
    }

    @Override
    public void create() {
        UserService service = (UserService) createClient(UserService.class);
        try {
            UserId body = service.createUser(getName()).execute().body();
            setId(body.getUser().getId());
            setPaused(body.getUser().getPaused());
            setPrimaryContact(body.getUser().getPrimary());
        } catch (IOException ex) {
            throw new BeamException(ex.getMessage());
        }
    }

    @Override
    public void update(Resource current, Set<String> changedProperties) {
        UserService service = (UserService) createClient(UserService.class);
        try {
            service.modifyUser(getId(), getName(), getPrimaryContact(), getPaused()).execute().body();
        } catch (IOException ex) {
            throw new BeamException(ex.getMessage());
        }
    }

    @Override
    public void delete() {
        UserService service = (UserService) createClient(UserService.class);
        try {
            service.deleteUser(getId()).execute().body();
        } catch (IOException ex) {
            throw new BeamException(ex.getMessage());
        }
    }

    @Override
    public String toDisplayString() {return "user " + getName();}
}
