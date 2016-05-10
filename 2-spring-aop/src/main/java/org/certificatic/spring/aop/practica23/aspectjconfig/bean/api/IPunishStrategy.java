package org.certificatic.spring.aop.practica23.aspectjconfig.bean.api;

import org.aspectj.lang.ProceedingJoinPoint;

public interface IPunishStrategy {

	Object punish(ProceedingJoinPoint pjp, boolean hacerTrampa);

}
