package gyro.pingdom.check;

import gyro.core.GyroException;
import gyro.core.resource.Resource;
import gyro.core.resource.ResourceUpdatable;
import gyro.core.resource.ResourceType;
import gyro.pingdom.api.model.check.Check;
import gyro.pingdom.api.model.check.CheckResponse;
import gyro.pingdom.api.model.check.CheckService;
import gyro.pingdom.api.model.check.TcpCheck;
import gyro.pingdom.api.model.common.Message;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Set;

@ResourceType("tcp-check")
public class TcpCheckResource extends CheckResource {

    private Integer port;
    private String stringToSend;
    private String stringToExpect;

    /* The target port to connect to. */
    @ResourceUpdatable
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    /* A string to send once connected to the host. */
    @ResourceUpdatable
    public String getStringToSend() {
        return stringToSend;
    }

    public void setStringToSend(String stringToSend) {
        this.stringToSend = stringToSend;
    }

    /* String that should be in the response to consider check successful. */
    @ResourceUpdatable
    public String getStringToExpect() {
        return stringToExpect;
    }

    public void setStringToExpect(String stringToExpect) {
        this.stringToExpect = stringToExpect;
    }

    @Override
    public void doRefresh(Check check) {
        TcpCheck tcpCheck = check.getType().getTcp();

        setPort(tcpCheck.getPort());
        setStringToExpect(tcpCheck.getStringToExpect());
        setStringToSend(tcpCheck.getStringToSend());
    }

    @Override
    public void create() {
        CheckService service = createClient(CheckService.class);

        try {
            Call<CheckResponse> call = service.createTcpCheck(
                getName(), getHostname(), "tcp", getPaused(), getResolution(), usersToString(), getSendNotificationWhenDown(),
                getNotifyAgainEvery(), getNotifyWhenBackUp(), tagsToString(), probeFiltersToString(), getIpv6(),
                getResponseTimeThreshold(), getIntegrationIds(), getTeamIds(),
                getPort(),
                getStringToSend(),
                getStringToExpect());

            Response<CheckResponse> response = call.execute();
            if (!response.isSuccessful()) {
                throw new GyroException(response.errorBody().string());
            }

            CheckResponse check = response.body();

            setId(check.getCheck().getId());
        } catch (IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public void update(Resource current, Set<String> changedProperties) {
        CheckService service = createClient(CheckService.class);

        try {
            Call<Message> call = service.modifyTcpCheck(
                getId(), getName(), getHostname(), getPaused(), getResolution(), usersToString(), getSendNotificationWhenDown(),
                getNotifyAgainEvery(), getNotifyWhenBackUp(), tagsToString(), probeFiltersToString(), getIpv6(),
                getResponseTimeThreshold(), getIntegrationIds(), getTeamIds(),
                getPort(),
                getStringToSend(),
                getStringToExpect());

            Response<Message> response = call.execute();
            if (!response.isSuccessful()) {
                throw new GyroException(response.errorBody().string());
            }
        } catch(IOException ex) {
            throw new GyroException(ex.getMessage());
        }
    }

    @Override
    public String toDisplayString() {
        return "tcp check on port " + getPort();
    }

}
