package net.zsygfddsd.qujing.common.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by mac on 2016/10/25.
 */

@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScoped {}
