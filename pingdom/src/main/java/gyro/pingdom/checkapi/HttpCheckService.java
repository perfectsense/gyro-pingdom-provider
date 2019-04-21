package gyro.pingdom.checkapi;

import gyro.pingdom.userapi.Message;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HttpCheckService {

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
}
