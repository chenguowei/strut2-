package com.dijing.server.form;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该字段不能为空
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月20日下午1:08:40
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FRequired {

	/**
	 * 字段名称
	 * @return
	 */
	public String name() default "";
}
