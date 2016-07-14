package com.dijing.server.web.utils.email;

/**
 * 
 * @author winter
 * @date   2015-5-4下午5:33:58
 */
public class DJEmail {

	/**
	 * 
	 * @param toAddress 目标email地址
	 * @param subject email标题
	 * @param content email内容
	 */
	public static void sendEmail(String toAddress, String subject, String content){
		MailSenderInfo mailInfo = new MailSenderInfo();    
		mailInfo.setMailServerHost(EmailConstants.MailServerHost);    
		mailInfo.setMailServerPort(String.valueOf(EmailConstants.MailServerPort));    
		mailInfo.setValidate(true);    
		mailInfo.setUserName(EmailConstants.UserName);    
		mailInfo.setPassword(EmailConstants.Password);//您的邮箱密码  
		mailInfo.setFromAddress(EmailConstants.UserName);
		mailInfo.setToAddress(toAddress);  
		mailInfo.setSubject(subject);
		mailInfo.setContent(content);    
		//这个类主要来发送邮件   
		MailSender sms = new MailSender();
//		sms.sendTextMail(mailInfo);//发送文体格式    
		sms.sendHtmlMail(mailInfo);//发送html格式
	}
}
