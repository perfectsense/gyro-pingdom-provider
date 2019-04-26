package gyro.pingdom.api.model.check;

import java.util.List;

public class CheckList {

    private List<Check> checks;

    @Override
    public String toString() {
        return "CheckList{" +
                "checks=" + checks +
                '}';
    }
}
