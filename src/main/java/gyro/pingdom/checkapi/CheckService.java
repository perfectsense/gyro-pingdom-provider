package gyro.pingdom.checkapi;

import gyro.pingdom.userapi.Message;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;
import java.util.Map;

public interface CheckService {

    @GET("checks")
    Call<CheckList> listChecks();

    @GET("checks/{checkid}")
    Call<CheckResponse> getCheck(@Path("checkid") Integer checkId);

    @POST("checks")
    @FormUrlEncoded
    Call<CheckId> createCheck(@Field("name") String name,
                              @Field("host") String hostname,
                              @Field("type") String typeCheck,
                               @Field("paused") Boolean paused,
                               @Field("resolution") Integer resolution,
                               @Field("sendnotificationwhendown") Integer sendnotificationwhendown,
                               @Field("notifyagainevery") Integer notifyagainevery,
                               @Field("notifywhenbackup") Boolean notifywhenbackup,
                               @Field("tags") List<String> tags,
                               @Field("userids") List<Integer> userids
                               );

    @POST("checks")
    @FormUrlEncoded
    Call<CheckId> createCheck(@Field("name") String name,
                                  @Field("host") String hostname,
                                  @Field("type") String typeCheck,
                                  @Field("paused") Boolean paused,
                                  @Field("resolution") Integer resolution,
                                  @Field("userids") List<Integer> userids,
                                  @Field("sendnotificationwhendown") Integer sendnotificationwhendown,
                                  @Field("notifyagainevery") Integer notifyagainevery,
                                  @Field("notifywhenbackup") Boolean notifywhenbackup,
                                  @Field("tags") List<String> tags,
                                  @Field("probe_filters") Map<String, String> probe_filters,
                                  @Field("ipv6") Boolean ipv6,
                                  @Field("responsetime_threshold") Integer responsetime_threshold,
                                  @Field("integrationids") List<Integer> integrationids,
                                  @Field("teamids") List<Integer> teamids);

    @PUT("checks/{checkid}")
    @FormUrlEncoded
    Call<Message> modifyCheck(@Path("checkid") Integer checkId,
                              @Field("name") String name,
                              @Field("host") String host,
                              @Field("type") String typeCheck,
                              @Field("paused") Boolean paused,
                              @Field("resolution") Integer resolution,
                              @Field("userids") List<Integer> userids,
                              @Field("sendnotificationwhendown") Integer sendnotificationwhendown,
                              @Field("notifyagainevery") Integer notifyagainevery,
                              @Field("notifywhenbackup") Boolean notifywhenbackup,
                              @Field("tags") List<String> tags,
                              @Field("probe_filters") Map<String, String> probe_filters,
                              @Field("ipv6") Boolean ipv6,
                              @Field("responsetime_threshold") Integer responsetime_threshold,
                              @Field("integrationids") List<Integer> integrationids,
                              @Field("teamids") List<Integer> teamids);

    @DELETE("checks/{checkid}")
    Call<Message> deleteCheck(@Path("checkid") Integer checkId);
}
