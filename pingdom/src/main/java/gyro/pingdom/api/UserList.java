package gyro.pingdom.api;

import java.util.List;

public class UserList {

    public List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "user=" + users +
                '}';
    }
}
