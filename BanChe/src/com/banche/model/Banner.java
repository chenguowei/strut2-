package com.banche.model;

import com.banche.form.BannerForm;
import com.dijing.server.dao.ANote;
import com.dijing.server.dao.AutoIncrement;
import com.dijing.server.dao.DJModel;
import com.dijing.server.dao.Id;

/**
 * 首页banner图
 * @author winter
 * @date 2016年3月30日上午10:56:52
 */
@ANote(value="banner轮播图")
public class Banner extends DJModel {

	@Id
	@AutoIncrement
	private long id;
	
	@ANote(value="图片")
	private String image;
	
	@ANote(value="图片标题")
	private String title;
	
	@ANote(value="链接")
	private String url;
	
	@ANote(value="排序")
	private int showOrder;
	
	@ANote(value="添加时间")
	private long time;
	
	@ANote(value="发布状态, 0为发布；1为不发布")
	private int status;
	
	@ANote(value="轮播图说明")
	private String intro;
	
	@ANote(value="备注")
	private String note;
	
	@ANote(value="类型，1为经销商；2为司机")
	private int bannerType = 1;
	
	public Banner(){}
	
	public Banner(BannerForm form){
		setFormData(form);
		this.time = System.currentTimeMillis();
	}
	
	public void setFormData(BannerForm form){
		if(form.getId() != 0){
			this.id = form.getId();
		}
		this.image = form.getImage();
		this.intro = form.getIntro();
		this.note = form.getNote();
		this.showOrder = form.getShowOrder();
		this.status = form.getStatus();
		this.title = form.getTitle();
		this.url = form.getUrl();
		this.bannerType = form.getBannerType();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getBannerType() {
		return bannerType;
	}

	public void setBannerType(int bannerType) {
		this.bannerType = bannerType;
	}
	
}
