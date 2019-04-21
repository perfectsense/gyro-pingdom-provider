package gyro.pingdom.checkapi;

public class CheckId {

    public Check check;

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "CheckId{" +
                "check=" + check +
                '}';
    }
}
