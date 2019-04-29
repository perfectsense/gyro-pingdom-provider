package gyro.pingdom.api.model.check;

public class Type {

    private HttpCheck httpCheck;

    public HttpCheck getHttp() {
        return httpCheck;
    }

    public void setHttp(HttpCheck httpCheck) {
        this.httpCheck = httpCheck;
    }

    public CheckType checkType() {
        if (getHttp() != null) {
            return CheckType.HTTP;
        }

        return CheckType.UNKNOWN;
    }

    public static enum CheckType {
        HTTP,
        HTTP_CUSTOM,
        TCP,
        SMTP,
        POP3,
        IMAP,
        PING,
        DNS,
        UDP,
        UNKNOWN
    }
}
