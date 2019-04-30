package gyro.pingdom.api.model.user;

import java.util.ArrayList;
import java.util.List;

public class ContactTarget {

    private Integer id;
    private List<EmailTarget> email;
    private List<SmsTarget> sms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<EmailTarget> getEmail() {
        if (email == null){
            email = new ArrayList<>();
        }

        return email;
    }

    public void setEmail(List<EmailTarget> email) {
        this.email = email;
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

}
