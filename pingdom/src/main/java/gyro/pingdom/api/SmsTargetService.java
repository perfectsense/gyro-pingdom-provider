package gyro.pingdom.api;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SmsTargetService {
    @POST("user/{userid}")
    Call<ContactTargetId> addSmsTargetToUser(@Path("userid") Integer id,
                                    @Field("country_code") String code,
                                    @Field("number") String number,
                                    @Field("provider") String provider,
                                    @Field("severity") String severity);

    @POST("user/{userid}/{targetid}")
    Call<String> modifySmsTarget(@Path("userid") Integer id,
                                 @Path("targetid") Integer targetId,
                                 @Field("country_code") String countryCode,
                                 @Field("number") String number,
                                 @Field("provider") String provider,
                                 @Field("severity") String severity);

    @DELETE("user/{userid}/{targetid}")
    Call<ContactTargets> deleteSmsTarget(@Path("userid") Integer id,
                                         @Path("targetid") Integer targetId);
}
