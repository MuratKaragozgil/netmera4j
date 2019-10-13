package com.github.muratkaragozgil.netmera4j.request.event;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Murat Karagözgil
 */
@Getter
@ToString
public class SingleEvent {
    @Expose(serialize = false)
    private Map<String, Object> parameters;

    private SingleEvent(SingleEventBuilder singleEventBuilder) {
        this.parameters = singleEventBuilder.parameters;
    }

    public static final class SingleEventBuilder {
        private Map<String, Object> parameters = new HashMap<>(2);

        public SingleEventBuilder(String externalId, String eventName) {
            this.parameters.put("extId", externalId);
            this.parameters.put("name", eventName);
        }

        public SingleEventBuilder addParameter(String key, Object value) {
            this.parameters.put(key, value);
            return this;
        }

        public SingleEvent build() {
            return new SingleEvent(this);
        }
    }
}
