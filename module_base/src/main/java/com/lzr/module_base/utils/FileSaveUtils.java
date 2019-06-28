package com.lzr.module_base.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.List;

/**
 * Created by Administrator on 2018/3/8.
 */

public class FileSaveUtils<T> {

    private static final String FILE_DIR = "/com.ifeimo.audiorecord/lyds";

    private FileSaveUtils() {

    }

    public static class FileUtilsInstance {
        public static final FileSaveUtils INSTANCE = new FileSaveUtils();
    }

    public static FileSaveUtils getInstance() {
        return FileUtilsInstance.INSTANCE;
    }

    /**
     * 将对象保存到本地(删除app删除内容)
     *
     * @param context
     * @param fileName 文件名
     * @param bean     对象
     * @return true 保存成功
     */
    public boolean writeObjectIntoLocal(Context context, String fileName, T bean) {
        try {
            // 通过openFileOutput方法得到一个输出流，方法参数为创建的文件名（不能有斜杠），操作模式
            @SuppressWarnings("deprecation")
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bean);//写入
            fos.close();//关闭输入流
            oos.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LogUtil.e(e.getMessage());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.e(e.getMessage());
            return false;
        }
    }

    /**
     * 将对象写入sd卡(删除app不会删除保存内容)
     *
     * @param fileName 文件名
     * @param bean     对象
     * @return true 保存成功
     */
    public boolean writObjectIntoSDcard(String fileName, T bean) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sdCardDir = Environment.getExternalStorageDirectory();//获取sd卡目录
            File sdFile = new File(sdCardDir, fileName);
            try {
                FileOutputStream fos = new FileOutputStream(sdFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(bean);//写入
                fos.close();
                oos.close();
                return true;
            } catch (FileNotFoundException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 将集合写入sd卡
     *
     * @param fileName 文件名
     * @param list     集合
     * @return true 保存成功
     */
    public boolean writeListIntoSDcard(String fileName, List<T> list) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sdCardDir = Environment.getExternalStorageDirectory();//获取sd卡目录
            File myDir = new File(sdCardDir + FILE_DIR);

            if (!myDir.exists()) {
                myDir.mkdirs();
            }
            File sdFile = new File(myDir, fileName);
            try {
                FileOutputStream fos = new FileOutputStream(sdFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(list);//写入
                fos.close();
                oos.close();
                return true;
            } catch (FileNotFoundException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 保存list到指定文件夹(删除APP删除内容)
     *
     * @param context  上下文
     * @param fileName 文件名
     * @param list     集合
     * @return
     */
    public boolean writeListIntoDataDir(Context context, String fileName, List<T> list) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File localDir = context.getFilesDir();
            File sdFile = new File(localDir, fileName);
            try {
                FileOutputStream fos = new FileOutputStream(sdFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(list);//写入
                fos.close();
                oos.close();
                return true;
            } catch (FileNotFoundException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }

    }


    /**
     * 读取本地对象
     *
     * @param context
     * @param fielName 文件名
     * @return
     */
    @SuppressWarnings("unchecked")
    public T readObjectFromLocal(Context context, String fielName) {
        T bean;
        try {
            FileInputStream fis = context.openFileInput(fielName);//获得输入流
            ObjectInputStream ois = new ObjectInputStream(fis);
            bean = (T) ois.readObject();
            fis.close();
            ois.close();
            return bean;
        } catch (StreamCorruptedException e) {
            LogUtil.e(e.getMessage());
            e.printStackTrace();
            return null;
        } catch (OptionalDataException e) {
            LogUtil.e(e.getMessage());
            e.printStackTrace();
            return null;
        } catch (FileNotFoundException e) {
            LogUtil.e(e.getMessage());
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            LogUtil.e(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 读取sd卡对象
     *
     * @param fileName 文件名
     * @return
     */
    @SuppressWarnings("unchecked")
    public T readObjectFromSdCard(String fileName) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {  //检测sd卡是否存在
            T bean;
            File sdCardDir = Environment.getExternalStorageDirectory();
            File sdFile = new File(sdCardDir, fileName);
            try {
                FileInputStream fis = new FileInputStream(sdFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                bean = (T) ois.readObject();
                fis.close();
                ois.close();
                return bean;
            } catch (StreamCorruptedException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return null;
            } catch (OptionalDataException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return null;
            } catch (FileNotFoundException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return null;
            } catch (ClassNotFoundException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 读取sd卡对象
     *
     * @param fileName 文件名
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> readListFromSdCard(String fileName) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {  //检测sd卡是否存在
            List<T> list;
            File sdCardDir = Environment.getExternalStorageDirectory();
            File myDir = new File(sdCardDir + FILE_DIR);
            if (!myDir.exists()) {
                myDir.mkdirs();
            }
            File sdFile = new File(myDir, fileName);
            try {
                FileInputStream fis = new FileInputStream(sdFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                list = (List<T>) ois.readObject();
                fis.close();
                ois.close();
                return list;
            } catch (StreamCorruptedException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return null;
            } catch (OptionalDataException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return null;
            } catch (FileNotFoundException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return null;
            } catch (ClassNotFoundException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> readListFromDataDir(Context context, String fileName) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {  //检测sd卡是否存在
            List<T> list;
            File localDir = context.getFilesDir();
            File sdFile = new File(localDir, fileName);
            if (!sdFile.exists()) {
                sdFile.mkdirs();
            }
            try {
                FileInputStream fis = new FileInputStream(sdFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                list = (List<T>) ois.readObject();
                fis.close();
                ois.close();
                return list;
            } catch (StreamCorruptedException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return null;
            } catch (OptionalDataException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return null;
            } catch (FileNotFoundException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return null;
            } catch (ClassNotFoundException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }


    /**
     * 从assets目录中复制整个文件夹内容
     *
     * @param context Context 使用CopyFiles类的Activity
     * @param oldPath String  原文件路径  如：/aa
     * @param newPath String  复制后路径  如：xx:/bb/cc
     */
    public String copyFilesFassets(Context context, String oldPath, String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);//获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {//如果是目录
                File file = new File(newPath);
                file.mkdirs();//如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesFassets(context, oldPath + "/" + fileName, newPath + "/" + fileName);
                }
            } else {//如果是文件
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {//循环从输入流读取 buffer字节
                    fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                }
                fos.flush();//刷新缓冲区
                is.close();
                fos.close();
            }
            return newPath;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //如果捕捉到错误则通知UI线程
        }
        return null;
    }

}
