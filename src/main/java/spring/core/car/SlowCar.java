package spring.core.car;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@ToString(callSuper = true)
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SlowCar extends AbstractRacingCar {

    public SlowCar(@Value("${car.slow.mark}") String mark,
                   @Value("${car.slow.model}") String model,
                   @Value("${car.slow.hp}") int horsePower) {
        super(mark, model, horsePower);
    }

    @Override
    protected String getRacingName() {
        return "Mad Slow Car";
    }
}
