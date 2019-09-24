/*
 * Copyright 2019, Perfect Sense, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gyro.pingdom;

import gyro.core.auth.Credentials;
import gyro.core.GyroException;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Properties;

public class PingdomCredentials extends Credentials {

    private String credentialFilePath;

    public String getCredentialFilePath() {
        return credentialFilePath;
    }

    public void setCredentialFilePath(String credentialFilePath) {
        this.credentialFilePath = credentialFilePath;
    }

    public <T> T createClient(Class<T> serviceClass) {
        Properties properties;
        String file = getCredentialFilePath();

        try (InputStreamReader input = new InputStreamReader(openInput(file), StandardCharsets.UTF_8)) {
            properties = new Properties();

            properties.load(input);

        } catch (IOException error) {
            throw new GyroException(
                String.format("Can't load [%s] properties file!", file),
                error);
        }

        String appKey = (String) properties.get("app-key");
        String email = (String) properties.get("email");
        String password = (String) properties.get("password");
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            String auth = okhttp3.Credentials.basic(email, password);

            Request request = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", auth)
                .addHeader("App-Key", appKey)
                .addHeader("Account-Email", email)
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

        return retro.create(serviceClass);
    }

}
