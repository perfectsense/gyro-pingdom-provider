package gyro.pingdom.api;

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
    @GET("user")
    Call<UserList> listUsers();

    //works
    @POST("user")
    @FormUrlEncoded
    Call<UserId> createUser(@Field("name") String userToAdd);

    @GET("user/{userid}")
    Call<ContactTargetsList> getUser(@Path("userid") Integer id);

    //works
    @PUT("user/{userid}")
    @FormUrlEncoded
    Call<UserList> modifyUser(@Path("userid") Integer id,
            @Field("name") String newName,
            @Field("primary") String primaryContact,
            @Field("paused") String paused);

    //works
    @DELETE("user/{userid}")
    Call<UserList> deleteUser(@Path("userid") Integer id);
}
