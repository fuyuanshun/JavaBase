package com.fys.util;

public class Entity {
    public Object key;
    public Object value;
    public Entity(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "[key=" + key + ", value=" + value + "]";
    }
}