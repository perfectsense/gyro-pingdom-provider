package gyro.pingdom.user;

import gyro.core.GyroException;
import gyro.core.GyroUI;
import gyro.core.resource.Resource;
import gyro.core.resource.Updatable;
import gyro.core.resource.Id;
import gyro.core.Type;
import gyro.core.resource.Output;
import gyro.core.scope.State;
import gyro.pingdom.PingdomResource;
import gyro.pingdom.api.model.common.Message;
import gyro.pingdom.api.model.user.CreateUserResponse;
import gyro.pingdom.api.model.user.EmailTarget;
import gyro.pingdom.api.model.user.ListUsersResponse;
import gyro.pingdom.api.model.user.SmsTarget;
import gyro.pingdom.api.model.user.User;
import gyro.pingdom.api.model.user.UserService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Type("user")
public class UserResource extends PingdomResource {

    private Integer id;
    private String name;
    private String paused;
    private List<EmailTargetResource> emailTarget;
    private List<SmsTargetResource> smsTarget;

    @Id
    @Output
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Updatable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Updatable
    public String getPaused() {
        return paused;
    }

    public void setPaused(String paused) {
        this.paused = paused;
    }

    /**
     * Subnets for the network.
     *
     * @subresource gyro.pingdom.user.EmailTargetResource
     */
    @Updatable
    public List<EmailTargetResource> getEmailTarget() {
        if (emailTarget == null) {
            emailTarget = new ArrayList<>();
        }

        return emailTarget;
    }

    public void setEmailTarget(List<EmailTargetResource> emailTarget) {
        this.emailTarget = emailTarget;
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
        UserService service = createClient(UserService.class);

        try {
            Call<ListUsersResponse> users = service.listUsers();
            Response<ListUsersResponse> response = users.execute();

            if (!response.isSuccessful()) {
                throw new GyroException(response.errorBody().string());
            }

            for (User user : response.body().getUsers()) {
                if (user.getId().equals(getId())) {
                    setName(user.getName());
                    setPaused(user.getPaused());

                    getEmailTarget().clear();
                    for (EmailTarget emailTarget : user.getEmail()) {
                        EmailTargetResource emailTargetResource = new EmailTargetResource(emailTarget);
                        getEmailTarget().add(emailTargetResource);
                    }

                    getSmsTarget().clear();
                    for (SmsTarget smsTarget : user.getSms()) {
                        SmsTargetResource smsTargetResource = new SmsTargetResource(smsTarget);
                        getSmsTarget().add(smsTargetResource);
                    }

                    return true;
                }
            }
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }

        return false;
    }

    @Override
    public void create(GyroUI ui, State state) {
        UserService service = createClient(UserService.class);

        try {
            Call<CreateUserResponse> call = service.createUser(getName());
            Response<CreateUserResponse> response = call.execute();

            if (!response.isSuccessful()) {
                throw new GyroException(response.errorBody().string());
            }

            CreateUserResponse createUserResponse = response.body();

            setId(createUserResponse.getUser().getId());

            modifyUser();
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public void update(GyroUI ui, State state, Resource current, Set<String> changedFieldNames) {
        modifyUser();
    }

    @Override
    public void delete(GyroUI ui, State state) {
        UserService service = createClient(UserService.class);

        try {
            Call<Message> call = service.deleteUser(getId());
            Response<Message> response = call.execute();

            if (!response.isSuccessful()) {
                throw new GyroException(response.errorBody().string());
            }
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    private void modifyUser() {
        UserService service = createClient(UserService.class);

        try {
            Call<Message> call = service.modifyUser(getId(), getName(), getPaused(), "true");
            Response<Message> response = call.execute();

            if (!response.isSuccessful()) {
                throw new GyroException(response.errorBody().string());
            }
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

}
