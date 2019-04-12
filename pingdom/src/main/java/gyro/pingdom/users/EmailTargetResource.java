package gyro.pingdom.users;

import gyro.core.BeamException;
import gyro.core.diff.ResourceName;
import gyro.lang.Resource;

import gyro.pingdom.PingdomResource;
import gyro.pingdom.api.ContactTargetId;
import gyro.pingdom.api.EmailTarget;
import gyro.pingdom.api.EmailTargetService;

import java.io.IOException;
import java.util.Set;

@ResourceName(parent = "user", value = "email-target")
public class EmailTargetResource extends PingdomResource {

    private String address;
    private Integer id;
    private String severity;

    public EmailTargetResource(EmailTarget emailTarget) {
        setAddress(emailTarget.getAddress());
        setId(emailTarget.getId());
        setSeverity(emailTarget.getSeverity());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        EmailTargetService service = (EmailTargetService) createClient(EmailTargetService.class);

        try {
            ContactTargetId contactTarget = service.addEmailTargetToUser(getUserId(), getAddress(),
                    getSeverity()).execute().body();

            //setId(contactTarget.getContactTarget().getId());
        } catch (IOException ex) {
            throw new BeamException(ex.getMessage());
        }
    }

    @Override
    public void update(Resource current, Set<String> changedProperties) {
        EmailTargetService service = (EmailTargetService) createClient(EmailTargetService.class);

        service.modifyEmailTarget(getUserId(), getId(), getAddress(), getSeverity());
    }

    @Override
    public void delete() {
        EmailTargetService service = (EmailTargetService) createClient(EmailTargetService.class);

        service.deleteEmailTarget(getUserId(), getId());
    }

    @Override
    public String toDisplayString() {return "email target " + getAddress();}
}
