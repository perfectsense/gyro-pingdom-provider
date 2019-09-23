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

public class Type {

    private HttpCheck http;
    private HttpCustomCheck httpcustom;
    private TcpCheck tcp;

    public HttpCheck getHttp() {
        return http;
    }

    public void setHttp(HttpCheck httpCheck) {
        this.http = httpCheck;
    }

    public HttpCustomCheck getHttpCustom() {
        return httpcustom;
    }

    public void setHttpCustom(HttpCustomCheck httpCustomCheck) {
        this.httpcustom = httpCustomCheck;
    }

    public TcpCheck getTcp() {
        return tcp;
    }

    public void setTcp(TcpCheck tcp) {
        this.tcp = tcp;
    }

}
