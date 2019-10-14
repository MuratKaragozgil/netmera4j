[![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg?style=plastic)](https://github.com/sedran/moment4j/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.muratkaragozgil/netmera4j.svg?maxAge=60&colorB=53C92E)](https://maven-badges.herokuapp.com/maven-central/com.github.muratkaragozgil/netmera4j)


Netmera4j
========

A pleasant and complete Java wrapper for the Netmera Rest Api!

For more information please visit [the website][1].

 [1]: https://netmera.readme.io/docs/rest-api-error-codes


Maven
--------------------------------------

```xml
<dependency>
  <groupId>com.github.muratkaragozgil</groupId>
  <artifactId>netmera4j</artifactId>
  <version>1.4</version>
</dependency>
```

Gradle
--------------------------------------

```groovy
implementation 'com.github.muratkaragozgil:netmera4j:1.4'
```

Usage
--------------------------------------

```java

import com.github.muratkaragozgil.netmera4j.Netmera;
import com.github.muratkaragozgil.netmera4j.NetmeraApi;

// Rest api key comes from Netmera Panel
// Target host is the rest api endpoint you will use (Netmera cloud environment is http://restapi.netmera.com)
Netmera netmerea = new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, REST_API_KEY);

```