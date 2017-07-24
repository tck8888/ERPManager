package com.tck.commonlibrary.utils;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 创建日期：2017-02-14  19:51
 * 描述: Gson操作的封装包
 * 作者: WNN
 * 修改日期:
 */


public class GsonUtil {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private GsonUtil() {
    }

    /**
     * 对象转成json
     *
     * @param object
     * @return
     */
    public static String GsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * json转成bean对象
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    public static <T> String beanToJson(T t) {
        String json;
        if (gson != null) {
            try {
                json = gson.toJson(t);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return json;
        } else {
            return null;
        }
    }

//
//    /**
//     * json转成list
//     *
//     * @param gsonString
//     * @param cls
//     * @return
//     */
//    public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
//        List<T> list = null;
//        if (gson != null) {
//            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
//            }.getType());
//        }
//        return list;
//    }


    /**
     * json转成list
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> ArrayList<T> GsonToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);
        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(gson.fromJson(jsonObject, clazz));
        }
        return arrayList;
    }

    /**
     * json转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * json转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
}