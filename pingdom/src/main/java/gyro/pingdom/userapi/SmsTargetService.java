package gyro.pingdom.userapi;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SmsTargetService {

    @GET("users/{userid}")
    Call<ContactTargetsList> getContactTargets(@Path("userid") Integer id);

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
}
