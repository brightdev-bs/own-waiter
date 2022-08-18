package vanilla.ownwaiter.common.utils;

import java.io.File;

public class FileUtils {

    public static String getFileExtension(File file) {
        String fileName = file.getName();
        String extension = file.getName().substring(fileName.lastIndexOf(".") + 1);
        return extension;
    }
}
