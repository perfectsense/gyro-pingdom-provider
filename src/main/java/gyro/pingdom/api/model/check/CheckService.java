package gyro.pingdom.api.model.check;

import gyro.pingdom.api.model.common.Message;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface CheckService {

    // -- Check API

    @GET("checks")
    Call<ListChecksResponse> listChecks();

    @GET("checks/{checkid}")
    Call<CheckResponse> getCheck(@Path("checkid") Integer checkId);

    @DELETE("checks/{checkid}")
    Call<Message> deleteCheck(@Path("checkid") Integer checkId);

    // -- HTTP Check API

    @POST("checks")
    @FormUrlEncoded
    Call<CheckResponse> createHttpCheck(
        @Field("name") String name,
        @Field("host") String host,
        @Field("type") String type,
        @Field("paused") Boolean paused,
        @Field("resolution") Integer resolution,
        @Field("userids") List<Integer> userids,
        @Field("sendnotificationwhendown") Integer sendNotificationWhenDown,
        @Field("notifyagainevery") Integer notifyAgainEvery,
        @Field("notifywhenbackup") Boolean notifyWhenBackup,
        @Field("tags") String tags,
        @Field("probe_filters") String probeFilters,
        @Field("ipv6") Boolean ipv6,
        @Field("responsetime_threshold") Integer responseTimeThreshold,
        @Field("integrationids") List<Integer> integrationIds,
        @Field("teamids") List<Integer> teamIds,
        @Field("url") String url,
        @Field("encryption") Boolean encryption,
        @Field("port") Integer port,
        @Field("auth") String auth,
        @Field("shouldcontain") String shouldContain,
        @Field("shouldnotcontain") String shouldNotContain,
        @Field("postdata") String postData);

    @PUT("checks/{checkid}")
    @FormUrlEncoded
    Call<Message> modifyHttpCheck(
        @Path("checkid") Integer checkId,
        @Field("name") String name,
        @Field("host") String host,
        @Field("paused") Boolean paused,
        @Field("resolution") Integer resolution,
        @Field("userids") List<Integer> userids,
        @Field("sendnotificationwhendown") Integer sendNotificationWhenDown,
        @Field("notifyagainevery") Integer notifyAgainEvery,
        @Field("notifywhenbackup") Boolean notifyWhenBackup,
        @Field("tags") String tags,
        @Field("probe_filters") String probeFilters,
        @Field("ipv6") Boolean ipv6,
        @Field("responsetime_threshold") Integer responseTimeThreshold,
        @Field("integrationids") List<Integer> integrationIds,
        @Field("teamids") List<Integer> teamIds,
        @Field("url") String url,
        @Field("encryption") Boolean encryption,
        @Field("port") Integer port,
        @Field("auth") String auth,
        @Field("shouldcontain") String shouldContain,
        @Field("shouldnotcontain") String shouldNotContain,
        @Field("postdata") String postData);

    // -- Custom HTTP Check API

    @POST("checks")
    @FormUrlEncoded
    Call<CheckResponse> createCustomHttpCheck(
        @Field("name") String name,
        @Field("host") String host,
        @Field("type") String type,
        @Field("paused") Boolean paused,
        @Field("resolution") Integer resolution,
        @Field("userids") List<Integer> userids,
        @Field("sendnotificationwhendown") Integer sendNotificationWhenDown,
        @Field("notifyagainevery") Integer notifyAgainEvery,
        @Field("notifywhenbackup") Boolean notifyWhenBackup,
        @Field("tags") String tags,
        @Field("probe_filters") String probeFilters,
        @Field("ipv6") Boolean ipv6,
        @Field("responsetime_threshold") Integer responseTimeThreshold,
        @Field("integrationids") List<Integer> integrationIds,
        @Field("teamids") List<Integer> teamIds,
        @Field("url") String url,
        @Field("encryption") Boolean encryption,
        @Field("port") Integer port,
        @Field("auth") String auth,
        @Field("additionalurls") String additionalUrls);

    @PUT("checks/{checkid}")
    @FormUrlEncoded
    Call<Message> modifyCustomHttpCheck(
        @Path("checkid") Integer checkId,
        @Field("name") String name,
        @Field("host") String host,
        @Field("paused") Boolean paused,
        @Field("resolution") Integer resolution,
        @Field("userids") List<Integer> userids,
        @Field("sendnotificationwhendown") Integer sendNotificationWhenDown,
        @Field("notifyagainevery") Integer notifyAgainEvery,
        @Field("notifywhenbackup") Boolean notifyWhenBackup,
        @Field("tags") String tags,
        @Field("probe_filters") String probeFilters,
        @Field("ipv6") Boolean ipv6,
        @Field("responsetime_threshold") Integer responseTimeThreshold,
        @Field("integrationids") List<Integer> integrationIds,
        @Field("teamids") List<Integer> teamIds,
        @Field("url") String url,
        @Field("encryption") Boolean encryption,
        @Field("port") Integer port,
        @Field("auth") String auth,
        @Field("additionalurls") String additionalUrls);


    // -- TCP Check API

    @POST("checks")
    @FormUrlEncoded
    Call<CheckResponse> createTcpCheck(
        @Field("name") String name,
        @Field("host") String host,
        @Field("type") String type,
        @Field("paused") Boolean paused,
        @Field("resolution") Integer resolution,
        @Field("userids") List<Integer> userids,
        @Field("sendnotificationwhendown") Integer sendNotificationWhenDown,
        @Field("notifyagainevery") Integer notifyAgainEvery,
        @Field("notifywhenbackup") Boolean notifyWhenBackup,
        @Field("tags") String tags,
        @Field("probe_filters") String probeFilters,
        @Field("ipv6") Boolean ipv6,
        @Field("responsetime_threshold") Integer responseTimeThreshold,
        @Field("integrationids") List<Integer> integrationIds,
        @Field("teamids") List<Integer> teamIds,
        @Field("port") Integer port,
        @Field("stringtosend") String stringToSend,
        @Field("stringtoexpect") String stringToExpect);

    @PUT("checks/{checkid}")
    @FormUrlEncoded
    Call<Message> modifyTcpCheck(
        @Path("checkid") Integer checkId,
        @Field("name") String name,
        @Field("host") String host,
        @Field("paused") Boolean paused,
        @Field("resolution") Integer resolution,
        @Field("userids") List<Integer> userids,
        @Field("sendnotificationwhendown") Integer sendNotificationWhenDown,
        @Field("notifyagainevery") Integer notifyAgainEvery,
        @Field("notifywhenbackup") Boolean notifyWhenBackup,
        @Field("tags") String tags,
        @Field("probe_filters") String probeFilters,
        @Field("ipv6") Boolean ipv6,
        @Field("responsetime_threshold") Integer responseTimeThreshold,
        @Field("integrationids") List<Integer> integrationIds,
        @Field("teamids") List<Integer> teamIds,
        @Field("port") Integer port,
        @Field("stringtosend") String stringToSend,
        @Field("stringtoexpect") String stringToExpect);

}
