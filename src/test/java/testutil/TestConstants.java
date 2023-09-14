package testutil;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestConstants {
    public static final String ZIP_DIR = "src/test/resources/ziptest";
    public static final String ZIP_NAME = "sample-feed.zip";
    public static final String EMPTY_DIR = "src/test/resources/parsertest/empty";
    public static final String FEED_DIR = "src/test/resources/parsertest/sample-feed";
}
