package gyro.pingdom.api.model.user;

public class UserId {

    public User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserId{" +
                "user=" + user +
                '}';
    }
}
