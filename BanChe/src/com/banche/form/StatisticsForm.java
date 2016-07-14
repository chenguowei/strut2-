package com.banche.form;

import com.dijing.server.form.FRequired;

public class StatisticsForm {
	@FRequired(name="统计时间类型")
	private long timeType;

	public long getTimeType() {
		return timeType;
	}

	public void setTimeType(long timeType) {
		this.timeType = timeType;
	}
}
