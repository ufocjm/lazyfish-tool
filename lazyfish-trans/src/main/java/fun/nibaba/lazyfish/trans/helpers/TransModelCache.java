package fun.nibaba.lazyfish.trans.helpers;

import com.google.common.collect.Maps;
import fun.nibaba.lazyfish.trans.fields.TransModel;

import java.util.Map;

public class TransModelCache {

    public static Map<Class<?>, TransModel> CACHE = Maps.newHashMap();

    public static void put(Class<?> clazz, TransModel transModel) {
        CACHE.put(clazz, transModel);
    }

    public static TransModel get(Class<?> clazz) {
        return CACHE.get(clazz);
    }

    public static boolean containsKey(Class<?> clazz) {
        return CACHE.containsKey(clazz);
    }


}
