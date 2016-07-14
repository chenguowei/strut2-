package com.banche.model;

import java.util.Date;

import com.dijing.server.dao.ANote;
import com.dijing.server.dao.AutoIncrement;
import com.dijing.server.dao.Id;

/**
 * 
 * @author winter
 * @date 2016年4月15日上午11:26:49
 */
@ANote(value="意见反馈")
public class Feedback {

	@Id
	@AutoIncrement
	private long id;
	
	@ANote(value="用户id")
	private long uid;
	
	@ANote(value="邮箱")
	private String email;
	
	@ANote(value="反馈内容")
	private String content;
	
	@ANote(value="创建时间")
	private Date createtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
