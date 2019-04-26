package gyro.pingdom.api.model.user;

public class EmailTarget {

    private Integer id;
    private String address;
    private String severity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
