package com.lzr.module_base.utils;


import com.google.gson.Gson;

public class GsonUtil {

    private static Gson mGson = new Gson();

    /**
     * 序列化
     * @param json  json字符串
     * @param classOfT 实体类
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object toObject(String json, Class classOfT) {
        return mGson.fromJson(json, classOfT);
    }

    /**
     * 反序列化
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String toJson(T object) {
        return mGson.toJson(object);
    }
}