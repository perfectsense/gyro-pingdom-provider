package gyro.pingdom;

import gyro.core.BeamException;
import gyro.lang.Resource;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public abstract class PingdomResource extends Resource {

    private Properties properties;

    @Override
    public Class resourceCredentialsClass() {
        return PingdomCredentials.class;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties() {
        PingdomCredentials credentials = (PingdomCredentials) resourceCredentials();

        try {
            File file = new File(credentials.getCredentialFilePath());
            FileInputStream fileInput = new FileInputStream(file);
            Properties props = new Properties();
            props.load(fileInput);
            fileInput.close();
            properties = props;
        } catch (FileNotFoundException ex) {
            throw new BeamException(ex.getMessage());
        } catch (IOException ex) {
            throw new BeamException(ex.getMessage());
        }
    }

    protected Object createClient(Class apiClass) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        setProperties();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                String auth = Credentials.basic(getProperties().getProperty("username"),
                        getProperties().getProperty("password"));

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", auth);
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
                Request.Builder requestBuilder = original.newBuilder()
                        .header("App-Key", getProperties().getProperty("app-key"));
                requestBuilder.addHeader("Accept", "application/json");
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });


        OkHttpClient okHttpClient = httpClient.build();

        Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://api.pingdom.com/2.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retro.create(apiClass);
    }
}
