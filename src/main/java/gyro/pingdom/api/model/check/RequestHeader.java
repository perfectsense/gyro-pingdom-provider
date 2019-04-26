package gyro.pingdom.api.model.check;

import java.util.List;

public class RequestHeader {

    public List<String> requests;

    public List<String> getRequests() {
        return requests;
    }

    public void setRequests(List<String> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "RequestHeader{" +
                "requests=" + requests +
                '}';
    }
}
