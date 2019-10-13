package com.github.muratkaragozgil.netmera4j.model.notification;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Murat Karagözgil
 */
@Getter
@ToString
public class BasicTarget extends Target {
    private List<String> tag;
    private List<String> segment;

    private BasicTarget(BasicTargetBuilder basicTargetBuilder) {
        super(basicTargetBuilder);
        this.tag = basicTargetBuilder.tag;
        this.segment = basicTargetBuilder.segment;
    }

    public static class BasicTargetBuilder extends TargetBuilder {
        private List<String> tag;
        private List<String> segment;

        public BasicTargetBuilder addTag(String tag) {
            if (this.tag == null) {
                this.tag = new ArrayList<>(1);
            }
            this.tag.add(tag);
            return this;
        }

        public BasicTargetBuilder addSegment(String segment) {
            if (this.segment == null) {
                this.segment = new ArrayList<>(1);
            }
            this.segment.add(segment);
            return this;
        }

        public BasicTarget build() {
            return new BasicTarget(this);
        }
    }
}
