package com.dijing.server.checkcoder;

import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * 提供图片验证码
 * @version 1.0 2012/08/22
 * @author dongliyang
 */
@SuppressWarnings("serial")
public class SecurityCodeImageAction extends ActionSupport implements SessionAware{
  
	public static final String SECURITY_CODE = "SECURITY_CODE";
	
    //Struts2中Map类型的session
    private Map<String, Object> session;
    
    //图片流
    private ByteArrayInputStream imageStream;
    
    private long timestamp;

    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }

    
    public String execute() throws Exception {
        //如果开启Hard模式，可以不区分大小写
//        String securityCode = SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard, false).toLowerCase();
        
        //获取默认难度和长度的验证码
		String securityCode = SecurityCode.getSecurityCode();
//    	String securityCode = "123456";
		imageStream = SecurityImage.getImageAsInputStream(securityCode);
		//放入session中
		session.put(SECURITY_CODE, securityCode);
		return SUCCESS;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}