package gyro.pingdom.checkapi;

import gyro.pingdom.userapi.Message;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;
import java.util.Map;

public interface HttpCustomCheckService {

    @POST("checks")
    @FormUrlEncoded
    Call<CheckId> createHttpCheck(@Field("name") String name,
                                  @Field("host") String host,
                                  @Field("type") String typeCheck,
                                  @Field("paused") Boolean paused,
                                  @Field("resolution") String resolution,
                                  @Field("userids") List<Integer> userids,
                                  @Field("sendnotificationwhendown") Integer sendnotificationwhendown,
                                  @Field("notifyagainevery") Integer notifyagainevery,
                                  @Field("notifywhenbackup") Boolean notifywhenbackup,
                                  @Field("tags") List<String> tags,
                                  @Field("probe_filters") Map<String, String> probe_filters,
                                  @Field("ipv6") Boolean ipv6,
                                  @Field("responsetime_threshold") Integer responsetime_threshold,
                                  @Field("integrationids") List<Integer> integrationids,
                                  @Field("teamids") List<Integer> teamids,
                                  @Field("url") String url,
                                  @Field("encryption") Boolean encryption,
                                  @Field("port") Integer port,
                                  @Field("auth") String auth,
                                  @Field("additionalurls") String additionalurls);

    @PUT("checks/{checkid}")
    @FormUrlEncoded
    Call<Message> modifyHttpCustomCheck(@Path("checkid") Integer checkId,
                                        @Field("name") String name,
                                        @Field("host") String host,
                                        @Field("type") String typeCheck,
                                        @Field("paused") Boolean paused,
                                        @Field("resolution") String resolution,
                                        @Field("userids") List<Integer> userids,
                                        @Field("sendnotificationwhendown") Integer sendnotificationwhendown,
                                        @Field("notifyagainevery") Integer notifyagainevery,
                                        @Field("notifywhenbackup") Boolean notifywhenbackup,
                                        @Field("tags") List<String> tags,
                                        @Field("probe_filters") Map<String, String> probe_filters,
                                        @Field("ipv6") Boolean ipv6,
                                        @Field("responsetime_threshold") Integer responsetime_threshold,
                                        @Field("integrationids") List<Integer> integrationids,
                                        @Field("teamids") List<Integer> teamids,
                                        @Field("url") String url,
                                        @Field("encryption") Boolean encryption,
                                        @Field("port") Integer port,
                                        @Field("auth") String auth,
                                        @Field("additionalurls") String additionalurls);
}
