package com.rdc.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rdc.bean.Msg;

import java.util.*;

public class GsonUtil {

    private static Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    public static Gson getGson() {
        return gson;
    }

    /**
     * 返回成功
     *
     * @return
     */
    public static String getSuccessJson() {
        return GsonUtil.getGson().toJson(new Msg("success", null));
    }

    /**
     * 返回成功字符串
     *
     * @param o
     * @return
     */
    public static String getSuccessJson(Object o) {
        return GsonUtil.getGson().toJson(new Msg("success", o));
    }

    /**
     * 用自己指定的gson
     *
     * @param gson
     * @param o
     * @return
     */
    public static String getSuccessJson(Gson gson, Object o) {
        return gson.toJson(new Msg("success", o));
    }

    public static String getErrorJson() {
        return GsonUtil.getGson().toJson(new Msg("error", null));
    }

    public static String getErrorJson(Object o) {
        return GsonUtil.getGson().toJson(new Msg("error", o));
    }

    public static String getErrorJson(Object o, String errorMsg) {
        return GsonUtil.getGson().toJson(new Msg(errorMsg, o));
    }

    /**
     * 过滤属性:格式  类、属性、属性、属性...类、属性、属性、属性...类、属性、属性、属性...
     *
     * @param objects
     * @return
     */
    public static Gson getFilterJson(Object... objects) {
        return filter(objects).serializeNulls().setDateFormat("yyyy-MM-dd").create();
    }

    /**
     * 保留传入的属性，其它全部过滤掉
     * 保留属性:格式  类、属性、属性、属性...类、属性、属性、属性...类、属性、属性、属性...
     *
     * @param objects
     * @return
     */
    public static Gson getExistJson(boolean result, Object... objects) {
        return delalProperty(result, objects).serializeNulls().setDateFormat("yyyy-MM-dd").create();
    }

    /**
     * GsonBuilder(以创建的gsonBuilder)
     * 属性:格式  类、属性、属性、属性...类、属性、属性、属性...类、属性、属性、属性...
     *
     * @param gsonBuilder
     * @param objects
     * @return
     */
    private static GsonBuilder delalProperty(GsonBuilder gsonBuilder, boolean result, Object[] objects) {
        Map<Class<?>, Set<String>> map = new HashMap<Class<?>, Set<String>>();
        Class<?> c = null;
        for (Object o : objects) {
            if (o instanceof Class) {
                c = (Class<?>) o;
                map.put(c, new HashSet<String>());
            } else {
                map.get(c).add((String) o);
            }
        }
        return gsonBuilder.setExclusionStrategies(new MapGsonFilter(map, result));
    }

    /**
     * GsonBuilder(以创建的gsonBuilder)
     * 过滤属性:格式  类、属性、属性、属性...类、属性、属性、属性...类、属性、属性、属性...
     *
     * @param gsonBuilder
     * @param objects
     * @return
     */
    public static GsonBuilder filter(GsonBuilder gsonBuilder, Object... objects) {
        return delalProperty(gsonBuilder, true, objects);
    }

    /**
     * 属性:格式  类、属性、属性、属性...类、属性、属性、属性...类、属性、属性、属性...
     *
     * @param objects
     * @return
     */
    private static GsonBuilder delalProperty(boolean result, Object[] objects) {
            Map<Class<?>, Set<String>> map = new HashMap<Class<?>, Set<String>>();
            Class<?> c = null;
            for (Object o : objects) {
            if (o instanceof Class) {
                c = (Class<?>) o;
                map.put(c, new HashSet<String>());
            } else {
                map.get(c).add((String) o);
            }
        }
        if (!result) {
            //存在过滤
            map.put(Msg.class, new HashSet<String>());
            Collections.addAll(map.get(Msg.class), "code", "result", "message");
        }
        return new GsonBuilder().setExclusionStrategies(new MapGsonFilter(map, result));
    }

    /**
     * 过滤属性:格式  类、属性、属性、属性...类、属性、属性、属性...类、属性、属性、属性...
     *
     * @param objects
     * @return
     */
    public static GsonBuilder filter(Object... objects) {
        return delalProperty(true, objects);
    }

    /**
     * gson过滤器内部类
     *
     * @author gmr
     */
    private static class MapGsonFilter implements ExclusionStrategy {
        boolean result = true;
        private Map<Class<?>, Set<String>> map = new HashMap<Class<?>, Set<String>>();

        /**
         * @param map
         * @param result true表示正常过滤，false表示保留这些字段
         */
        private MapGsonFilter(Map<Class<?>, Set<String>> map, boolean result) {
            this.map = map;
            this.result = result;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            Set<String> set = map.get(f.getDeclaringClass());
            if (set != null && set.contains(f.getName())) {
                return result;
            }
            return !result;
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

    }
}
