package com.text.demo.constant;


import com.text.demo.model.CustomerException;

import java.util.HashMap;
import java.util.Map;

public class BusinessException {
    public static final int GENERAL_CODE = 0x400;
    public static final int PARAMETER_INVALID = 0x401;
    public static final int ACCOUNT_IS_LOCKED = 0x402;
    public static final int ACCOUNT_NOT_LOGIN = 0x403;
    public static final int ACCOUNT_NOT_EXISTS = 0x404;
    public static final int REMOTE_SERVER_ERROR = 0x410;

    public static final int ACCOUNT_EMPTY = 0x411;
    public static final int ACCOUNT_FORMAT_ERROR = 0x412;
    public static final int ACCOUNT_EXISTS = 0x413;
    public static final int PASSWORD_EMPTY = 0x414;
    public static final int CONFIRM_PASSWORD_EMPTY = 0x415;
    public static final int PASSWORD_NOT_MATCH = 0x416;
    public static final int PASSWORD_LESS_MIN_LENGTH = 0x417;
    public static final int ACCOUNT_OR_PASSWORD_INVALID = 0x418;
    public static final int EXCEED_MAX_LOGIN_TIMES = 0x419;

    public static final int EMAIL_EMPTY = 0x420;
    public static final int EMAIL_FORMAT_ERROR = 0x421;
    public static final int EMAIL_EXISTS = 0x422;
    public static final int PERMISSION_EMPTY = 0x423;

    public static final int LOCKED_ONESELF = 0x424;
    public static final int DELETE_ONESELF = 0x425;

    public static final int ACTIVITY_NAME_EMPTY = 0x426;
    public static final int ACTIVITY_BEGIN_EMPTY = 0x427;
    public static final int ACTIVITY_END_EMPTY = 0x428;
    public static final int ACTIVITY_END_LESS_BEGIN = 0x429;
    public static final int ACTIVITY_BEGIN_LESS_NOW = 0x430;
    public static final int ACTIVITY_END_LESS_NOW = 0x431;

    public static final int ACTIVITY_SALE_TYPE_EMPTY = 0x432;
    public static final int VIP_YEAR_PRICE_REQUIRED_GREATER_ZERO = 0x433;
    public static final int VIP_MONTH_RPICE_REQUIRED_GREATER_ZERO = 0x434;

    public static final int DURATION_HOUR_EMPTY = 0x435;
    public static final int DURATION_REQUIRED_GREATER_ZERO = 0x436;
    public static final int PREPAY_YEAR_AMOUNT_REQUIRED_GREATER_ZERO = 0x437;
    public static final int PREPAY_MONTH_AMOUNT_REQUIRED_GREATER_ZERO = 0x438;

    public static final int ATTEND_NUMBER_EMPTY = 0x439;
    public static final int ATTEND_NUMBER_REQUIRED_GREATER_ZERO = 0x440;
    public static final int ATTEND_NUMBER_MIN_GREATER_MAX = 0x441;

    public static final int COUPON_NAME_EMPTY = 0x442;
    public static final int COUPON_GET_WAY_EMPTY = 0x443;
    public static final int COUPON_TOTAL_REQUIRED_GREATER_ZERO = 0x444;
    public static final int COUPON_AMOUNT_REQUIRED_GREATER_ZERO = 0x445;
    public static final int COUPON_AMOUNT_LIMIT_LESS_ZERO = 0x446;
    public static final int COUPON_VALID_DATE_WAY_EMPTY = 0x447;

    public static final int COUPON_VALID_BEGIN_DATE_EMPTY = 0x448;
    public static final int COUPON_VALID_END_DATE_EMPTY = 0x449;
    public static final int COUPON_VALID_BEGIN_GREATER = 0x450;
    public static final int COUPON_VALID_DATE_UNIT_EMPTY = 0x451;
    public static final int COUPON_VALID_DATE_VALUE_EMPTY = 0x452;
    public static final int COUPON_VALID_DATE_VALUE_REQUIRED_GREATER_ZERO = 0x453;

    public static final int EVENT_TYPE_INVALID = 0x454;
    public static final int EVENT_VALUE_REQUIRED_GREATER_ZERO = 0x455;
    public static final int EVENT_DAILY_CREDIT_VALUE_REQUIRED_GREATER_ZERO = 0x456;

