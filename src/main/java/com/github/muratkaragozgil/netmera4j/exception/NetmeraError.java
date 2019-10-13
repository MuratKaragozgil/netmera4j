package com.github.muratkaragozgil.netmera4j.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
public class NetmeraError {
    private String error;
    private Integer code;
}
