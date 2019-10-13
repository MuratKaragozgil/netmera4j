package com.github.muratkaragozgil.netmera4j.exception;

/**
 * @author Murat Karagözgil
 */
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ValidationException(String message) {
        super(message);
    }
}