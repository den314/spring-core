package spring.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RaceAspect {

    @Pointcut("execution(* spring.core.car.*.race(..))")
    private void onEachCarRace(){}

    @Around("onEachCarRace()")
    public String measureRaceTime(ProceedingJoinPoint pjp) throws Throwable {

        final long start = System.currentTimeMillis(); // @Before
        final String carName = (String) pjp.proceed();
        final long stop = System.currentTimeMillis();  // @After

        System.out.printf("--- %s finished race with time: %s%n", carName, (stop - start));
        return carName; // must be returned, otherwise value will be lost
    }
}
