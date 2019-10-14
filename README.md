[![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg?style=plastic)](https://github.com/muratkaragozgil/netmera4j/blob/master/LICENSE)
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
  <version>1.6</version>
</dependency>
```

Gradle
--------------------------------------

```groovy
implementation 'com.github.muratkaragozgil:netmera4j:1.6'
```

Usage
--------------------------------------

```java

import com.github.muratkaragozgil.netmera4j.Netmera;
import com.github.muratkaragozgil.netmera4j.NetmeraApi;

// Rest api key comes from Netmera Panel
// Target host is the rest api endpoint you will use (Netmera cloud environment is https://restapi.netmera.com)
private static final String TARGET_HOST = "https://restapi.netmera.com";
private static final String REST_API_KEY = "<your_rest_api_key>";

Netmera netmera = new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, REST_API_KEY).build();

```

Retry Configuration
--------------------------------------

```java

Netmera netmera = new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, REST_API_KEY).build();
netmera = new NetmeraApi.NetmeraApiBuilder(restEndpoint, restApiKey) //
                .withMaxRetryCount(5) //
                .withNetmeraRetryPolicy(new NetmeraRetryPolicy.NetmeraRetryPolicyBuilder() //
                        .delay(5) //
                        .maxDelay(60) //
                        .unit(ChronoUnit.MINUTES) //
                        .build())
                .build();

```

License
=======

    MIT License
    
    Copyright (c) 2019 Murat Karagözgil
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
