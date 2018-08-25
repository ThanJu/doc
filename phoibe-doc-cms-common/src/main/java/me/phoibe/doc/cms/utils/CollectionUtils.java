package me.phoibe.doc.cms.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author tengzhaolei
 * @Title: CollectionUtils
 * @Description: class
 * @date 2018/8/25 0:52
 */
public class CollectionUtils {

    public static boolean isNotEmpty(Collection collection){
        if (collection == null){
            return false;
        }
        if (collection.isEmpty()){
            return false;
        }

        if (collection.size() <= 0){
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(Map map){
        if (map == null){
            return false;
        }
        if (map.isEmpty()){
            return false;
        }

        if (map.size() <= 0){
            return false;
        }
        return true;
    }




}
