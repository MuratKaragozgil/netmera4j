package com.github.muratkaragozgil.netmera4j;

import com.github.muratkaragozgil.netmera4j.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Murat Karagözgil
 */

public class NetmeraApiTest {

    private Netmera netmera;
    private final String TARGET_HOST = "https://restapi.netmera.com";
    private final String REST_API_KEY = "rest-api-key";

    @BeforeEach
    void setUp() {
        netmera = new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, REST_API_KEY).build();
    }

    @Test
    public void testIsTargetHostIsNull() {
        assertThrows(NullPointerException.class, () -> new NetmeraApi.NetmeraApiBuilder(null, REST_API_KEY).build());
    }

    @Test
    public void testIsRestApiKeyIsNull() {
        assertThrows(NullPointerException.class, () -> new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, null).build());
    }

    @Test
    public void testIsRestApiKeyIsEmpty() {
        assertThrows(ValidationException.class, () -> new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, "").build());
    }

    @Test
    public void testIsTargetHostIsEmpty() {
        assertThrows(ValidationException.class, () -> new NetmeraApi.NetmeraApiBuilder("", REST_API_KEY).build());
    }

    @Test
    public void shouldThrowValidationExceptionWhenSetWriteTimeoutIsMinusZero() {
        assertThrows(ValidationException.class, () -> new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, REST_API_KEY).withWriteTimeout(-1).build());
    }

    @Test
    public void shouldThrowValidationExceptionWhenSetReadTimeoutIsMinusZero() {
        assertThrows(ValidationException.class, () -> new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, REST_API_KEY).withReadTimeout(-1).build());
    }

    @Test
    public void shouldThrowValidationExceptionWhenSetConnectionTimeoutIsMinusZero() {
        assertThrows(ValidationException.class, () -> new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, REST_API_KEY).withConnectionTimeout(-1).build());
    }

    @Test
    public void shouldThrowValidationExceptionWhenSetCallTimeoutIsMinusZero() {
        assertThrows(ValidationException.class, () -> new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, REST_API_KEY).withCallTimeout(-1).build());
    }

    @Test
    public void shouldThrowValidationExceptionWhenSetRetryCountGreaterThanFifty() {
        assertThrows(ValidationException.class, () -> new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, REST_API_KEY).withMaxRetryCount(51).build());
    }

    @Test
    public void shouldThrowValidationExceptionWhenSetRetryCountIsMinusZero() {
        assertThrows(ValidationException.class, () -> new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, REST_API_KEY).withMaxRetryCount(-1).build());
    }

    @Test
    public void shouldThrowNullPointerExceptionExceptionWhenSetRetryPolicyNull() {
        assertThrows(NullPointerException.class, () -> new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, REST_API_KEY).withNetmeraRetryPolicy(null).build());
    }

    @Test
    public void shouldThrowNullPointerExceptionExceptionWhenSetConnectionPoolNull() {
        assertThrows(NullPointerException.class, () -> new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, REST_API_KEY).withConnectionPool(null).build());
    }

}