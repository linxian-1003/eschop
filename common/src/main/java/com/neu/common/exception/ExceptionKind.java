package com.neu.common.exception;
/**
 * 
  * @ClassName: ExceptionKind
  * @Company: 麦子科技
  * @Description: 异常枚举
  * @author 技术部-刘强峰
  * @date 2016年4月2日 下午5:19:13
  *
 */
public enum ExceptionKind {
    SYS_E(ExceptionKind.SYS_EXCEPTION),
    PARAM_E(ExceptionKind.PARAM_EXCEPTION),
    NODATA_E(ExceptionKind.NODATA_EXCEPTION),
    PARSE_E(ExceptionKind.PARSE_EXCEPTION),
    NO_HEAEER_E(ExceptionKind.NO_HEADER_EXCEPTION),
    AUTHO_FALSE_E(ExceptionKind.AUTHO_FALSE_EXCEPTION),
    FORMAT_E(ExceptionKind.FORMAT_EXCEPTION),
    ACCESS_E(ExceptionKind.ACCESS_EXCEPTION),
    INVOCATION_E(ExceptionKind.INVOCATION_EXCEPTION),
    ADD_E(ExceptionKind.ADD_EXCEPTION),
    DELETE_E(ExceptionKind.DELETE_EXCEPTION),
    UPDATE_E(ExceptionKind.UPDATE_EXCEPTION),
    IO_E(ExceptionKind.IO_EXCEPTION),
    UPLOAD_E(ExceptionKind.UPLOAD_EXCEPTION),
    BUSINESS_E(ExceptionKind.BUSINESS_EXCEPTION),
	
	NOT_FOUND_E(ExceptionKind.NOT_FOUND_EXCEPTION);
    
    private String message;
    
	private ExceptionKind(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	private static final String SYS_EXCEPTION="系统异常请联系管理员";
	private static final String PARAM_EXCEPTION="参数错误";
	
    private static final String NODATA_EXCEPTION="数据已被删除或修改";
	private static final String PARSE_EXCEPTION="数据解析异常";
	private static final String NO_HEADER_EXCEPTION="没有请求头";
	private static final String AUTHO_FALSE_EXCEPTION="授权失败";
	private static final String FORMAT_EXCEPTION="非法参数,请使用正确参数";
	
	private static final String ACCESS_EXCEPTION="访问异常";
	private static final String INVOCATION_EXCEPTION="调用异常";
	
	private static final String ADD_EXCEPTION="添加失败";
	private static final String DELETE_EXCEPTION="删除失败";
	private static final String UPDATE_EXCEPTION="更新失败";
	
	private static final String IO_EXCEPTION="IO异常";
	private static final String BUSINESS_EXCEPTION="业务异常";
	
	private static final String UPLOAD_EXCEPTION="文件上传异常";
	
	private static final String NOT_FOUND_EXCEPTION="页面不存在";
}
