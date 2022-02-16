package com.text.demo.utils;

/**
 * 
 * @author
 *要求本项目所有返回格式必须为LayuiJsonResult
 *1.普通返回要求返回状态码和提示信息，普通返回请使用常用枚举，成功状态码为0
 *2.带数据返回返回状态码，提示信息，数据
 *3.有分页返回状态吗，提示信息，数据，数据条数
 * @param <T>
 */
 

public class JsonResult<T> {

    private T msg;//成功失败提示信息
    private  Integer code;//状态码 成功0，失败500
    private  T data;//返回的数据
    private  Integer count;//数据条数
    
    public JsonResult() {
		super();
	}

	
	//返回状态码
	public JsonResult(Integer code) {
		super();
		this.code = code;
	}

	//返回状态码和提示信息
	public JsonResult(T msg, Integer count) {
		super();
		this.msg = msg;
		this.count = count;
	}
	
	//返回状态吗，提示信息，数据
	public JsonResult(T msg, T data, Integer code) {
		super();
		this.msg = msg;
		this.data = data;
		this.code = code;
	}

	//有分页要求：返回状态码，提示信息，数据，数据条数
	public JsonResult(T msg, T data, Integer code, Integer count) {
		super();
		this.msg = msg;
		this.data = data;
		this.code = code;
		this.count = count;
	}

	public T getMsg() {
		return msg;
	}

	public void setMsg(T msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "JsonResult [msg=" + msg + ", code=" + code + ", data=" + data + ", count=" + count + "]";
	}


	

    

}
