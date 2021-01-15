package com.hw.text;

import lombok.SneakyThrows;
import org.apache.http.util.TextUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 文件夹分片与合并
 * Date: 2020/12/11
 * @author WX964987
 */
public class MergeFileTest {

    @Test
    @SneakyThrows
    public void test2() {

        File sourceFile = new File("C:\\Users\\Administrator\\Desktop\\swx999666\\995MB.zip");
        String chunkPath = "C:\\Users\\Administrator\\Desktop\\swx999666\\sk12\\";
        File chunkFolder = new File(chunkPath);
        if (!chunkFolder.exists()) {
            chunkFolder.mkdirs();
        }
        // 分块大小
        long chunkSize = 1024 * 1024 * 10;
        // 分块数量
        long chunkNum = (long) Math.ceil(sourceFile.length() * 1.0 / chunkSize);
        if (chunkNum <= 0) {
            chunkNum = 1;
        }
        // 缓冲区大小
        byte[] b = new byte[1024];
        RandomAccessFile raf_read = new RandomAccessFile(sourceFile, "r");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < chunkNum; i++) {
            // 创建分块文件
            File file = new File(chunkPath + i);
            boolean newFile = file.createNewFile();
            if (newFile) {
                // 向分块文件中写数据
                RandomAccessFile raf_write = new RandomAccessFile(file, "rw");
                int len = -1;
                while ((len = raf_read.read(b)) != -1) {
                    raf_write.write(b, 0, len);
                    if (file.length() > chunkSize) {
                        break;
                    }
                }
                raf_write.close();
            }
        }
        raf_read.close();
        long endTime = System.currentTimeMillis();
        System.out.println("分片消耗的时间为:" + (endTime - startTime) / 1000 + "m");
    }


    @Test
    @SneakyThrows
    public void test3() {
        // 分块文件目录
        File chunkFolder = new File("C:\\Users\\Administrator\\Desktop\\swx999666\\sk12\\");
        // 合并文件
        File mergeFile = new File("C:\\Users\\Administrator\\Desktop\\swx999666\\686\\995MB.zip");
        if (mergeFile.exists()) {
            mergeFile.delete();
        }
        // 创建新的合并文件
        mergeFile.createNewFile();
        RandomAccessFile raf_write = new RandomAccessFile(mergeFile, "rw");
        // 指针指向文件顶端
        raf_write.seek(0);
        // 缓冲区
        byte [] b = new byte[1024];
        // 分块列表
        File[] filesArray = chunkFolder.listFiles();

        long startTime = System.currentTimeMillis();
        // 转为集合
        List<File> fileList = new ArrayList<>(Arrays.asList(filesArray));
        // 从小到大排序
        fileList.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())) {
                    return -1;
                }
                return 1;
            }
        });
        // 合并文件
        for (File chunkFile : fileList) {
            RandomAccessFile raf_read = new RandomAccessFile(chunkFile,"rw");
            int len = -1;
            while ((len = raf_read.read(b)) != -1) {
                raf_write.write(b, 0, len);
            }
            raf_read.close();
        }
        raf_write.close();
        long endTime = System.currentTimeMillis();
        System.out.println("合并的消耗的时间为:" + (endTime - startTime) / 1000 + "m");
    }


    public static boolean mergeFiles(String[] fpaths, String resultPath) {
        if (fpaths == null || fpaths.length < 1 || TextUtils.isEmpty(resultPath)) {
            return false;
        }
        if (fpaths.length == 1) {
            return new File(fpaths[0]).renameTo(new File(resultPath));
        }

        File[] files = new File[fpaths.length];
        for (int i = 0; i < fpaths.length; i ++) {
            files[i] = new File(fpaths[i]);
            if (TextUtils.isEmpty(fpaths[i]) || !files[i].exists() || !files[i].isFile()) {
                return false;
            }
        }

        File resultFile = new File(resultPath);

        try {
            FileChannel resultFileChannel = new FileOutputStream(resultFile, true).getChannel();
            for (int i = 0; i < fpaths.length; i ++) {
                FileChannel blk = new FileInputStream(files[i]).getChannel();
                resultFileChannel.transferFrom(blk, resultFileChannel.size(), blk.size());
                blk.close();
            }
            resultFileChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        for (int i = 0; i < fpaths.length; i ++) {
            files[i].delete();
        }

        return true;
    }


}
