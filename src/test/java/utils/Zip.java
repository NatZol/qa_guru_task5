
package utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Zip {
    public static void unzip(String path, String unzipPath, String password) throws ZipException {

        ZipFile zipFile = new ZipFile(path);
        if (zipFile.isEncrypted()) {
            zipFile.setPassword(password);
        }
        zipFile.extractAll(unzipPath);
    }

    public static void unzip(String path, String unzipPath) throws ZipException {
        unzip(path, unzipPath, "");
        System.out.println("test1");
        System.out.println("test2");
        System.out.println("test3");
    }
}