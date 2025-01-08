package com.suzintech.domain;

public class Type {

    public enum ActivityType {
        REVENUE("revenue"),
        EXPENSE("expense");

        private String value;

        ActivityType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
