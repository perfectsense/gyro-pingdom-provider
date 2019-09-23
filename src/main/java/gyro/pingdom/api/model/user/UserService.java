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

package gyro.pingdom.api.model.user;

import gyro.pingdom.api.model.common.Message;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    // -- Email Targets API

    @POST("users/{userid}")
    @FormUrlEncoded
    Call<NewContactTargetResponse> addEmailTarget(
        @Path("userid") Integer userId,
        @Field("email") String email,
        @Field("severitylevel") String severity);

    @PUT("users/{userid}/{targetid}")
    @FormUrlEncoded
    Call<Message> modifyEmailTarget(
        @Path("userid") Integer userId,
        @Path("targetid") Integer targetId,
        @Field("email") String email,
        @Field("severitylevel") String severity);

    // -- SMS Targets API

    @POST("users/{userid}")
    @FormUrlEncoded
    Call<NewContactTargetResponse> addSmsTarget(
        @Path("userid") Integer userId,
        @Field("countrycode") String code,
        @Field("number") String number,
        @Field("provider") String provider,
        @Field("severitylevel") String severityLevel);

    @PUT("users/{userid}/{targetid}")
    @FormUrlEncoded
    Call<Message> modifySmsTarget(
        @Path("userid") Integer userId,
        @Path("targetid") Integer targetId,
        @Field("countrycode") String countryCode,
        @Field("number") String number,
        @Field("provider") String provider,
        @Field("severitylevel") String severityLevel);

    @DELETE("users/{userid}/{targetid}")
    Call<ContactTarget> deleteTarget(
        @Path("userid") Integer userId,
        @Path("targetid") Integer targetId);

    // -- User API

    @GET("users")
    Call<ListUsersResponse> listUsers();

    @POST("users")
    @FormUrlEncoded
    Call<CreateUserResponse> createUser(@Field("name") String name);

    @DELETE("users/{userid}")
    Call<Message> deleteUser(@Path("userid") Integer userId);

    @PUT("users/{userid}")
    @FormUrlEncoded
    Call<Message> modifyUser(
        @Path("userid") Integer userId,
        @Field("name") String name,
        @Field("paused") String paused,
        @Field("use_severity_levels") String useSeverityLevels);

}
