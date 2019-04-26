package gyro.pingdom.api.model.user;

import java.util.ArrayList;
import java.util.List;

public class ContactTarget {

    public List<EmailTarget> email;
    public Integer id;
    public List<SmsTarget> sms;


    public List<EmailTarget> getEmail() {
        if (email == null){
            email = new ArrayList<>();
        }

        return email;
    }

    public void setEmail(List<EmailTarget> email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ContactTarget{" +
                "email=" + email +
                ", id=" + id +
                ", sms=" + sms +
                '}';
    }
}
