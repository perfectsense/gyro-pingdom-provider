package gyro.pingdom.api.model.user;

public class ContactTargetId {

    public ContactTarget contact_target;

    public ContactTarget getContactTarget() {return contact_target;}

    public void setContactTarget(ContactTarget contact_target) {
       this.contact_target = contact_target;
    }

    @Override
    public String toString() {
        return "ContactTargetId{" +
                "contact_target=" + contact_target +
                '}';
    }
}
