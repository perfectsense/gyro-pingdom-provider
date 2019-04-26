package gyro.pingdom.userapi;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmailTargetService {

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
}
