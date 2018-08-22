package spring.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import spring.core.car.AbstractRacingCar;
import spring.core.car.FastCar;
import spring.core.car.SlowCar;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy
public class SpringCoreDemo {

    public static void main(String[] args) {

        final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringCoreDemo.class);

        System.out.println("\nIs fastCar singleton? " + ctx.isSingleton("fastCar"));
        System.out.println("Is slowCar singleton? " + ctx.isSingleton("slowCar") + "\n");

        tryGetScopedBeansDemo(ctx);

        ctx.close(); // so ApplicationClosedEvent will be processed
    }

    private static void tryGetScopedBeansDemo(AnnotationConfigApplicationContext ctx) {
        final String mark = "Ferrari";
        final String model = "458";
        final int hp = 320;
        // IMPORTANT: even if arguments are supplied,
        // they WILL NOT be processed in Singleton scope
        System.out.printf("Getting SlowCar bean from the context with args: model (%s), mark (%s), hp(%d)%n", mark, model, hp);
        final AbstractRacingCar slowCarBean = ctx.getBean(SlowCar.class, mark, model, hp);
        System.out.println("Bean returned: " + slowCarBean);

        // would be fine for Prototype scoped bean
        System.out.println("Trying same with Prototype scoped bean.");
        final AbstractRacingCar fastCarBean = ctx.getBean(FastCar.class, mark, model, hp);
        System.out.println("Bean returned: " + fastCarBean);
    }
}
