[![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg?style=plastic)](https://github.com/muratkaragozgil/netmera4j/blob/master/LICENSE)
[![Chat at https://gitter.im/javalin-io/general](https://badges.gitter.im/netmera4j/general.svg)](https://gitter.im/netmera4j/general)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.muratkaragozgil/netmera4j.svg?maxAge=60&colorB=53C92E)](https://maven-badges.herokuapp.com/maven-central/com.github.muratkaragozgil/netmera4j)
[![Javadocs](http://www.javadoc.io/badge/com.github.muratkaragozgil/netmera4j.svg)](http://www.javadoc.io/doc/com.github.muratkaragozgil/netmera4j)
[![Build Status](https://travis-ci.com/MuratKaragozgil/netmera4j.svg?branch=master)](https://travis-ci.com/MuratKaragozgil/netmera4j)

Netmera4j - A pleasant and complete Java wrapper for the Netmera Rest Api!
========

Netmera4j is a wrapper library for using Netmera restapi features. Includes pooling, retring and async calling logic by default. But fortunately it gives functionality to change these settings. Netmera4j allows you to manage device registration and notifications. By sending HTTP request you can register, tag, change profile attribute of user/devices. You can also send notification and get the details of sent notification via REST API.

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

netmera = new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, REST_API_KEY) //
                .withMaxRetryCount(5) //
                .withNetmeraRetryPolicy(new NetmeraRetryPolicy.NetmeraRetryPolicyBuilder() //
                        .delay(5) //
                        .maxDelay(60) //
                        .unit(ChronoUnit.MINUTES) //
                        .build())
                .build();

```

Fire Event
--------------------------------------
To fire an event, you must set external id and event name firstly.
Then you can set optional and required parameters of event. You can also set multiple event at one time.
Note that you can set optional and required event parameters from panel.

```java

netmera.sendRequest(FireEventsRequest.builder()
                .eventList(Arrays.asList(new SingleEvent.SingleEventBuilder(EXTERNAL_ID, EVENT_NAME) //
                        .addParameter("itemId", "1234") //
                        .addParameter("channel", "Facebook") //
                        .build(), //
                         new SingleEvent.SingleEventBuilder(EXTERNAL_ID, EVENT_NAME)
                        .addParameter("itemId", "12345") //
                        .addParameter("channel", "Instagram") //
                        .build())) //
                .build(), new NetmeraCallBack<Void>() {
            @Override
            protected void handleResponseCode(int httpStatus) {
            }

            @Override
            protected void handleResponseData(Void data) {

            }

            @Override
            protected void handleError(Response<Void> response) {

            }

            @Override
            protected void handleException(Exception t) {

            }
        });
```

Send Bulk Notification
--------------------------------------

To sending notification to all registered users.
 
```java

netmera.sendRequest(SendBulkNotificationRequest.builder()
                .message(BulkMessage.builder() //
                        .title(TITLE) //
                        .text("testSendBasicBulkNotification") //
                        .platforms(Arrays.asList(Platform.ANDROID, Platform.IOS)) //
                        .build()) //
                .target(new BasicTarget.BasicTargetBuilder() //
                        .sendToAll(true) //
                        .build()) //
                .build(), new NetmeraCallBack<NotificationResponse>() {
            @Override
            protected void handleResponseCode(int httpStatus) {
            }

            @Override
            protected void handleResponseData(NotificationResponse data) {
            }

            @Override
            protected void handleError(Response<NotificationResponse> response) {
            }

            @Override
            protected void handleException(Exception t) {
            }
        });

```

Send Transactional Notification
--------------------------------------

Transactional notifications are previously configured messages(from panel) which can be sent to specific user.
You need to set TRANSACTIONAL_NOTIFICATION_KEY identifier which created message from netmera panel.
 
```java

netmera.sendRequest(SendTransactionalNotificationRequest.builder() //
                .message(SingleMessage.builder().build() //
                        .addParameter("customMessage", "your-funny-message")) //
                .notificationKey(TRANSACTIONAL_NOTIFICATION_KEY.toString()) //
                .target(new BasicTarget.BasicTargetBuilder() //
                        .externalId(EXTERNAL_ID) //
                        .build()) //
                .build(), new NetmeraCallBack<Void>() {

            @Override
            protected void handleResponseCode(int httpStatus) {
            }

            @Override
            protected void handleResponseData(Void data) {
            }

            @Override
            protected void handleError(Response<Void> response) {
            }

            @Override
            protected void handleException(Exception t) {
            }
        });
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
