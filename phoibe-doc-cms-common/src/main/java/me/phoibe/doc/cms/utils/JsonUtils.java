package me.phoibe.doc.cms.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.phoibe.doc.cms.entity.Constant;

/**
 * @author tengzhaolei
 * @Title: JsonUtils
 * @Description: class
 * @date 2018/8/23 1:13
 */
public class JsonUtils {

    private static Gson gson = new Gson();

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(Constant.DEFAULT_DATE_PATTERN);
        gson = gsonBuilder.create();
    }

    public static  String toJson(Object object){
        return gson.toJson(object);
    }

    public static <T> T fromjson(String json, Class<T> clazz){
        return gson.fromJson(json, clazz);
    }

}