    public static final int ACTIVITY_NOT_EXISTS = 0x457;
    public static final int USER_NOT_EXISTS = 0x458;

    public static final int ORIGIN_PASSWORD_EMPTY = 0x459;
    public static final int ORIGIN_PASSWORD_ERROR = 0x460;
    public static final int NEW_PASSWORD_EMPTY = 0x461;
    public static final int NEW_PASSWORD_CONFIRM_EMPTY = 0x462;
    public static final int NEW_PASSWORD_CONFIRM_NOT_MATCH = 0x463;

    public static final int BATCH_ADD_CODE_LIMIT = 0x464;

    public static final int DOCUMENT_AREA_EMPTY = 0x465;
    public static final int DOCUMENT_GRADE_EMPTY = 0x466;
    public static final int DOCUMENT_DISCIPLINE_EMPTY = 0x467;
    public static final int DOCUMENT_TYPE_EMPTY = 0x468;
    public static final int DOCUMENT_FILE_EMPTY = 0x469;
    public static final int DOCUMENT_FILE_NAME_EMPTY = 0x470;
    public static final int DOCUMENT_FILE_PATH_EMPTY = 0x471;
    public static final int DOCUMENT_FILE_SUFFIX_ERROR = 0x472;

    public static final int BATCH_ADD_VALID_MONTH_LIMIT = 0x473;

