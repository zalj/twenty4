package concert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
@ImportResource("Concert.xml")
public class ConcertConfig {
    @Bean
    public Audience audience() {
        return new Audience();
    }

    @Bean
    public Concert concert() {
        return new Concert();
    }
}
