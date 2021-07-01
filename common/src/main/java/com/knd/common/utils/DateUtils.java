package com.knd.common.utils;

import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import com.binaryfork.spanny.Spanny;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    //将秒数转换成 00:00:00 时分秒格式
    public static String formateSeconds(int seconds){
        String hourStr="00";
        String minStr="00";
        String secondStr="00";
        if(seconds>=3600){
            int hour=seconds/3600;
            if(hour<10){
                hourStr="0"+hour;
            }else{
                hourStr=hour+"";
            }
            seconds=seconds-hour*3600;
        }
        if(seconds>=60){
            int min=seconds/60;
            if(min<10){
                minStr="0"+min;
            }else{
                minStr=min+"";
            }
            seconds=seconds-min*60;
        }
        if(seconds<10){
            secondStr="0"+seconds;
        }else{
            secondStr=seconds+"";
        }
        return hourStr+":"+minStr+":"+secondStr;

    }

    //将秒数转成00:00
    public static String format2Seconds(int seconds){
        String hourStr="00";
        String minStr="00";
        String secondStr="00";
        if(seconds>=3600){
            int hour=seconds/3600;
            if(hour<10){
                hourStr="0"+hour;
            }else{
                hourStr=hour+"";
            }
            seconds=seconds-hour*3600;
        }
        if(seconds>=60){
            int min=seconds/60;
            if(min<10){
                minStr="0"+min;
            }else{
                minStr=min+"";
            }
            seconds=seconds-min*60;
        }
        if(seconds<10){
            secondStr="0"+seconds;
        }else{
            secondStr=seconds+"";
        }
        return minStr+":"+secondStr;

    }

    public static String now(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 将秒数转化为 10min 10s
     * @param seconds
     * @return
     */
    public static Spanny formateSeconds(String seconds){
       int sec=  StringUtils.toInt(seconds);
        String hourStr="00";
        String minStr="00";
        String secondStr="00";
        Spanny spanny=new Spanny();
        if(sec==0) return spanny.append(""+sec).append("s",new RelativeSizeSpan(0.75f),new ForegroundColorSpan(0xFF6E6E6E));
        if(sec>=3600){
            int hour=sec/3600;
            spanny.append(""+hour).append("h",new RelativeSizeSpan(0.75f),new ForegroundColorSpan(0xFF6E6E6E));
            sec=sec-hour*3600;
        }
        if(sec>=60){
            int min=sec/60;
            spanny.append(""+min).append("min",new RelativeSizeSpan(0.75f),new ForegroundColorSpan(0xFF6E6E6E));
            sec=sec-min*60;
        }
        if(sec>0){
            spanny.append(""+sec).append("s",new RelativeSizeSpan(0.75f),new ForegroundColorSpan(0xFF6E6E6E));
        }
        return spanny;
    }

    public static Spanny formateSecondsToChines(String seconds){
        long sec=  StringUtils.toLong(seconds);
        if(sec>=100L*365*24*60*60){
            return new Spanny("神之毅力");
        }else if(sec>=365*24*60*60){
            //大于1年
            Spanny spanny=new Spanny();
            int year= (int) (sec/(60*60*24*365));
            spanny.append(year+"").append("年",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
            sec=sec-year*60*60*24*365;
            int month= (int) (sec/(24*60*60*30));
            if(month>0){
                spanny.append(month+"").append("个月",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
            }
            sec=sec-month*30*24*60*60;
            int day= (int) (sec/(24*60*60));
            if(day>0){
                spanny.append(day+"").append("天",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
            }
            return spanny;

        }else if(sec>=30*24*60*60){
            //大于1月
            Spanny spanny=new Spanny();
            int month= (int) (sec/(60*60*24*30));
            spanny.append(month+"").append("个月",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
            sec=sec-month*60*60*24*30;
            int day= (int) (sec/(24*60*60));
            if(day>0){
                spanny.append(day+"").append("天",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
            }
            sec=sec-day*24*60*60;
            int hour= (int) (sec/3600);
            if(hour>0){
                spanny.append(hour+"").append("小时",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
            }
            return spanny;



        }else if(sec>=24*60*60){
            //大于1天
            Spanny spanny=new Spanny();
            int days= (int) (sec/(60*60*24));
            spanny.append(days+"").append("天",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
            sec=sec-days*60*60*24;
            int hour= (int) (sec/(60*60));
            if(hour>0){
                spanny.append(hour+"").append("小时",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
            }
            sec=sec-hour*60*60;
            int s= (int) (sec/60);
            if(s>0){
                spanny.append(s+"").append("分",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
            }
            return spanny;

        }else{
            //x小时x分
            Spanny spanny=new Spanny();
            int hour= (int) (sec/(60*60));
            if(hour>0){
                spanny.append(hour+"").append("小时",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
            }
            int s= (int) ((sec-hour*60*60)/60);
            if(s>0){
                spanny.append(s+"").append("分",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
            }
            if(spanny.length()==0){
                spanny.append(seconds+"").append("秒",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
            }
            return spanny;
        }
    }

    public static Spanny formateWeightToChines(String strWeight){
        long weight=  StringUtils.toLong(strWeight);
        if(weight<=999){
            return new Spanny(weight+"").append("公斤",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
        }else if(weight<=999.99*1000){
            DecimalFormat decimalFormat=new DecimalFormat(".00");
            float data= (float) (weight*1.0/1000);
            return new Spanny(decimalFormat.format(data)).append("吨",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
        }else if(weight<=9.99*1000*1000){
            DecimalFormat decimalFormat=new DecimalFormat(".00");
            float data= (float) (weight*1.0/1000/1000);
            return new Spanny(decimalFormat.format(data)).append("千吨",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));

        }else if(weight<=999.99*10000*1000){
            DecimalFormat decimalFormat=new DecimalFormat(".00");
            float data= (float) (weight*1.0/10000/1000);
            return new Spanny(decimalFormat.format(data)).append("万吨",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
        }else if(weight<=999.99*1000*10000*1000){
            DecimalFormat decimalFormat=new DecimalFormat(".00");
            float data= (float) (weight*1.0/1000/10000/1000);
            return new Spanny(decimalFormat.format(data)).append("千万吨",new RelativeSizeSpan(0.5f),new ForegroundColorSpan(0xFF6E6E6E));
        }else{
            return new Spanny("神之力量");
        }
    }



    /**
     * 将秒数转化为 分钟
     * @param seconds
     * @return
     */
    public static Spanny formateSecondsToMinuts(String seconds){
        int data=  StringUtils.toInt(seconds);
        if(data<0) return new Spanny();

        Spanny spanny;
        int miniuts= (int) Math.ceil(data*1.0f/60);
        spanny=new Spanny(miniuts+"").append("分钟",new RelativeSizeSpan(0.75f),new ForegroundColorSpan(0xFF6E6E6E));

        return spanny;
    }
}
