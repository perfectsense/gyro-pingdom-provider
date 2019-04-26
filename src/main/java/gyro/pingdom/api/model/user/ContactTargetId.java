package gyro.pingdom.api.model.user;

public class ContactTargetId {

    private ContactTarget contactTarget;

    public ContactTarget getContactTarget() {
        return contactTarget;
    }

    public void setContactTarget(ContactTarget contactTarget) {
       this.contactTarget = contactTarget;
    }

    @Override
    public String toString() {
        return "ContactTargetId{" +
                "contact_target=" + contactTarget +
                '}';
    }
}