    private static final Map<Integer, String> businessExceptions = new HashMap<Integer, String>() {
        {
            put(GENERAL_CODE, "操作失败，请稍候再试");
            put(PARAMETER_INVALID, "参数无效");
            put(ACCOUNT_IS_LOCKED, "账号已锁定");
            put(ACCOUNT_NOT_EXISTS, "账号不存在");
            put(ACCOUNT_NOT_LOGIN, "账号未登录");
            put(REMOTE_SERVER_ERROR, "远程服务器错误");

            put(ACCOUNT_EMPTY, "账号不能为空");
            put(ACCOUNT_FORMAT_ERROR, "账号格式错误");
            put(ACCOUNT_EXISTS, "账号已存在");
            put(PASSWORD_EMPTY, "密码不能为空");
            put(CONFIRM_PASSWORD_EMPTY, "确认密码不能为空");
            put(PASSWORD_NOT_MATCH, "两次密码不一致");
            put(PASSWORD_LESS_MIN_LENGTH, String.format("密码不能少于%d位", UserLogin.PASSWORD_MIN_LENGTH));
            put(ACCOUNT_OR_PASSWORD_INVALID, "账号或密码无效");
            put(EXCEED_MAX_LOGIN_TIMES, "超出最大登录次数");
            put(EMAIL_EMPTY, "邮箱不能为空");
            put(EMAIL_FORMAT_ERROR, "邮箱格式错误");
            put(EMAIL_EXISTS, "邮箱已存在");
            put(PERMISSION_EMPTY, "权限不能为空");
            put(LOCKED_ONESELF, "不能自己锁定自己");
            put(DELETE_ONESELF, "不能自己删除自己");

            put(ACTIVITY_NAME_EMPTY, "活动名称不能为空");
            put(ACTIVITY_BEGIN_EMPTY, "活动开始日期不能为空");
            put(ACTIVITY_END_EMPTY, "活动截止日期不能为空");
            put(ACTIVITY_END_LESS_BEGIN, "活动截止日期不能小于开始日期");
            put(ACTIVITY_BEGIN_LESS_NOW, "活动开始日期不能小于当前日期");
            put(ACTIVITY_END_LESS_NOW, "活动截止日期不能小于当前日期");
            put(ACTIVITY_SALE_TYPE_EMPTY, "销售方式不能为空");

            put(VIP_YEAR_PRICE_REQUIRED_GREATER_ZERO, "年费VIP价格不能小于0");
            put(VIP_MONTH_RPICE_REQUIRED_GREATER_ZERO, "月费VIP价格不能小于0");

            put(DURATION_HOUR_EMPTY, "持续小时数不能为空");
            put(DURATION_REQUIRED_GREATER_ZERO, "持续小时数需为大于0的整数");
            put(PREPAY_YEAR_AMOUNT_REQUIRED_GREATER_ZERO, "年费VIP预付款不能小于0");
            put(PREPAY_MONTH_AMOUNT_REQUIRED_GREATER_ZERO, "月费VIP预付款不能小于0");

            put(ATTEND_NUMBER_EMPTY, "参团人数不能为空");
            put(ATTEND_NUMBER_REQUIRED_GREATER_ZERO, "参团人数需为大于0的整数");
            put(ATTEND_NUMBER_MIN_GREATER_MAX, "最小参团人数不能大于最大参团人数");
            put(COUPON_NAME_EMPTY, "优惠券名称不能为空");
            put(COUPON_GET_WAY_EMPTY, "优惠券获得方式不能为空");
            put(COUPON_TOTAL_REQUIRED_GREATER_ZERO, "优惠券总量需为大于0的整数");
            put(COUPON_AMOUNT_REQUIRED_GREATER_ZERO, "优惠券面额需为大于0的整数");
            put(COUPON_AMOUNT_LIMIT_LESS_ZERO, "优惠券使用限制金额不能小于0");
            put(COUPON_VALID_DATE_WAY_EMPTY, "优惠券有效期规则不能为空");
            put(COUPON_VALID_BEGIN_DATE_EMPTY, "优惠券有效期开始日期不能为空");
            put(COUPON_VALID_END_DATE_EMPTY, "优惠券有效期结束日期不能为空");
            put(COUPON_VALID_BEGIN_GREATER, "优惠券有效期开始日期不能大于结束日期");
            put(COUPON_VALID_DATE_UNIT_EMPTY, "优惠券有效期持续时间单位不能为空");
            put(COUPON_VALID_DATE_VALUE_EMPTY, "优惠券有效期持续时间值不能为空");
            put(COUPON_VALID_DATE_VALUE_REQUIRED_GREATER_ZERO, "优惠券有效期持续时间值需大于0");

            put(EVENT_TYPE_INVALID, "事件类型无效");
            put(EVENT_VALUE_REQUIRED_GREATER_ZERO, "分值必须大于0");
            put(EVENT_DAILY_CREDIT_VALUE_REQUIRED_GREATER_ZERO, "每日上限分值无效");
            put(ACTIVITY_NOT_EXISTS, "活动不存在");
            put(USER_NOT_EXISTS, "用户不存在");

            put(ORIGIN_PASSWORD_EMPTY, "原始密码不能为空");
            put(ORIGIN_PASSWORD_ERROR, "原始密码错误");
            put(NEW_PASSWORD_EMPTY, "新密码不能为空");
            put(NEW_PASSWORD_CONFIRM_EMPTY, "确认新密码不能为空");
            put(NEW_PASSWORD_CONFIRM_NOT_MATCH, "两次新密码不一致");

            put(BATCH_ADD_CODE_LIMIT, "单次生成兑换码数量不能超来100,000");

            put(DOCUMENT_AREA_EMPTY, "省市不能为空");
            put(DOCUMENT_GRADE_EMPTY, "年级不能为空");
            put(DOCUMENT_DISCIPLINE_EMPTY, "科目不能为空");
            put(DOCUMENT_TYPE_EMPTY, "资料类型不能为空");
            put(DOCUMENT_FILE_EMPTY, "文件不能为空");
            put(DOCUMENT_FILE_NAME_EMPTY, "文件名称不能为空");
            put(DOCUMENT_FILE_PATH_EMPTY, "文件路径不能为空");
            put(DOCUMENT_FILE_SUFFIX_ERROR, "不支持的文件格式");

            put(BATCH_ADD_VALID_MONTH_LIMIT, "会员期限不期超36个月");
        }
    };

    public static String getMessage(int code) {
        if (businessExceptions.containsKey(code)) {
            return businessExceptions.get(code);
        }
        return "未定义错误类型";
    }

    public static void throwCustomException(int code) throws CustomerException {
        throw new CustomerException(code, getMessage(code));
    }

    public static void throwCustomException(int code, String errmsg) throws CustomerException {
        throw new CustomerException(code, errmsg);
    }

    public static void throwIfTrueCondition(boolean condition, int code) throws CustomerException {
        if (condition) {
            throwCustomException(code);
        }
    }

    public static void throwIfTrueCondition(boolean condition, int code, String errmsg) throws CustomerException {
        if (condition) {
            throwCustomException(code, errmsg);
        }
    }
}
