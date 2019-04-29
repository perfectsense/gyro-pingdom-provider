package gyro.pingdom.api.model.check;

public class Type {

    private HttpCheck httpCheck;
    private HttpCustomCheck httpCustomCheck;

    public HttpCheck getHttp() {
        return httpCheck;
    }

    public void setHttp(HttpCheck httpCheck) {
        this.httpCheck = httpCheck;
    }

    public HttpCustomCheck getHttpCustom() {
        return httpCustomCheck;
    }

    public void setHttpCustom(HttpCustomCheck httpCustomCheck) {
        this.httpCustomCheck = httpCustomCheck;
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
