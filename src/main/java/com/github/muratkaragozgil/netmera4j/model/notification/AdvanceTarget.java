package com.github.muratkaragozgil.netmera4j.model.notification;

import com.github.muratkaragozgil.netmera4j.constant.TargetCondition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
public class AdvanceTarget extends Target {
    private Map<TargetCondition, List<String>> tag;
    private Map<TargetCondition, List<String>> segment;

    private AdvanceTarget(AdvanceTargetBuilder advanceTargetBuilder) {
        super(advanceTargetBuilder);
        this.tag = advanceTargetBuilder.tag;
        this.segment = advanceTargetBuilder.segment;
    }

    public static class AdvanceTargetBuilder extends TargetBuilder {
        private Map<TargetCondition, List<String>> tag;
        private Map<TargetCondition, List<String>> segment;

        public AdvanceTargetBuilder addTag(TargetCondition condition, List<String> tag) {
            if (this.tag == null) {
                this.tag = new HashMap<>(1);
            }
            this.tag.put(condition, tag);
            return this;
        }

        public AdvanceTargetBuilder addSegment(TargetCondition condition, List<String> segment) {
            if (this.segment == null) {
                this.segment = new HashMap<>(1);
            }
            this.segment.put(condition, segment);
            return this;
        }

        public AdvanceTarget build() {
            return new AdvanceTarget(this);
        }
    }
}
