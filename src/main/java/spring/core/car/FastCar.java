package spring.core.car;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named // JSR-330 annotation
@ToString(callSuper = true)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // default: Singleton
public class FastCar extends AbstractRacingCar {

    public FastCar(@Value("${car.fast.mark}") String mark,
                   @Value("${car.fast.model}") String model,
                   @Value("${car.fast.hp}") int horsePower) {
        super(mark, model, horsePower);
    }

    @Override
    protected String getRacingName() {
        return "The Fast Car";
    }
}
