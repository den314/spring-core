package spring.core.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import spring.core.util.BannerUtil;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class Track {

    private FastCar fastCar;
    private SlowCar slowCar;

    private BannerUtil bannerUtil;

    /* Implicit @Autowired - inject the fastCar with default constructor arguments */
    /* Can make use of JSR-330 annotations instead Spring related (@Autowired) */
    @Inject
    public Track(FastCar fastCar, SlowCar slowCar) {
        this.fastCar = fastCar;
        this.slowCar = slowCar;
    }

    @PostConstruct // JSR-250 annotation
    public void init() {
        System.out.println("Two cars are waiting on the track:");
        System.out.println("first: " + fastCar);
        System.out.println("second: " + slowCar);
        race();

        // if bean wiring is optional, developer must take care about NPE
        if (bannerUtil != null) {
            bannerUtil.printFinish();
        }
    }

    private void race() {

        final Callable<String> slowCarTask = () -> slowCar.race();
        final Callable<String> fastCarTask = () -> fastCar.race();
        final ExecutorService ex = Executors.newFixedThreadPool(2);

        try {
            final String firstCarOnFinish = ex.invokeAny(Arrays.asList(fastCarTask, slowCarTask));
            System.out.println("### Winner is: " + firstCarOnFinish);
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Could not finish the race.", e);
        }

        waitOnOtherCars(ex);
    }

    private void waitOnOtherCars(ExecutorService ex) {
        ex.shutdown();
        try {
            ex.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // optional dependency, if proper profile is active, will be wired
    // see BannerUtil @Profile annotation
    @Autowired(required = false)
    public void setBannerUtil(BannerUtil bannerUtil) {
        this.bannerUtil = bannerUtil;
    }
}
