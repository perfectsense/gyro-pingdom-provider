package gyro.pingdom.api.model.user;

public class SmsTarget {

    public String country_code;
    public Integer id;
    public String number;
    public String provider;
    public String severity;

    public String getCountryCode() {
        return country_code;
    }

    public void setCountryCode(String country_code) {
        this.country_code = country_code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @Override
    public String toString() {
        return "SmsTarget{" +
                "country_code=" + country_code +
                ", id=" + id +
                ", number='" + number + '\'' +
                ", provider='" + provider + '\'' +
                ", severity='" + severity + '\'' +
                '}';
    }
}
