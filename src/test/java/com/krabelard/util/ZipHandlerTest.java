package com.krabelard.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class ZipHandlerTest {
    @AfterAll
    @SneakyThrows
    static void cleanUp() {
        ZipHandler.cleanUp(TestConstants.ZIP_DIR, ".txt");
    }

    @Nested
    class Unzip {
        @Test
        @SneakyThrows
        void should_unZIPArchive_whenItExists() {
            var zip = TestConstants.ZIP_DIR + "/" + TestConstants.ZIP_NAME;
            assertDoesNotThrow(() -> ZipHandler.unzip(zip));

            var expectedFiles = Set.of(
                    "agency.txt",
                    "attributions.txt",
                    "calendar.txt",
                    "calendar_dates.txt",
                    "fare_attributes.txt",
                    "fare_rules.txt",
                    "frequencies.txt",
                    "levels.txt",
                    "pathways.txt",
                    "routes.txt",
                    "shapes.txt",
                    "stop_times.txt",
                    "stops.txt",
                    "transfers.txt",
                    "translations.txt",
                    "trips.txt"
            );
            Files.walkFileTree(
                    Path.of(TestConstants.ZIP_DIR),
                    new SimpleFileVisitor<>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                            log.info(file.toString());
                            if (file.endsWith(".txt")) {
                                assertTrue(expectedFiles.contains(file.getFileName().toString()));
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    }
            );
        }
    }

    @Nested
    class CleanUp {
        @Test
        @SneakyThrows
        void should_removeFiles() {
            assertDoesNotThrow(() -> ZipHandler.cleanUp(TestConstants.ZIP_DIR, ".txt"));
            Files.walkFileTree(
                    Path.of(TestConstants.ZIP_DIR),
                    new SimpleFileVisitor<>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                            assertFalse(file.endsWith(".txt"));
                            return FileVisitResult.CONTINUE;
                        }
                    }
            );
        }
    }
}