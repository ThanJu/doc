package me.phoibe.doc.cms.utils;

import java.text.DateFormat;
import java.text.spi.DateFormatProvider;
import java.util.Date;

/**
 * 日期处理工具类
 *
 * @author tengzhaolei
 * @Title: JsonUtils
 * @Description: class
 * @date 2018/8/23 1:13
 */
public class DateUtils {

    private static DateFormat dateFormat = DateFormat.getDateInstance();
    public static String formatDate(Date date, String pattern){
        return dateFormat.format(date);
    }

}
