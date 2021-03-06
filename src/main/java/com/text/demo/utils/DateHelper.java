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
     * ?????????????????????
     * ???????????????????????? addDayBetter()
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
     * ????????????????????????
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
     * ??????????????????????????????
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
     * Unix????????? ???date
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
     * ????????????????????????????????????
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
     * ???????????????????????????????????????????????????
     *
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // hour??????????????????????????????????????? 1~7
        // 1=????????? 7=????????????????????????
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    /**
     * ???????????????zh???
     *
     * @param date
     * @return
     */
    public static String getWeekStr(Date date) {
        String str = "";
        str = getWeek(date);
        if ("1".equals(str)) {
            str = "?????????";
        } else if ("2".equals(str)) {
            str = "?????????";
        } else if ("3".equals(str)) {
            str = "?????????";
        } else if ("4".equals(str)) {
            str = "?????????";
        } else if ("5".equals(str)) {
            str = "?????????";
        } else if ("6".equals(str)) {
            str = "?????????";
        } else if ("7".equals(str)) {
            str = "?????????";
        }
        return str;
    }

    /**
     * ???????????????????????????date1???????????????1???date2???????????????-1??????????????????0
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
            // ??????????????????
            cl.add(Calendar.HOUR_OF_DAY, diff);

        } else if ("next".equals(option)) {
            // ??????????????????
            cl.add(Calendar.HOUR_OF_DAY, diff);
        } else {
            // do nothing
        }
        return cl.getTime();
    }
}