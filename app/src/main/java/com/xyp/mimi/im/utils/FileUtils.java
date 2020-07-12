package com.xyp.mimi.im.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

import com.xyp.mimi.app.base.BaseApp;
import com.xyp.mimi.im.common.LogTag;
import com.xyp.mimi.im.utils.log.SLog;

public class FileUtils {
    public static String saveBitmapToFile(Bitmap bitmap, File toFile) {
        try {
            FileOutputStream fos = new FileOutputStream(toFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

            fos.flush();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        SLog.e(LogTag.FILE, "save image to path:" + toFile.getPath());

        return toFile.getPath();
    }

    public static String saveBitmapToPublicPictures(Bitmap bitmap, String fileName) {
        File saveFileDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!saveFileDirectory.exists()) {
            saveFileDirectory.mkdirs();
        }

        File saveFile = new File(saveFileDirectory, fileName);
        return saveBitmapToFile(bitmap, saveFile);
    }

    public static String saveBitmapToCache(Bitmap bitmap, String fileName) {
        File saveFileDirectory = BaseApp.getInstance().getExternalCacheDir();
        if(saveFileDirectory == null){
            saveFileDirectory = BaseApp.getInstance().getCacheDir();
        }
        if (!saveFileDirectory.exists()) {
            saveFileDirectory.mkdirs();
        }

        File saveFile = new File(saveFileDirectory, fileName);
        return saveBitmapToFile(bitmap, saveFile);
    }
}
