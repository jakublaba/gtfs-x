package com.krabelard.parsers;

import java.util.Collection;

public interface GtfsCsvParser<E> {
    Collection<E> parse() throws GtfsParsingException;
}
