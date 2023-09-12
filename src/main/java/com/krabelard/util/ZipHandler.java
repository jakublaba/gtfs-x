package com.krabelard.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipInputStream;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ZipHandler {
    /**
     * Unzips the specified <code>.zip</code> archive into the same directory it's in.
     *
     * @param zip Fully qualified name of the <code>.zip</code> archive
     * @throws FileNotFoundException When <code>.zip</code> archive cannot be found
     * @throws IOException           Thrown from {@link FileInputStream} constructor
     */
    public static void unzip(String zip) throws IOException {
        try (
                var fileInputStream = new FileInputStream(zip);
                var zipInputStream = new ZipInputStream(fileInputStream)
        ) {
            var destDir = Paths.get(zip).getParent();
            log.info("Unzipping .zip archive to {}", destDir);

            var zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                var buffer = new byte[(int) zipEntry.getSize()];
                var inflatedFile = new File(String.format("%s".repeat(3), destDir, File.separator, zipEntry.getName()));
                log.info("Inflating {}", inflatedFile.getAbsoluteFile());
                try (var outputStream = new FileOutputStream(inflatedFile)) {
                    int len;
                    while ((len = zipInputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, len);
                    }
                    zipInputStream.closeEntry();
                    zipEntry = zipInputStream.getNextEntry();
                }
            }
            zipInputStream.closeEntry();
        }
    }

    /**
     * Deletes all files with specified extension in the specified directory.
     *
     * @param dir       Fully qualified name of the directory to clean up
     * @param extension File extension to look for during cleanup
     * @throws IOException When something unexpected happens during clean-up
     *                     (rethrown from {@link SimpleFileVisitor#visitFile(Object, BasicFileAttributes)})
     */
    public static void cleanUp(String dir, String extension) throws IOException {
        Files.walkFileTree(
                Path.of(dir),
                new SimpleFileVisitor<>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if (file.getFileName().toString().endsWith(extension)) {
                            Files.delete(file);
                            log.info("Deleted file: {}", file);
                        }
                        return FileVisitResult.CONTINUE;
                    }
                }
        );
    }
}
