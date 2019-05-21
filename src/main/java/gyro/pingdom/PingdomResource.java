package gyro.pingdom;

import gyro.core.resource.Resource;

public abstract class PingdomResource extends Resource {

    protected <T> T createClient(Class<T> serviceClass) {
        return credentials(PingdomCredentials.class).createClient(serviceClass);
    }

}
