package gyro.pingdom.api.model.user;

public class SmsTarget {

    private Integer id;
    private String country_code;
    private String number;
    private String provider;
    private String severity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryCode() {
        return country_code;
    }

    public void setCountryCode(String countryCode) {
        this.country_code = countryCode;
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

}
