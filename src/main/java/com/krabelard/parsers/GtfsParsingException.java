package com.krabelard.parsers;

import java.io.IOException;

public class GtfsParsingException extends IOException {
    public GtfsParsingException(String csv, Throwable cause) {
        super("Error parsing " + csv, cause);
    }
}
