package com.krabelard.model.enums;

public interface Parsable<E extends Enum<E> & Parsable<E, V>, V> {
    V value();
}
