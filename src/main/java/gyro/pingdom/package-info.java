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

/**
 * Pingdom
 * -------
 *
 * The Pingdom provider implements support for the Pingdom website monitoring service.
 *
 * Usage
 * +++++
 *
 * The Pingdom provider is implemented as a plugin. To use it add the plugin to your init file.
 * It uses the format ``@plugin: gyro:gyro-azure-provider:<version>``.
 *
 * .. code:: shell
 *
 *     {@literal @}plugin: 'gyro:gyro-pingdom-provider:0.99.0-SNAPSHOT'
 *
 * This lets Gyro load the Pingdom provider plugin and lets you start managing Pingdom resources using Gyro.
 *
 * Authentication
 * ++++++++++++++
 *
 * The Pingdom provider expects credentials to be in Java properties file format. The
 * ``app-key``, ``email``, and ``password`` are required.
 *
 * **Example credentials.props file:**
 *
 * .. code::
 *
 *     app-key=iFi1Ueyaiyaihah4oeheu1walohweish
 *     email=user@example.com
 *     password=eic6paoHecieShooLe3eah2xiequ7ahc
 *
 * Then use these credentials in ``.gyro/init.gyro`` in your Gyro project:
 *
 * .. code:: shell
 *
 *     {@literal @}credentials pingdom::credentials
 *          credentials-file-path: "/path/to/credentials.props"
 *     {@literal @}end
 */
@DocNamespace("pingdom")
@Namespace("pingdom")
package gyro.pingdom;

import gyro.core.Namespace;
import gyro.core.resource.DocNamespace;
