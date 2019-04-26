package gyro.pingdom.api.model.user;

import java.util.ArrayList;
import java.util.List;

public class User {

    public Integer id;
    public String name;
    public String paused;
    public String primary;
    public List<SmsTarget> sms;
    public List<EmailTarget> email;

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

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public List<SmsTarget> getSms() {
        if (sms == null) {
            sms = new ArrayList<>();
        }

        return sms;
    }

    public void setSms(List<SmsTarget> sms) {
        this.sms = sms;
    }

    public List<EmailTarget> getEmail() {
        if (email == null) {
            email = new ArrayList<>();
        }

        return email;
    }

    public void setEmail(List<EmailTarget> email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", paused='" + paused + '\'' +
                ", primary='" + primary + '\'' +
                ", sms=" + sms +
                ", email=" + email +
                '}';
    }
}
