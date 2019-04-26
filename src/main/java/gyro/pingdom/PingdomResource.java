package gyro.pingdom;

import gyro.core.resource.Resource;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Map;

public abstract class PingdomResource extends Resource {

    @Override
    public Class resourceCredentialsClass() {
        return PingdomCredentials.class;
    }

    protected Object createClient(Class apiClass) {
        Map<String, String> credentials = resourceCredentials().findCredentials();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                String auth = Credentials.basic(credentials.get("username"), credentials.get("password"));

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder().header("Authorization", auth);
                requestBuilder.addHeader("Accept", "application/json");
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder().header("App-Key", credentials.get("app-key"));
                requestBuilder.addHeader("Accept", "application/json");
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://api.pingdom.com/api/2.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retro.create(apiClass);
    }
}
