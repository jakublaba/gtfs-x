package com.krabelard.parsers;

/**
 * Meant to be implemented on enums for representing set of csv file headers.
 * Ensures the implementing enum has a way of extracting its underlying value.
 */
public interface CsvHeaders {
    String getValue();
}
