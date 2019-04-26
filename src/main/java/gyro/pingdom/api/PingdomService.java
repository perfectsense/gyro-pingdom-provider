package gyro.pingdom.api;

import gyro.pingdom.api.model.check.CheckId;
import gyro.pingdom.api.model.check.CheckList;
import gyro.pingdom.api.model.check.CheckResponse;
import gyro.pingdom.api.model.user.ContactTarget;
import gyro.pingdom.api.model.user.ContactTargetId;
import gyro.pingdom.api.model.user.ContactTargetsList;
import gyro.pingdom.api.model.user.Message;
import gyro.pingdom.api.model.user.UserId;
import gyro.pingdom.api.model.user.UserList;
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

public interface PingdomService {

    // -- Check API

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
                              @Field("userids") List<Integer> userids);

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

    // -- HTTP Check API

    @PUT("checks/{checkid}")
    @FormUrlEncoded
    Call<Message> modifyHttpCheck(@Path("checkid") Integer checkId,
                                  @Field("url") String url,
                                  @Field("encryption") Boolean encryption,
                                  @Field("port") Integer port,
                                  @Field("auth") String auth,
                                  @Field("shouldcontain") String shouldcontain,
                                  @Field("shouldnotcontain") String shouldnotcontain,
                                  @Field("postdata") String postdata);

    @PUT("checks/{checkid}")
    @FormUrlEncoded
    Call<Message> modifyRequestHeader(@Path("checkid") Integer checkId,
                                      @Field("requestheader{X}") String requestHeader);

    // -- HTTP Check API

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

    // -- TCP Check API

    @POST("checks")
    @FormUrlEncoded
    Call<CheckId> createTcpCheck(@Field("name") String name,
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
                                 @Field("port") Integer port,
                                 @Field("stringtosend") String stringtosend,
                                 @Field("stringtoexpect") String stringtoexpect);

    @PUT("checks/{checkid}")
    @FormUrlEncoded
    Call<Message> modifyTcpCheck(@Path("checkid") Integer checkId,
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
                                 @Field("port") Integer port,
                                 @Field("stringtosend") String stringtosend,
                                 @Field("stringtoexpect") String stringtoexpect);

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
