package spring.core.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
@Profile("banner")
public class BannerUtil {

    /* Handling the ClassPathResource */
    @Value("${classpath:finish-banner.txt}")
    private File banner;

    public void printFinish() {
        try {
            Files.lines(banner.toPath())
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("RACE FINISHED");
        }
    }

}
