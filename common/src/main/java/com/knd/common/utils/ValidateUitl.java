package com.knd.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidateUitl {
    //判断手机号码的正则表达式
//    private static final String MOBILE_NUM_REGEX = "^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9]))\\d{8}$";
    private static final String MOBILE_NUM_REGEX = "^(1)\\d{10}$";

    /**
     * 验证一个号码是不是手机号
     *
     * @param mobileNumber
     */
    public static boolean validateMobileNumber(String mobileNumber) {
        Pattern p = Pattern.compile(MOBILE_NUM_REGEX);
        Matcher m = p.matcher(mobileNumber);
        return m.matches();
    }

    /**
     * 验证密码，长度大于6位，必须包含字母数字
     * @param password
     * @return
     */
    public static boolean validatePassword(String password){
        if(password.matches("^[A-Za-z0-9]{6,20}$")){
            Pattern numPatter=Pattern.compile("[0-9]+");
            Pattern lowerPatter=Pattern.compile("[a-z]+");
            Pattern upperPatter=Pattern.compile("[A-Z]+");
            if(numPatter.matcher(password).find()&&(lowerPatter.matcher(password).find()
                    ||upperPatter.matcher(password).find())){
                return true;
            }
        }
        return false;
    }

    public static String isContainsChinest(String str){
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                Matcher m = p.matcher(str);
                if (m.find()) {
                  return  m.group();
                }
                 return "";
    }
}
