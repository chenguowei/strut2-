package com.dijing.server.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该字段不作为数据库字段进行存储
 * @author winter , 361482732@qq.com
 * @Date: 2015年12月8日下午4:25:53
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Transparent {

}
