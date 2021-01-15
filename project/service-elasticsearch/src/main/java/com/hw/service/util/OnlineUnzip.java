package com.hw.service.util;

import com.github.junrar.Archive;
import com.github.junrar.VolumeManager;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Date: 2021/1/6
 * @author WX964987
 */
public class OnlineUnzip {

    public static void unzipForZip(String filePath, String dirPath) throws IOException {
        ZipFile zipFile = new ZipFile(new File(filePath));
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();

            File file = new File(dirPath);
            if (!file.exists()) {
                boolean mkdirs = file.mkdirs();
            }

            File f = new File(dirPath + "\\" + zipEntry.getName());
            if (zipEntry.isDirectory()) {
                if (!f.exists()) {
                    boolean mkdirs = f.mkdirs();
                }
            } else {
                if (!f.exists()) {
                    boolean newFile = f.createNewFile();
                }
                InputStream is = zipFile.getInputStream(zipEntry);
                OutputStream os = new FileOutputStream(f);
                byte[] buf = new byte[2048];
                int len;
                while ((len = is.read(buf)) != -1) {
                    os.write(buf, 0, len);
                }
                os.close();
                is.close();
            }
        }
    }

    public static void unzipForRar(String filePath) throws IOException, RarException {
        File outFileDir = new File("d:\\zf\\");
        if (!outFileDir.exists()) {
            boolean isMakDir = outFileDir.mkdirs();
            if (isMakDir) {
                System.out.println("创建压缩目录成功");
            }
        }
        Archive archive = new Archive((VolumeManager) new FileInputStream(new File(filePath)));
        FileHeader fileHeader = archive.nextFileHeader();
        while (fileHeader != null) {
            if (fileHeader.isDirectory()) {
                fileHeader = archive.nextFileHeader();
                continue;
            }
            File out = new File("d:\\zf\\" + fileHeader.getFileNameString());
            if (!out.exists()) {
                if (!out.getParentFile().exists()) {
                    boolean mkdirs = out.getParentFile().mkdirs();
                }
                boolean newFile = out.createNewFile();
            }
            FileOutputStream os = new FileOutputStream(out);
            archive.extractFile(fileHeader, os);

            os.close();

            fileHeader = archive.nextFileHeader();
        }
        archive.close();
    }

    public static void main(String[] args) throws IOException {
        unzipForZip("D:\\zip\\ideaIC-2020.1.3.zip",
            "D:\\zip\\" + UUID.randomUUID().toString().substring(0,5));
    }
}
