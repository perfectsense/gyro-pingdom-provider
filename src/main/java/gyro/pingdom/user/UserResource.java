package gyro.pingdom.user;

import gyro.core.GyroException;
import gyro.core.resource.Resource;
import gyro.core.resource.ResourceName;

import gyro.core.resource.ResourceDiffProperty;
import gyro.core.resource.ResourceOutput;

import gyro.pingdom.PingdomResource;
import gyro.pingdom.api.PingdomService;
import gyro.pingdom.api.model.user.ContactTargetsList;
import gyro.pingdom.api.model.user.EmailTarget;
import gyro.pingdom.api.model.user.SmsTarget;
import gyro.pingdom.api.model.user.UserId;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ResourceName("user")
public class UserResource extends PingdomResource {

    private List<EmailTargetResource> emailTarget;
    private Integer id;
    private String name;
    private String paused;
    private String primaryContact;
    private List<SmsTargetResource> smsTarget;

    /**
     * Subnets for the network.
     *
     * @subresource gyro.pingdom.user.EmailTargetResource
     */
    @ResourceDiffProperty(updatable = true)
    public List<EmailTargetResource> getEmailTarget() {
        if (emailTarget == null) {
            emailTarget = new ArrayList<>();
        }

        return emailTarget;
    }

    public void setEmailTarget(List<EmailTargetResource> emailTarget) {
        this.emailTarget = emailTarget;
    }

    @ResourceOutput
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ResourceDiffProperty(updatable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ResourceDiffProperty(updatable = true)
    public String getPaused() {
        return paused;
    }

    public void setPaused(String paused) {
        this.paused = paused;
    }

    @ResourceDiffProperty(updatable = true)
    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    /**
     * Subnets for the network.
     *
     * @subresource gyro.pingdom.user.SmsTargetResource
     */
    public List<SmsTargetResource> getSmsTarget() {
        if (smsTarget == null) {
            smsTarget = new ArrayList<>();
        }

        return smsTarget;
    }

    public void setSmsTarget(List<SmsTargetResource> smsTarget) {
        this.smsTarget = smsTarget;
    }

    @Override
    public boolean refresh() {
        PingdomService service = createClient();

        try {
            ContactTargetsList body = service.getUser(getId()).execute().body();

            if (body == null) {
                return false;
            }

            getEmailTarget().clear();
            for (EmailTarget emailTarget : body.getContactTargets().getEmail()) {
                EmailTargetResource emailTargetResource = new EmailTargetResource(emailTarget);
                emailTargetResource.parent(this);
                getEmailTarget().add(emailTargetResource);
            }


            getSmsTarget().clear();
            for (SmsTarget smsTarget : body.getContactTargets().getSms()) {
                SmsTargetResource smsTargetResource = new SmsTargetResource(smsTarget);
                smsTargetResource.parent(this);
                getSmsTarget().add(smsTargetResource);
            }
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }

        return true;
    }

    @Override
    public void create() {
        PingdomService service = createClient();

        try {
            UserId body = service.createUser(getName()).execute().body();

            setId(body.getUser().getId());

            service.modifyPrimaryAndPaused(getId(), getPrimaryContact(), getPaused()).execute().body();

            service.modifyUserName(getId(), getName()).execute().body();

        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public void update(Resource current, Set<String> changedProperties) {
        PingdomService service = createClient();

        try {
            service.modifyUserNamePaused(getId(), getName(), getPaused()).execute().body();
            service.modifyPrimary(getId(), getPrimaryContact()).execute().body();
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public void delete() {
        PingdomService service = createClient();

        try {
            service.deleteUser(getId()).execute().body();
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public String toDisplayString() {return "user " + getName();}
}
