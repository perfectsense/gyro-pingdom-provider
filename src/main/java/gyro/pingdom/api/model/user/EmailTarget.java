package gyro.pingdom.api.model.user;

public class EmailTarget {

    public String address;
    public Integer id;
    public String severity;

    public String getAddress() { return address; }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @Override
    public String toString() {
        return "EmailTarget{" +
                "address='" + address + '\'' +
                ", id=" + id +
                ", severity='" + severity + '\'' +
                '}';
    }
}
