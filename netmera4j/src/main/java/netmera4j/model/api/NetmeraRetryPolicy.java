package netmera4j.model.api;

import lombok.Getter;
import lombok.ToString;
import netmera4j.util.Assert;

import java.time.temporal.ChronoUnit;

/**
 * @author Murat Karagözgil
 */
@Getter
@ToString
public class NetmeraRetryPolicy {
    private long delay;
    private long maxDelay;
    private ChronoUnit unit;

    private NetmeraRetryPolicy(long delay, long maxDelay, ChronoUnit unit) {
        this.delay = delay;
        this.maxDelay = maxDelay;
        this.unit = unit;
    }

    public static final class NetmeraRetryPolicyBuilder {
        private long delay = 30;
        private long maxDelay = 60 * 10;
        private ChronoUnit unit = ChronoUnit.SECONDS;

        public NetmeraRetryPolicyBuilder() {
        }

        public static NetmeraRetryPolicyBuilder Builder() {
            return new NetmeraRetryPolicyBuilder();
        }

        public NetmeraRetryPolicyBuilder delay(long delay) {
            Assert.mustGreaterThan(0, delay, "Delay");
            this.delay = delay;
            return this;
        }

        public NetmeraRetryPolicyBuilder maxDelay(long maxDelay) {
            Assert.mustGreaterThan(0, delay, "Delay");
            this.maxDelay = maxDelay;
            return this;
        }

        public NetmeraRetryPolicyBuilder unit(ChronoUnit unit) {
            this.unit = unit;
            return this;
        }

        public NetmeraRetryPolicy build() {
            return new NetmeraRetryPolicy(delay, maxDelay, unit);
        }
    }
}
