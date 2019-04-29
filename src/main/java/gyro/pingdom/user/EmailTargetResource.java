package gyro.pingdom.user;

import gyro.core.GyroException;
import gyro.core.resource.Resource;
import gyro.core.resource.ResourceName;
import gyro.core.resource.ResourceOutput;
import gyro.core.resource.ResourceDiffProperty;

import gyro.pingdom.PingdomResource;
import gyro.pingdom.api.model.user.ContactTargetId;
import gyro.pingdom.api.model.user.EmailTarget;
import gyro.pingdom.api.model.user.UserService;

import java.io.IOException;
import java.util.Set;

@ResourceName(parent = "user", value = "email-target")
public class EmailTargetResource extends PingdomResource {

    private Integer id;
    private String email;
    private String severity;

    public EmailTargetResource() {

    }

    EmailTargetResource(EmailTarget emailTarget) {
        setEmail(emailTarget.getAddress());
        setId(emailTarget.getId());
        setSeverity(emailTarget.getSeverity());
    }

    /**
     * The id of the target. (Required)
     */
    @ResourceOutput
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * The email for the target. (Required)
     */
    @ResourceDiffProperty(updatable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The severity of the target. (Required)
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
    public boolean refresh() {
        return false;
    }

    @Override
    public void create() {
        UserService service = createClient(UserService.class);

        try {
            ContactTargetId contactTarget = service.addEmailTargetToUser(getUserId(), getEmail(),
                    getSeverity()).execute().body();

            setId(contactTarget.getContactTarget().getId());

            service.modifyEmailTarget(getUserId(), getId(), getEmail(), getSeverity()).execute().body();

        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public void update(Resource current, Set<String> changedProperties) {
        UserService service = createClient(UserService.class);

        EmailTargetResource oldResource = (EmailTargetResource) current;

        try {
            service.modifyEmailTarget(getUserId(), oldResource.getId(), getEmail(), getSeverity()).execute().body();
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public void delete() {
        UserService service = createClient(UserService.class);

        try {
            service.deleteEmailTarget(getUserId(), getId()).execute().body();
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public String primaryKey() {
        return String.format("%s", getEmail());
    }

    @Override
    public String toDisplayString() {
        return "email target " + getEmail();
    }

    @Override
    public String toString() {
        return "EmailTargetResource{" +
            "email='" + email + '\'' +
            ", id=" + id +
            ", severity='" + severity + '\'' +
            '}';
    }
}
