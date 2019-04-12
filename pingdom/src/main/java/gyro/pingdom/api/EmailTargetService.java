package gyro.pingdom.api;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmailTargetService {
    @POST("user/{userid}")
    Call<ContactTargetId> addEmailTargetToUser(@Path("userid") Integer id,
                                    @Field("address") String address,
                                    @Field("severity") String severity);

    @POST("user/{userid}/{targetid}")
    Call<String> modifyEmailTarget(@Path("userid") Integer id,
                                   @Path("targetid") Integer targetId,
                                   @Field("address") String address,
                                   @Field("severity") String severity);

    @DELETE("user/{userid}/{targetid}")
    Call<UserList> deleteEmailTarget(@Path("userid") Integer id,
                                    @Path("targetid") Integer targetId);
}
