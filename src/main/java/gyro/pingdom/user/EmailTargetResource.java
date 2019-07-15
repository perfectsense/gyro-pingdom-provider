package gyro.pingdom.user;

import gyro.core.GyroException;
import gyro.core.resource.Resource;
import gyro.core.resource.Updatable;
import gyro.core.resource.Output;
import gyro.core.scope.State;
import gyro.pingdom.PingdomResource;
import gyro.pingdom.api.model.common.Message;
import gyro.pingdom.api.model.user.ContactTarget;
import gyro.pingdom.api.model.user.EmailTarget;
import gyro.pingdom.api.model.user.NewContactTargetResponse;
import gyro.pingdom.api.model.user.UserService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Set;

public class EmailTargetResource extends PingdomResource {

    private Integer id;
    private String email;
    private String severity;

    public EmailTargetResource() {

    }

    EmailTargetResource(EmailTarget emailTarget) {
        setId(emailTarget.getId());
        setEmail(emailTarget.getAddress());
        setSeverity(emailTarget.getSeverity());
    }

    /**
     * The id of the target. (Required)
     */
    @Output
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * The email for the target. (Required)
     */
    @Updatable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The severity of the target. (Required)
     */
    @Updatable
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
    public void create(State state) {
        UserService service = createClient(UserService.class);

        try {
            Call<NewContactTargetResponse> call = service.addEmailTarget(getUserId(), getEmail(), getSeverity());
            Response<NewContactTargetResponse> response = call.execute();

            if (!response.isSuccessful()) {
                throw new GyroException(response.errorBody().string());
            }

            NewContactTargetResponse contactTargetResponse = response.body();
            ContactTarget contactTarget = contactTargetResponse.getContactTarget();

            setId(contactTarget.getId());
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public void update(State state, Resource current, Set<String> changedFieldNames) {
        UserService service = createClient(UserService.class);

        try {
            Call<Message> call = service.modifyEmailTarget(getUserId(), getId(), getEmail(), getSeverity());
            Response<Message> response = call.execute();

            if (!response.isSuccessful()) {
                throw new GyroException(response.errorBody().string());
            }
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public void delete(State state) {
        UserService service = createClient(UserService.class);

        try {
            service.deleteTarget(getUserId(), getId()).execute().body();
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public String primaryKey() {
        return null;
    }

    @Override
    public String toDisplayString() {
        return "email target " + getEmail();
    }

}
