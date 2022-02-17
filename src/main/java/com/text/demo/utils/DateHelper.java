package com.text.demo.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
    private static final SimpleDateFormat STANDARD_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat STANDARD_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat FILE_NAME_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat STANDARD_TIME_FORMAT_NO_SECOND = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    private static final Calendar calendar = Calendar.getInstance();

    public static class Serialize extends JsonSerializer<Date> {
        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (null == date) {
                jsonGenerator.writeNull();
                return;
            }
            jsonGenerator.writeString(simpleDateFormatThreadLocal.get().format(date));
        }
    }

    public static class Deserialize extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            String dateString = jsonParser.getValueAsString();
            if (StringUtils.isEmpty(dateString)) {
                return null;
            }
            try {
                return simpleDateFormatThreadLocal.get().parse(dateString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * String convert to date
     *
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Date targetDate = null;
        try {
            targetDate = STANDARD_DATE_FORMAT.parse(dateStr);
        } catch (Exception ex) {
        }
        return targetDate;
    }

    /**
     * String convert to time
     *
     * @param dateStr
     * @return
     */
    public static Date stringToTime(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Date targetDate = null;
        try {
            targetDate = STANDARD_TIME_FORMAT.parse(dateStr);
        } catch (Exception ex) {
        }
        return targetDate;
    }

    /**
     * Date convert to string
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return "";
        }
        return STANDARD_DATE_FORMAT.format(date);
    }

    /**
     * Date convert to string
     *
     * @param date
     * @return
     */
    public static String timeToString(Date date) {
        if (date == null) {
            return "";
        }
        return STANDARD_TIME_FORMAT.format(date);
    }

    /**
     * Date convert to string
     *
     * @param date
     * @return
     */
    public static String timeToStringNoSecond(Date date) {
        if (date == null) {
            return "";
        }
        return STANDARD_TIME_FORMAT_NO_SECOND.format(date);
    }

    /**
     * String convert to date
     *
     * @param dateStr
     * @param simpleDateFormat
     * @return
     */
    public static Date stringToDate(String dateStr, SimpleDateFormat simpleDateFormat) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Date targetDate = null;
        try {
            targetDate = simpleDateFormat.parse(dateStr);
        } catch (Exception ex) {
        }
        return targetDate;
    }

    /**
     * Date convert to string
     *
     * @param date
     * @param simpleDateFormat
     * @return
     */
    public static String dateToString(Date date, SimpleDateFormat simpleDateFormat) {
        if (date == null) {
            return "";
        }
        return simpleDateFormat.format(date);
    }

    public static String dateToString(Instant instant, DateTimeFormatter dateTimeFormatter) {
        if (instant == null) {
            return "";
        }
        return dateTimeFormatter.format(instant);
    }

    /**
     * 给日期添加天数
     * 线程不安全，参考 addDayBetter()
     * @param date
     * @param num
     * @return
     */
    @Deprecated
    public static Date addDay(Date date, int num) {
        if (null == date) {
            return null;
        }
        calendar.setTime(date);
        calendar.add(Calendar.DATE, num);
        return calendar.getTime();
    }

    public static String addDayBetter(LocalDate localDate, int num) {
        return localDate.plusDays(num).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * 给日期添加分钟数
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addMinute(Date date, int num) {
        if (null == date) {
            return null;
        }
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, num);
        return calendar.getTime();
    }

    /**
     * 获取不带时分秒的日期
     *
     * @param date
     * @return
     */
    public static Date getDate(Date date) {
        if (null == date) {
            return null;
        }
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * Unix时间戳 转date
     *
     * @return
     */
    public static Date getDateFromUnixtime(Long unixTime) {
        if (null == unixTime) {
            return null;
        }
        try {
            return STANDARD_TIME_FORMAT.parse(STANDARD_TIME_FORMAT.format(new Date(unixTime * 1000)));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取日期是一年中的第几周
     *
     * @param date
     */
    public static int getWeekOfYear(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    /**
     * 获取星期（zh）
     *
     * @param date
     * @return
     */
    public static String getWeekStr(Date date) {
        String str = "";
        str = getWeek(date);
        if ("1".equals(str)) {
            str = "星期日";
        } else if ("2".equals(str)) {
            str = "星期一";
        } else if ("3".equals(str)) {
            str = "星期二";
        } else if ("4".equals(str)) {
            str = "星期三";
        } else if ("5".equals(str)) {
            str = "星期四";
        } else if ("6".equals(str)) {
            str = "星期五";
        } else if ("7".equals(str)) {
            str = "星期六";
        }
        return str;
    }

    /**
     * 比较两个日期大小，date1较大时返回1，date2较大时返回-1，相等时返回0
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compare(Date date1, Date date2) {
        long t1 = date1.getTime();
        long t2 = date2.getTime();
        if (t1 > t2) {
            return 1;
        } else {
            if (t1 < t2) {
                return -1;
            }
        }

        return 0;
    }

    public static Date checkOptionTime(String option, Date date,Integer diff) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        if ("pre".equals(option)) {
            // 时间减几小时
            cl.add(Calendar.HOUR_OF_DAY, diff);

        } else if ("next".equals(option)) {
            // 时间加几小时
            cl.add(Calendar.HOUR_OF_DAY, diff);
        } else {
            // do nothing
        }
        return cl.getTime();
    }
}