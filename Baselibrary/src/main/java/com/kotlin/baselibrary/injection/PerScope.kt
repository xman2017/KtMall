package com.kotlin.baselibrary.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME


/**
 *
 * author：xman
 * created on: 2019/6/19 4:12 PM
 * description:
 *
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class PerScope