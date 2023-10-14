package concert;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConcertMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConcertConfig.class);
        Performance performance = context.getBean(Performance.class);
        performance.perform();
        Encoreable encoreable = (Encoreable) performance;
        encoreable.performanceEncore();
    }
}
