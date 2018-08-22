package spring.core.car;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;

import java.util.stream.LongStream;

@Data
public abstract class AbstractRacingCar implements InitializingBean {

    String mark;
    String model;
    int horsePower;

    AbstractRacingCar(String mark, String model, int horsePower) {
        this.mark = mark;
        this.model = model;
        this.horsePower = horsePower;
    }

    public String race() {
        System.out.println(getRacingName() + " started!");
        simulateDrive();
        System.out.println(getRacingName() + " finished!");
        return getRacingName();
    }

    private void simulateDrive() {
        try {
            Thread.sleep(getDriveTime());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private Long getDriveTime() {
        return LongStream.rangeClosed(500, 3000).boxed().findFirst().get();
    }

    protected abstract String getRacingName();

    @Override
    public void afterPropertiesSet() throws Exception {
        this.horsePower = 0; // to show IncreaseCarHpBeanPostProcessor in action
    }
}
