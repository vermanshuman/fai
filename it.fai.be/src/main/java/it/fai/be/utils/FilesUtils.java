package it.fai.be.utils;

import it.fai.be.constant.ValueConstant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class FilesUtils implements Serializable {

    private static final long serialVersionUID = -2531936873713255834L;

    private FilesUtils() {
        throw new UnsupportedOperationException();
    }
    /**
     * It writes the string content in a file, through a base64 encoding format.
     */
    public static void writeBase64File(String path, String fileContent) throws Exception{
        String directory = getFileDirectory(path);
            Files.createDirectories(Paths.get(directory));
            FileOutputStream fos = new FileOutputStream(path);
            byte[] decoder = Base64.getDecoder().decode(fileContent.getBytes());
            fos.write(decoder);
            fos.flush();
            fos.close();
    }

    public static String getFileDirectory(String path) {
        return path.substring(0, path.lastIndexOf(ValueConstant.BSLASH));
    }
}
