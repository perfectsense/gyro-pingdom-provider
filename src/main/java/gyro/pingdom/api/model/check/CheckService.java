/*
 * Copyright 2019, Perfect Sense, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gyro.pingdom.api.model.check;

import gyro.pingdom.api.model.common.Message;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;
import java.util.Map;

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
        @Field("userids") String userids,
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
        @Field("postdata") String postData,
        @FieldMap Map<String, String> requestHeaders);

    @PUT("checks/{checkid}")
    @FormUrlEncoded
    Call<Message> modifyHttpCheck(
        @Path("checkid") Integer checkId,
        @Field("name") String name,
        @Field("host") String host,
        @Field("paused") Boolean paused,
        @Field("resolution") Integer resolution,
        @Field("userids") String userids,
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
        @Field("postdata") String postData,
        @FieldMap Map<String, String> requestHeaders);

    // -- Custom HTTP Check API

    @POST("checks")
    @FormUrlEncoded
    Call<CheckResponse> createCustomHttpCheck(
        @Field("name") String name,
        @Field("host") String host,
        @Field("type") String type,
        @Field("paused") Boolean paused,
        @Field("resolution") Integer resolution,
        @Field("userids") String userids,
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
        @Field("userids") String userids,
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
        @Field("userids") String userids,
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
        @Field("userids") String userids,
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
