package spring.core.beans.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import spring.core.car.AbstractRacingCar;
import spring.core.car.FastCar;

@Component
public class IncreaseCarHpBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AbstractRacingCar && !(bean instanceof FastCar)) {
            AbstractRacingCar fastCar = (AbstractRacingCar) bean;
            fastCar.setHorsePower(fastCar.getHorsePower() + 10);
        }
        return bean;
    }
}
