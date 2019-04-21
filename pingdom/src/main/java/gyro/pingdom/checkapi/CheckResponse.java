package gyro.pingdom.checkapi;

public class CheckResponse {

    public CheckResponseObject check;

    public CheckResponseObject getCheck() {
        return check;
    }

    public void setCheck(CheckResponseObject check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "CheckResponse{" +
                "check=" + check +
                '}';
    }
}
