package gyro.pingdom.userapi;

public class ContactTargetsList {

    public ContactTarget contact_targets;

    public ContactTarget getContactTargets() {
        return contact_targets;
    }

    public void setContactTargets(ContactTarget contact_targets) {
        this.contact_targets = contact_targets;
    }

    @Override
    public String toString() {
        return "ContactTargetsList{" +
                "contact_targets=" + contact_targets +
                '}';
    }
}
