package site.acacia.flea.search.conf;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 张胤
 *
 *         2019年1月2日-下午4:53:38
 */
@Aspect
@Component
public class LogAspect {
	@Pointcut("execution(public * site.acacia.flea.search.service.impl.*.*(..))")
	public void webLog() {
	}

	@Pointcut("execution(public * site.acacia.flea.search.listening.*.*(..))")
	public void webLog2() {
	}

	@Before("webLog() || webLog2()")
	public void deBefore(JoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		if (args == null || args.length == 0) {
			LoggerFactory
					.getLogger(
							joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName())
					.info("==> Request parameter Empty");
		} else {
			LoggerFactory
					.getLogger(
							joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName())
					.info("==> Request parameter: " + Arrays.toString(args));
		}

	}
}
