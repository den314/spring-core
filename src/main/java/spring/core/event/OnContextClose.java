package spring.core.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;

@Component
public class OnContextClose implements ApplicationListener<ContextClosedEvent>, ResourceLoaderAware {

    private Resource props;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("#### Closing APPLICATION...");
        try {
            printProps();
        } catch (IOException e) {
            // at that point the properties will exist
            // otherwise SlowCar & FastCar won't be created
        }
    }

    private void printProps() throws IOException {
        System.out.println("You've used following property files:");
        Files.lines(props.getFile().toPath())
                .filter((line) -> line.length() > 0)
                .forEach(System.out::println);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.props = resourceLoader.getResource("classpath:application.properties");
    }
}
