package com.knd.common.utils;

public class StringUtils {
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || str.toUpperCase().equals("NULL");
    }

    /**
     * 字符串转int
     * @param str
     * @return 空或者不是数字类型 返回-1
     */
    public static int toInt(String str) {
        return toInt(str,-1);
    }

    public static int toInt(String str,int def) {
        if (isEmpty(str)) return def;
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static long toLong(String str){
        return toLong(str,-1);
    }

    public static long toLong(String str,long def){
        if (isEmpty(str)) return def;
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return def;
        }
    }


    public static float toFloat(String str) {
        return toFloat(str,0);
    }

    public static float toFloat(String str ,float def) {
        if (isEmpty(str)) return def;
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    //如果有中文，就返回第一个中文
    //没有中文就取前两个字符
    public static String getAbbreviation(String str){
        if(StringUtils.isEmpty(str)) return "";
        String result=ValidateUitl.isContainsChinest(str);
        if(StringUtils.isEmpty(result)){
            if(str.length()<=1){
                return str.toUpperCase();
            }else{
                 return str.substring(0,2).toUpperCase();
            }
        }else{
            return result;
        }
    }
}
