package gyro.pingdom.api;

import java.util.ArrayList;
import java.util.List;

public class ContactTargets {

    public List<SmsTarget> sms;
    public List<EmailTarget> email;

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
