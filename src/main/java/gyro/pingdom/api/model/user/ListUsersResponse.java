package gyro.pingdom.api.model.user;

import java.util.List;

public class ListUsersResponse {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
