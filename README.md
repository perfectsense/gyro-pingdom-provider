<img src="https://github.com/perfectsense/gyro/blob/master/etc/gyro.png" height="200"/>

[![Gitter](https://img.shields.io/gitter/room/perfectsense/gyro)](https://gitter.im/perfectsense/gyro)
[![TravisCI](https://api.travis-ci.org/perfectsense/gyro-pingdom-provider.svg?branch=master)](https://travis-ci.org/perfectsense/gyro-pingdom-provider)
[![Apache License 2.0](https://img.shields.io/github/license/perfectsense/gyro-pingdom-provider)](https://github.com/perfectsense/gyro-pingdom-provider/blob/master/LICENSE)


The **Pingdom Provider for Gyro** enables users to easily work with Pingdom website monitoring service. The PIngdom provider extends Gyro allowing you to manage your Pingdom configurations.

To learn more about Gyro see [getgyro.io](https://getgyro.io) and [gyro](https://github.com/perfectsense/gyro). 

* [Resource Documentation](https://gyro.dev/providers/pingdom/index.html)
* [Submit an Issue](https://github.com/perfectsense/gyro-pingdom-provider/issues)
* [Getting Help](#getting-help)

## Using the Pingdom Provider

### Pingdom Account ###

Before you can use Pingdom provider, you will need an Pingdom account. Please see [Sign Up for Pingdom](https://www.pingdom.com/website-monitoring/) to create a Pingdom Account.

The Pingdom provider expects credentials to be in Java properties file format. The ``app-key``, ``email``, and ``password`` are required.

```
app-key=iFi1Ueyaiyaihah4oeheu1walohweish
email=user@example.com
password=eic6paoHecieShooLe3eah2xiequ7ahc
``` 

### Using The Provider ###

#### Import ####

Load the Pingdom provider in your project by consuming it as a `plugin` directive in your init file. It uses the format `@plugin: gyro:gyro-pingdom-provider:<version>`.

```shell
@repository: 'https://artifactory.psdops.com/gyro-releases'
@plugin: 'gyro:gyro-pingdom-provider:0.99.0'
```

#### Authentication ####

Provide the Pingdom provider with the path of the credentials file by defining the following in your `.gyro/init.gyro` file:

```
@credentials 'pingdom::credentials'
    credentials-file-path: '<pingdom_credentials_file_path>'
@end
```

See [Pingdom authentication for Gyro](https://gyro.dev/providers/pingdom/index.html#authentication) for more details.

## Supported Services

* [Check](https://gyro.dev/providers/pingdom/check/index.html)
* [User](https://gyro.dev/providers/pingdom/user/index.html)

## Developing the Pingdom Provider

The provider is written in Java using Gradle as the build tool.

We recommend installing [AdoptOpenJDK](https://adoptopenjdk.net/) 11 or higher if you're going to contribute to this provider. 

Gyro uses the Gradle build tool. Once you have a JDK installed building is easy, just run ./gradlew at the root of the Gyro project. This wrapper script will automatically download and install Gradle for you, then build the provider:
```shell
$ ./gradlew
Downloading https://services.gradle.org/distributions/gradle-5.2.1-all.zip
..............................................................................................................................
Welcome to Gradle 5.2.1!
Here are the highlights of this release:
 - Define sets of dependencies that work together with Java Platform plugin
 - New C++ plugins with dependency management built-in
 - New C++ project types for gradle init
 - Service injection into plugins and project extensions
For more details see https://docs.gradle.org/5.2.1/release-notes.html
Starting a Gradle Daemon, 1 stopped Daemon could not be reused, use --status for details
.
.
.
BUILD SUCCESSFUL in 17s
38 actionable tasks: 28 executed, 10 from cache
$
```

## Getting Help

* Join the Gyro community chat on [Gitter](https://gitter.im/perfectsense/gyro).
* Take a look at the [documentation](https://gyro.dev/providers/pingdom/index.html) for tutorial and examples.

## License

[Apache License 2.0](https://github.com/perfectsense/gyro-pingdom-provider/blob/master/LICENSE) 
This software is open source under the [Apache License 2.0](https://github.com/perfectsense/gyro-pingdom-provider/blob/master/LICENSE).