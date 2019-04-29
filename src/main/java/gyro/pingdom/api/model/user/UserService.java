package gyro.pingdom.api.model.user;

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

    @GET("users/{userid}")
    Call<ContactTargetsList> getContactTargets(@Path("userid") Integer id);


    @POST("users/{userid}")
    @FormUrlEncoded
    Call<ContactTargetId> addEmailTargetToUser(@Path("userid") Integer id,
                                               @Field("email") String email,
                                               @Field("severitylevel") String severity);

    @PUT("users/{userid}/{targetid}")
    @FormUrlEncoded
    Call<Message> modifyEmailTarget(@Path("userid") Integer id,
                                    @Path("targetid") Integer targetId,
                                    @Field("email") String email,
                                    @Field("severitylevel") String severity);

    @PUT("users/{userid}/{targetid}")
    @FormUrlEncoded
    Call<Message> modifySeverity(@Path("userid") Integer id,
                                 @Path("targetid") Integer targetId,
                                 @Field("severitylevel") String severity);

    @DELETE("users/{userid}/{targetid}")
    Call<Message> deleteEmailTarget(@Path("userid") Integer id,
                                    @Path("targetid") Integer targetId);

    // -- SMS Targets API

    @POST("users/{userid}")
    @FormUrlEncoded
    Call<ContactTargetId> addSmsTargetToUser(@Path("userid") Integer id,
                                             @Field("countrycode") String code,
                                             @Field("number") String number,
                                             @Field("provider") String provider,
                                             @Field("severitylevel") String severity);

    @POST("users/{userid}/{targetid}")
    @FormUrlEncoded
    Call<String> modifySmsTarget(@Path("userid") Integer id,
                                 @Path("targetid") Integer targetId,
                                 @Field("country_code") String countryCode,
                                 @Field("number") String number,
                                 @Field("provider") String provider,
                                 @Field("severitylevel") String severity);

    @DELETE("users/{userid}/{targetid}")
    Call<ContactTarget> deleteSmsTarget(@Path("userid") Integer id,
                                        @Path("targetid") Integer targetId);

    // -- User API

    @GET("users")
    Call<UserList> listUsers();

    @POST("users")
    @FormUrlEncoded
    Call<UserId> createUser(@Field("name") String userToAdd);

    @GET("users/{userid}")
    Call<ContactTargetsList> getUser(@Path("userid") Integer id);

    @PUT("users/{userid}")
    @FormUrlEncoded
    Call<Message> modifyUser(@Path("userid") Integer id,
                             @Field("name") String newName,
                             @Field("paused") String paused,
                             @Field("primary") String primaryContact);

    @PUT("users/{userid}")
    @FormUrlEncoded
    Call<Message> modifyUserName(@Path("userid") Integer id,
                                 @Field("name") String newName);

    @PUT("users/{userid}")
    @FormUrlEncoded
    Call<Message> modifyUserNamePaused(@Path("userid") Integer id,
                                       @Field("name") String newName,
                                       @Field("paused") String paused);

    @PUT("users/{userid}")
    @FormUrlEncoded
    Call<Message> modifyPrimary(@Path("userid") Integer id,
                                @Field("primary") String paused);

    @PUT("users/{userid}")
    @FormUrlEncoded
    Call<Message> modifyPrimaryAndPaused(@Path("userid") Integer id,
                                         @Field("primary") String primary,
                                         @Field("paused") String paused);

    @DELETE("users/{userid}")
    Call<Message> deleteUser(@Path("userid") Integer id);

}
