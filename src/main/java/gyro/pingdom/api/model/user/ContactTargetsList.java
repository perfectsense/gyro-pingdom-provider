package gyro.pingdom.api.model.user;

public class ContactTargetsList {

    private ContactTarget contactTargets;

    public ContactTarget getContactTargets() {
        return contactTargets;
    }

    public void setContactTargets(ContactTarget contactTargets) {
        this.contactTargets = contactTargets;
    }

    @Override
    public String toString() {
        return "ContactTargetsList{" +
                "contact_targets=" + contactTargets +
                '}';
    }
}
