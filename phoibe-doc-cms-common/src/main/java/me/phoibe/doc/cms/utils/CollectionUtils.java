package me.phoibe.doc.cms.utils;

import java.util.Collection;
import java.util.Map;


public class CollectionUtils {

	public static boolean isNotEmpty(Collection collection) {
		if (collection == null) {
			return false;
		}
		if (collection.isEmpty()) {
			return false;
		}

		if (collection.size() <= 0) {
			return false;
		}
		return true;
	}

	public static boolean isNotEmpty(Map map) {
		if (map == null) {
			return false;
		}
		if (map.isEmpty()) {
			return false;
		}

		if (map.size() <= 0) {
			return false;
		}
		return true;
	}

	public static boolean isNumeric00(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			System.out.println("异常：\"" + str + "\"不是数字/整数...");
			return false;
		}
	}

}
