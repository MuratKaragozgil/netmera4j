package netmera4j.model.notification;

import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Murat Karagözgil
 */
@Getter
@ToString
public class Target {
    protected Location location;
    protected boolean sendToAll;
    protected String extId;
    protected String deviceToken;
    protected Map<String, List<Object>> profile;

    protected Target(TargetBuilder targetBuilder) {
        this.location = targetBuilder.location;
        this.sendToAll = targetBuilder.sendToAll;
        this.extId = targetBuilder.extId;
        this.deviceToken = targetBuilder.deviceToken;
        this.profile = targetBuilder.profile;
    }

    public static class TargetBuilder {
        protected Location location;
        protected boolean sendToAll;
        protected String extId;
        protected String deviceToken;
        protected Map<String, List<Object>> profile;

        public TargetBuilder location(Location location) {
            this.location = location;
            return this;
        }

        public TargetBuilder sendToAll(boolean sendToAll) {
            this.sendToAll = sendToAll;
            return this;
        }

        public TargetBuilder externalId(String externalId) {
            this.extId = externalId;
            return this;
        }

        public TargetBuilder derviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
            return this;
        }

        public TargetBuilder profile(Map<String, List<Object>> profile) {
            this.profile = profile;
            return this;
        }

        public TargetBuilder addProfile(String attributeName, List<Object> profile) {
            if (this.profile == null) {
                this.profile = new HashMap<>(1);
            }
            this.profile.put(attributeName, profile);
            return this;
        }

        public Target build() {
            return new Target(this);
        }
    }
}
