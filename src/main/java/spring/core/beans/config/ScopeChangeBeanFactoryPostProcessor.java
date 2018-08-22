package spring.core.beans.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("scop1e")
@Component
public class ScopeChangeBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        final BeanDefinition factoryCar = beanFactory.getBeanDefinition("fastCar");
        final BeanDefinition slowCar = beanFactory.getBeanDefinition("slowCar");

        System.out.println(factoryCar);
        System.out.println(slowCar);

        factoryCar.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
        System.out.println(factoryCar);
    }
}
