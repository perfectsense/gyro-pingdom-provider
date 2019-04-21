package gyro.pingdom.userapi;

import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface UserService {

    //works
    //can get an individual user
    @GET("users")
    Call<UserList> listUsers();

    //works
    @POST("users")
    @FormUrlEncoded
    Call<UserId> createUser(@Field("name") String userToAdd);

    @GET("users/{userid}")
    Call<ContactTargetsList> getUser(@Path("userid") Integer id);

    //works
    @PUT("users/{userid}")
    @FormUrlEncoded
    Call<Message> modifyUser(@Path("userid") Integer id,
                             @Field("name") String newName,
                             @Field("paused") String paused,
                             @Field("primary") String primaryContact);

    //works
    @PUT("users/{userid}")
    @FormUrlEncoded
    Call<Message> modifyUserName(@Path("userid") Integer id,
                                 @Field("name") String newName);

    //works
    @PUT("users/{userid}")
    @FormUrlEncoded
    Call<Message> modifyUserNamePaused(@Path("userid") Integer id,
                                       @Field("name") String newName,
                                       @Field("paused") String paused);

    //works
    @PUT("users/{userid}")
    @FormUrlEncoded
    Call<Message> modifyPrimary(@Path("userid") Integer id,
                                @Field("primary") String paused);

    //works
    @PUT("users/{userid}")
    @FormUrlEncoded
    Call<Message> modifyPrimaryAndPaused(@Path("userid") Integer id,
                                         @Field("primary") String primary,
                                         @Field("paused") String paused);

    //works
    @DELETE("users/{userid}")
    Call<Message> deleteUser(@Path("userid") Integer id);
}
