package com.krabelard.model.enums;

/**
 * Interface meant to be implemented by enums with a single underlying value, to allow them to be used by {@link com.krabelard.util.CsvUtil#parseEnum(Class, V)}
 *
 * @param <V> Type of underlying value in the implementing enum
 */
public interface Parsable<V> {
    V value();
}
