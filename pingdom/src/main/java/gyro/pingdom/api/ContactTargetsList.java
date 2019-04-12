package gyro.pingdom.api;

public class ContactTargetsList {

    public ContactTargets contact_targets;

    public ContactTargets getContactTargets() {
        return contact_targets;
    }

    public void setContactTargets(ContactTargets contact_targets) {
        this.contact_targets = contact_targets;
    }

    @Override
    public String toString() {
        return "ContactTargetsList{" +
                "contact_targets=" + contact_targets +
                '}';
    }
}
