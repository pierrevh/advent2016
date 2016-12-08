package org.pvhees.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputReader {

    private InputReader() {
    }

    public static List<String> filenameToList(String filenaam) throws IOException {
        return fileToList(Paths.get(filenaam));
    }

    public static Path filenameToPath(String filenaam) {
        return Paths.get(filenaam);
    }

    public static List<String> fileToList(Path path) throws IOException {
        List<String> result = null;

        try (Stream<String> stream = Files.lines(path)) {
            result = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
