package com.dijing.server.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据表字段关系信息
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月14日下午1:56:51
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ARelated {

	public String value() default "";
}
