package com.github.muratkaragozgil.netmera4j.model.notification;

import com.github.muratkaragozgil.netmera4j.constant.Platform;
import com.github.muratkaragozgil.netmera4j.constant.SendStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
public class SingleResult {
    private Platform platform;
    private SendStatus status;
    private String errorCode;
    private String installationId;
    private String extId;
    private String errorMsg;
    private Long timeStamp;
    private String msgTitle;
    private String msgText;
}
