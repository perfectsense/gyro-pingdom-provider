package gyro.pingdom.api.model.user;

public class GetUserResponse {

    private ContactTarget contactTargets;

    public ContactTarget getContactTargets() {
        return contactTargets;
    }

    public void setContactTargets(ContactTarget contactTargets) {
        this.contactTargets = contactTargets;
    }

}
