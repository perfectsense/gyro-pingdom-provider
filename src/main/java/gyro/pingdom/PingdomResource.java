package gyro.pingdom;

import gyro.core.resource.Resource;
import gyro.pingdom.api.PingdomService;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public abstract class PingdomResource extends Resource {

    @Override
    public Class resourceCredentialsClass() {
        return PingdomCredentials.class;
    }

    protected PingdomService createClient() {
        Map<String, String> credentials = resourceCredentials().findCredentials();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            String auth = Credentials.basic(credentials.get("email"), credentials.get("password"));

            Request request = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", auth)
                .addHeader("App-Key", credentials.get("app-key"))
                .addHeader("Account-Email", credentials.get("email"))
                .build();

            return chain.proceed(request);
        });

        // Temporary workaround to allow OkHTTP to shutdown when Gyro exits. The full fix
        // is to evict the OkHTTP connection pool on exit. This requires implementing a shutdown
        // hook in Gyro core.
        httpClient.protocols(Collections.singletonList(Protocol.HTTP_1_1));

        Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://api.pingdom.com/api/2.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retro.create(PingdomService.class);
    }
}
