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

import java.util.Map;

public class HttpCheck {

    private String url;
    private Boolean encryption;
    private Integer port;
    private String auth;
    private String shouldcontain;
    private String shouldnotcontain;
    private String postdata;
    private Map<String, String> requestheaders;
    private String typeName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getEncryption() {
        return encryption;
    }

    public void setEncryption(Boolean encryption) {
        this.encryption = encryption;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getShouldContain() {
        return shouldcontain;
    }

    public void setShouldContain(String shouldContain) {
        this.shouldcontain = shouldContain;
    }

    public String getShouldNotContain() {
        return shouldnotcontain;
    }

    public void setShouldNotContain(String shouldNotContain) {
        this.shouldnotcontain = shouldNotContain;
    }

    public String getPostData() {
        return postdata;
    }

    public void setPostData(String postData) {
        this.postdata = postData;
    }

    public Map<String, String> getRequestHeaders() {
        return requestheaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestheaders = requestHeaders;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
